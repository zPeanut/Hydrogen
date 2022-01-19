package me.peanut.hydrogen.module.modules.combat;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 26/07/2021
 */
@Info(name = "HitBox", description = "Extends the enemys hitbox", category = Category.Combat)
public class HitBox extends Module {
    public HitBox() {
        addSetting(new Setting("Expand", this, 0.15, 0.1, 1, false));
    }
}
