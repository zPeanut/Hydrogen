package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventSafeWalk;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.ui.clickgui.component.components.sub.Keybind;
import tk.peanut.hydrogen.ui.clickgui.component.components.sub.ModeButton;
import tk.peanut.hydrogen.utils.ReflectionUtil;

/**
 * Created by peanut on 07/02/2021
 */
public class SafeWalk extends Module {

    public boolean safewalk;

    public SafeWalk() {
        super("SafeWalk", "", Keyboard.KEY_NONE, Category.Player, -1);
    }

    @Override
    public void onDisable() {
        safewalk = false;
    }

    @EventTarget
    public void onUpdate(EventSafeWalk e) {
        if(this.isToggled()) {
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
