package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventPreMotion;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.TimeUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;

import java.sql.Time;

/**
 * Created by peanut on 20/02/2021
 */
@Info(name = "AntiAFK", description = "Prevents you from getting kicked", category = Category.Player)
public class AntiAFK extends Module {

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(mc.thePlayer.onGround && this.isEnabled()) {
            mc.thePlayer.jump();
        }
    }
}
