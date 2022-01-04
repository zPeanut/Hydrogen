package me.peanut.hydrogen.module.modules.player;

import me.peanut.hydrogen.file.files.*;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.Hydrogen;

/**
 * Created by peanut on 22/12/2021
 */

@Info(name = "§cPanic", description = "Disables all client functionality. Needs a restart if activated.", category = Category.Player)
public class Panic extends Module {

    public Panic() {}

    @Override
    public void onEnable() {
        // saves all settings

        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.loadConfig();
        SettingsButtonFile.saveState();
        SettingsComboBoxFile.saveState();
        SettingsSliderFile.saveState();
        ClickGuiFile.saveClickGui();
        TextFile.saveState();

        // disables commands, disables keybinds, disables hud
        // -> mixinminecraft, mixinentityplayers

        Hydrogen.getClient().panic = true;

        // disables clickgui, if open

        mc.displayGuiScreen(null);

        // disables all modules

        for (Module m : Hydrogen.getClient().moduleManager.getEnabledMods()) {
            m.setDisabled();
        }
    }
}
