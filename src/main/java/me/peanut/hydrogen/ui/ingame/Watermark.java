package me.peanut.hydrogen.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.FontHelper;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.World;
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
@Info(name = "Watermark", description = "Shows the client name", category = Category.Gui)
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

            boolean background = Hydrogen.getClient().settingsManager.getSettingByName(this, "Background").isEnabled();
            boolean time = Hydrogen.getClient().settingsManager.getSettingByName("Time").isEnabled();
            boolean outline = Hydrogen.getClient().settingsManager.getSettingByName(this, "Outline").isEnabled();
            boolean timeformat = Hydrogen.getClient().settingsManager.getSettingByName("Time Format").getValString().equals("24H");
            boolean ttf = Hydrogen.getClient().settingsManager.getSettingByName("Font").getValString().equalsIgnoreCase("TTF");
            LocalDateTime now = LocalDateTime.now();
            String currenttime = timeformat ? timeFormat24.format(now) : timeFormat12.format(now);

            if (Hydrogen.getClient().settingsManager.getSettingByName("Watermark").getValString().equalsIgnoreCase("New")) {

                if (time) {

                    String watermark = Hydrogen.version + " §7(" + currenttime + ")" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : "");

                    if(outline) {
                        Gui.drawRect(ttf ? FontHelper.sf_l.getStringWidth(watermark) + 24 : mc.fontRendererObj.getStringWidth(watermark) + 28, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark) + 25 : mc.fontRendererObj.getStringWidth(watermark) + 29, 23, 0x99000000);
                        Gui.drawRect(0, 23, ttf ? FontHelper.sf_l.getStringWidth(watermark) + 25 : mc.fontRendererObj.getStringWidth(watermark) + 29, 24, 0x99000000);
                    }

                    if (background) {
                        Gui.drawRect(0, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark) + 24 : mc.fontRendererObj.getStringWidth(watermark) + 28, 23, Integer.MIN_VALUE);
                    }

                    if(ttf) {
                        FontHelper.sf_l2.drawStringWithShadow("h", 2, -1, Color.white);
                        FontHelper.sf_l.drawStringWithShadow("2", 13, 12, Color.white);
                        FontHelper.sf_l.drawStringWithShadow(watermark, 22, 5, Color.white);
                    } else {
                        mc.fontRendererObj.drawStringWithShadow("2", 17, 13, -1);
                        mc.fontRendererObj.drawStringWithShadow(watermark, 26, 7, -1);
                        GL11.glPushMatrix();
                        GL11.glScalef(2, 2, 2);
                        mc.fontRendererObj.drawStringWithShadow("h", 2, 1, -1);
                        GL11.glPopMatrix();
                    }

                } else {

                    String watermark_no_time = Hydrogen.version + (Hydrogen.getClient().outdated ? " §7(Outdated)" : "");

                    if(outline) {
                        Gui.drawRect(ttf ? FontHelper.sf_l.getStringWidth(watermark_no_time) + 28 : mc.fontRendererObj.getStringWidth(watermark_no_time) + 28, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark_no_time) + 29 : mc.fontRendererObj.getStringWidth(watermark_no_time) + 29, 23, 0x99000000);
                        Gui.drawRect(0, 23, ttf ? FontHelper.sf_l.getStringWidth(watermark_no_time) + 29 : mc.fontRendererObj.getStringWidth(watermark_no_time) + 29, 24, 0x99000000);
                    }

                    if (background) {
                        Gui.drawRect(0, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark_no_time) + 28 : mc.fontRendererObj.getStringWidth(watermark_no_time) + 28, 23, Integer.MIN_VALUE);
                    }

                    if(ttf) {
                        FontHelper.sf_l2.drawStringWithShadow("h", 2, -1, Color.white);
                        FontHelper.sf_l.drawStringWithShadow("2", 13, 12, Color.white);
                        FontHelper.sf_l.drawStringWithShadow(watermark_no_time, 22, 5, Color.white);
                    } else {
                        mc.fontRendererObj.drawStringWithShadow("2", 17, 13, -1);
                        mc.fontRendererObj.drawStringWithShadow(watermark_no_time, 26, 7, -1);
                        GL11.glPushMatrix();
                        GL11.glScalef(2, 2, 2);
                        mc.fontRendererObj.drawStringWithShadow("h", 2, 1, -1);
                        GL11.glPopMatrix();
                    }
                }

            }

            if (Hydrogen.getClient().settingsManager.getSettingByName("Watermark").getValString().equalsIgnoreCase("Old")) {

                if (time) {

                    String watermark = String.format("%s %s §7(%s)" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : ""), Hydrogen.name, Hydrogen.version, currenttime);

                    if(outline) {
                        Gui.drawRect(ttf ? FontHelper.sf_l.getStringWidth(watermark) + 4 : mc.fontRendererObj.getStringWidth(watermark) + 4, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark) + 3 : mc.fontRendererObj.getStringWidth(watermark) + 3, 11, 0x99000000);
                        Gui.drawRect(0, 11, ttf ? FontHelper.sf_l.getStringWidth(watermark) + 4 : mc.fontRendererObj.getStringWidth(watermark) + 4, 12, 0x99000000);
                    }

                    if (background) {
                        Gui.drawRect(0, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark) + 3 : mc.fontRendererObj.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
                    }

                    if(ttf) {
                        FontHelper.sf_l.drawStringWithShadow(watermark, 2, 0, Color.white);
                    } else {
                        mc.fontRendererObj.drawStringWithShadow(watermark, 2, 2, -1);
                    }

                } else {

                    String watermark_no_time = String.format("%s %s" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : ""), Hydrogen.name, Hydrogen.version);

                    if(outline) {
                        Gui.drawRect(0, 11, ttf ? FontHelper.sf_l.getStringWidth(watermark_no_time) + 4 : mc.fontRendererObj.getStringWidth(watermark_no_time) + 4, 12, 0x99000000);
                        Gui.drawRect(ttf ? FontHelper.sf_l.getStringWidth(watermark_no_time) + 4 : mc.fontRendererObj.getStringWidth(watermark_no_time) + 4, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark_no_time) + 3 : mc.fontRendererObj.getStringWidth(watermark_no_time) + 3, 11, 0x99000000);
                    }

                    if (background) {
                        Gui.drawRect(0, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark_no_time) + 3 : mc.fontRendererObj.getStringWidth(watermark_no_time) + 3, 11, Integer.MIN_VALUE);
                    }

                    if(ttf) {
                        FontHelper.sf_l.drawStringWithShadow(watermark_no_time, 2, 0, Color.white);
                    } else {
                        mc.fontRendererObj.drawStringWithShadow(watermark_no_time, 2, 2, -1);
                    }
                }
            }
        }
    }
}
