package me.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.events.EventPreMotion;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;

@Info(name = "NoBob", description = "Removes the bobbing animation", category = Category.Render)
public class NoBob extends Module {

    public NoBob() {}

    @EventTarget
    public void onRender(EventPreMotion e) {
        Minecraft.getMinecraft().thePlayer.distanceWalkedModified = 0f;
    }
}
