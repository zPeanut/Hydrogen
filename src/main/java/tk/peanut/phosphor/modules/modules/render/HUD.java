package tk.peanut.phosphor.modules.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.modules.ModuleCategory;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class HUD extends Module {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public HUD() {
        super("HUD", "The Overlay", ModuleCategory.RENDER, false, true, Keyboard.KEY_NONE);
        setState(true);


    }

    private static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.8f, 0.7f).getRGB();
    }

    @EventTarget
    private void render2D(EventRender2D event) {

    }
}
