package tk.peanut.phosphor.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.events.EventPreMotion;
import tk.peanut.phosphor.module.Category;
import tk.peanut.phosphor.module.Module;

public class NoBob extends Module {

    public NoBob() {
        super("NoBob", "Removes the bobbing animation", Keyboard.KEY_NONE, Category.Render, -1);
    }

    @EventTarget
    public void onRender(EventPreMotion e) {
        Minecraft.getMinecraft().thePlayer.distanceWalkedModified = 0f;
    }
}
