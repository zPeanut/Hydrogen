package tk.peanut.hydrogen.ui.ingame;

import akka.actor.SupervisorStrategyLowPriorityImplicits;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.Utils;
import tk.peanut.hydrogen.utils.fontRenderer.GlyphPage;
import tk.peanut.hydrogen.utils.fontRenderer.GlyphPageFontRenderer;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

public class uiHUD {

    static Minecraft mc = Minecraft.getMinecraft();
    static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");

    public uiHUD() {

        new Thread(() -> {
            while (true) {
                try {
                    if (!ReflectionUtil.running.getBoolean(mc)) break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(3L);
                } catch (InterruptedException e) {
                }
                for (Module mod : Hydrogen.getInstance().moduleManager.getModules()) {
                    if (mod.isEnabled()) {
                        if (mod.getSlide() < mc.fontRendererObj.getStringWidth(mod.getName())) {
                            mod.setSlide(mod.getSlide() + 1);
                        }

                    } else if (mod.getSlide() != 0 && !mod.isEnabled()) {
                        if (mod.getSlide() > 0) {
                            mod.setSlide(mod.getSlide() - 1);
                        }

                    }
                }
            }
        },"smooth array").start();


        Collections.sort(Hydrogen.getInstance().moduleManager.getModules(), new Comparator<Module>() {
            @Override
            public int compare(Module mod1, Module mod2) {
                if (mc.fontRendererObj.getStringWidth(mod1.getName() + mod1.getSuffix()) > mc.fontRendererObj.getStringWidth(mod2.getName() + mod2.getSuffix())) {
                    return -1;
                }
                if (mc.fontRendererObj.getStringWidth(mod1.getName() + mod1.getSuffix()) < mc.fontRendererObj.getStringWidth(mod2.getName() + mod2.getSuffix())) {
                    return 1;
                }
                return 0;
            }
        });

    }


    @EventTarget
    public static void render(EventRender2D e) {
        if(mc.gameSettings.showDebugInfo)
            return;
        if(Hydrogen.getInstance().moduleManager.getModulebyName("HUD").isEnabled()) {
            drawWatermark();
            drawArray();
            drawInfo();
        }

    }

    private static void drawWatermark() {
            LocalDateTime now = LocalDateTime.now();
            String currenttime = timeFormat.format(now);

            String watermark = String.format("%s %s §7(%s)", Hydrogen.getInstance().name, Hydrogen.getInstance().version, currenttime);


            if(Hydrogen.getInstance().settingsManager.getSettingByName("Watermark").getValString().equalsIgnoreCase("New")) {

                if (Hydrogen.getInstance().settingsManager.getSettingByName("Background").isEnabled()) {
                    Gui.drawRect(0, 0, mc.fontRendererObj.getStringWidth(watermark) - 22, 21, Integer.MIN_VALUE);
                }

                GL11.glPushMatrix();
                GL11.glScalef(2f, 2f, 2f);
                mc.fontRendererObj.drawStringWithShadow("H", 2, 1, -1);
                GL11.glPopMatrix();

                mc.fontRendererObj.drawStringWithShadow("2", 17, 12, -1);
                mc.fontRendererObj.drawStringWithShadow(Hydrogen.getInstance().version + " §7(" + currenttime + ")", 27, 6, -1);
            } else {

                if (Hydrogen.getInstance().settingsManager.getSettingByName("Background").isEnabled()) {
                    Gui.drawRect(0, 0, mc.fontRendererObj.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
                }
                mc.fontRendererObj.drawStringWithShadow(watermark, 2, 2, -1);
            }

            }

    private static void drawArray() {
        int count = 0;
        for (int i = 0; i < Hydrogen.getInstance().moduleManager.getEnabledMods().size(); i++) {
            ScaledResolution sr = new ScaledResolution(mc);

            Module mod = Hydrogen.getInstance().moduleManager.getEnabledMods().get(i);


            int mwidth = 2 + mod.getSlide() - (mc).fontRendererObj.getStringWidth(mod.getName());
            int mheight = count * 11 + i + 23;
            int mheight2 = count * 11 + i + 2;
            int mcolor = Utils.getRainbow(5, 0.4f, 1, count * 100);
            int color = Hydrogen.getInstance().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("rainbow") ? mcolor : mod.getColor();


            if (!mod.getName().equalsIgnoreCase("hud")) {


                if (Hydrogen.getInstance().settingsManager.getSettingByName("List Side").getValString().equalsIgnoreCase("Left")) {

                    if(Hydrogen.getInstance().settingsManager.getSettingByName("Watermark").getValString().equalsIgnoreCase("New")) {

                        if (Hydrogen.getInstance().settingsManager.getSettingByName("Background").isEnabled()) {
                            Gui.drawRect(mod.getSlide() - (mc).fontRendererObj.getStringWidth(mod.getName()), 21 + i * 12, mod.getSlide() + (mc).fontRendererObj.getStringWidth(mod.getSuffix()) + 4, i * 12 + 33, Integer.MIN_VALUE);
                        }
                        mc.fontRendererObj.drawStringWithShadow(mod.getName(), mwidth, mheight, color);

                    } else {

                        if (Hydrogen.getInstance().settingsManager.getSettingByName("Background").isEnabled()) {
                            Gui.drawRect(mod.getSlide() - (mc).fontRendererObj.getStringWidth(mod.getName()), 11 + i * 12, mod.getSlide() + (mc).fontRendererObj.getStringWidth(mod.getSuffix()) + 4, i * 12 + 23, Integer.MIN_VALUE);
                        }
                        mc.fontRendererObj.drawStringWithShadow(mod.getName(), mwidth, mheight2 + 11, color);

                    }

                } else if (Hydrogen.getInstance().settingsManager.getSettingByName("List Side").getValString().equalsIgnoreCase("Right")) {
                    if (Hydrogen.getInstance().settingsManager.getSettingByName("Background").isEnabled()) {
                        Gui.drawRect(sr.getScaledWidth() - mod.getSlide() - 6, 1 + i * 12, sr.getScaledWidth(), i * 12 + 13, Integer.MIN_VALUE);
                    }
                    mc.fontRendererObj.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlide() - 3, mheight - 20, Hydrogen.getInstance().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("Rainbow") ? mcolor : mod.getColor());

                }
                count++;
            }
        }
    }

    public static void drawInfo() {
        if (Hydrogen.getInstance().settingsManager.getSettingByName("Info").isEnabled()) {
            String x = String.valueOf((int)mc.thePlayer.posX);
            String y = String.valueOf((int)mc.thePlayer.posY);
            String z = String.valueOf((int)mc.thePlayer.posZ);
            String coordinates = String.format("XYZ §7(%s, %s, %s)", x, y, z);
            String fps = String.format("FPS §7%s", mc.getDebugFPS());
            if (!Boolean.toString(mc.ingameGUI.getChatGUI().getChatOpen()).equals("true")) {
                mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 10, -1);
                mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 20, -1);
            } else {
                mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 24, -1);
                mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 34, -1);
            }
        }
    }





}
