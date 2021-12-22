package me.peanut.hydrogen.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.FontHelper;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "Hotbar", category = Category.Gui, description = "Shows an advanced hotbar with information")
public class Hotbar extends Module {

    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");

    public Hotbar() {
        super(0x00);

        Hydrogen.getClient().settingsManager.rSetting(new Setting("FPS", this, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Coordinates", this, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Time / Date", this, true));

    }

    @EventTarget
    public void drawHotbar(EventRender2D e) {
        if(Hydrogen.getClient().panic) {
            return;
        }
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {

            Utils.drawRect(0, Utils.getScaledRes().getScaledHeight() - 23, Utils.getScaledRes().getScaledWidth() - 7, Utils.getScaledRes().getScaledHeight(), 0x44000000);

            EntityPlayer entityplayer = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();

            float needX = (Utils.getScaledRes().getScaledWidth() / 2 - 91 + entityplayer.inventory.currentItem * 20);
            float steps = 10f;

            Module mod = Hydrogen.getClient().moduleManager.getModulebyName("Hotbar");
            boolean fps = Hydrogen.getClient().settingsManager.getSettingByName(mod, "FPS").isEnabled();
            boolean coord = Hydrogen.getClient().settingsManager.getSettingByName(mod, "Coordinates").isEnabled();
            boolean tdate = Hydrogen.getClient().settingsManager.getSettingByName(mod, "Time / Date").isEnabled();
            boolean ttf = Hydrogen.getClient().settingsManager.getSettingByName("Font").getValString().equalsIgnoreCase("TTF");

            Utils.addSlide(needX, steps);

            boolean timeformat = Hydrogen.getClient().settingsManager.getSettingByName("Time Format").getValString().equals("24H");
            LocalDateTime now = LocalDateTime.now();
            String date = dateFormat.format(now);
            String time = timeformat ? timeFormat24.format(now) : timeFormat12.format(now);
            String fps1 = String.format("FPS §7%s", mc.getDebugFPS());

            String x = String.valueOf((int) mc.thePlayer.posX);
            String y = String.valueOf((int) mc.thePlayer.posY);
            String z = String.valueOf((int) mc.thePlayer.posZ);

            String coordinates = String.format("X: §7%s §fY: §7%s §fZ: §7%s", x, y, z);

            if (tdate) {
                if(ttf) {
                    FontHelper.sf_l.drawStringWithShadow(date, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(date) - 9, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(time, timeformat ? Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(time) - 10 : Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(time) - 10, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(date, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(date) - 16, Utils.getScaledRes().getScaledHeight() - 10, -1);
                    mc.fontRendererObj.drawStringWithShadow(time, timeformat ? Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(time) - 15 : Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(time) - 15, Utils.getScaledRes().getScaledHeight() - 21, -1);
                }
            }

            if (coord) {
                if(ttf) {
                    FontHelper.sf_l.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 10, -1);
                }
            }

            if (fps) {
                if(ttf) {
                    FontHelper.sf_l.drawStringWithShadow(fps1, 2, coord ? Utils.getScaledRes().getScaledHeight() - 23 : Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(fps1, 2, coord ? Utils.getScaledRes().getScaledHeight() - 21 : Utils.getScaledRes().getScaledHeight() - 10, -1);
                }
            }

        }
    }

    public static double x = 400.0D;

    public static void addUntil(double needX, double steps) {
        if (x != needX) {
            if (x < needX)
                if (x <= needX - steps) {
                    x += steps;
                } else if (x > needX - steps) {
                    x = needX;
                }
            if (x > needX)
                if (x >= needX + steps) {
                    x -= steps;
                } else if (x < needX + steps) {
                    x = needX;
                }
        }
    }

}
