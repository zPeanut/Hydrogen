package me.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender3D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;

/**
 * Created by peanut on 16/02/2021
 */
@Info(name = "ItemESP", description = "Draws a box around dropped items", category = Category.Render)
public class ItemESP extends Module {
    public ItemESP() {
        addSetting(new Setting("Outline", this, true));
        addSetting(new Setting("Red", this, 255, 0, 255, true));
        addSetting(new Setting("Blue", this, 255, 0, 255, true));
        addSetting(new Setting("Green", this, 255, 0, 255, true));
        addSetting(new Setting("Alpha", this, 120, 0, 255, true));
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        boolean outline = h2.settingsManager.getSettingByName(this, "Outline").isEnabled();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        for (Entity e : mc.theWorld.loadedEntityList) {
            if (e instanceof EntityItem) {
                EntityItem item = (EntityItem)e;
                float pTicks = mc.timer.renderPartialTicks;
                double RPX = Minecraft.getMinecraft().getRenderManager().renderPosX;
                double RPY = Minecraft.getMinecraft().getRenderManager().renderPosY;
                double RPZ = Minecraft.getMinecraft().getRenderManager().renderPosZ;
                double x = item.lastTickPosX + (item.posX - item.lastTickPosX) * pTicks - RPX;
                double y = item.lastTickPosY + (item.posY - item.lastTickPosY) * pTicks - RPY;
                double z = item.lastTickPosZ + (item.posZ - item.lastTickPosZ) * pTicks - RPZ;
                int r = (int) h2.settingsManager.getSettingByName(this, "Red").getValue();
                int b = (int) h2.settingsManager.getSettingByName(this, "Blue").getValue();
                int g = (int) h2.settingsManager.getSettingByName(this, "Green").getValue();
                int a = (int) h2.settingsManager.getSettingByName(this, "Alpha").getValue();
                Color c = new Color(r, g, b, a);
                if(outline) {
                    RenderUtil.renderBoxWithOutline(x, y - 0.7D, z, 0.5F, 0.5F, c);
                } else {
                    RenderUtil.renderBox(x, y - 0.7D, z, 0.5F, 0.5F, c);
                }
            }
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
    }
}
