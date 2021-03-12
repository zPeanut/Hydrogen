package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender3D;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;

/**
 * Created by peanut on 16/02/2021
 */
@Info(name = "ItemESP", description = "Draws a box around dropped items", category = Category.Render)
public class ItemESP extends Module {
    public ItemESP() {
        super(0x00, colorRender);

        addSetting(new Setting("Outline", this, true));
        addSetting(new Setting("Red", this, 255, 0, 255, true));
        addSetting(new Setting("Blue", this, 255, 0, 255, true));
        addSetting(new Setting("Green", this, 255, 0, 255, true));
        addSetting(new Setting("Alpha", this, 120, 0, 255, true));
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        boolean outline = Hydrogen.getClient().settingsManager.getSettingByName(this, "Outline").isEnabled();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        for (Entity e : this.mc.theWorld.loadedEntityList) {
            if (e instanceof EntityItem) {
                EntityItem item = (EntityItem)e;
                float pTicks = this.mc.timer.renderPartialTicks;
                double RPX = Minecraft.getMinecraft().getRenderManager().renderPosX;
                double RPY = Minecraft.getMinecraft().getRenderManager().renderPosY;
                double RPZ = Minecraft.getMinecraft().getRenderManager().renderPosZ;
                double x = item.lastTickPosX + (item.posX - item.lastTickPosX) * pTicks - RPX;
                double y = item.lastTickPosY + (item.posY - item.lastTickPosY) * pTicks - RPY;
                double z = item.lastTickPosZ + (item.posZ - item.lastTickPosZ) * pTicks - RPZ;
                int r = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Red").getValDouble();
                int b = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Blue").getValDouble();
                int g = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Green").getValDouble();
                int a = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Alpha").getValDouble();
                Color c = new Color(r, g, b, a);
                if(outline) {
                    Hydrogen.getUtils().renderBoxWithOutline(x, y - 0.7D, z, 0.5F, 0.5F, c);
                } else {
                    Hydrogen.getUtils().renderBox(x, y - 0.7D, z, 0.5F, 0.5F, c);
                }
            }
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
    }
}
