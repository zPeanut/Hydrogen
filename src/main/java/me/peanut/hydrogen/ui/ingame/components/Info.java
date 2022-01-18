package me.peanut.hydrogen.ui.ingame.components;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by peanut on 18/02/2021
 */
@me.peanut.hydrogen.module.Info(name = "Info", description = "Shows FPS and Coordinates", category = Category.Hud)
public class Info extends Module {

    public Info() {
        java.util.ArrayList<String> alignment = new ArrayList<>();
        alignment.add("Left");
        alignment.add("Right");

        java.util.ArrayList<String> c_alignment = new ArrayList<>();
        c_alignment.add("1-Line");
        c_alignment.add("3-Line");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Alignment", this, "Right", alignment));
        addSetting(new Setting("XYZ Style", this, "1-Line", c_alignment));
    }

    @EventTarget
    public void drawInfo(EventRender2D e) {
        if (Hydrogen.getClient().panic) {
            return;
        }

        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled() && !Hydrogen.getClient().moduleManager.getModulebyName("Hotbar").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }

            Hydrogen.getClient().hud.style.drawInfo();
        }
    }
}
