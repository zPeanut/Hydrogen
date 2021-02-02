package tk.peanut.phosphor.modules.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class TestModule3 extends Module {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public TestModule3() {
        super("The equivalence", "The Overlay", Keyboard.KEY_O, Category.Render, -1);
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
