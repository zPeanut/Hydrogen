package tk.peanut.hydrogen.module.modules.render;

import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;

/**
 * Created by peanut on 05/02/2021
 */

@Info(name = "OutlineESP", description = "", category = Category.Render, color = -1)
public class ESP extends Module {
    public ESP() {
        super(Keyboard.KEY_NONE);

        Hydrogen.getInstance().settingsManager.rSetting(new Setting("LineWidth", this, 3, 1, 10, false));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("Entities", this, true));
    }

}
