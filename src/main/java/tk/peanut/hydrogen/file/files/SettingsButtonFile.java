package tk.peanut.hydrogen.file.files;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.file.FileManager;
import tk.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 03/02/2021
 */
public class SettingsButtonFile {

    private static FileManager ButtonList = new FileManager("button", "Hydrogen");

    public SettingsButtonFile() {
        try {
            loadState();
        } catch (Exception e) {
        }
    }

    public static void saveState() {
        try {
            ButtonList.clear();
            for (Setting setting : Hydrogen.getClient().settingsManager.getSettings()) {
                String line = (setting.getName() + ":" + String.valueOf(setting.isEnabled()));
                ButtonList.write(line);
            }
        } catch (Exception e) {
        }
    }

    public static void loadState() {
        try {
            for (String s : ButtonList.read()) {
                for (Setting setting : Hydrogen.getClient().settingsManager.getSettings()) {
                    String name = s.split(":")[0];
                    boolean toggled = Boolean.parseBoolean(s.split(":")[1]);
                    if (setting.getName().equalsIgnoreCase(name)) {
                        setting.setValBoolean(toggled);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
