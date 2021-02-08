package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.Category;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;

@Info(name = "HUD", description = "The overlay", category = Category.Render, color = -1)
public class HUD extends Module {

    public HUD() {
        super(Keyboard.KEY_NONE);

        ArrayList<String> options = new ArrayList<>();
        options.add("Left");
        options.add("Right");

        ArrayList<String> array = new ArrayList<>();
        array.add("Rainbow");
        array.add("White");

        ArrayList<String> watermark = new ArrayList<>();
        watermark.add("Old");
        watermark.add("New");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Watermark", this, "New", watermark));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("List Side", this, "Left", options));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("List Color",this, "White", array));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Background", this, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Info", this, true));
    }
}
