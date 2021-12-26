package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.ReflectionUtil;

import java.util.Objects;

/**
 * Created by peanut on 09/02/2021
 */
@Info(name = "InventoryWalk", description = "Lets you walk while in inventory", category = Category.Player)
public class InventoryWalk extends Module {

    public InventoryWalk() {}

    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        KeyBinding[] moveKeys = { mc.gameSettings.keyBindRight, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindForward, mc.gameSettings.keyBindJump, mc.gameSettings.keyBindSprint };
        if (mc.currentScreen instanceof GuiContainer) {

            KeyBinding[] arrayOfKeyBinding1;
            int j = (arrayOfKeyBinding1 = moveKeys).length;

            for (int i = 0; i < j; i++) {
                KeyBinding key = arrayOfKeyBinding1[i];
                try {
                    ReflectionUtil.pressed.set(key, Keyboard.isKeyDown(key.getKeyCode()));
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        } else if (Objects.isNull(mc.currentScreen)) {
            KeyBinding[] arrayOfKeyBinding1;
            int j = (arrayOfKeyBinding1 = moveKeys).length;
            for (int i = 0; i < j; i++) {
                KeyBinding bind = arrayOfKeyBinding1[i];
                if (!Keyboard.isKeyDown(bind.getKeyCode())) {
                    KeyBinding.setKeyBindState(bind.getKeyCode(), false);
                }
            }
        }
    }
}
