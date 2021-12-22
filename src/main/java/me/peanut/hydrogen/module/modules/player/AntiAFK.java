package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 20/02/2021
 */
@Info(name = "AntiAFK", description = "Prevents you from getting kicked", category = Category.Player)
public class AntiAFK extends Module {
    public AntiAFK() {
        super(0x00);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(this.isEnabled()) {
            mc.gameSettings.keyBindJump.pressed = true;
        }
    }

}
