package tk.peanut.phosphor.modules.modules.movement;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.utils.ReflectionUtil;

import java.nio.charset.MalformedInputException;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints", Keyboard.KEY_NONE, Category.Movement, -1);
    }

    @Override
    public void onEnable() {
        try {
            ReflectionUtil.pressed.set(Minecraft.getMinecraft().gameSettings.keyBindSprint, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            ReflectionUtil.pressed.set(Minecraft.getMinecraft().gameSettings.keyBindSprint, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
