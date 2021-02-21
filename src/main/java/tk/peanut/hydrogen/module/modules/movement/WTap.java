package tk.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventTick;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.TimeHelper;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;

import static tk.peanut.hydrogen.utils.TimeHelper.getCurrentMS;

/*
 * Created by peanut on 20/02/2021
 */
@Info(name = "W-Tap", category = Category.Movement,  description = "Stops holding W when near an enemy for more knockback")
public class WTap extends Module {

    private long lastAttack = 0L;
    private long lastHold = 2000000000L;

    public WTap() {
        super(0x00, new Color(173, 234, 255));

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
    public void onUpdate(EventTick e) {
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

        for (Entity e1 : this.mc.theWorld.getLoadedEntityList()) {
            if (e1 != this.mc.thePlayer && this.isTargetValid(e1)) {

                float yaw = Utils.getAngles(e1)[1];

                double yawdistance = Utils.getDistanceBetweenAngles(yaw, this.mc.thePlayer.rotationYaw);

                if (yawdistance < 50.0D) {
                    ens = e1;
                }
            }
        }

        if (ens != null && this.mc.thePlayer.isSprinting() && TimeHelper.hasTimePassedMS((long) Hydrogen.getClient().settingsManager.getSettingByName("Delay").getValDouble())) {
            this.mc.gameSettings.keyBindForward.pressed = false;
            this.lastHold = getCurrentMS();
            TimeHelper.reset();
        }


        if (this.lastHold != -1L && TimeHelper.hasTimePassedMS(this.lastHold, (long) Hydrogen.getClient().settingsManager.getSettingByName("Held").getValDouble())) {
            this.mc.gameSettings.keyBindForward.pressed = true;
            this.lastHold = -1L;
        }
    }
}
