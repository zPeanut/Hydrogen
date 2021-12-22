package me.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;

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
