package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 19/12/2021
 */
@Info(name = "Freecam", description = "Allows you to move your camera outside of your player", category = Category.Render)
public class Freecam extends Module {

    public Freecam() {
        super(0x00);
    }
}
