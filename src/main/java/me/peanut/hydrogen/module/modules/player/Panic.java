package me.peanut.hydrogen.module.modules.player;

import me.peanut.hydrogen.file.files.*;
import me.peanut.hydrogen.file.files.deprecated.*;
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

        for (Module m : h2.moduleManager.getEnabledMods()) {
            m.toggle();
        }

        // disables commands, disables keybinds, disables hud
        // -> mixinminecraft, mixinentityplayers

        h2.panic = true;

        // disables clickgui, if open

        mc.displayGuiScreen(null);
    }
}
