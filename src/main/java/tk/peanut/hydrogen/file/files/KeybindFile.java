package tk.peanut.hydrogen.file.files;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.file.FileManager;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 03/02/2021
 */
public class KeybindFile {

    private static FileManager bindList = new FileManager("binds", "Hydrogen");

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
                String line = (module.getName() + ":" + String.valueOf(module.getKeybind()));
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
