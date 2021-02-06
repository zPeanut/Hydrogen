package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.Category;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.settings.Setting;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HUD extends Module {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public HUD() {
        super("HUD", "The Overlay", Keyboard.KEY_M, Category.Render, -1);

        ArrayList<String> theme = new ArrayList<>();
        theme.add("Tephra");
        theme.add("Xave");
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("Theme", this, "Tephra", theme));

        ArrayList<String> options = new ArrayList<>();
        options.add("Left");
        if(Hydrogen.getInstance().settingsManager.getSettingByName("Theme").getValString().equalsIgnoreCase("xave")) {
            options.add("Right");
        }
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("List Side", this, "Left", options));

        ArrayList<String> array = new ArrayList<>();
        array.add("Rainbow");
        array.add("White");
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("List Color",this, "White", array));

        Hydrogen.getInstance().settingsManager.rSetting(new Setting("Background", this, false));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("Watermark", this, true));
        Hydrogen.getInstance().settingsManager.rSetting(new Setting("Info", this, true));
    }

}
