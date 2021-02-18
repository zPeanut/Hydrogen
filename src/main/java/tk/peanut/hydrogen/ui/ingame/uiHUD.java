package tk.peanut.hydrogen.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.font.H2FontRenderer;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

import static tk.peanut.hydrogen.utils.Utils.addSlide;

public class uiHUD {

    static Minecraft mc = Minecraft.getMinecraft();
    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");
    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public uiHUD() {

        new Thread(() -> {
            while (true) {
                try {
                    if (!ReflectionUtil.running.getBoolean(mc)) break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep((long) Hydrogen.getClient().settingsManager.getSettingByName("List Speed").getValDouble());
                } catch (InterruptedException e) {
                }
                for (Module mod : Hydrogen.getClient().moduleManager.getModules()) {
                    if (mod.isEnabled()) {
                        if (mod.getSlide() < FontHelper.hfontbold.getStringWidth(mod.getName())) {
                            mod.setSlide(mod.getSlide() + 1);
                        }

                    } else if (mod.getSlide() != 0 && !mod.isEnabled()) {
                        if (mod.getSlide() > 0) {
                            mod.setSlide(mod.getSlide() - 1);
                        }

                    }
                }
            }
        }, "smooth array").start();


        Collections.sort(Hydrogen.getClient().moduleManager.getModules(), new Comparator<Module>() {
            @Override
            public int compare(Module mod1, Module mod2) {
                if (FontHelper.hfontbold.getStringWidth(mod1.getName() + mod1.getSuffix()) > FontHelper.hfontbold.getStringWidth(mod2.getName() + mod2.getSuffix())) {
                    return -1;
                }
                if (FontHelper.hfontbold.getStringWidth(mod1.getName() + mod1.getSuffix()) < FontHelper.hfontbold.getStringWidth(mod2.getName() + mod2.getSuffix())) {
                    return 1;
                }
                return 0;
            }
        });

    }


    @EventTarget
    public static void render(EventRender2D e) {
        if (mc.gameSettings.showDebugInfo)
            return;
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {

                drawBackgrounds();
                drawWatermark();
                drawArray();
                drawInfo();

                if(Hydrogen.getClient().settingsManager.getSettingByName("Hotbar").isEnabled()) {
                    drawHotbar();
                }
            }
        }

    private static void drawWatermark() {
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

    private static void drawBackgrounds() {
        int count = 0;
        for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {
            ScaledResolution sr = new ScaledResolution(mc);
            Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);
            boolean background = Hydrogen.getClient().settingsManager.getSettingByName("Background").isEnabled();

           /* double rectX = (sr.getScaledWidth() - mod.getSlide() - 5);
            double rectX2 = rectX + FontHelper.hfontbold.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3.0D;
            double rectY = (1 + i * 12);
            double rectY2 = rectY + FontHelper.hfontbold.getHeight() + 3;

             TODO:

            if (outline && background) {
                if (i == 0) {

                    // if first module, then draw side line 1px higher, so it connects with the top line

                    Utils.drawRect(rectX - 1.0D, rectY - 1.0D, rectX2 + 2, rectY, outlinecolor);

                    // top line

                    Utils.drawRect(rectX - 2.0D, rectY - 2, rectX - 1, rectY2, outlinecolor);
                } else {

                    // side line

                    Utils.drawRect(rectX - 2.0D, rectY - 1, rectX - 1, rectY2 - 2, outlinecolor);
                }

                if (i == Hydrogen.getClient().moduleManager.getEnabledMods().size() - 1) {

                    // bottom arraylist line


                    Utils.drawRect(rectX - 2.0D, rectY2, rectX2 + 20, rectY2 - 1, outlinecolor);
                }

                if (i != Hydrogen.getClient().moduleManager.getEnabledMods().size() - 1) {
                    double modwidth = (FontHelper.hfontbold.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()));
                    double mwidthNext = (FontHelper.hfontbold.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()));
                    double difference = modwidth - mwidthNext;
                    if (modwidth < mwidthNext) {
                        if ((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlide() < FontHelper.hfontbold.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()) + 3) {
                            if ((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getSlide() >= FontHelper.hfontbold.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3) {
                                rectX = rectX - (Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlide() + FontHelper.hfontbold.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) - difference + 2.0D;
                            }
                        }
                    }

                    // bottom line

                    Utils.drawRect(rectX - 2, rectY2, rectX + difference - 1, rectY2 - 1, outlinecolor);
                }
            }**/
            if(background) {
                Gui.drawRect(sr.getScaledWidth() - mod.getSlide() - 6, 1 + i * 12, sr.getScaledWidth(), i * 12 + 13, 0x66000000);
            }
            count++;
        }
    }
    private static void drawArray() {
        int count = 0;
        for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {

            ScaledResolution sr = new ScaledResolution(mc);
            Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);

            boolean modcolor = Hydrogen.getClient().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("rainbow");
            int mwidth = 2 + mod.getSlide() - (mc).fontRendererObj.getStringWidth(mod.getName());
            int mheight = (count * 11 + i) + 1;
            Color mcolor = Utils.getRainbow2(5, 0.4f, 1, count * 100);
            Color color = modcolor ? mcolor : Color.white;

            FontHelper.hfontbold.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlide() - 3, mheight, color);
            count++;
        }
    }

    public static void drawInfo() {
        if (Hydrogen.getClient().settingsManager.getSettingByName("Info").isEnabled() && !Hydrogen.getClient().settingsManager.getSettingByName("Hotbar").isEnabled()) {
            String x = String.valueOf((int) mc.thePlayer.posX);
            String y = String.valueOf((int) mc.thePlayer.posY);
            String z = String.valueOf((int) mc.thePlayer.posZ);
            String coordinates = String.format("XYZ §7(%s, %s, %s)", x, y, z);
            String fps = String.format("FPS §7%s", mc.getDebugFPS());
            if (!Boolean.toString(mc.ingameGUI.getChatGUI().getChatOpen()).equals("true")) {
                FontHelper.hfontbold.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                FontHelper.hfontbold.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 22, Color.white);
            } else {
                FontHelper.hfontbold.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                FontHelper.hfontbold.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 36, Color.white);
            }
        }
    }


    public static void drawHotbar() {

        EntityPlayer entityplayer = (EntityPlayer)Minecraft.getMinecraft().getRenderViewEntity();

        float needX = (Utils.getScaledRes().getScaledWidth() / 2 - 91 + entityplayer.inventory.currentItem * 20);
        float steps = 10f;

        addSlide(needX, steps);

        LocalDateTime now = LocalDateTime.now();
        String date = dateFormat.format(now);
        String time = timeFormat24.format(now);
        String fps = String.format("FPS §7%s", mc.getDebugFPS());

        String x = String.valueOf((int) mc.thePlayer.posX);
        String y = String.valueOf((int) mc.thePlayer.posY);
        String z = String.valueOf((int) mc.thePlayer.posZ);

        String coordinates = String.format("X: §7%s §fY: §7%s §fZ: §7%s", x, y, z);

        FontHelper.hfontbold.drawStringWithShadow(date, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(date) - 9, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
        FontHelper.hfontbold.drawStringWithShadow(time, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(time) - 22, Utils.getScaledRes().getScaledHeight() - 23, Color.white);

        FontHelper.hfontbold.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
        FontHelper.hfontbold.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 23, Color.white);

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
