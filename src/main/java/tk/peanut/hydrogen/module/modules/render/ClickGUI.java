package tk.peanut.hydrogen.module.modules.render;

import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.file.files.ClickGuiFile;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.ui.clickgui.ClickGui;

/**
 * Created by peanut on 03/02/2021
 */

@Info(name = "ClickGUI", description = "", category = Category.Render, color = -1)
public class ClickGUI extends Module {

    public ClickGui clickgui;

    public ClickGUI() {
        super(Keyboard.KEY_LSHIFT);
   /*     ArrayList<String> options = new ArrayList<>();
        options.add("DefaultOption");
        options.add("Option2");
        options.add("Option3");
        Phosphor.getInstance().settingsManager.rSetting(new Setting("OptionSelector", this, "DefaultOption", options));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("BooleanOption", this, false));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("SliderOptionInt", this, 255, 0, 255, true));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("SliderOptionDouble", this, 10, 0, 20, false));*/
    }

    @Override
    public void onEnable()
    {
        if(this.clickgui == null)
            this.clickgui = new ClickGui();
        ClickGuiFile.loadClickGui();
        mc.displayGuiScreen(this.clickgui);
        toggle();
        super.onEnable();
    }

}
