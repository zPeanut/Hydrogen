package me.peanut.hydrogen.module.modules.player;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 16/01/2022
 */
@Info(name = "NoSwing", description = "Cancels the swing animation", category = Category.Render)
public class NoSwing extends Module {

    public NoSwing() {
        addSetting(new Setting("Server-side", this, false));
    }

}
