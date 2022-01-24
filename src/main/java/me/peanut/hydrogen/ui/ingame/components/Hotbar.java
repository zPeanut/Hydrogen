package me.peanut.hydrogen.ui.ingame.components;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.BlurUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import me.peanut.hydrogen.settings.Setting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "Hotbar", category = Category.Hud, description = "Shows an advanced hotbar with information")
public class Hotbar extends Module {

    public Hotbar() {
        addSetting(new Setting("FPS", this, true));
        addSetting(new Setting("Coordinates", this, true));
        addSetting(new Setting("Time / Date", this, true));
    }

    @EventTarget
    public void onRender(EventRender2D e) {
        if(Hydrogen.getClient().panic || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }

        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            Hydrogen.getClient().hud.style.drawHotbar();
        }
    }
}
