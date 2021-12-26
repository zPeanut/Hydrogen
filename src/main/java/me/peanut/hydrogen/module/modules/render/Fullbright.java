package me.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventManager;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;

@Info(name = "Fullbright", description = "Brightens up the world", category = Category.Render)
public class Fullbright extends Module {
    public Fullbright() {}

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
