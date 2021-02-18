package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;

/**
 * Created by peanut on 18/02/2021
 */
@tk.peanut.hydrogen.module.Info(name = "Info", description = "Shows FPS and Coordinates", color = -1, category = Category.Gui)
public class Info extends Module {
    public Info() {
        super(0x00);
    }

    @EventTarget
    public static void drawInfo(EventRender2D e) {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
            return;
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

    @EventTarget
    public static void drawBackgrounds(EventRender2D e) {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
            return;
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
}
