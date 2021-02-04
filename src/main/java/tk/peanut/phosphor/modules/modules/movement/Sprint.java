package tk.peanut.phosphor.modules.modules.movement;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;

import java.nio.charset.MalformedInputException;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints", Keyboard.KEY_NONE, Category.Movement, -1);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().gameSettings.keyBindSprint.pressed = true;
    }

    @Override
    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.keyBindSprint.pressed = false;
    }
}
