package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import tk.peanut.hydrogen.Hydrogen;

/**
 * Created by peanut on 11/02/2021
 */
@Mixin(RenderItemFrame.class)
public abstract class MixinRenderItemFrame extends MixinRenderer<EntityItemFrame> {


    @Overwrite
    protected void renderName(EntityItemFrame entity, double x, double y, double z) {
        if (Minecraft.isGuiEnabled() && entity.getDisplayedItem() != null && entity.getDisplayedItem().hasDisplayName() && Minecraft.getMinecraft().getRenderManager().pointedEntity == entity) {
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            double d0 = entity.getDistanceSqToEntity(Minecraft.getMinecraft().getRenderManager().livingPlayer);
            float f2 = entity.isSneaking() ? 32.0F : 64.0F;
            if (d0 < (double)(f2 * f2)) {
                String s = entity.getDisplayedItem().getDisplayName();
                if (entity.isSneaking()) {
                    if(!Hydrogen.getClient().moduleManager.getModulebyName("NameTags").isEnabled()) {
                        FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
                        GlStateManager.pushMatrix();
                        GlStateManager.translate((float) x + 0.0F, (float) y + entity.height + 0.5F, (float) z);
                        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                        GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
                        GlStateManager.scale(-f1, -f1, f1);
                        GlStateManager.disableLighting();
                        GlStateManager.translate(0.0F, 0.25F / f1, 0.0F);
                        GlStateManager.depthMask(false);
                        GlStateManager.enableBlend();
                        GlStateManager.blendFunc(770, 771);
                        Tessellator tessellator = Tessellator.getInstance();
                        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
                        int i = fontrenderer.getStringWidth(s) / 2;
                        GlStateManager.disableTexture2D();
                        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                        worldrenderer.pos((double) (-i - 1), -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                        worldrenderer.pos((double) (-i - 1), 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                        worldrenderer.pos((double) (i + 1), 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                        worldrenderer.pos((double) (i + 1), -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                        tessellator.draw();
                        GlStateManager.enableTexture2D();
                        GlStateManager.depthMask(true);
                        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, 553648127);
                        GlStateManager.enableLighting();
                        GlStateManager.disableBlend();
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        GlStateManager.popMatrix();
                    }
                } else {
                    this.renderLivingLabel(entity, s, x, y, z, 64);
                }
            }
        }

    }


}
