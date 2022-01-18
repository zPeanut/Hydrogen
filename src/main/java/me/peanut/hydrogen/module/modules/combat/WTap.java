package me.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventTick;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import me.peanut.hydrogen.utils.TimeUtils;

import static me.peanut.hydrogen.utils.TimeUtils.getCurrentMS;

/*
 * Created by peanut on 20/02/2021
 */
@Info(name = "WTap", category = Category.Combat,  description = "Stops holding W when sprinting and hitting an enemy.")
public class WTap extends Module {

    private long lastHold = 2000000000L;

    public WTap() {
        addSetting(new Setting("Delay", this, 500, 100, 2000, false));
        addSetting(new Setting("Held", this, 100, 50, 250, false));
    }

    protected boolean isTargetValid(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (!entity.isEntityAlive()) {
            return false;
        }
        if (!mc.thePlayer.canEntityBeSeen(entity)) {
            return false;
        }
        float range = 4f;
        if (mc.thePlayer.getDistanceToEntity(entity) > range) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
        }
        return true;
    }

    @EventTarget
    public void onUpdate(EventTick e) {
        if (!isEnabled())
            return;
        if (mc.theWorld == null)
            return;
        if (mc.thePlayer == null)
            return;
        if (!mc.thePlayer.isEntityAlive())
            return;
        if (mc.currentScreen != null)
            return;
        Entity ens = null;

        for (Entity e1 : mc.theWorld.getLoadedEntityList()) {
            if (e1 != mc.thePlayer && this.isTargetValid(e1)) {
                if (mc.thePlayer.isSwingInProgress) {
                    ens = e1;
                }
            }
        }

        if (ens != null && mc.thePlayer.isSprinting() && TimeUtils.hasTimePassedMS((long) h2.settingsManager.getSettingByName("Delay").getValue())) {
            mc.gameSettings.keyBindForward.pressed = false;
            this.lastHold = getCurrentMS();
            TimeUtils.reset();
        }


        if (this.lastHold != -1L && TimeUtils.hasTimePassedMS(this.lastHold, (long) h2.settingsManager.getSettingByName("Held").getValue())) {
            mc.gameSettings.keyBindForward.pressed = true;
            this.lastHold = -1L;
        }
    }
}
