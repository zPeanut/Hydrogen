package me.peanut.hydrogen.file.files;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.file.FileManager;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 03/02/2021
 */
public class SettingsSliderFile {

    private static FileManager SliderValue = new FileManager("slider", "Hydrogen");

    public SettingsSliderFile() {
        try {
            loadState();
        } catch (Exception e) {
        }
    }

    public static void saveState() {
        try {
            SliderValue.clear();
            for (Setting setting : Hydrogen.getClient().settingsManager.getSettings()) {
                String line = (setting.getName() + ":" + setting.getParentMod().getName() + ":" + String.valueOf(setting.getValDouble()));
                SliderValue.write(line);
            }
        } catch (Exception e) {
        }
    }

    public static void loadState() {
        try {
            for (String s : SliderValue.read()) {
                for (Setting setting : Hydrogen.getClient().settingsManager.getSettings()) {
                    String name = s.split(":")[0];
                    String modname = s.split(":")[1];
                    double value = Double.parseDouble(s.split(":")[2]);
                    if (setting.getName().equalsIgnoreCase(name) && setting.getParentMod().getName().equalsIgnoreCase(modname)) {
                        setting.setValDouble(value);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
