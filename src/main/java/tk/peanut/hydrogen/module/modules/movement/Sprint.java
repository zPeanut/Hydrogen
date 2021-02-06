package tk.peanut.hydrogen.module.modules.movement;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.ReflectionUtil;

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
