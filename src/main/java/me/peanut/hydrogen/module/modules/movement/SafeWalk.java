package me.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.events.EventSafeWalk;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.ReflectionUtil;

/**
 * Created by peanut on 07/02/2021
 */

@Info(name = "SafeWalk", description = "Doesn't let you fall off of blocks", category = Category.Movement)
public class SafeWalk extends Module {

    public boolean safewalk;

    public SafeWalk() {}

    @Override
    public void onDisable() {
        safewalk = false;
    }

    @EventTarget
    public void onUpdate(EventSafeWalk e) {
        if(this.isEnabled()) {
            try {
                e.setCancelled(!ReflectionUtil.pressed.getBoolean(Minecraft.getMinecraft().gameSettings.keyBindJump) && mc.thePlayer.onGround);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
