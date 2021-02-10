package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.ReflectionUtil;

@Info(name = "FastPlace", description = "Removes the right-click delay", category = Category.Player, color = -1)
public class FastPlace extends Module {

    public FastPlace() {
        super(Keyboard.KEY_U);
    }


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
