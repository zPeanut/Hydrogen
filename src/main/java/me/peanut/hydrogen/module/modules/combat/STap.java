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
 * Created by peanut on 29/07/2021
 */
@Info(name = "STap", category = Category.Combat,  description = "Stops holding S when sprinting and hitting an enemy.")
public class STap extends Module {

    private long lastAttack = 0L;
    private long lastHold = 2000000000L;

    public STap() {
        super(0x00);

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
        if (!this.mc.thePlayer.canEntityBeSeen(entity)) {
            return false;
        }
        float range = 4f;
        if (this.mc.thePlayer.getDistanceToEntity(entity) > range) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
        }
        return true;
    }

    @EventTarget
    public void onUpdate(EventTick event) {
        if (!isEnabled())
            return;
        if (this.mc.theWorld == null)
            return;
        if (this.mc.thePlayer == null)
            return;
        if (!this.mc.thePlayer.isEntityAlive())
            return;
        if (this.mc.currentScreen != null)
            return;

        Entity ens = null;
        for (Entity en : this.mc.theWorld.getLoadedEntityList()) {
            if (en != this.mc.thePlayer && isTargetValid(en)) {
                if (mc.thePlayer.isSwingInProgress) {
                    ens = en;
                }
            }
        }
        if (ens != null && this.mc.thePlayer.isSprinting() && TimeUtils.hasTimePassedMS((long) Hydrogen.getClient().settingsManager.getSettingByName(this, "Delay").getValDouble())) {
            this.mc.gameSettings.keyBindBack.pressed = true;
            this.lastHold = getCurrentMS();
            TimeUtils.reset();
        }
        if (this.lastHold != -1L && TimeUtils.hasTimePassedMS(this.lastHold, (long) Hydrogen.getClient().settingsManager.getSettingByName(this, "Held").getValDouble())) {
            this.mc.gameSettings.keyBindBack.pressed = false;
            this.lastHold = -1L;
        }
    }
}
