package me.peanut.hydrogen.ui.ingame.style.styles;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.modules.gui.Hotbar;
import me.peanut.hydrogen.ui.ingame.components.ArrayList;
import me.peanut.hydrogen.ui.ingame.components.Info;
import me.peanut.hydrogen.ui.ingame.style.Style;
import me.peanut.hydrogen.utils.ColorUtil;
import me.peanut.hydrogen.utils.HTTPUtil;
import me.peanut.hydrogen.utils.ReflectionUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * Created by peanut on 18/01/2022
 */
public class Classic implements Style {

    public static Minecraft mc = Minecraft.getMinecraft();
    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");

    public Classic() {

        Hydrogen.getClient().moduleManager.getModules().sort((m1, m2) -> {
            if (mc.fontRendererObj.getStringWidth(m1.getName()) > mc.fontRendererObj.getStringWidth(m2.getName()))
                return -1;
            if (mc.fontRendererObj.getStringWidth(m1.getName()) < mc.fontRendererObj.getStringWidth(m2.getName()))
                return 1;
            return 0;
        });
    }

    public static void classicArrayThread() {
        new Thread(() -> {
            while (true) {
                try {
                    if (!ReflectionUtil.running.getBoolean(mc)) {
                        break;
                    }

                    Thread.sleep(5L);

                    Iterator<Module> iterator = Hydrogen.getClient().moduleManager.getModules().iterator();

                    while (iterator.hasNext()) {
                        Module mod = iterator.next();
                        if (mod.isEnabled()) {
                            if (mod.getSlideMC() < Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod.getName())) {
                                mod.setSlideMC(mod.getSlideMC() + 1);
                            }

                        } else if (mod.getSlideMC() != 0 && !mod.isEnabled()) {
                            if (mod.getSlideMC() > 0) {
                                mod.setSlideMC(mod.getSlideMC() - 1);
                            }

                        }
                    }
                } catch (Exception ignored) {
                }
            }
        },"smooth array minecraft font").start();
    }

    @Override
    public void drawArrayList() {
        int count = 0;
        float rbdelay = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Delay").getValue();
        float rbsaturation = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Saturation").getValue();
        float rbcolorcount = (float) Hydrogen.getClient().settingsManager.getSettingByName("Color Count").getValue();

        for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
            Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);
            int mheight = count * 11 + i + 2;
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

            if(Hydrogen.getClient().settingsManager.getSettingByName("Background").isEnabled()) {
                Gui.drawRect(sr.getScaledWidth() - mod.getSlideMC() - 6, 11 + i * 12, sr.getScaledWidth(), i * 12 - 1, Integer.MIN_VALUE);
            }

            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlideMC() - 3, mheight, color.getRGB());
            count++;
        }
    }

    @Override
    public void drawInfo() {
        String x = String.valueOf((int) mc.thePlayer.posX);
        String y = String.valueOf((int) mc.thePlayer.posY);
        String z = String.valueOf((int) mc.thePlayer.posZ);
        String coordinates = String.format("XYZ §7(%s, %s, %s)", x, y, z);
        String coordinates_x = String.format("X §7%s", x);
        String coordinates_y = String.format("Y §7%s", y);
        String coordinates_z = String.format("Z §7%s", z);

        String fps = String.format("FPS §7%s", Minecraft.getDebugFPS());
        boolean lines = Hydrogen.getClient().settingsManager.getSettingByName(Hydrogen.getClient().moduleManager.getModule(Info.class), "XYZ Style").getMode().equalsIgnoreCase("1-Line");


        if (Hydrogen.getClient().settingsManager.getSettingByName(Hydrogen.getClient().moduleManager.getModule(Info.class), "Alignment").getMode().equalsIgnoreCase("right")) {

            if (mc.ingameGUI.getChatGUI().getChatOpen()) {
                if (lines) {
                    mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 37, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 59, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_x) - 3, Utils.getScaledRes().getScaledHeight() - 48, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_y) - 3, Utils.getScaledRes().getScaledHeight() - 37, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_z) - 3, Utils.getScaledRes().getScaledHeight() - 26, -1);
                }

            } else {

                if (lines) {
                    mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 23, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 45, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_x) - 2, Utils.getScaledRes().getScaledHeight() - 34, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_y) - 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_z) - 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                }
            }

        } else {

            if (mc.ingameGUI.getChatGUI().getChatOpen()) {

                if (lines) {
                    mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                    mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 37, -1);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 59, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 48, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 37, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                }

            } else {

                if (lines) {
                    mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                    mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
                } else {
                    mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 45, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 34, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
                    mc.fontRendererObj.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                }
            }
        }
    }

    @Override
    public void drawPotionEffects() {
        if(Hydrogen.getClient().panic) {
            return;
        }
        int offset = 0;
        for (Object var4 : Minecraft.getMinecraft().thePlayer.getActivePotionEffects()) {
            int posY = 11 * offset;
            PotionEffect effect = (PotionEffect) var4;
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            String name = I18n.format(potion.getName());
            switch (effect.getAmplifier()) {
                case 0:
                    name += " I";
                    break;
                case 1:
                    name += " II";
                    break;
                case 2:
                    name += " III";
                    break;
                case 3:
                    name += " IV";
                    break;
                case 4:
                    name += " V";
                    break;
                case 5:
                    name += " VI";
                    break;
                case 6:
                    name += " VII";
                    break;
                case 7:
                    name += " VIII";
                    break;
                case 8:
                    name += " IX";
                    break;
                case 9:
                    name += " X";
                    break;
                default:
                    name += " X+";
                    break;
            }
            name += " §7(§f" + Potion.getDurationString(effect) + "§7)";
            int color = Integer.MIN_VALUE;
            switch (effect.getEffectName()) {
                case "potion.weither":
                    color = -16777246;
                    break;
                case "potion.weakness":
                    color = -9864951;
                    break;
                case "potion.waterBreathing":
                    color = -16758065;
                    break;
                case "potion.saturation":
                    color = -11159217;
                    break;
                case "potion.resistance":
                    color = -5655199;
                    break;
                case "potion.regeneration":
                    color = -1145130;
                    break;
                case "potion.poison":
                    color = -14553374;
                    break;
                case "potion.nightVision":
                    color = -6735204;
                    break;
                case "potion.moveSpeed":
                    color = -7875870;
                    break;
                case "potion.moveSlowdown":
                    color = -16751493;
                    break;
                case "potion.jump":
                    color = -5375161;
                    break;
                case "potion.invisibility":
                    color = -9405272;
                    break;
                case "potion.hunger":
                    color = -16754448;
                    break;
                case "potion.heal":
                    color = -65556;
                    break;
                case "potion.harm":
                    color = -3735043;
                    break;
                case "potion.fireResistance":
                    color = -29656;
                    break;
                case "potion.healthBoost":
                    color = -40151;
                    break;
                case "potion.digSpeed":
                    color = -989556;
                    break;
                case "potion.digSlowdown":
                    color = -5655199;
                    break;
                case "potion.damageBoost":
                    color = -7665712;
                    break;
                case "potion.confusion":
                    color = -5195482;
                    break;
                case "potion.blindness":
                    color = -8355712;
                    break;
                case "potion.absorption":
                    color = -23256;
                    break;
            }
            Info info = new Info();
            boolean infoIsRight = Hydrogen.getClient().settingsManager.getSettingByName(info, "Alignment").getMode().equalsIgnoreCase("Right");
            boolean infoEnabled = Hydrogen.getClient().moduleManager.getModule(Info.class).isEnabled();
            boolean chatOpen = mc.ingameGUI.getChatGUI().getChatOpen();
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) - 1, Utils.getScaledRes().getScaledHeight() - posY - 12 - (infoIsRight && infoEnabled ? 22 : 0) - (chatOpen ? 14 : 0), color);
            ++offset;
        }
    }

    @Override
    public void drawWatermark() {
        ArrayList arrayList = new ArrayList();
        boolean background = Hydrogen.getClient().settingsManager.getSettingByName(arrayList, "Background").isEnabled();
        boolean time = Hydrogen.getClient().settingsManager.getSettingByName("Time").isEnabled();
        boolean outline = Hydrogen.getClient().settingsManager.getSettingByName(arrayList, "Outline").isEnabled();
        boolean timeformat = Hydrogen.getClient().settingsManager.getSettingByName("Time Format").getMode().equals("24H");
        LocalDateTime now = LocalDateTime.now();
        String currenttime = timeformat ? timeFormat24.format(now) : timeFormat12.format(now);

        if(!Hydrogen.getClient().isStableBuild && !(Hydrogen.getClient().moduleManager.getModule(Hotbar.class).isEnabled() || Hydrogen.getClient().settingsManager.getSettingByName("Alignment").getMode().equalsIgnoreCase("Left"))) {
            mc.fontRendererObj.drawStringWithShadow(String.format("§7Latest Commit: %s | %s", HTTPUtil.commitDate, HTTPUtil.commitTime), 2, Utils.getScaledRes().getScaledHeight() - 10, -1);
        }

        if (time) {

            String watermark = String.format("%s %s §7(%s)" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : ""), Hydrogen.name, Hydrogen.version, currenttime);

            if (background) {
                Gui.drawRect(0, 0, mc.fontRendererObj.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
            }

            if(outline) {
                Gui.drawRect(mc.fontRendererObj.getStringWidth(watermark) + 4, 0, mc.fontRendererObj.getStringWidth(watermark) + 3, 11, 0x99000000);
                Gui.drawRect(0, 11, mc.fontRendererObj.getStringWidth(watermark) + 4, 12, 0x99000000);
            }


            mc.fontRendererObj.drawStringWithShadow(watermark, 2, 2, -1);

        } else {

            String watermark_no_time = String.format("%s %s" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : ""), Hydrogen.name, Hydrogen.version);

            if(outline) {
                Gui.drawRect(0, 11, mc.fontRendererObj.getStringWidth(watermark_no_time) + 4, 12, 0x99000000);
                Gui.drawRect(mc.fontRendererObj.getStringWidth(watermark_no_time) + 4, 0, mc.fontRendererObj.getStringWidth(watermark_no_time) + 3, 11, 0x99000000);
            }

            if (background) {
                Gui.drawRect(0, 0, mc.fontRendererObj.getStringWidth(watermark_no_time) + 3, 11, Integer.MIN_VALUE);
            }

            mc.fontRendererObj.drawStringWithShadow(watermark_no_time, 2, 2, -1);
        }
    }
}
