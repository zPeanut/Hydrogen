package tk.peanut.phosphor.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.event.FMLLoadEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import sun.awt.EventQueueItem;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.injection.mixins.MixinMinecraft;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.modules.modules.render.HUD;
import tk.peanut.phosphor.utils.Utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;

public class uiHUD {

    public static Minecraft mc = Minecraft.getMinecraft();

    public uiHUD() {

        new Thread(() -> {
            while (Minecraft.getMinecraft().running) {
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
        }

    }


    private static void drawWatermark() {

        String watermark = String.format("%s §7%s", Phosphor.getInstance().name, Phosphor.getInstance().version);

        if(Phosphor.getInstance().settingsManager.getSettingByName("HUD Alignment").getValString().equalsIgnoreCase("Left")) {

            if(Phosphor.getInstance().settingsManager.getSettingByName("Background Rect").getValBoolean()) {
                Gui.drawRect(0, 0, Minecraft.getMinecraft().fontRendererObj.getStringWidth(watermark) + 3, 11, Integer.MIN_VALUE);
            }

            mc.fontRendererObj.drawStringWithShadow(watermark, 2, 2, -1);

        } else if (Phosphor.getInstance().settingsManager.getSettingByName("HUD Alignment").getValString().equalsIgnoreCase("Right")) {

            if(Phosphor.getInstance().settingsManager.getSettingByName("Background Rect").getValBoolean()) {
                Gui.drawRect(Utils.getScaledRes().getScaledWidth(), 0, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(watermark) - 5, 11, Integer.MIN_VALUE);
            }

            mc.fontRendererObj.drawStringWithShadow(watermark, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(watermark) - 2, 2, -1);

        }
    }

    private static void drawArray() {
        int count = 0;
        for (int i = 0; i < Phosphor.getInstance().moduleManager.getEnabledMods().size(); i++) {
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

            Module mod = Phosphor.getInstance().moduleManager.getEnabledMods().get(i);


            int mwidth = 2 + mod.getSlide() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName());
            int mheight = count * 11 + i + 13;



            if(Phosphor.getInstance().settingsManager.getSettingByName("HUD Alignment").getValString().equalsIgnoreCase("Left")) {

                if(Phosphor.getInstance().settingsManager.getSettingByName("Background Rect").getValBoolean()) {
                    Gui.drawRect(mod.getSlide() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()), 11 + i * 12, mod.getSlide() + (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getSuffix()) + 4, i * 12 + 23, Integer.MIN_VALUE);
                }

                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(mod.getName(), mwidth, mheight, mod.getColor());

            } else if (Phosphor.getInstance().settingsManager.getSettingByName("HUD Alignment").getValString().equalsIgnoreCase("Right")) {

                if(Phosphor.getInstance().settingsManager.getSettingByName("Background Rect").getValBoolean()) {
                    Gui.drawRect(sr.getScaledWidth() - mod.getSlide() - 6, 11 + i * 12, sr.getScaledWidth(), i * 12 + 23, Integer.MIN_VALUE);
                }

                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlide() - 3, mheight, mod.getColor());

            }
            count++;
        }
    }





}
