package tk.peanut.hydrogen.module.modules.player;

import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.awt.*;

/**
 * Created by peanut on 28/02/2021
 */
public class BedAura extends Module {
    public BedAura() {
        super(0x00, colorPlayer);

        addSetting(new Setting("Bed", this, false));
        addSetting(new Setting("Cake", this, false));
        addSetting(new Setting("Egg", this, false));
        addSetting(new Setting("BypassWall", this, false));
    }


}
