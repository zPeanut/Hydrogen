package me.peanut.hydrogen.file.files;


import com.google.gson.*;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by peanut on 04/01/2022
 */
public class SettingsConfig {

    private final File settingsFile;

    public SettingsConfig() {
        this.settingsFile = new File(Hydrogen.getClient().directory + File.separator + "settings.json");
    }

    public void saveConfig() {
        try {

            final JsonObject jsonMod = new JsonObject();
            Hydrogen.getClient().moduleManager.getModules().forEach(module -> {
                final JsonObject jsonSetting = new JsonObject();
                final Iterator<Setting> iterator = Hydrogen.getClient().settingsManager.getSettings().iterator();
                while (iterator.hasNext()) {
                    final Setting setting = iterator.next();
                    if(setting.isModeButton() && setting.getParentMod() == module) {
                        jsonSetting.addProperty(setting.getName(), setting.isEnabled());
                    }
                    if(setting.isModeSelect() && setting.getParentMod() == module) {
                        jsonSetting.addProperty(setting.getName(), setting.getMode());
                    }
                    if(setting.isModeSlider() && setting.getParentMod() == module) {
                        jsonSetting.addProperty(setting.getName(), setting.getValue());
                    }
                    if(setting.isModeText() && setting.getParentMod() == module) {
                        jsonSetting.addProperty(setting.getName(), setting.getText());
                    }
                }
                jsonMod.add(module.getName(), jsonSetting);
            });

            final PrintWriter printWriter = new PrintWriter(new FileWriter(this.settingsFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            printWriter.println(gson.toJson(jsonMod));
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse((Reader) new BufferedReader(new FileReader(this.settingsFile)));
            if(jsonElement instanceof JsonNull) {
                return;
            }
            for (final Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                final Module module = Hydrogen.getClient().moduleManager.getModulebyName(entry.getKey());
                if (module != null) {
                    final JsonObject jsonModule = (JsonObject)entry.getValue();
                    boolean toggled = jsonModule.get("toggled").getAsBoolean();
                    if(toggled) {
                        module.toggle();
                    }
                    module.setVisible(jsonModule.get("visible").getAsBoolean());
                    module.setKeyBind(jsonModule.get("keybind").getAsInt());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
