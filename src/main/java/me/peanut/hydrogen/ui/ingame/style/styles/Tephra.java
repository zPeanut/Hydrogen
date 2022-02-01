package me.peanut.hydrogen.ui.ingame.style.styles;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.ingame.components.ArrayList;
import me.peanut.hydrogen.ui.ingame.components.Hotbar;
import me.peanut.hydrogen.ui.ingame.components.Info;
import me.peanut.hydrogen.ui.ingame.components.Watermark;
import me.peanut.hydrogen.ui.ingame.style.Style;
import me.peanut.hydrogen.utils.ColorUtil;
import me.peanut.hydrogen.utils.HTTPUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;


/**
 * Created by peanut on 20/01/2022
 */
public class Tephra implements Style {

    static final Minecraft mc = Minecraft.getMinecraft();
    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");
    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static boolean lmao;

    public static void loadSettings() {
        if(!lmao) {
            ArrayList arrayListModule = Hydrogen.getClient().moduleManager.getModule(ArrayList.class);
            Watermark watermark = Hydrogen.getClient().moduleManager.getModule(Watermark.class);

            Hydrogen.getClient().settingsManager.getSettingByName("List Color").setMode("Rainbow");
            Hydrogen.getClient().settingsManager.getSettingByName(arrayListModule, "Background").setState(true);
            Hydrogen.getClient().settingsManager.getSettingByName(watermark, "Background").setState(true);
            lmao = true;
        }
    }


    @Override
    public void drawArrayList() {
        int count = 0;
        Module arrayList = Hydrogen.getClient().moduleManager.getModule(ArrayList.class);
        float rbdelay = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Delay").getValue();
        float rbsaturation = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Saturation").getValue();
        float rbcolorcount = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Color Count").getValue();
        boolean background = Hydrogen.getClient().settingsManager.getSettingByName(arrayList, "Background").isEnabled();

        for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {
            Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);
            int mwidth = 2 + mod.getSlideMC() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()) + 4;
            int mheight = count * 11 + i + 3;
            Color rainbow = ColorUtil.getRainbowColor(rbdelay, rbsaturation, 1, (long) (count * rbcolorcount));
            Color color = Color.WHITE;
            switch (Hydrogen.getClient().settingsManager.getSettingByName("List Color").getMode()) {
                case "White":
                    color = Color.WHITE;
                    break;
                case "Category":
                    color = mod.getColor();
                    break;
                case "Rainbow":
                    color = rainbow;
                    break;
            }

            if(Hydrogen.getClient().moduleManager.getModule(Watermark.class).isEnabled()) {
                if(background) {
                    RenderUtil.rect(mod.getSlideMC() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()) + 3, 11 + i * 12, mod.getSlideMC() + 8, i * 12 + 23, Integer.MIN_VALUE);
                    Gui.drawRect(mod.getSlideMC() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()) + 3, 11 + i * 12, 0, i * 12 + 23, (color.getRGB() & 0x00FFFFFF) | 0x99000000);
                }
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(mod.getName(), background ? mwidth : mwidth - 4, mheight + 10, color.getRGB());
            } else {
                if(background) {
                    RenderUtil.rect(mod.getSlideMC() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()) + 3, i * 12, mod.getSlideMC() + 8, i * 12 + 12, Integer.MIN_VALUE);
                    RenderUtil.rect(mod.getSlideMC() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()) + 3, i * 12, 0, i * 12 + 12, (color.getRGB() & 0x00FFFFFF) | 0x99000000);
                }
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(mod.getName(), background ? mwidth : mwidth - 4, mheight, color.getRGB());
            }
            count++;
        }
    }

    @Override
    public void drawInfo() {
        String x = String.valueOf((int) mc.thePlayer.posX);
        String y = String.valueOf((int) mc.thePlayer.posY);
        String z = String.valueOf((int) mc.thePlayer.posZ);
        String coordinates = String.format("§7XYZ (%s, %s, %s)", x, y, z);
        String coordinates_x = String.format("§8X §7%s", x);
        String coordinates_y = String.format("§8Y §7%s", y);
        String coordinates_z = String.format("§8Z §7%s", z);
        boolean lines = Hydrogen.getClient().settingsManager.getSettingByName(Hydrogen.getClient().moduleManager.getModule(Info.class), "XYZ Style").getMode().equalsIgnoreCase("1-Line");


        if (Hydrogen.getClient().settingsManager.getSettingByName(Hydrogen.getClient().moduleManager.getModule(Info.class), "Alignment").getMode().equalsIgnoreCase("right")) {

            if (mc.ingameGUI.getChatGUI().getChatOpen()) {
                if (lines) {
                    mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_x) - 3, Utils.getScaledRes().getScaledHeight() - 48, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_y) - 3, Utils.getScaledRes().getScaledHeight() - 37, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_z) - 3, Utils.getScaledRes().getScaledHeight() - 26, -1);
                }

            } else {

                if (lines) {
                    mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_x) - 2, Utils.getScaledRes().getScaledHeight() - 34, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_y) - 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_z) - 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                }
            }

        } else {

            if (mc.ingameGUI.getChatGUI().getChatOpen()) {

                if (lines) {
                    mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 48, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 37, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                }

            } else {

                if (lines) {
                    mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 34, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                }
            }
        }
    }

    @Override
    public void drawPotionEffects() {
        RenderUtil.drawPotionEffectsMC();
    }

    @Override
    public void drawWatermark() {
        Watermark watermark = Hydrogen.getClient().moduleManager.getModule(Watermark.class);
        boolean background = Hydrogen.getClient().settingsManager.getSettingByName(watermark, "Background").isEnabled();
        boolean time = Hydrogen.getClient().settingsManager.getSettingByName("Time").isEnabled();
        boolean timeformat = Hydrogen.getClient().settingsManager.getSettingByName("Time Format").getMode().equals("24H");
        LocalDateTime now = LocalDateTime.now();
        String currenttime = timeformat ? timeFormat24.format(now) : timeFormat12.format(now);

        if(!Hydrogen.getClient().isStableBuild && !(Hydrogen.getClient().moduleManager.getModule(Hotbar.class).isEnabled() || Hydrogen.getClient().settingsManager.getSettingByName("Alignment").getMode().equalsIgnoreCase("Left"))) {
            mc.fontRendererObj.drawStringWithShadow("§7Indev Build", 2, Utils.getScaledRes().getScaledHeight() - (mc.ingameGUI.getChatGUI().getChatOpen() ? 38 : 24), -1);
            mc.fontRendererObj.drawStringWithShadow(String.format("§7Latest Commit: %s | %s", HTTPUtil.commitDate, HTTPUtil.commitTime), 2, Utils.getScaledRes().getScaledHeight() - (mc.ingameGUI.getChatGUI().getChatOpen() ? 26 : 12), -1);
        }

        String watermarkString = String.format("%s v%s | %s FPS %s %s", Hydrogen.name, Hydrogen.version, Minecraft.getDebugFPS(), time ? "§7(" + currenttime + ")" : "", Hydrogen.getClient().outdated ? "§7(Outdated)" : "");

        if(background) {
            RenderUtil.rect(0, 0, 3, 11, 0x88ffffff);
            RenderUtil.rect(3, 0, Minecraft.getMinecraft().fontRendererObj.getStringWidth(watermarkString) + 3, 11, Integer.MIN_VALUE);
        }
        mc.fontRendererObj.drawStringWithShadow(watermarkString, background ? 6 : 2, 2, -1);

    }

    @Override
    public void drawHotbar() {
        EntityPlayer entityplayer = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();

        float needX = ((float) Utils.getScaledRes().getScaledWidth() / 2 - 91 + entityplayer.inventory.currentItem * 20);
        float steps = 10f;

        Module mod = Hydrogen.getClient().moduleManager.getModulebyName("Hotbar");
        boolean fps = Hydrogen.getClient().settingsManager.getSettingByName(mod, "FPS").isEnabled();
        boolean coord = Hydrogen.getClient().settingsManager.getSettingByName(mod, "Coordinates").isEnabled();
        boolean tdate = Hydrogen.getClient().settingsManager.getSettingByName(mod, "Time / Date").isEnabled();

        Utils.addSlide(needX, steps);

        boolean timeformat = Hydrogen.getClient().settingsManager.getSettingByName("Time Format").getMode().equals("24H");
        LocalDateTime now = LocalDateTime.now();
        String date = dateFormat.format(now);
        String time = timeformat ? timeFormat24.format(now) : timeFormat12.format(now);
        String fps1 = String.format("FPS §7%s", Minecraft.getDebugFPS());

        String x = String.valueOf((int) mc.thePlayer.posX);
        String y = String.valueOf((int) mc.thePlayer.posY);
        String z = String.valueOf((int) mc.thePlayer.posZ);

        String coordinates = String.format("X: §7%s §fY: §7%s §fZ: §7%s", x, y, z);

        if (tdate) {
            mc.fontRendererObj.drawStringWithShadow(date, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(date) - 10, Utils.getScaledRes().getScaledHeight() - 10, -1);
            mc.fontRendererObj.drawStringWithShadow(time, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(time) - 10, Utils.getScaledRes().getScaledHeight() - 21, -1);
        }

        if (coord) {
            mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 10, -1);
        }

        if (fps) {
            mc.fontRendererObj.drawStringWithShadow(fps1, 2, coord ? Utils.getScaledRes().getScaledHeight() - 21 : Utils.getScaledRes().getScaledHeight() - 10, -1);
        }
    }
}
