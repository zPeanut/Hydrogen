package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "ArrayList", description = "Shows enabled modules", color = -1, category = Category.Gui)
public class ArrayList extends Module {
    public ArrayList() {
        super(0x00);

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
    public static void drawArray(EventRender2D e) {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
            return;
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
}
