package me.peanut.hydrogen.ui.ingame.style.styles;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.ingame.components.Hotbar;
import me.peanut.hydrogen.ui.ingame.components.ArrayList;
import me.peanut.hydrogen.ui.ingame.components.Info;
import me.peanut.hydrogen.ui.ingame.components.Watermark;
import me.peanut.hydrogen.ui.ingame.style.Style;
import me.peanut.hydrogen.utils.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by peanut on 18/01/2022
 */
public class New implements Style {

    public static boolean lmao;

    static final Minecraft mc = Minecraft.getMinecraft();
    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");
    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void loadSettings() {
        if(!lmao) {
            me.peanut.hydrogen.ui.ingame.components.ArrayList arrayListModule = Hydrogen.getClient().moduleManager.getModule(me.peanut.hydrogen.ui.ingame.components.ArrayList.class);
            Watermark watermark = Hydrogen.getClient().moduleManager.getModule(Watermark.class);

            Hydrogen.getClient().settingsManager.getSettingByName("List Color").setMode("Rainbow");
            Hydrogen.getClient().settingsManager.getSettingByName(arrayListModule, "Background").setState(true);
            Hydrogen.getClient().settingsManager.getSettingByName(watermark, "Background").setState(true);
            Hydrogen.getClient().settingsManager.getSettingByName("Outline").setState(true);
            lmao = true;
        }
    }

    public static void newArrayThread() {
        new Thread(() -> {
            while (true) {
                try {
                    if (!ReflectionUtil.running.getBoolean(mc)) {
                        break;
                    }
                    long listDelay = (long) Hydrogen.getClient().settingsManager.getSettingByName("List Delay").getValue();
                    Thread.sleep(listDelay);

                    Iterator<Module> iterator = Hydrogen.getClient().moduleManager.getModules().iterator();

                    while (iterator.hasNext()) {
                        Module mod = iterator.next();
                        if (mod.isEnabled()) {
                            if (mod.getSlideTTF() < FontHelper.sf_l.getStringWidth(mod.getName())) {
                                mod.setSlideTTF(mod.getSlideTTF() + 1);
                            }

                        } else if (mod.getSlideTTF() != 0 && !mod.isEnabled()) {
                            if (mod.getSlideTTF() > 0) {
                                mod.setSlideTTF(mod.getSlideTTF() - 1);
                            }

                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }, "smooth array ttf font").start();
    }

    public static void sortModules() {
        boolean lengthSort = Hydrogen.getClient().settingsManager.getSettingByName("Sorting").getMode().equalsIgnoreCase("Length");
        if (lengthSort) {
            Hydrogen.getClient().moduleManager.getModules().sort((m1, m2) -> Integer.compare(FontHelper.sf_l.getStringWidth(m2.getName()), FontHelper.sf_l.getStringWidth(m1.getName())));
        } else {
            Hydrogen.getClient().moduleManager.getModules().sort(Comparator.comparing(Module::getName));
        }
    }

    @Override
    public void drawArrayList() {
        int count = 0;
        float rbdelay = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Delay").getValue();
        float rbsaturation = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Saturation").getValue();
        float rbcolorcount = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Color Count").getValue();

        for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {
            ScaledResolution sr = new ScaledResolution(mc);
            Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);
            Module arraylist = Hydrogen.getClient().moduleManager.getModule(ArrayList.class);
            Color rainbow = ColorUtil.getRainbowColor(rbdelay, rbsaturation, 1, (long) (count * rbcolorcount));

            boolean background = Hydrogen.getClient().settingsManager.getSettingByName(arraylist, "Background").isEnabled();
            boolean outline = Hydrogen.getClient().settingsManager.getSettingByName(arraylist, "Outline").isEnabled();

            int mheight = (count * 11 + i) + 1;

            double rectX = (sr.getScaledWidth() - mod.getSlideTTF() - 5);
            double rectX2 = rectX + FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3.0D;
            double rectY = (1 + i * 12);
            double rectY2 = rectY + FontHelper.sf_l.getFont().getHeight() - 2;

            int outlinecolor = 0x80000000;
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

            if (outline && background) {
                if (i == 0) {

                    // if first module, then draw side line 1px higher, so it connects with the top line

                    RenderUtil.rect(rectX - 1.0D, rectY - 1.0D, rectX2 + 10, rectY, outlinecolor);

                    // top line

                    RenderUtil.rect(rectX - 2.0D, rectY - 2, rectX - 1, rectY2 - 5, outlinecolor);
                } else {

                    // side line

                    RenderUtil.rect(rectX - 2.0D, rectY, rectX - 1, rectY2 - 5, outlinecolor);
                }

                if (i == Hydrogen.getClient().moduleManager.getEnabledMods().size() - 1) {

                    // bottom arraylist line

                    RenderUtil.rect(rectX - 2.0D, rectY2 - 5, rectX2 + 10, rectY2 - 4, outlinecolor);
                }

                if (i != Hydrogen.getClient().moduleManager.getEnabledMods().size() - 1) {
                    double modwidth = (FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()));
                    double mwidthNext = (FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()));
                    double difference = modwidth - mwidthNext;
                    if (modwidth < mwidthNext) {
                        if ((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlideTTF() < FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()) + 3) {
                            if ((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getSlideTTF() >= FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3) {
                                rectX = rectX - (Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlideTTF() + FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) - difference + 2.0D;
                            }
                        }
                    }
                    RenderUtil.rect(rectX - 2, rectY2 - 5, rectX + 3.0D + difference - 5, rectY2 - 4, outlinecolor);
                }
            }

            if (background) {
                RenderUtil.rect(sr.getScaledWidth() - mod.getSlideTTF() - 6, 1 + i * 12, sr.getScaledWidth(), i * 12 + 13, 0x66000000);
            }

            FontHelper.sf_l.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlideTTF() - 3, mheight, color);

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

        Module info = Hydrogen.getClient().moduleManager.getModule(Info.class);
        boolean lines = Hydrogen.getClient().settingsManager.getSettingByName(info, "XYZ Style").getMode().equalsIgnoreCase("1-Line");

        if(Hydrogen.getClient().settingsManager.getSettingByName(info, "Alignment").getMode().equalsIgnoreCase("right")) {
            if (mc.ingameGUI.getChatGUI().getChatOpen()) {
                if(lines) {
                    FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 37, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                } else {
                    FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 59, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_x) - 3, Utils.getScaledRes().getScaledHeight() - 48, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_y) - 3, Utils.getScaledRes().getScaledHeight() - 37, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_z) - 3, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                }
            } else {
                if(lines) {
                        FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                        FontHelper.sf_l.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                } else {
                    FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 45, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_x) - 2, Utils.getScaledRes().getScaledHeight() - 34, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_y) - 2, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_z) - 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                }
            }
        } else {
            if (mc.ingameGUI.getChatGUI().getChatOpen()) {
                if(lines) {
                    FontHelper.sf_l.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 37, Color.white);
                } else {
                    FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 59, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 48, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 37, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                }
            } else {
                if(lines) {
                    FontHelper.sf_l.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                } else {
                    FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 45, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 34, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                    FontHelper.sf_l.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                }
            }
        }
    }

    @Override
    public void drawPotionEffects() {
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
            FontHelper.sf_l.drawStringWithShadow(name, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(name) - 1, Utils.getScaledRes().getScaledHeight() - posY - 12 - (infoIsRight && infoEnabled ? 22 : 0) - (chatOpen ? 14 : 0), new Color(color, true));
            ++offset;
        }
    }

    @Override
    public void drawWatermark() {
        Module watermarkModule = Hydrogen.getClient().moduleManager.getModule(Watermark.class);
        boolean background = Hydrogen.getClient().settingsManager.getSettingByName(watermarkModule, "Background").isEnabled();
        boolean time = Hydrogen.getClient().settingsManager.getSettingByName(watermarkModule, "Time").isEnabled();
        boolean outline = Hydrogen.getClient().settingsManager.getSettingByName(watermarkModule, "Outline").isEnabled();
        boolean timeformat = Hydrogen.getClient().settingsManager.getSettingByName("Time Format").getMode().equals("24H");
        LocalDateTime now = LocalDateTime.now();
        String currenttime = timeformat ? timeFormat24.format(now) : timeFormat12.format(now);

        if (!Hydrogen.getClient().isStableBuild && !(Hydrogen.getClient().moduleManager.getModule(Hotbar.class).isEnabled() || Hydrogen.getClient().settingsManager.getSettingByName("Alignment").getMode().equalsIgnoreCase("Left"))) {
            FontHelper.sf_l.drawStringWithShadow("§7Indev Build", 2, Utils.getScaledRes().getScaledHeight() - (mc.ingameGUI.getChatGUI().getChatOpen() ? 36 : 22), Color.WHITE);
            FontHelper.sf_l.drawStringWithShadow(String.format("§7Latest Commit: %s | %s", HTTPUtil.commitDate, HTTPUtil.commitTime), 2, Utils.getScaledRes().getScaledHeight() - (mc.ingameGUI.getChatGUI().getChatOpen() ? 26 : 12), Color.WHITE);
        }

        if (time) {

            String watermark = Hydrogen.version + " §7(" + currenttime + ")" + (Hydrogen.getClient().outdated ? " §7(Outdated)" : "");

            if (outline) {
                Gui.drawRect(FontHelper.sf_l.getStringWidth(watermark) + 24, 0, FontHelper.sf_l.getStringWidth(watermark) + 25, 23, 0x99000000);
                Gui.drawRect(0, 23, FontHelper.sf_l.getStringWidth(watermark) + 25, 24, 0x99000000);
            }

            if (background) {
                Gui.drawRect(0, 0, FontHelper.sf_l.getStringWidth(watermark) + 24, 23, Integer.MIN_VALUE);
            }

            FontHelper.sf_l2.drawStringWithShadow("h", 2, -1, Color.white);
            FontHelper.sf_l.drawStringWithShadow("2", 13, 12, Color.white);
            FontHelper.sf_l.drawStringWithShadow(watermark, 22, 5, Color.white);

        } else {

            String watermark_no_time = Hydrogen.version + (Hydrogen.getClient().outdated ? " §7(Outdated)" : "");

            if (outline) {
                Gui.drawRect(FontHelper.sf_l.getStringWidth(watermark_no_time) + 28, 0, FontHelper.sf_l.getStringWidth(watermark_no_time) + 29, 23, 0x99000000);
                Gui.drawRect(0, 23, FontHelper.sf_l.getStringWidth(watermark_no_time) + 29, 24, 0x99000000);
            }

            if (background) {
                Gui.drawRect(0, 0, FontHelper.sf_l.getStringWidth(watermark_no_time) + 28, 23, Integer.MIN_VALUE);
            }

            FontHelper.sf_l2.drawStringWithShadow("h", 2, -1, Color.white);
            FontHelper.sf_l.drawStringWithShadow("2", 13, 12, Color.white);
            FontHelper.sf_l.drawStringWithShadow(watermark_no_time, 22, 5, Color.white);
        }
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
            FontHelper.sf_l.drawStringWithShadow(date, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(date) - 9, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
            FontHelper.sf_l.drawStringWithShadow(time, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(time) - 10, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
        }

        if (coord) {
            FontHelper.sf_l.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
        }

        if (fps) {
            FontHelper.sf_l.drawStringWithShadow(fps1, 2, coord ? Utils.getScaledRes().getScaledHeight() - 23 : Utils.getScaledRes().getScaledHeight() - 12, Color.white);
        }
    }
}
