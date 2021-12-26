package me.peanut.hydrogen.module.modules.render;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 25/12/2021
 */
@Info(name = "AntiBlind", description = "Removes any visually impairing effects", category = Category.Render)
public class AntiBlind extends Module {

    public AntiBlind() {
        addSetting(new Setting("Pumpkin", this, true));
        addSetting(new Setting("Fire", this, true));
        addSetting(new Setting("Potion", this, true));
    }


}
