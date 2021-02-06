package tk.peanut.phosphor.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.events.EventSafeWalk;
import tk.peanut.phosphor.events.EventUpdate;
import tk.peanut.phosphor.module.Category;
import tk.peanut.phosphor.module.Module;
import tk.peanut.phosphor.utils.ReflectionUtil;

public class SafeWalk extends Module {

    public boolean safewalk;

    public SafeWalk() {
        super("SafeWalk", "avoids falling off blocks", Keyboard.KEY_NONE, Category.Render, -1);
    }

    @Override
    public void onDisable() {
        safewalk = false;
    }

    @EventTarget
    public void onUpdate(EventSafeWalk e) {
        if(this.isToggled()) {
            try {
                if(ReflectionUtil.pressed.getBoolean(Minecraft.getMinecraft().gameSettings.keyBindJump) || Minecraft.getMinecraft().thePlayer.onGround) {
                    e.setCancelled(true);
                } else {
                    e.setCancelled(false);
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
