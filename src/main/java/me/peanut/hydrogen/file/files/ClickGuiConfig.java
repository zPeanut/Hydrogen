package me.peanut.hydrogen.file.files;

import com.google.gson.*;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.clickgui.ClickGui;
import me.peanut.hydrogen.ui.clickgui.component.Frame;

import java.io.*;
import java.util.Map;

/**
 * Created by peanut on 05/01/2022
 */
public class ClickGuiConfig {

    private final File clickGuiFile;

    public ClickGuiConfig() {
        this.clickGuiFile = new File(Hydrogen.getClient().directory + File.separator + "clickgui.json");
    }


    public void saveConfig() {
        try {
            final JsonObject jsonObject = new JsonObject();
            for (Frame frame : ClickGui.frames) {
                JsonObject jsonMod = new JsonObject();
                jsonMod.addProperty("posX", frame.getX());
                jsonMod.addProperty("posY", frame.getY());
                jsonMod.addProperty("open", frame.isOpen());
                jsonObject.add(frame.category.name(), jsonMod);
            }
            final PrintWriter printWriter = new PrintWriter(new FileWriter(this.clickGuiFile));
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
            JsonElement jsonElement = jsonParser.parse(new BufferedReader(new FileReader(this.clickGuiFile)));
            if(jsonElement instanceof JsonNull) {
                return;
            }
            JsonObject jsonObject = (JsonObject) jsonElement;
            for (Frame frame : ClickGui.frames) {
                if(!jsonObject.has(frame.category.name())) {
                    continue;
                }
                JsonObject jsonPanel = jsonObject.getAsJsonObject(frame.category.name());
                frame.setX(jsonPanel.get("posX").getAsInt());
                frame.setY(jsonPanel.get("posY").getAsInt());
                frame.setOpen(jsonPanel.get("open").getAsBoolean());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
