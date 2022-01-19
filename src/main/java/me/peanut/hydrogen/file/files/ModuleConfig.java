package me.peanut.hydrogen.file.files;


import com.google.gson.*;
import com.sun.jna.platform.win32.WinNT;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import net.minecraftforge.common.util.JsonUtils;
import scala.reflect.internal.Symbols;

import java.io.*;
import java.util.Map;

/**
 * Created by peanut on 04/01/2022
 */
public class ModuleConfig {

    private final File moduleFile;

    public ModuleConfig() {
        this.moduleFile = new File(Hydrogen.getClient().directory + File.separator + "modules.json");
    }

    public void saveConfig() {
        try {
            final JsonObject jsonObject = new JsonObject();
            for (Module module : Hydrogen.getClient().moduleManager.getModules()) {
                JsonObject jsonMod = new JsonObject();
                jsonMod.addProperty("toggled", module.isEnabled());
                jsonMod.addProperty("visible", module.isVisible());
                jsonMod.addProperty("keybind", module.getKeybind());
                jsonObject.add(module.getName(), jsonMod);
            }
            final PrintWriter printWriter = new PrintWriter(new FileWriter(this.moduleFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            printWriter.println(gson.toJson(jsonObject));
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new BufferedReader(new FileReader(this.moduleFile)));
            if(jsonElement instanceof JsonNull) {
                return;
            }
            for (final Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                final Module module = Hydrogen.getClient().moduleManager.getModulebyName(entry.getKey());
                if (module != null) {
                    final JsonObject jsonModule = (JsonObject)entry.getValue();
                    boolean toggled = jsonModule.get("toggled").getAsBoolean();
                    if(toggled && !(module.getName().equalsIgnoreCase("freecam") || module.getName().equalsIgnoreCase("§cpanic"))) {
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
