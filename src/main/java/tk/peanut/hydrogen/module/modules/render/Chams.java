package tk.peanut.hydrogen.module.modules.render;

import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

@Info(name = "Chams", description = "", category = Category.Render, color = -1)
public class Chams extends Module {
    public Chams() {
        super(Keyboard.KEY_NONE);
    }
}
