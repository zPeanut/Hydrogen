package tk.peanut.hydrogen.module.modules.render;

import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.awt.*;

/**
 * Created by peanut on 05/02/2021
 */

@Info(name = "OutlineESP", description = "Draws an outline through walls", category = Category.Render)
public class ESP extends Module {
    public ESP() {
        super(Keyboard.KEY_NONE, new Color(199, 255, 201));

        Hydrogen.getClient().settingsManager.rSetting(new Setting("LineWidth", this, 3, 1, 10, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Entities", this, true));
    }

}
