package tk.peanut.hydrogen.module.modules.player;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.file.files.*;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.ui.clickgui.ClickGuiModule;

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

        // disables commands, disables keybinds
        // -> mixinminecraft, mixinentityplayersp

        Hydrogen.getClient().panic = true;

        // disables all modules

        for (Module m : Hydrogen.getClient().moduleManager.getEnabledMods()) {
            m.setDisabled();
        }

        // saves all settings

        KeybindFile.saveKeybinds();
        SettingsButtonFile.saveState();
        SettingsComboBoxFile.saveState();
        SettingsSliderFile.saveState();
        ClickGuiFile.saveClickGui();
        ModuleFile.saveModules();
    }
}
