package me.peanut.hydrogen.ui.ingame.components;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.Info;
import net.minecraft.client.Minecraft;


import java.awt.*;

/**
 * Created by peanut on 15/01/2022
 */
@Info(name = "PotionEffects", description = "Shows active potion effects", category = Category.Hud)
public class PotionEffects extends Module {

    @EventTarget
    public void onRender(EventRender2D e) {
        if(Hydrogen.getClient().panic || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            Hydrogen.getClient().hud.style.drawPotionEffects();
        }
    }

}
