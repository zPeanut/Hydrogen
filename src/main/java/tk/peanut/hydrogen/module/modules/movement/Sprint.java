package tk.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.ReflectionUtil;

import java.awt.*;

@Info(name = "Sprint", description = "Automatically sprints when W is pressed", category = Category.Movement)
public class Sprint extends Module {
    public Sprint() {
        super(Keyboard.KEY_NONE);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        int sprintKeyBind = this.mc.gameSettings.keyBindSprint.getKeyCode();
        KeyBinding.setKeyBindState(sprintKeyBind, true);
        KeyBinding.onTick(sprintKeyBind);
    }
}
