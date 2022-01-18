package me.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.utils.PlayerUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peanut on 27/07/2021
 */
@Info(name = "BowAimbot", category = Category.Combat, description = "Automatically aims a bow for you")
public class BowAimbot extends Module {

    boolean send;
    boolean isFiring;
    public static EntityLivingBase target;

    public BowAimbot() {}

    // TODO: redo this

    @EventTarget
    public void onUpdate(EventUpdate event) {
        target = getTarget();
        if(shouldAim()) {
            if (target != null) {

                float[] rotations = PlayerUtil.getBowAngles(target);
                boolean silent = h2.settingsManager.getSettingByName(this, "Silent").isEnabled();

                mc.thePlayer.rotationYaw = rotations[0];
                mc.thePlayer.rotationPitch = rotations[1];
            }
        }
    }


    public static boolean shouldAim() {
        if(mc.thePlayer.inventory.getCurrentItem() == null || !(mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemBow)) {
            return false;
        }
        if(h2.moduleManager.getModule(FastBow.class).isEnabled() && mc.gameSettings.keyBindUseItem.pressed) {
            return true;
        }
        return mc.thePlayer.isUsingItem();
    }

    private EntityLivingBase getTarget() {
        List<EntityLivingBase> loaded = new ArrayList();
        for (Object o : mc.theWorld.getLoadedEntityList()) {
            if (o instanceof EntityLivingBase) {
                EntityLivingBase ent = (EntityLivingBase) o;
                if (ent instanceof EntityPlayer && ent != mc.thePlayer && mc.thePlayer.canEntityBeSeen(ent) && mc.thePlayer.getDistanceToEntity(ent) < 65) {
                    loaded.add(ent);
                }
            }
        }
        if (loaded.isEmpty()) {
            return null;
        }
        loaded.sort((o1, o2) -> {
            float[] rot1 = PlayerUtil.getRotations(o1);
            float[] rot2 = PlayerUtil.getRotations(o2);
            return (int) ((PlayerUtil.getDistanceBetweenAngles(mc.thePlayer.rotationYaw, rot1[0])
                    + PlayerUtil.getDistanceBetweenAngles(mc.thePlayer.rotationPitch, rot1[1]))
                    - (PlayerUtil.getDistanceBetweenAngles(mc.thePlayer.rotationYaw, rot2[0])
                    + PlayerUtil.getDistanceBetweenAngles(mc.thePlayer.rotationPitch, rot2[1])));
        });
        EntityLivingBase target = loaded.get(0);
        return target;
    }

    @Override
    public void onDisable(){
        target = null;
    }

}
