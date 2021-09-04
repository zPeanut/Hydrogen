package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.ReflectionUtil;

import java.awt.*;
import java.util.Objects;

/**
 * Created by peanut on 09/02/2021
 */
@Info(name = "InventoryWalk", description = "Lets you walk while in inventory", category = Category.Player)
public class InventoryWalk extends Module {

    public InventoryWalk() {
        super(Keyboard.KEY_NONE);
    }
    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        KeyBinding[] moveKeys = { this.mc.gameSettings.keyBindRight, this.mc.gameSettings.keyBindLeft,
                this.mc.gameSettings.keyBindBack, this.mc.gameSettings.keyBindForward,
                this.mc.gameSettings.keyBindJump, this.mc.gameSettings.keyBindSprint };
        if ((this.mc.currentScreen instanceof GuiContainer))
        {
            KeyBinding[] arrayOfKeyBinding1;
            int j = (arrayOfKeyBinding1 = moveKeys).length;
            for (int i = 0; i < j; i++)
            {
                KeyBinding key = arrayOfKeyBinding1[i];
                try {
                    ReflectionUtil.pressed.set(key, Keyboard.isKeyDown(key.getKeyCode()));
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        }
        else if (Objects.isNull(this.mc.currentScreen))
        {
            KeyBinding[] arrayOfKeyBinding1;
            int j = (arrayOfKeyBinding1 = moveKeys).length;
            for (int i = 0; i < j; i++)
            {
                KeyBinding bind = arrayOfKeyBinding1[i];
                if (!Keyboard.isKeyDown(bind.getKeyCode())) {
                    KeyBinding.setKeyBindState(bind.getKeyCode(), false);
                }
            }
        }
    }

}
