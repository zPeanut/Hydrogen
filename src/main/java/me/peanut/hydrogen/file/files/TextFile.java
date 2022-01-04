package me.peanut.hydrogen.file.files;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.file.FileManager;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 04/01/2022
 */
public class TextFile {

    private static final FileManager TextList = new FileManager("text", "Hydrogen");


    public TextFile() {
        try {
            loadState();
        } catch (Exception e) {
        }
    }

    public static void saveState() {
        try {
            TextList.clear();
            for (Setting setting : Hydrogen.getClient().settingsManager.getSettings()) {
                if(setting.getText() != null) {
                    String line = (setting.getName() + ":" + setting.getParentMod().getName() + ":\"" + setting.getText() + "\"");
                    TextList.write(line);
                }
            }
        } catch (Exception e) {
        }
    }

    public static void loadState() {
        try {
            for (String s : TextList.read()) {
                for (Setting setting : Hydrogen.getClient().settingsManager.getSettings()) {
                    String name = s.split(":")[0];
                    String modname = s.split(":")[1];
                    String text = String.valueOf(s.split("\"")[1]);
                    System.out.println(text);
                    if (setting.getName().equalsIgnoreCase(name) && setting.getParentMod().getName().equalsIgnoreCase(modname)) {
                        setting.setText(text);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
