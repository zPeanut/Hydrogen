package me.peanut.hydrogen.file.files.deprecated;

import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.file.FileManager;

/**
 * Created by peanut on 03/02/2021
 */
@Deprecated
public class KeybindFile {

    private static final FileManager bindList = new FileManager("binds", "Hydrogen");

    public KeybindFile() {
        try {
            loadKeybinds();
        } catch (Exception e) {
        }
    }

    public static void saveKeybinds() {
        try {
            bindList.clear();
            for (Module module : Hydrogen.getClient().moduleManager.getModules()) {
                String line = (module.getName() + ":" + module.getKeybind());
                bindList.write(line);
            }
        } catch (Exception e) {
        }
    }

    public static void loadKeybinds() {
        try {
            for (String s : bindList.read()) {
                for (Module module : Hydrogen.getClient().moduleManager.getModules()) {
                    String name = s.split(":")[0];
                    int key = Integer.parseInt(s.split(":")[1]);
                    if (module.getName().equalsIgnoreCase(name)) {
                        module.setKeyBind(key);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

}