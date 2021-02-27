package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventManager;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;
import java.awt.image.Kernel;

@Info(name = "Fullbright", description = "Brightens up the world", category = Category.Render)
public class Fullbright extends Module {
    public Fullbright() {
        super(Keyboard.KEY_NONE, colorRender);
    }

    @Override
    public void onEnable() {
        mc.gameSettings.gammaSetting = 5f;
        EventManager.register(this);
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = 0f;
        EventManager.unregister(this);
    }


}
