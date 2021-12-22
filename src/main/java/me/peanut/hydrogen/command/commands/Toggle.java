package me.peanut.hydrogen.command.commands;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.file.files.ModuleFile;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.command.Command;

/**
 * Created by peanut on 14/03/2021
 */
public class Toggle extends Command {

    public void execute(String[] args) {
        if (args.length != 1) {
            msg(getAll());
        } else {
            String module = args[0];
            Module mod = Hydrogen.getClient().moduleManager.getModulebyName(module);
            if (mod == null) {
                msg("§cThe requested module was not found!");
            } else {
                Hydrogen.getClient().moduleManager.getModulebyName(module).toggle();
                msg(String.format("§b%s §7has been %s", Hydrogen.getClient().moduleManager.getModulebyName(module).getName(), Hydrogen.getClient().moduleManager.getModulebyName(module).isEnabled() ? "§aenabled" : "§cdisabled."));
                ModuleFile.saveModules();
            }
        }
    }

    public String getName() {
        return "t";
    }

    public String getDesc() {
        return "Toggles modules.";
    }

    public String getSyntax() {
        return ".t";
    }

    public String getAll() {
        return getSyntax() + " - " + getDesc();
    }

}
