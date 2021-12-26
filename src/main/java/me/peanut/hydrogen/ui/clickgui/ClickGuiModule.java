package me.peanut.hydrogen.ui.clickgui;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.file.files.ClickGuiFile;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;

/**
 * Created by peanut on 03/02/2021
 */

@Info(name = "ClickGUI", description = "The click gui", category = Category.Gui, keybind = Keyboard.KEY_LSHIFT)
public class ClickGuiModule extends Module {

    public ClickGui clickgui;

    public ClickGuiModule() {
        ArrayList<String> font = new ArrayList<>();
        font.add("TTF");
        font.add("Minecraft");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Font Type", this, "TTF", font));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Blur", this, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Tooltip", this, true));

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Red", this, 163, 0, 255, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Blue", this, 223, 0, 255, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Green", this, 255, 0, 255, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Alpha", this, 220, 0, 255, true));
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
        ClickGuiFile.loadClickGui();
        mc.displayGuiScreen(this.clickgui);
        toggle();
        super.onEnable();
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(Hydrogen.getClient().panic) {
            this.setEnabled();
        }
    }

}
