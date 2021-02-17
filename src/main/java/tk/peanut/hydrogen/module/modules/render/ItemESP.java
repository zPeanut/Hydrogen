package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 16/02/2021
 */
@Info(name = "ItemESP", description = "Draws a box around dropped items", color = -1, category = Category.Render)
public class ItemESP extends Module {
    public ItemESP() {
        super(0x00);
    }

    @EventTarget
    public void onRender(EventUpdate e) {
        
    }
}
