package me.peanut.hydrogen.module.modules.player;

import me.peanut.hydrogen.file.files.*;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.Hydrogen;

/**
 * Created by peanut on 22/12/2021
 */

@Info(name = "Panic", description = "Disables all client functionality. Needs a restart if activated.", category = Category.Player)
public class Panic extends Module {

    public Panic() {
        super(0x00);
    }

    @Override
    public void onEnable() {
        // saves all settings

        KeybindFile.saveKeybinds();
        SettingsButtonFile.saveState();
        SettingsComboBoxFile.saveState();
        SettingsSliderFile.saveState();
        ClickGuiFile.saveClickGui();
        ModuleFile.saveModules();

        // disables commands, disables keybinds
        // -> mixinminecraft, mixinentityplayers

        Hydrogen.getClient().panic = true;

        // disables all modules

        for (Module m : Hydrogen.getClient().moduleManager.getAllEnabledMods()) {
            m.setDisabled();
        }
    }
}
