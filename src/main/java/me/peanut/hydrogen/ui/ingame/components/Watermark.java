package me.peanut.hydrogen.ui.ingame.components;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.module.modules.gui.Hotbar;
import me.peanut.hydrogen.utils.HTTPUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "Watermark", description = "Shows the client name", category = Category.Hud)
public class Watermark extends Module {

    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");

    public Watermark() {
        java.util.ArrayList<String> watermark = new ArrayList<>();
        watermark.add("Old");
        watermark.add("New");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Watermark", this, "Old", watermark));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Background", this, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Outline", this, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Time", this, true));
    }

    @EventTarget
    public void drawWatermark(EventRender2D e) {
        if(Hydrogen.getClient().panic) {
            return;
        }
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }
            Hydrogen.getClient().hud.style.drawWatermark();
        }
    }
}