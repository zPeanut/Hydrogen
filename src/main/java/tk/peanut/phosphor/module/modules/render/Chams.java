package tk.peanut.phosphor.module.modules.render;

import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.module.Category;
import tk.peanut.phosphor.module.Module;

public class Chams extends Module {
    public Chams() {
        super("Chams", "See people through walls", Keyboard.KEY_NONE, Category.Render, -1);
    }
}
