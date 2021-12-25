package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
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

    private final TimeUtils timeUtils;

    public AntiAFK() {
        super(0x00);
        timeUtils = new TimeUtils();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        mc.gameSettings.keyBindForward.pressed = true;
        if (timeUtils.hasDelayRun(500L)) {
            EntityPlayerSP entityPlayerSP = mc.thePlayer;
            entityPlayerSP.rotationYaw += 180.0f;
            TimeUtils.reset();
        }
    }

    @Override
    public void onDisable() {
        if (!GameSettings.isKeyDown(mc.gameSettings.keyBindForward)) {
            mc.gameSettings.keyBindForward.pressed = true;
        }
    }

}
