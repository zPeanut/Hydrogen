package tk.peanut.phosphor.modules.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.ui.clickgui.clickgui.ClickGui;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by peanut on 03/02/2021
 */

public class ClickGUI extends Module {

    public ClickGui clickgui;

    public ClickGUI() {
        super("ClickGui", "The Overlay", Keyboard.KEY_K, Category.Render, -1);
        ArrayList<String> options = new ArrayList<>();
        options.add("DefaultOption");
        options.add("Option2");
        options.add("Option3");
        Phosphor.getInstance().settingsManager.rSetting(new Setting("OptionSelector", this, "DefaultOption", options));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("BooleanOption", this, false));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("SliderOptionInt", this, 255, 0, 255, true));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("SliderOptionDouble", this, 10, 0, 20, false));
    }

    @Override
    public void onEnable()
    {
        if(this.clickgui == null)
            this.clickgui = new ClickGui();

        mc.displayGuiScreen(this.clickgui);
        toggle();
        super.onEnable();
    }

}
