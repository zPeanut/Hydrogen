package me.peanut.hydrogen.file.files.deprecated;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.file.FileManager;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 24/12/2021
 */
@Deprecated
public class VisibleFile {

    private static final FileManager VisibleList = new FileManager("visible", "Hydrogen");

    // TODO: put all module settings into a json instead of seperate txt files some time

    public VisibleFile() {
        try {
            loadState();
        } catch (Exception e) {
        }
    }

    public static void saveState() {
        try {
            VisibleList.clear();
            for (Module mod : Hydrogen.getClient().moduleManager.getModules()) {
                String line = (mod.getName() + ":" + mod.isVisible());
                VisibleList.write(line);
            }
        } catch (Exception e) {
        }
    }

    public static void loadState() {
        try {
            for (String s : VisibleList.read()) {
                for (Module mod : Hydrogen.getClient().moduleManager.getModules()) {
                    String name = s.split(":")[0];
                    boolean visible = Boolean.parseBoolean(s.split(":")[1]);
                    if (mod.getName().equalsIgnoreCase(name)) {
                        mod.setVisible(visible);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}