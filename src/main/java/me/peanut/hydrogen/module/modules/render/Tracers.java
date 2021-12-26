package me.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender3D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;

/**
 * Created by peanut on 19/12/2021
 */
@Info(name = "Tracers", description = "Draws a line to players", category = Category.Render)
public class Tracers extends Module {

    public Tracers() {
        addSetting(new Setting("Red", this, 255, 0, 255, true));
        addSetting(new Setting("Blue", this, 255, 0, 255, true));
        addSetting(new Setting("Green", this, 255, 0, 255, true));
        addSetting(new Setting("Alpha", this, 120, 0, 255, true));
    }

    @EventTarget
    public void onRender3D(EventRender3D event) {
        int red = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Red").getValDouble();
        int blue = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Blue").getValDouble();
        int green = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Green").getValDouble();
        int alpha = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Alpha").getValDouble();
        for (final Entity entity : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
            if (entity != null && entity != Minecraft.getMinecraft().thePlayer && entity instanceof EntityPlayer) {
                Utils.drawTracer(entity, new Color(red, green, blue, alpha));
            }
        }
    }
}
