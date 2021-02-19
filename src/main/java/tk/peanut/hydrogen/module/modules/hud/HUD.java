package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.Category;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.FontHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Info(name = "HUD", description = "The overlay", category = Category.Gui, color = -1)
public class HUD extends Module {

    private static java.util.ArrayList<Module> activemodules;

    public HUD() {
        super(Keyboard.KEY_NONE);
        activemodules = new java.util.ArrayList<>();
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Background", this, false));

        ArrayList<String> time = new ArrayList<>();
        time.add("24H");
        time.add("12H");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Time Format", this, "24H", time));
    }




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

}
