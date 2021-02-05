package tk.peanut.phosphor.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.events.EventUpdate;
import tk.peanut.phosphor.module.Category;
import tk.peanut.phosphor.module.Module;
import tk.peanut.phosphor.utils.ReflectionUtil;

public class FastPlace extends Module {

    public FastPlace() {
        super("FastPlace", "Automatic FastBridge", Keyboard.KEY_U, Category.Player, -1);
    }


    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(this.isToggled()) {
            try {
                ReflectionUtil.delayTimer.setInt(Minecraft.getMinecraft(), 0);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
