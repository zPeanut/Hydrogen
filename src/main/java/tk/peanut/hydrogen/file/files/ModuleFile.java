package tk.peanut.hydrogen.file.files;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.file.FileManager;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 08/02/2021
 */
public class ModuleFile {

    private static FileManager ModuleList = new FileManager("modules", "Hydrogen");

    public ModuleFile() {
        try {
            loadModules();
        } catch (Exception e) {
        }
    }

    public static void saveModules() {
        try {
            ModuleList.clear();
            for (Module module : Hydrogen.getClient().moduleManager.getModules()) {
                String line = (module.getName() + ":" + String.valueOf(module.isEnabled()));
                ModuleList.write(line);
            }
        } catch (Exception e) {
        }
    }

    public static void loadModules() {
        try {
            for (String s : ModuleList.read()) {
                for (Module module : Hydrogen.getClient().moduleManager.getModules()) {
                    String name = s.split(":")[0];
                    boolean toggled = Boolean.parseBoolean(s.split(":")[1]);
                    if (module.getName().equalsIgnoreCase(name) && toggled) {
                        module.toggle();
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
