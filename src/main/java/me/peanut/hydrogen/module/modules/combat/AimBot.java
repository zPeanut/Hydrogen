package me.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.events.EventRender3D;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Info(name = "AimAssist", description = "Automatically aims at enemies", category = Category.Combat, keybind = Keyboard.KEY_R)
public class AimBot extends Module {

    @SuppressWarnings("unchecked")
    final HashMap<String, GetCriteriaValue> selectionCriterias = new HashMap();
    EntityLivingBase target = null;

    boolean offset = false;
    boolean onlyPrimaryTarget = false;

    public AimBot() {

        selectionCriterias.put("Distance", (thePlayer, target) -> (thePlayer.getPositionEyes(0).distanceTo(target.getPositionEyes(0))));
        selectionCriterias.put("Health", ((thePlayer, target) -> target.getHealth()));

        ArrayList<String> selectionOrders = new ArrayList<String>(selectionCriterias.keySet());

        addSetting(new Setting("Select", this, "Health", selectionOrders));
        addSetting(new Setting("Select smaller", this, true));

        addSetting(new Setting("on Click Only", this, false));
        addSetting(new Setting("Max Distance", this, 4.2, 1, 6, false));
        addSetting(new Setting("Visible Only", this, true));
        addSetting(new Setting("Alive Only", this, true));
        addSetting(new Setting("Offset", this, true));
        addSetting(new Setting("Target Only", this, false));

        addSetting(new Setting("Target Player", this, false));
        addSetting(new Setting("Target Mobs", this, false));
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        boolean targetPlayer = h2.settingsManager.getSettingByName(this, "Target Player").isEnabled();
        boolean targetMobs = h2.settingsManager.getSettingByName(this, "Target Mobs").isEnabled();
        boolean selectSmaller = h2.settingsManager.getSettingByName(this, "Select smaller").isEnabled();
        boolean visibleOnly = h2.settingsManager.getSettingByName(this, "Visible Only").isEnabled();
        boolean aliveOnly = h2.settingsManager.getSettingByName(this, "Alive Only").isEnabled();
        double maxDistance = h2.settingsManager.getSettingByName("Max Distance").getValue();
        offset = h2.settingsManager.getSettingByName(this, "Offset").isEnabled();
        onlyPrimaryTarget = h2.settingsManager.getSettingByName(this, "Target Only").isEnabled();
        GetCriteriaValue getCriteriaValue = selectionCriterias.get(h2.settingsManager.getSettingByName("Select").getMode());

        if (getCriteriaValue == null) {
            System.err.println("getCriteriaValue is null for: " + h2.settingsManager.getSettingByName("Select").getMode());
            return;
        }

        target = null;
        double value = Double.MAX_VALUE;

        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityLivingBase || entity instanceof EntityOtherPlayerMP)) continue;

            if (mc.thePlayer.getPositionEyes(0).distanceTo(entity.getPositionEyes(0)) > maxDistance)
                continue;

            if ((targetPlayer && entity instanceof EntityOtherPlayerMP || targetMobs && entity instanceof EntityMob) && (!visibleOnly || !entity.isInvisible()) && (!aliveOnly || !entity.isDead && entity.isEntityAlive())) {
                double candidateValue = getCriteriaValue.op(mc.thePlayer, (EntityLivingBase) entity);
                if (selectSmaller ? candidateValue < value : candidateValue > value) {
                    value = candidateValue;
                    target = (EntityLivingBase) entity;
                    if(target.equals(TargetSelect.primaryTarget)) {
                        return;
                    }
                }
            }
        }
    }

    @EventTarget
    public void onRender(EventRender3D e) {
        if(!target.equals(TargetSelect.primaryTarget) && onlyPrimaryTarget)
            return;
        boolean onClick = h2.settingsManager.getSettingByName(this, "on Click Only").isEnabled();
        final Vec3 positionEyes = target.getPositionEyes(e.getPartialTicks());
        Vec3 thePlayerPositionEyes = mc.thePlayer.getPositionEyes(e.getPartialTicks());
        double diffX = positionEyes.xCoord - thePlayerPositionEyes.xCoord;
        double diffY = positionEyes.yCoord - thePlayerPositionEyes.yCoord;
        double diffZ = positionEyes.zCoord - thePlayerPositionEyes.zCoord;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);

        if(!onClick || mc.gameSettings.keyBindAttack.pressed) {
            mc.thePlayer.rotationYaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F;
            mc.thePlayer.rotationPitch = (float) -Math.toDegrees(Math.atan2(diffY, diffXZ));

            if (offset) {
                double f = ((double) new Date().getTime()) / 200f;
                mc.thePlayer.rotationYaw += (float) Math.sin(f) * 2f;
                mc.thePlayer.rotationPitch += (float) Math.cos(f) * 2f;
            }
        }
    }

    public interface GetCriteriaValue {
        double op(EntityPlayerSP thePlayer, EntityLivingBase target);
    }
}
