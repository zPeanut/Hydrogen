package tk.peanut.hydrogen.file.files;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.file.FileManager;
import tk.peanut.hydrogen.settings.Setting;

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
                String line = (setting.getName() + ":" + String.valueOf(setting.getValDouble()));
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
                    double value = Double.parseDouble(s.split(":")[1]);
                    if (setting.getName().equalsIgnoreCase(name)) {
                        setting.setValDouble(value);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
