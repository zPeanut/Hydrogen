package me.peanut.hydrogen.module.impl.movement;

//
// Created by peanut on 07.01.2024
//


import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.event.EventUpdate;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.ModuleInfo;
import me.peanut.hydrogen.module.Type;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Sprint", desc = "Automatically sprints for you", type = Type.MOVEMENT, bind = Keyboard.KEY_I)
public class Sprint extends Module {

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        if((mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0) && mc.thePlayer.getFoodStats().getFoodLevel() > 6 && !mc.thePlayer.isSneaking()) {
            mc.thePlayer.setSprinting(true);
        }
        System.out.printf("Enabled!");
    }
}
