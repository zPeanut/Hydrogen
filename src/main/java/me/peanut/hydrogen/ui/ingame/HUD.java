package me.peanut.hydrogen.ui.ingame;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.Hydrogen;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;

@Info(name = "HUD", description = "The overlay", category = Category.Gui)
public class HUD extends Module {

    // this module basically only exists to have general settings, also to disable the hud alltogether

    private static java.util.ArrayList<Module> activemodules;

    public HUD() {
        super(Keyboard.KEY_NONE);
        activemodules = new java.util.ArrayList<>();

        ArrayList<String> time = new ArrayList<>();
        time.add("24H");
        time.add("12H");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Time Format", this, "24H", time));

        ArrayList<String> font = new ArrayList<>();
        font.add("TTF");
        font.add("Minecraft");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Font", this, "TTF", font));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Drop Shadow", this, true));
    }
}