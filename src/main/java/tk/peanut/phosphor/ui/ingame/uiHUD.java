package tk.peanut.phosphor.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.module.Module;
import tk.peanut.phosphor.utils.ReflectionUtil;
import tk.peanut.phosphor.utils.Utils;

import java.util.Collections;
import java.util.Comparator;

public class uiHUD {

    public static Minecraft mc = Minecraft.getMinecraft();

    public uiHUD() {

        new Thread(() -> {
            while (true) {
                try {
                    if (!ReflectionUtil.running.getBoolean(Minecraft.getMinecraft())) break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(3L);
                } catch (InterruptedException e) {
                }
                for (Module mod : Phosphor.getInstance().moduleManager.getModules()) {
                    if (mod.isEnabled()) {
                        if (mod.getSlide() < Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod.getName())) {
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


        Collections.sort(Phosphor.getInstance().moduleManager.getModules(), new Comparator<Module>() {
            @Override
            public int compare(Module mod1, Module mod2) {
                if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod1.getName() + mod1.getSuffix()) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod2.getName() + mod2.getSuffix())) {
                    return -1;
                }
                if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod1.getName() + mod1.getSuffix()) < Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod2.getName() + mod2.getSuffix())) {
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
        if(Phosphor.getInstance().moduleManager.getModulebyName("HUD").isEnabled()) {
            drawWatermark();
            drawArray();
            drawCoordinates();
        }

    }


    private static void drawWatermark() {

        String watermark = String.format("%s §7%s", Phosphor.getInstance().name, Phosphor.getInstance().version);

            if(Phosphor.getInstance().settingsManager.getSettingByName("Background").getValBoolean()) {
                Gui.drawRect(0, 0, Minecraft.getMinecraft().fontRendererObj.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
            }
            mc.fontRendererObj.drawStringWithShadow(watermark, 2, 2, -1);
    }

    private static void drawArray() {
        int count = 0;
        for (int i = 0; i < Phosphor.getInstance().moduleManager.getEnabledMods().size(); i++) {
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

            Module mod = Phosphor.getInstance().moduleManager.getEnabledMods().get(i);


            int mwidth = 2 + mod.getSlide() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName());
            int mheight = count * 11 + i + 13;
            int mcolor = Utils.getRainbow(5, 0.4f, 1, count * 100);


            if (!mod.getName().equalsIgnoreCase("hud")) {
                if (Phosphor.getInstance().settingsManager.getSettingByName("List Side").getValString().equalsIgnoreCase("Left")) {

                    if (Phosphor.getInstance().settingsManager.getSettingByName("Background").getValBoolean()) {
                        Gui.drawRect(mod.getSlide() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()), 11 + i * 12, mod.getSlide() + (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getSuffix()) + 4, i * 12 + 23, Integer.MIN_VALUE);
                    }

                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(mod.getName(), mwidth, mheight, Phosphor.getInstance().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("rainbow") ? mcolor : mod.getColor());

                } else if (Phosphor.getInstance().settingsManager.getSettingByName("List Side").getValString().equalsIgnoreCase("Right")) {

                    if (Phosphor.getInstance().settingsManager.getSettingByName("Background").getValBoolean()) {
                        Gui.drawRect(sr.getScaledWidth() - mod.getSlide() - 6, 1 + i * 12, sr.getScaledWidth(), i * 12 + 13, Integer.MIN_VALUE);
                    }


                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlide() - 3, mheight - 10, Phosphor.getInstance().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("Rainbow") ? mcolor : mod.getColor());

                }
                count++;
            }
        }
    }

    public static void drawCoordinates() {
        if(Phosphor.getInstance().settingsManager.getSettingByName("Coordinates").getValBoolean()) {

            String x = "§8X §7" + String.valueOf((int)Minecraft.getMinecraft().thePlayer.posX);
            String y = "§8Y §7" + (int)Minecraft.getMinecraft().thePlayer.posY;
            String z = "§8Z §7" + String.valueOf((int)Minecraft.getMinecraft().thePlayer.posZ);
            if (Boolean.toString(Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()).equals("true"))
            {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(x, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(x) - 2, Utils.getScaledRes().getScaledHeight() - 43, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(y, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(y) - 2, Utils.getScaledRes().getScaledHeight() - 33, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(z, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(z) - 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
            }
            else
            {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(x, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(x) - 2, Utils.getScaledRes().getScaledHeight() - 30, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(y, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(y) - 2, Utils.getScaledRes().getScaledHeight() - 20, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(z, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(z) - 2, Utils.getScaledRes().getScaledHeight() - 10, -1);
            }
        }
    }





}
