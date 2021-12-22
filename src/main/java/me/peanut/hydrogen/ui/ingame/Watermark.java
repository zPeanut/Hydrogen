package me.peanut.hydrogen.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.FontHelper;
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
@Info(name = "Watermark", description = "Shows the client name", category = Category.Gui)
public class Watermark extends Module {

    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");

    public Watermark() {
        super(0x00);

        java.util.ArrayList<String> watermark = new ArrayList<>();
        watermark.add("Old");
        watermark.add("New");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Watermark", this, "New", watermark));
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

                    String watermarknew = Hydrogen.getClient().version + " §7(" + currenttime + ")" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : "");

                    if(outline) {
                        Gui.drawRect(ttf ? FontHelper.sf_l.getStringWidth(watermarknew) + 24 : mc.fontRendererObj.getStringWidth(watermarknew) + 28, 0, ttf ? FontHelper.sf_l.getStringWidth(watermarknew) + 25 : mc.fontRendererObj.getStringWidth(watermarknew) + 29, 23, 0x99000000);
                        Gui.drawRect(0, 23, ttf ? FontHelper.sf_l.getStringWidth(watermarknew) + 25 : mc.fontRendererObj.getStringWidth(watermarknew) + 29, 24, 0x99000000);
                    }

                    if (background) {
                        Gui.drawRect(0, 0, ttf ? FontHelper.sf_l.getStringWidth(watermarknew) + 24 : mc.fontRendererObj.getStringWidth(watermarknew) + 28, 23, Integer.MIN_VALUE);
                    }

                    if(ttf) {
                        FontHelper.sf_l2.drawStringWithShadow("h", 2, -1, Color.white);
                        FontHelper.sf_l.drawStringWithShadow("2", 13, 12, Color.white);
                        FontHelper.sf_l.drawStringWithShadow(watermarknew, 22, 5, Color.white);
                    } else {

                        mc.fontRendererObj.drawStringWithShadow("2", 17, 13, -1);
                        mc.fontRendererObj.drawStringWithShadow(watermarknew, 26, 7, -1);
                        GL11.glPushMatrix();
                        GL11.glScalef(2, 2, 2);
                        mc.fontRendererObj.drawStringWithShadow("h", 2, 1, -1);
                        GL11.glPopMatrix();
                    }

                } else {
                    String watermarknew_notime = Hydrogen.getClient().version + (Hydrogen.getClient().outdated ? " §7(Outdated)" : "");

                    if(outline) {
                        Gui.drawRect(ttf ? FontHelper.sf_l.getStringWidth(watermarknew_notime) + 28 : mc.fontRendererObj.getStringWidth(watermarknew_notime) + 28, 0, ttf ? FontHelper.sf_l.getStringWidth(watermarknew_notime) + 29 : mc.fontRendererObj.getStringWidth(watermarknew_notime) + 29, 23, 0x99000000);
                        Gui.drawRect(0, 23, ttf ? FontHelper.sf_l.getStringWidth(watermarknew_notime) + 29 : mc.fontRendererObj.getStringWidth(watermarknew_notime) + 29, 24, 0x99000000);
                    }

                    if (background) {
                        Gui.drawRect(0, 0, ttf ? FontHelper.sf_l.getStringWidth(watermarknew_notime) + 28 : mc.fontRendererObj.getStringWidth(watermarknew_notime) + 28, 23, Integer.MIN_VALUE);
                    }

                    if(ttf) {
                        FontHelper.sf_l2.drawStringWithShadow("h", 2, -1, Color.white);
                        FontHelper.sf_l.drawStringWithShadow("2", 13, 12, Color.white);
                        FontHelper.sf_l.drawStringWithShadow(watermarknew_notime, 22, 5, Color.white);
                    } else {

                        mc.fontRendererObj.drawStringWithShadow("2", 17, 13, -1);
                        mc.fontRendererObj.drawStringWithShadow(watermarknew_notime, 26, 7, -1);
                        GL11.glPushMatrix();
                        GL11.glScalef(2, 2, 2);
                        mc.fontRendererObj.drawStringWithShadow("h", 2, 1, -1);
                        GL11.glPopMatrix();
                    }
                }

            } else {

                if (time) {

                    String watermark = String.format("%s %s §7(%s)" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : ""), Hydrogen.getClient().name, Hydrogen.getClient().version, currenttime);

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

                    String watermark_notime = String.format("%s %s" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : ""), Hydrogen.getClient().name, Hydrogen.getClient().version);

                    if(outline) {
                        Gui.drawRect(0, 11, ttf ? FontHelper.sf_l.getStringWidth(watermark_notime) + 4 : mc.fontRendererObj.getStringWidth(watermark_notime) + 4, 12, 0x99000000);
                        Gui.drawRect(ttf ? FontHelper.sf_l.getStringWidth(watermark_notime) + 4 : mc.fontRendererObj.getStringWidth(watermark_notime) + 4, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark_notime) + 3 : mc.fontRendererObj.getStringWidth(watermark_notime) + 3, 11, 0x99000000);
                    }

                    if (background) {
                        Gui.drawRect(0, 0, ttf ? FontHelper.sf_l.getStringWidth(watermark_notime) + 3 : mc.fontRendererObj.getStringWidth(watermark_notime) + 3, 11, Integer.MIN_VALUE);
                    }

                    if(ttf) {
                        FontHelper.sf_l.drawStringWithShadow(watermark_notime, 2, 0, Color.white);
                    } else {
                        mc.fontRendererObj.drawStringWithShadow(watermark_notime, 2, 2, -1);
                    }
                }
            }
        }
    }
}
