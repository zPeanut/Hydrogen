package tk.peanut.hydrogen.ui.mainmenu;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.awt.*;

/**
 * Created by peanut on 26/02/2021
 */
@Info(name = "MainMenu", category = Category.Gui, description = "Enables the custom main menu")
public class MainMenuModule extends Module {
    public MainMenuModule() {
        super(0x00);

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Rainbow", this, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Startup Sound", this, true));
    }
}
