package me.peanut.hydrogen.module.modules.gui;

import me.peanut.hydrogen.file.files.ClickGuiConfig;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.clickgui.ClickGui;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;

/**
 * Created by peanut on 03/02/2021
 */

@Info(name = "ClickGUI", description = "The click gui", category = Category.Gui, keybind = Keyboard.KEY_LSHIFT)
public class ClickGUI extends Module {

    public ClickGui clickgui;

    public ClickGUI() {
        ArrayList<String> font = new ArrayList<>();
        font.add("TTF");
        font.add("Minecraft");

        addSetting(new Setting("Font Type", this, "TTF", font));
        addSetting(new Setting("Blur", this, true));
        addSetting(new Setting("Tooltip", this, true));
        addSetting(new Setting("Particles", this, false));

        addSetting(new Setting("Red", this, 163, 0, 255, true));
        addSetting(new Setting("Blue", this, 223, 0, 255, true));
        addSetting(new Setting("Green", this, 255, 0, 255, true));
        addSetting(new Setting("Alpha", this, 220, 0, 255, true));

   /*
        this exists to i dont have to remember how to add options lol.

   ArrayList<String> options = new ArrayList<>();
        options.add("DefaultOption");
        options.add("Option2");
        options.add("Option3");
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("OptionSelector", this, "DefaultOption", options));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("BooleanOption", this, false));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("SliderOptionInt", this, 255, 0, 255, true));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("SliderOptionDouble", this, 10, 0, 20, false));*/
    }

    @Override
    public void onEnable() {
        if(this.clickgui == null) {
            this.clickgui = new ClickGui();
        }
        ClickGuiConfig clickGuiConfig = new ClickGuiConfig();
        clickGuiConfig.loadConfig();
        mc.displayGuiScreen(this.clickgui);
        toggle();
        super.onEnable();
    }
}
