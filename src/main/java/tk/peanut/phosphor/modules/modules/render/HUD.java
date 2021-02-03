package tk.peanut.phosphor.modules.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.modules.Category;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HUD extends Module {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public HUD() {
        super("HUD", "The Overlay", Keyboard.KEY_M, Category.Render, -1);

        ArrayList<String> options = new ArrayList<>();
        options.add("Left");
        options.add("Right");
        Phosphor.getInstance().settingsManager.rSetting(new Setting("HUD Alignment", this, "Left", options));

    }

}
