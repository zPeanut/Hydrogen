package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 19/12/2021
 */
@Info(name = "Tracers", description = "Draws a line to entities and players", category = Category.Render)
public class Tracers extends Module {

    public Tracers() {
        super(0x00);
    }
}
