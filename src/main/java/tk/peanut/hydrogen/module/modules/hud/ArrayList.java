package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.Priority;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "ArrayList", description = "Shows enabled modules", category = Category.Gui)
public class ArrayList extends Module {

    public ArrayList() {
        super(0x00, Color.white);

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
                        if (mod.getSlide() < FontHelper.hfontnormal.getStringWidth(mod.getName())) {
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

        java.util.ArrayList<String> array = new java.util.ArrayList<>();
        array.add("Rainbow");
        array.add("White");
        array.add("Category");

        addSetting(new Setting("Outline", this, true));
        addSetting(new Setting("List Color",this, "Rainbow", array));
        addSetting(new Setting("List Speed", this, 3, 0, 20, false));
        addSetting(new Setting("Rb. Saturation", this, 0.4, 0, 1, false));
        addSetting(new Setting("Rb. Delay", this, 4, 1, 10, true));
    }


    @EventTarget(Priority.HIGHEST)
    public void drawArray(EventRender2D e) {
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }

            int count = 0;
            float rbdelay = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Delay").getValDouble();
            float rbsaturation = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Saturation").getValDouble();

            for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {
                ScaledResolution sr = new ScaledResolution(mc);
                Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);
                Color rainbow = Utils.getRainbowColor(rbdelay, rbsaturation, 1, count * 100);
                Color color = Hydrogen.getClient().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("White") ? Color.white : (Hydrogen.getClient().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("Rainbow") ? rainbow : mod.getColor());
                boolean background = Hydrogen.getClient().settingsManager.getSettingByName("Background").isEnabled();
                boolean outline = Hydrogen.getClient().settingsManager.getSettingByName(this, "Outline").isEnabled();
                int mheight = (count * 11 + i) + 1;
                double rectX = (sr.getScaledWidth() - mod.getSlide() - 5);
                double rectX2 = rectX + FontHelper.hfontnormal.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3.0D;
                double rectY = (1 + i * 12);
                double rectY2 = rectY + FontHelper.hfontnormal.getFont().getHeight();
                int outlinecolor = 0x80000000;

                if (outline && background) {
                    if (i == 0) {

                        // if first module, then draw side line 1px higher, so it connects with the top line

                        Utils.rect(rectX - 1.0D, rectY - 1.0D, rectX2 + 10, rectY, outlinecolor);

                        // top line

                        Utils.rect(rectX - 2.0D, rectY - 2, rectX - 1, rectY2 - 5, outlinecolor);
                    } else {

                        // side line

                        Utils.rect(rectX - 2.0D, rectY, rectX - 1, rectY2 - 5, outlinecolor);
                    }

                    if (i == Hydrogen.getClient().moduleManager.getEnabledMods().size() - 1) {

                        // bottom arraylist line

                        Utils.rect(rectX - 2.0D, rectY2 - 5, rectX2 + 10, rectY2 - 4, outlinecolor);
                    }

                    if (i != Hydrogen.getClient().moduleManager.getEnabledMods().size() - 1) {
                        double modwidth = (FontHelper.hfontnormal.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()));
                        double mwidthNext = (FontHelper.hfontnormal.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()));
                        double difference = modwidth - mwidthNext;
                        if (modwidth < mwidthNext) {
                            if ((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlide() < FontHelper.hfontnormal.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getName()) + 3) {
                                if ((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getSlide() >= FontHelper.hfontnormal.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) + 3) {
                                    rectX = rectX - (Hydrogen.getClient().moduleManager.getEnabledMods().get(i + 1)).getSlide() + FontHelper.hfontnormal.getStringWidth((Hydrogen.getClient().moduleManager.getEnabledMods().get(i)).getName()) - difference + 2.0D;
                                }
                            }
                        }
                        Utils.rect(rectX - 2, rectY2 - 5, rectX + 3.0D + difference - 5, rectY2 - 4, outlinecolor);
                    }
                }

                if (background) {
                    Utils.rect(sr.getScaledWidth() - mod.getSlide() - 6, 1 + i * 12, sr.getScaledWidth(), i * 12 + 13, 0x66000000);
                }

                FontHelper.hfontnormal.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlide() - 3, mheight, color);
                count++;

            }
        }


    }


    @EventTarget(Priority.LOWEST)
    public void drawBackgrounds(EventRender2D e) {

    }



}
