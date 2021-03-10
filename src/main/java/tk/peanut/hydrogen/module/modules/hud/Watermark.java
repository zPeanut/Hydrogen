package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.FontHelper;

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
        super(0x00, Color.white);

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
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }

            boolean background = Hydrogen.getClient().settingsManager.getSettingByName(this, "Background").isEnabled();
            boolean time = Hydrogen.getClient().settingsManager.getSettingByName("Time").isEnabled();
            boolean outline = Hydrogen.getClient().settingsManager.getSettingByName("Outline").isEnabled();
            boolean timeformat = Hydrogen.getClient().settingsManager.getSettingByName("Time Format").getValString().equals("24H");
            LocalDateTime now = LocalDateTime.now();
            String currenttime = timeformat ? timeFormat24.format(now) : timeFormat12.format(now);

            if (Hydrogen.getClient().settingsManager.getSettingByName("Watermark").getValString().equalsIgnoreCase("New")) {

                if (time) {
                    String watermarknew = Hydrogen.getClient().version + " §7(" + currenttime + ")" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : "");

                    if (background) {

                        if(outline) {
                            Gui.drawRect(0, 0, FontHelper.hfontnormal.getStringWidth(watermarknew) + 28, 23, Integer.MIN_VALUE);
                            Gui.drawRect(0, 23, FontHelper.hfontnormal.getStringWidth(watermarknew) + 29, 24, 0x99000000);
                        }

                        Gui.drawRect(FontHelper.hfontnormal.getStringWidth(watermarknew) + 28, 0, FontHelper.hfontnormal.getStringWidth(watermarknew) + 29, 23, 0x99000000);
                    }

                    GL11.glPushMatrix();
                    GL11.glScalef(2f, 2f, 2f);
                    FontHelper.hfontnormal.drawStringWithShadow("H", 2, -1, Color.white);
                    GL11.glPopMatrix();

                    FontHelper.hfontnormal.drawStringWithShadow("2", 17, 12, Color.white);
                    FontHelper.hfontnormal.drawStringWithShadow(watermarknew, 27, 5, Color.white);

                } else {
                    String watermarknew = Hydrogen.getClient().version + (Hydrogen.getClient().outdated ? " §7(Outdated)" : "");

                    if (background) {

                        if(outline) {
                            Gui.drawRect(0, 0, FontHelper.hfontnormal.getStringWidth(watermarknew) + 28, 23, Integer.MIN_VALUE);
                            Gui.drawRect(0, 23, FontHelper.hfontnormal.getStringWidth(watermarknew) + 29, 24, 0x99000000);
                        }

                        Gui.drawRect(FontHelper.hfontnormal.getStringWidth(watermarknew) + 28, 0, FontHelper.hfontnormal.getStringWidth(watermarknew) + 29, 23, 0x99000000);
                    }

                    GL11.glPushMatrix();
                    GL11.glScalef(2f, 2f, 2f);
                    FontHelper.hfontnormal.drawStringWithShadow("H", 2, -1, Color.white);
                    GL11.glPopMatrix();

                    FontHelper.hfontnormal.drawStringWithShadow("2", 17, 12, Color.white);
                    FontHelper.hfontnormal.drawStringWithShadow(watermarknew, 27, 5, Color.white);
                }

            } else {

                if (time) {
                    String watermark = String.format("%s %s §7(%s)" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : ""), Hydrogen.getClient().name, Hydrogen.getClient().version, currenttime);

                    if (background) {

                        if(outline) {
                            Gui.drawRect(0, 0, FontHelper.hfontnormal.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
                            Gui.drawRect(0, 11, FontHelper.hfontnormal.getStringWidth(watermark) + 4, 12, 0x99000000);
                        }

                        Gui.drawRect(FontHelper.hfontnormal.getStringWidth(watermark) + 4, 0, FontHelper.hfontnormal.getStringWidth(watermark) + 3, 11, 0x99000000);
                    }

                    FontHelper.hfontnormal.drawStringWithShadow(watermark, 2, 1, Color.white);
                } else {
                    String watermark = String.format("%s %s" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : ""), Hydrogen.getClient().name, Hydrogen.getClient().version);

                    if (background) {

                        if(outline) {
                            Gui.drawRect(0, 0, FontHelper.hfontnormal.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
                            Gui.drawRect(0, 11, FontHelper.hfontnormal.getStringWidth(watermark) + 4, 12, 0x99000000);
                        }

                        Gui.drawRect(FontHelper.hfontnormal.getStringWidth(watermark) + 4, 0, FontHelper.hfontnormal.getStringWidth(watermark) + 3, 11, 0x99000000);
                    }

                    FontHelper.hfontnormal.drawStringWithShadow(watermark, 2, 1, Color.white);
                }
            }
        }
    }
}
