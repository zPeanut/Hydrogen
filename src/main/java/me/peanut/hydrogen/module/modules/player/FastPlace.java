package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.ReflectionUtil;

@Info(name = "FastPlace", description = "Removes the right-click delay", category = Category.Player, keybind = Keyboard.KEY_U)
public class FastPlace extends Module {

    public FastPlace() {}

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(this.isEnabled()) {
            try {
                ReflectionUtil.delayTimer.setInt(Minecraft.getMinecraft(), 0);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
