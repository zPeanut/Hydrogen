package me.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 21/02/2021
 */
@Info(name = "Flight", category = Category.Movement, description = "Lets you fly")
public class Flight extends Module {
    public Flight() {
        addSetting(new Setting("Speed", this, 1, 0, 5, false));
    }

    public float modifier = 1.0F;

    @EventTarget
    public void onUpdate(EventUpdate event) {

        if (mc.thePlayer.isSprinting()) {
            this.modifier = 1.5F;
        } else {
            this.modifier = 1.0F;
        }
        mc.thePlayer.capabilities.isFlying = false;
        mc.thePlayer.motionX = 0.0D;
        mc.thePlayer.motionY = 0.0D;
        mc.thePlayer.motionZ = 0.0D;
        mc.thePlayer.setAIMoveSpeed(this.modifier * (float) Hydrogen.getClient().settingsManager.getSettingByName(this, "Speed").getValue());
        mc.thePlayer.jumpMovementFactor = (this.modifier * (float) Hydrogen.getClient().settingsManager.getSettingByName(this, "Speed").getValue());
        if (mc.inGameHasFocus)
        {
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
                mc.thePlayer.motionY += this.modifier * Hydrogen.getClient().settingsManager.getSettingByName(this, "Speed").getValue() / 2.0F + 0.2F;
            }
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode())) {
                mc.thePlayer.motionY -= this.modifier * (float) Hydrogen.getClient().settingsManager.getSettingByName(this, "Speed").getValue() / 2.0F + 0.2F;
            }
        }
    }

}
