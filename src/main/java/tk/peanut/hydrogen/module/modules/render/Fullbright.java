package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventManager;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;

import java.awt.image.Kernel;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright", "Lightens up the game", Keyboard.KEY_NONE, Category.Render, -1);
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
