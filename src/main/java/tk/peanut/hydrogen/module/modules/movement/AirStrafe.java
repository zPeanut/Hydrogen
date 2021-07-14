package tk.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.util.MathHelper;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;

/**
 * Created by peanut on 16/02/2021
 */
@Info(name = "AirStrafe", description = "Lets you move in directions while in air", category = Category.Movement)
public class AirStrafe extends Module {
    public AirStrafe() {
        super(0x00);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if ((!isEnabled()) || (!mc.thePlayer.onGround)) {
            float forward = 0.1F;
            float strafe = 0.1F;
            double speed = 0.2D;
            float var5 = MathHelper.sin(mc.thePlayer.rotationYaw * 3.1415927F / 180.0F);
            float var6 = MathHelper.cos(mc.thePlayer.rotationYaw * 3.1415927F / 180.0F);
            if (mc.gameSettings.keyBindForward.pressed) {
                forward += 1.0F;
            }
            if (mc.gameSettings.keyBindBack.pressed) {
                forward -= 1.0F;
            }
            if (mc.gameSettings.keyBindLeft.pressed) {
                strafe += 1.0F;
            }
            if (mc.gameSettings.keyBindRight.pressed) {
                strafe -= 1.0F;
            }
            double motionX = (strafe * var6 - forward * var5) * speed;
            double motionZ = (forward * var6 + strafe * var5) * speed;
            mc.thePlayer.motionX = motionX;
            mc.thePlayer.motionZ = motionZ;
        }
    }
}
