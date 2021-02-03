package tk.peanut.phosphor.modules.modules.render;

import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;

import java.util.ArrayList;

/**
 * Created by peanut on 03/02/2021
 */
public class ClickGUI extends Module {
    
    public ClickGUI() {
        super("ClickGUI", "Shows the ClickGUI", Keyboard.KEY_J, Category.Render, -1);
    }
    
    @Override
    public void setup(){
        ArrayList<String> options = new ArrayList<>();
        options.add("JellyLike");
        options.add("New");
        Phosphor.getInstance().settingsManager.rSetting(new Setting("Design", this, "New", options));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("Sound", this, false));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("GuiRed", this, 255, 0, 255, true));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("GuiGreen", this, 26, 0, 255, true));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("GuiBlue", this, 42, 0, 255, true));
    }
    @Override
    public void onEnable()
    {
        mc.displayGuiScreen(Phosphor.getInstance().clickgui);
        toggle();
        super.onEnable();
    }
    
}
