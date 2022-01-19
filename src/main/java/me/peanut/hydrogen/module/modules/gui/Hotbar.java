package me.peanut.hydrogen.module.modules.gui;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import me.peanut.hydrogen.settings.Setting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "Hotbar", category = Category.Gui, description = "Shows an advanced hotbar with information")
public class Hotbar extends Module {

    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("h:mm a");
    static final DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm");

    public Hotbar() {
        addSetting(new Setting("FPS", this, true));
        addSetting(new Setting("Coordinates", this, true));
        addSetting(new Setting("Time / Date", this, true));

    }

    @EventTarget
    public void drawHotbar(EventRender2D e) {
        if (h2.panic) {
            return;
        }

        RenderUtil.rect(0, Utils.getScaledRes().getScaledHeight() - 23, Utils.getScaledRes().getScaledWidth() - 7, Utils.getScaledRes().getScaledHeight(), 0x44000000);

        EntityPlayer entityplayer = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();

        float needX = (Utils.getScaledRes().getScaledWidth() / 2 - 91 + entityplayer.inventory.currentItem * 20);
        float steps = 10f;

        Module mod = h2.moduleManager.getModulebyName("Hotbar");
        boolean fps = h2.settingsManager.getSettingByName(mod, "FPS").isEnabled();
        boolean coord = h2.settingsManager.getSettingByName(mod, "Coordinates").isEnabled();
        boolean tdate = h2.settingsManager.getSettingByName(mod, "Time / Date").isEnabled();

        Utils.addSlide(needX, steps);

        boolean timeformat = h2.settingsManager.getSettingByName("Time Format").getMode().equals("24H");
        LocalDateTime now = LocalDateTime.now();
        String date = dateFormat.format(now);
        String time = timeformat ? timeFormat24.format(now) : timeFormat12.format(now);
        String fps1 = String.format("FPS §7%s", Minecraft.getDebugFPS());

        String x = String.valueOf((int) mc.thePlayer.posX);
        String y = String.valueOf((int) mc.thePlayer.posY);
        String z = String.valueOf((int) mc.thePlayer.posZ);

        String coordinates = String.format("X: §7%s §fY: §7%s §fZ: §7%s", x, y, z);

        if (tdate) {
            mc.fontRendererObj.drawStringWithShadow(date, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(date) - 10, Utils.getScaledRes().getScaledHeight() - 10, -1);
            mc.fontRendererObj.drawStringWithShadow(time, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(time) - 11, Utils.getScaledRes().getScaledHeight() - 21, -1);
        }

        if (coord) {
            mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 10, -1);
        }

        if (fps) {
            mc.fontRendererObj.drawStringWithShadow(fps1, 2, coord ? Utils.getScaledRes().getScaledHeight() - 21 : Utils.getScaledRes().getScaledHeight() - 10, -1);
        }
    }
}
