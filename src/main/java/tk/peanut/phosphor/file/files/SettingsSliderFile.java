package tk.peanut.phosphor.file.files;

import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.file.FileManager;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;

/**
 * Created by peanut on 03/02/2021
 */
public class SettingsSliderFile {

    private static FileManager SliderValue = new FileManager("slider", "Phosphor");

    public SettingsSliderFile() {
        try {
            loadState();
        } catch (Exception e) {
        }
    }

    public static void saveState() {
        try {
            SliderValue.clear();
            for (Setting setting : Phosphor.getInstance().settingsManager.getSettings()) {
                String line = (setting.getName() + ":" + String.valueOf(setting.getValDouble()));
                SliderValue.write(line);
            }
        } catch (Exception e) {
        }
    }

    public static void loadState() {
        try {
            for (String s : SliderValue.read()) {
                for (Setting setting : Phosphor.getInstance().settingsManager.getSettings()) {
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
