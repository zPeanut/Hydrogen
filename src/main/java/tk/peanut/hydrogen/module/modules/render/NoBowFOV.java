package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 04/09/2021
 */

@Info(name = "NoBowFOV", category = Category.Render, description = "Removes Bow FOV")
public class NoBowFOV extends Module {

    public NoBowFOV() {
        super(0x00);
    }
}
