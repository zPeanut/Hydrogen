package me.peanut.hydrogen.ui.ingame.components;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.Priority;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.ColorUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "ArrayList", description = "Shows enabled modules", category = Category.Hud)
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

    }


    @EventTarget
    public void drawArray(EventRender2D e) {
        if(Hydrogen.getClient().panic) {
            return;
        }
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo) {
                return;
            }
            Hydrogen.getClient().hud.style.drawArrayList();
        }
    }
}
