package tk.peanut.hydrogen.module.modules.render;

import net.minecraft.client.renderer.RenderGlobal;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;

@Info(name = "Chams", description = "Draws models through walls", category = Category.Render)
public class Chams extends Module {
    public Chams() {
        super(Keyboard.KEY_NONE, new Color(199, 255, 201));
    }
}
