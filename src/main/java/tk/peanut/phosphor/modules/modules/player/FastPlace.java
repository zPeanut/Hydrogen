package tk.peanut.phosphor.modules.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.events.EventUpdate;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;

public class FastPlace extends Module {


    public FastPlace() {
        super("FastPlace", "Removes right click cooldown", Keyboard.KEY_U, Category.Player, -1);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(this.isToggled()) {
                this.mc.rightClickDelayTimer = 0;
        }
    }
}
