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
        super(0x00, new Color(199, 255, 201));
    }

    @EventTarget
    public void onRender(EventRender3D event) {
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
                Color c = new Color(255, 255, 255, 50);
                Utils.renderBox(x, y - 0.699999988079071D, z, 0.5F, 0.5F, c);
            }
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
    }
}
