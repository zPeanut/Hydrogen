package me.peanut.hydrogen.module.modules.ui;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.Priority;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.ColorUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "ArrayList", description = "Shows enabled modules", category = Category.Gui)
public class ArrayList extends Module {

    public ArrayList() {
        java.util.ArrayList<String> array = new java.util.ArrayList<>();
        array.add("Rainbow");
        array.add("White");
        array.add("Category");

        addSetting(new Setting("Outline", this, true));
        addSetting(new Setting("Background", this, true));
        addSetting(new Setting("List Color",this, "Rainbow", array));
        addSetting(new Setting("Color Count", this, 100, 50, 1000, true));
        addSetting(new Setting("Rb. Saturation", this, 0.4, 0, 1, false));
        addSetting(new Setting("Rb. Delay", this, 4, 1, 10, true));
    }

    public static void arrayListThread() {
        new Thread(() -> {
            while (Minecraft.getMinecraft().running) {
                try {
                    Thread.sleep(5);
                    for (Module mod : Hydrogen.getClient().moduleManager.getModules()) {
                        if (mod.isEnabled()) {
                            if (mod.getSlide() < FontHelper.sf_l.getStringWidth(mod.getName())) {
                                mod.setSlide(mod.getSlide() + 1);
                            }
                        } else if (mod.getSlide() != 0 && !mod.isEnabled()) {
                            if (mod.getSlide() > 0) {
                                mod.setSlide(mod.getSlide() - 1);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "smooth array").start();

        Hydrogen.getClient().moduleManager.getModules().sort((m1, m2) -> {
            if (FontHelper.sf_l.getStringWidth(m1.getName()) > FontHelper.sf_l.getStringWidth(m2.getName()))
                return -1;
            if (FontHelper.sf_l.getStringWidth(m1.getName()) < FontHelper.sf_l.getStringWidth(m2.getName()))
                return 1;
            return 0;
        });
    }


    @EventTarget(Priority.HIGHEST)
    public void drawArray(EventRender2D e) {
        if(Hydrogen.getClient().panic) {
            return;
        }
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }

            int count = 0;
            float rbdelay = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Delay").getValue();
            float rbsaturation = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Saturation").getValue();
            float rbcolorcount = (float) Hydrogen.getClient().settingsManager.getSettingByName("Color Count").getValue();

            for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {
                ScaledResolution sr = new ScaledResolution(mc);
                Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);
                Color rainbow = ColorUtil.getRainbowColor(rbdelay, rbsaturation, 1, (long) (count * rbcolorcount));
                Color color = Hydrogen.getClient().settingsManager.getSettingByName("List Color").getMode().equalsIgnoreCase("White") ? Color.white : (Hydrogen.getClient().settingsManager.getSettingByName("List Color").getMode().equalsIgnoreCase("Rainbow") ? rainbow : mod.getColor());
                boolean background = Hydrogen.getClient().settingsManager.getSettingByName(this, "Background").isEnabled();
                boolean outline = Hydrogen.getClient().settingsManager.getSettingByName(this, "Outline").isEnabled();
                int mheight = (count * 11 + i) + 1;
                double rectX = (sr.getScaledWidth() - mod.getSlide() - 5);
                double rectX2 = rectX + FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3.0D;
                double rectY = (1 + i * 12);
                double rectY2 = rectY + FontHelper.sf_l.getFont().getHeight() - 2;
                int outlinecolor = 0x80000000;

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
                            if ((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlide() < FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()) + 3) {
                                if ((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getSlide() >= FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3) {
                                    rectX = rectX - (Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlide() + FontHelper.sf_l.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) - difference + 2.0D;
                                }
                            }
                        }
                        RenderUtil.rect(rectX - 2, rectY2 - 5, rectX + 3.0D + difference - 5, rectY2 - 4, outlinecolor);
                    }
                }

                if (background) {
                    RenderUtil.rect(sr.getScaledWidth() - mod.getSlide() - 6, 1 + i * 12, sr.getScaledWidth(), i * 12 + 13, 0x66000000);
                }

                FontHelper.sf_l.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlide() - 3, mheight, color);

                count++;

            }
        }
    }


}
