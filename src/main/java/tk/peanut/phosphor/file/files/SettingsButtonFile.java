package tk.peanut.phosphor.file.files;

import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.file.FileManager;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;

/**
 * Created by peanut on 03/02/2021
 */
public class SettingsButtonFile {

    private static FileManager ButtonList = new FileManager("button", "Phosphor");

    public SettingsButtonFile() {
        try {
            loadState();
        } catch (Exception e) {
        }
    }

    public static void saveState() {
        try {
            ButtonList.clear();
            for (Setting setting : Phosphor.getInstance().settingsManager.getSettings()) {
                String line = (setting.getName() + ":" + String.valueOf(setting.getValBoolean()));
                ButtonList.write(line);
            }
        } catch (Exception e) {
        }
    }

    public static void loadState() {
        try {
            for (String s : ButtonList.read()) {
                for (Setting setting : Phosphor.getInstance().settingsManager.getSettings()) {
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
