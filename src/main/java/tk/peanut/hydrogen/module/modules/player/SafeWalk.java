package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventSafeWalk;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.ui.clickgui.component.components.sub.Keybind;
import tk.peanut.hydrogen.ui.clickgui.component.components.sub.ModeButton;
import tk.peanut.hydrogen.utils.ReflectionUtil;

import java.awt.*;

/**
 * Created by peanut on 07/02/2021
 */

@Info(name = "SafeWalk", description = "Doesn't let you fall off of blocks", category = Category.Player)
public class SafeWalk extends Module {

    public boolean safewalk;

    public SafeWalk() {
        super(Keyboard.KEY_NONE);
    }

    @Override
    public void onDisable() {
        safewalk = false;
    }

    @EventTarget
    public void onUpdate(EventSafeWalk e) {
        if(this.isEnabled()) {
            try {
                if(ReflectionUtil.pressed.getBoolean(Minecraft.getMinecraft().gameSettings.keyBindJump) || !mc.thePlayer.onGround) {
                    e.setCancelled(false);
                } else {
                    e.setCancelled(true);
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
