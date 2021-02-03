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
        new Thread(new Runnable() {
            @Override
            public void run() {

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
        }, "smooth array list").start();

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
            mc.fontRendererObj.drawStringWithShadow(watermark, 2, 2, -1);
        } else if (Phosphor.getInstance().settingsManager.getSettingByName("HUD Alignment").getValString().equalsIgnoreCase("Right")) {
            mc.fontRendererObj.drawStringWithShadow(watermark, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(watermark) - 2, 2, -1);
        }
    }

    private static void drawArray() {
        int count = 0;
        for (int i = 0; i < Phosphor.getInstance().moduleManager.getEnabledMods().size(); i++) {
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

            Module mod = Phosphor.getInstance().moduleManager.getEnabledMods().get(i);


            int mwidth = 2 + mod.getSlide() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()) + 24;
            int mheight = count * 11 + i + 13;

            //Gui.drawRect(mod.getSlide() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(String.valueOf(mod.getName()) + mod.getSuffix()) + 3, 11 + i * 12, mod.getSlide() + (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getSuffix()) + 8, i * 12 + 23, -2147483648);
            //Gui.drawRect(mod.getSlide() - (Minecraft.getMinecraft()).fontRendererObj.getStringWidth(mod.getName()) + 3, 11 + i * 12, 0, i * 12 + 23, mod.getColor());

            if(Phosphor.getInstance().settingsManager.getSettingByName("HUD Alignment").getValString().equalsIgnoreCase("Left")) {
                mc.fontRendererObj.drawStringWithShadow(mod.getName(), 2, mheight, -1);
            } else if (Phosphor.getInstance().settingsManager.getSettingByName("HUD Alignment").getValString().equalsIgnoreCase("Right")) {
                mc.fontRendererObj.drawStringWithShadow(mod.getName(), Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(mod.getName()) - 2, mheight, mod.getColor());
            }
            count++;

        }
    }




}
