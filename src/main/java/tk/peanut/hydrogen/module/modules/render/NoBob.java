package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventPreMotion;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;

@Info(name = "No-Bob", description = "Removes the bobbing animation", category = Category.Render)
public class NoBob extends Module {

    public NoBob() {
        super(Keyboard.KEY_NONE, colorRender);
    }

    @EventTarget
    public void onRender(EventPreMotion e) {
        Minecraft.getMinecraft().thePlayer.distanceWalkedModified = 0f;
    }
}
