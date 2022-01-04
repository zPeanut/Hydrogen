package me.peanut.hydrogen.file.files;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.file.FileManager;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 03/02/2021
 */
public class SettingsComboBoxFile {

    private static final FileManager ComboSetting = new FileManager("combobox", "Hydrogen");

    public SettingsComboBoxFile() {
        try {
            loadState();
        } catch (Exception e) {
        }
    }

    public static void saveState() {
        try {
            ComboSetting.clear();
            for (Setting setting : Hydrogen.getClient().settingsManager.getSettings()) {
                if(setting.isModeCombo()) {
                    String line = (setting.getName() + ":" + setting.getParentMod().getName() + (String.valueOf(setting.getValString()) != null ? ":" + setting.getValString() : ""));
                    ComboSetting.write(line);
                }
            }
        } catch (Exception e) {
        }
    }

    public static void loadState() {
        try {
            for (String s : ComboSetting.read()) {
                for (Setting setting : Hydrogen.getClient().settingsManager.getSettings()) {
                    String name = s.split(":")[0];
                    String modname = s.split(":")[1];
                    String Setting = String.valueOf(s.split(":")[2]);
                    if (setting.getName().equalsIgnoreCase(name) && setting.getParentMod().getName().equalsIgnoreCase(modname)) {
                        setting.setValString(Setting);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
