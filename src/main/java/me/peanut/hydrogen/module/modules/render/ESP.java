package me.peanut.hydrogen.module.modules.render;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 05/02/2021
 */

@Info(name = "ESP", description = "Draws an outline on entities through walls", category = Category.Render)
public class ESP extends Module {
    public ESP() {
        addSetting(new Setting("LineWidth", this, 3, 1, 10, false));
        addSetting(new Setting("Entities", this, true));
    }
}
