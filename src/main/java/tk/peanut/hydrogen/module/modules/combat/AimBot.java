package tk.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender3D;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Info(name = "AimAssist", description = "Automatically aims at enemies", category = Category.Combat)
public class AimBot extends Module {

    HashMap<String, GetCriteriaValue> selectionCriterias = new HashMap();
    EntityLivingBase target = null;

    boolean offset = false;
    boolean onlyPrimaryTarget = false;

    public AimBot() {
        super(Keyboard.KEY_R);

        selectionCriterias.put("Distance", (thePlayer, target) -> (thePlayer.getPositionEyes(0).distanceTo(target.getPositionEyes(0))));
        selectionCriterias.put("Health", ((thePlayer, target) -> target.getHealth()));

        ArrayList<String> selectionOrders = new ArrayList<String>(selectionCriterias.keySet());

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Select", this, "Health", selectionOrders));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Select smaller", this, true));

        Hydrogen.getClient().settingsManager.rSetting(new Setting("on Click Only", this, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Max Distance", this, 4.2, 1, 6, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Visible Only", this, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Alive Only", this, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Offset", this, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Target Only", this, false));

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Target Player", this, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Target Mobs", this, false));
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        boolean targetPlayer = Hydrogen.getClient().settingsManager.getSettingByName(this, "Target Player").isEnabled();
        boolean targetMobs = Hydrogen.getClient().settingsManager.getSettingByName(this, "Target Mobs").isEnabled();
        boolean selectSmaller = Hydrogen.getClient().settingsManager.getSettingByName(this, "Select smaller").isEnabled();
        boolean visibleOnly = Hydrogen.getClient().settingsManager.getSettingByName(this, "Visible Only").isEnabled();
        boolean aliveOnly = Hydrogen.getClient().settingsManager.getSettingByName(this, "Alive Only").isEnabled();
        double maxDistance = Hydrogen.getClient().settingsManager.getSettingByName("Max Distance").getValDouble();
        offset = Hydrogen.getClient().settingsManager.getSettingByName(this, "Offset").isEnabled();
        onlyPrimaryTarget = Hydrogen.getClient().settingsManager.getSettingByName(this, "Target Only").isEnabled();
        GetCriteriaValue getCriteriaValue = selectionCriterias.get(Hydrogen.getClient().settingsManager.getSettingByName("Select").getValString());

        if (getCriteriaValue == null) {
            System.err.println("getCriteriaValue is null for: " + Hydrogen.getClient().settingsManager.getSettingByName("Select").getValString());
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
        boolean onClick = Hydrogen.getClient().settingsManager.getSettingByName(this, "on Click Only").isEnabled();
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
