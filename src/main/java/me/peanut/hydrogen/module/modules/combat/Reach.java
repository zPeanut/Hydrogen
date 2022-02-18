package me.peanut.hydrogen.module.modules.combat;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 26/07/2021
 */

@Info(name = "Reach", description = "Extends your reach", category = Category.Combat)
public class Reach extends Module {

    public Reach() {
        addSetting(new Setting("Distance", this, 4, 3, 7, true));
    }

}
