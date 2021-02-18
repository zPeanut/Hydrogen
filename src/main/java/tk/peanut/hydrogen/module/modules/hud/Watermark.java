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
import tk.peanut.hydrogen.utils.FontHelper;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "Watermark", description = "Shows the client name", color = -1, category = Category.Gui)
public class Watermark extends Module {

    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");

    public Watermark() {
        super(0x00);
    }

    @EventTarget
    public static void drawWatermark(EventRender2D e) {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
            return;
        LocalDateTime now = LocalDateTime.now();
        String currenttime = timeFormat12.format(now);

        String watermark = String.format("%s %s §7(%s)", Hydrogen.getClient().name, Hydrogen.getClient().version, currenttime);


        if (Hydrogen.getClient().settingsManager.getSettingByName("Watermark").getValString().equalsIgnoreCase("New")) {

            String watermarknew = Hydrogen.getClient().version + " §7(" + currenttime + ")";

            if (Hydrogen.getClient().settingsManager.getSettingByName("Background").isEnabled()) {
                Gui.drawRect(0, 0, FontHelper.hfontbold.getStringWidth(watermarknew) + 28, 23, Integer.MIN_VALUE);
                Gui.drawRect(0, 23, FontHelper.hfontbold.getStringWidth(watermarknew) + 29, 24, 0x99000000);
                Gui.drawRect(FontHelper.hfontbold.getStringWidth(watermarknew) + 28, 0, FontHelper.hfontbold.getStringWidth(watermarknew) + 29, 23, 0x99000000);
            }

            GL11.glPushMatrix();
            GL11.glScalef(2f, 2f, 2f);
            FontHelper.hfontbold.drawStringWithShadow("H", 2, -1, Color.white);
            GL11.glPopMatrix();

            FontHelper.hfontbold.drawStringWithShadow("2", 17, 12, Color.white);
            FontHelper.hfontbold.drawStringWithShadow(watermarknew, 27, 5, Color.white);
        } else {

            if (Hydrogen.getClient().settingsManager.getSettingByName("Background").isEnabled()) {
                Gui.drawRect(0, 0, FontHelper.hfontbold.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
                Gui.drawRect(0, 11, FontHelper.hfontbold.getStringWidth(watermark) + 4, 12, 0x99000000);
                Gui.drawRect(FontHelper.hfontbold.getStringWidth(watermark) + 4, 0, FontHelper.hfontbold.getStringWidth(watermark) + 3, 11, 0x99000000);
            }
            //FontHelper.hfontbold.drawStringWithShadow(watermark, 2, 2, -1);
            FontHelper.hfontbold.drawStringWithShadow(watermark, 2, 2, Color.white);
            FontHelper.hfontbold.drawStringWithShadow(watermark, 2, 12, -1);


            //TODO: FontHelper.cfArrayList.drawString(watermark, 2, 12, -1);

        }

    }
}
