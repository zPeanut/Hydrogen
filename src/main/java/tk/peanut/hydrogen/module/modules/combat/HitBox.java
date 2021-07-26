package tk.peanut.hydrogen.module.modules.combat;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 26/07/2021
 */
@Info(name = "HitBox", description = "Extends the enemys hitbox", category = Category.Combat)
public class HitBox extends Module {
    public HitBox() {
        super(0x00);

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Expand", this, 0.15, 0.1, 1, false));
    }
}
