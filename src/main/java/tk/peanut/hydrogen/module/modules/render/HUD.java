package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.Category;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.settings.Setting;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HUD extends Module {

    public HUD() {
        super("HUD", "The Overlay", Keyboard.KEY_M, Category.Render, -1);

        ArrayList<String> options = new ArrayList<>();
        options.add("Left");
        options.add("Right");

        ArrayList<String> array = new ArrayList<>();
        array.add("Rainbow");
        array.add("White");

        ArrayList<String> watermark = new ArrayList<>();
        watermark.add("Old");
        watermark.add("New");

        Hydrogen.getInstance().settingsManager.rSetting(new Setting("Watermark", this, "New", watermark));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("List Side", this, "Left", options));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("List Color",this, "White", array));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("Background", this, false));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("Info", this, true));
    }

}
