package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventPreMotion;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

@Info(name = "NoBob", description = "", category = Category.Render, color = -1)
public class NoBob extends Module {

    public NoBob() {
        super(Keyboard.KEY_NONE);
    }

    @EventTarget
    public void onRender(EventPreMotion e) {
        Minecraft.getMinecraft().thePlayer.distanceWalkedModified = 0f;
    }
}
