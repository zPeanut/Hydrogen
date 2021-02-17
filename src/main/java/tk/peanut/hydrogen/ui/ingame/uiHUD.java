package tk.peanut.hydrogen.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.BlurUtil;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

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
        }, "smooth array").start();


        Collections.sort(Hydrogen.getClient().moduleManager.getModules(), new Comparator<Module>() {
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
        if (mc.gameSettings.showDebugInfo)
            return;
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
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
                Gui.drawRect(0, 0, mc.fontRendererObj.getStringWidth(watermarknew) + 28, 23, Integer.MIN_VALUE);
                Gui.drawRect(0, 23, mc.fontRendererObj.getStringWidth(watermarknew) + 29, 24, 0x99000000);
                Gui.drawRect(mc.fontRendererObj.getStringWidth(watermarknew) + 28, 0, mc.fontRendererObj.getStringWidth(watermarknew) + 29, 23, 0x99000000);
            }

            GL11.glPushMatrix();
            GL11.glScalef(2f, 2f, 2f);
            mc.fontRendererObj.drawStringWithShadow("H", 2, 1, -1);
            GL11.glPopMatrix();

            mc.fontRendererObj.drawStringWithShadow("2", 17, 12, -1);
            mc.fontRendererObj.drawStringWithShadow(watermarknew, 27, 7, -1);
        } else {

            if (Hydrogen.getClient().settingsManager.getSettingByName("Background").isEnabled()) {
                Gui.drawRect(0, 0, mc.fontRendererObj.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
                Gui.drawRect(0, 11, mc.fontRendererObj.getStringWidth(watermark) + 4, 12, 0x99000000);
                Gui.drawRect(mc.fontRendererObj.getStringWidth(watermark) + 4, 0, mc.fontRendererObj.getStringWidth(watermark) + 3, 11, 0x99000000);
            }
            mc.fontRendererObj.drawStringWithShadow(watermark, 2, 2, -1);

            //TODO: FontHelper.cfArrayList.drawString(watermark, 2, 12, -1);

        }

    }

    private static void drawArray() {
        int count = 0;
        for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {
            ScaledResolution sr = new ScaledResolution(mc);

            Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);

            boolean modcolor = Hydrogen.getClient().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("rainbow");
            boolean background = Hydrogen.getClient().settingsManager.getSettingByName("Background").isEnabled();
            boolean outline = Hydrogen.getClient().settingsManager.getSettingByName("List Outline").isEnabled();

            int mwidth = 2 + mod.getSlide() - (mc).fontRendererObj.getStringWidth(mod.getName());
            int mheight = count * 11 + i + 23;
            int mheight2 = count * 11 + i + 2;
            int mcolor = Utils.getRainbow(5, 0.4f, 1, count * 100);
            int color = modcolor ? mcolor : mod.getColor();

            double rectX = (sr.getScaledWidth() - mod.getSlide() - 5);
            double rectX2 = rectX + mc.fontRendererObj.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3.0D;
            double rectY = (1 + i * 12);
            double rectY2 = rectY + mc.fontRendererObj.FONT_HEIGHT + 3;

            int outlinecolor = modcolor ? mcolor : 0x88000000;

            if (outline && background) {

                if (i == 0) {
                    Utils.drawRect(rectX - 1.0D, rectY - 1.0D, rectX2 + 2, rectY, outlinecolor);
                    Utils.drawRect(rectX - 2.0D, rectY - 2, rectX - 1, rectY2, outlinecolor);
                } else {
                    Utils.drawRect(rectX - 2.0D, rectY + 1, rectX - 1, rectY2, outlinecolor);
                }

                if (i == Hydrogen.getClient().moduleManager.getEnabledMods().size() - 1) {
                    Utils.drawRect(rectX - 2.0D, rectY2, rectX2 + 20, rectY2 + 1.0D, outlinecolor);
                }

                if (i != Hydrogen.getClient().moduleManager.getEnabledMods().size() - 1) {
                    double modwidth = (mc.fontRendererObj.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()));
                    double mwidthNext = (mc.fontRendererObj.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()));
                    double difference = modwidth - mwidthNext;
                    if (modwidth < mwidthNext) {
                        if (((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlide() < mc.fontRendererObj.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()) + 3) {
                            if (((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getSlide() >= mc.fontRendererObj.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3) {
                                rectX = rectX - ((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlide() + mc.fontRendererObj.getStringWidth(((Module) Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) - difference + 2.0D;
                            }
                        }
                    }
                    Utils.drawRect(rectX - 2, rectY2, rectX + 1.0D + difference - 2, rectY2 + 1, outlinecolor);
                }
            }
            if(background) {
                Gui.drawRect(sr.getScaledWidth() - mod.getSlide() - 6, 1 + i * 12, sr.getScaledWidth(), i * 12 + 13, 0x66000000);
            }



            mc.fontRendererObj.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlide() - 3, mheight - 20, modcolor ? mcolor : mod.getColor());
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
                mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 10, -1);
                mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 20, -1);
            } else {
                mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 24, -1);
                mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 34, -1);
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

        mc.fontRendererObj.drawStringWithShadow(date, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(date) - 9, Utils.getScaledRes().getScaledHeight() - 10, -1);
        mc.fontRendererObj.drawStringWithShadow(time, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(time) - 22, Utils.getScaledRes().getScaledHeight() - 21, -1);

        mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 10, -1);
        mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 21, -1);

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
