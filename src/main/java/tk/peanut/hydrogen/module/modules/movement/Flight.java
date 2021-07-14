package tk.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.awt.*;

/**
 * Created by peanut on 21/02/2021
 */
@Info(name = "Flight", category = Category.Movement, description = "Lets you fly")
public class Flight extends Module {
    public Flight() {
        super(0x00);

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
        mc.thePlayer.setAIMoveSpeed(this.modifier * (float) Hydrogen.getClient().settingsManager.getSettingByName(this, "Speed").getValDouble());
        mc.thePlayer.jumpMovementFactor = (this.modifier * (float) Hydrogen.getClient().settingsManager.getSettingByName(this, "Speed").getValDouble());
        if (mc.inGameHasFocus)
        {
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
                mc.thePlayer.motionY += this.modifier * Hydrogen.getClient().settingsManager.getSettingByName(this, "Speed").getValDouble() / 2.0F + 0.2F;
            }
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode())) {
                mc.thePlayer.motionY -= this.modifier * (float) Hydrogen.getClient().settingsManager.getSettingByName(this, "Speed").getValDouble() / 2.0F + 0.2F;
            }
        }
    }

}
