package tk.peanut.hydrogen.module.modules.render;

import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;

public class Chams extends Module {
    public Chams() {
        super("Chams", "See people through walls", Keyboard.KEY_NONE, Category.Render, -1);
    }
}
