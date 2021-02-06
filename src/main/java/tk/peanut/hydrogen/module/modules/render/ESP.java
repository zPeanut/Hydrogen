package tk.peanut.hydrogen.module.modules.render;

import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 05/02/2021
 */
public class ESP extends Module {
    public ESP() {
        super("OutlineESP", "Outlines entities through walls", Keyboard.KEY_NONE, Category.Render, -1);
    }
}
