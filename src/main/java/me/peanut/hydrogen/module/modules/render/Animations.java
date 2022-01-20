package me.peanut.hydrogen.module.modules.render;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 19/01/2022
 */
@Info(name = "Animations", description = "Adds back pre 1.8 animations", category = Category.Render)
public class Animations extends Module {

    public Animations() {
        addSetting(new Setting("BlockHit", this, true)); // done
        addSetting(new Setting("Rod", this, true)); // done
        addSetting(new Setting("Bow", this, true)); // done
        addSetting(new Setting("Third-person Block", this, true)); // done
        addSetting(new Setting("Armor Damage", this, true)); // done
        addSetting(new Setting("Inventory Offset", this, true)); // done
        addSetting(new Setting("Dropped Items", this, true)); // done
        addSetting(new Setting("Tab List", this, true)); // done
    }

}
