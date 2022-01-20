package me.peanut.hydrogen.injection.mixins.render;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.player.MurderMystery;
import me.peanut.hydrogen.module.modules.render.NameTags;
import me.peanut.hydrogen.utils.PlayerUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Render.class)
public abstract class MixinRender<T extends Entity> {

    @Shadow
    protected abstract boolean bindEntityTexture(T entity);

    @Shadow
    protected abstract void renderName(T entity, double x, double y, double z);

    @Shadow
    protected boolean canRenderName(T entity) {
        return false;
    }

    @Shadow
    public abstract FontRenderer getFontRendererFromRenderManager();

    @Shadow
    protected abstract void renderOffsetLivingLabel(T entityIn, double x, double y, double z, String str, float p_177069_9_, double p_177069_10_);

    @Shadow @Final protected RenderManager renderManager;

    /**
     * @author
     */
    @Overwrite
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if(Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
            if (this.canRenderName(entity) && entity.getEntityId() != -3)
            {
                if (Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
                    String p_147906_2_ = entity.getDisplayName().getFormattedText();

                    double[] pos = PlayerUtil.entityWorldPos(entity);
                    double[] pos2 = PlayerUtil.entityWorldPos(Minecraft.getMinecraft().thePlayer);
                    float xd = (float)(pos2[0]-pos[0]);
                    float yd = (float)(pos2[1]-pos[1]);
                    float zd = (float)(pos2[2]-pos[2]);
                    double dist = MathHelper.sqrt_float(xd*xd+yd*yd+zd*zd);

                    float distance = (float)dist;
                    float scaleFactor = distance < 10.0F ? 0.9F : distance / 11.11F;

                    int color = 16777215;
                    float height = 0.0F;
                    if ((entity instanceof EntityPlayer)) {
                        EntityPlayer player = (EntityPlayer) entity;
                        if (Hydrogen.getClient().settingsManager.getSettingByName("Health").isEnabled()) {
                            if (player.getHealth() > 16.0F) {
                                p_147906_2_ = p_147906_2_ + " \u00a7a" + (int)player.getHealth();
                            } else if (player.getHealth() > 8.0F) {
                                p_147906_2_ = p_147906_2_ + " \u00a7e" + (int)player.getHealth();
                            } else {
                                p_147906_2_ = p_147906_2_ + " \u00a7c" + (int)player.getHealth();
                            }
                        }


                        if (Hydrogen.getClient().settingsManager.getSettingByName("State").isEnabled()) {
                            if (player.isSneaking()) {
                                p_147906_2_ += " \u00a74[S]";
                            } else if (player.isInvisible()) {
                                p_147906_2_ += " \u00a7f[I]";
                            }
                        }

                        if (Hydrogen.getClient().settingsManager.getSettingByName("MurderMystery").isEnabled() && Hydrogen.getClient().moduleManager.getModulebyName("MurderMystery").isEnabled() && MurderMystery.isMurderer) {
                            p_147906_2_ += " §c[MURDERER]";
                        }

                        if (distance >= 10.0F) {
                            height = (float) (height + (distance / 40.0F - 0.25D));
                        }
                    }
                    FontRenderer var12 = this.getFontRendererFromRenderManager();
                    if (var12 == null) {
                        return;
                    }

                    p_147906_2_+= "";

                    float var13 = 1.6F;
                    float var14 = 0.016666668F * var13;
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float)x + 0.0F, (float)y + entity.height + 0.5F, (float)z);
                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
                    GlStateManager.scale(-var14*scaleFactor, -var14*scaleFactor, var14*scaleFactor);
                    GlStateManager.disableLighting();
                    GlStateManager.depthMask(false);
                    GlStateManager.disableDepth();
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                    Tessellator var15 = Tessellator.getInstance();
                    WorldRenderer var16 = var15.getWorldRenderer();

                    GlStateManager.disableTexture2D();

                    int var18 = var12.getStringWidth(p_147906_2_) / 2;
                    float w = var18;
                    float h = var12.FONT_HEIGHT;
                    float offY = 0;

                    RenderUtil.rectBorder(-w-4, -4+offY, w+4, h+3+offY, 0x99111111);
                    RenderUtil.rect(-w-3, -3+offY, w+3, h+2+offY, 0x44111111);

                    GlStateManager.enableTexture2D();
                    int co = -1;

                    var12.drawString(p_147906_2_, -var12.getStringWidth(p_147906_2_) / 2, 0, co);


                }

                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                GlStateManager.enableLighting();
                GlStateManager.disableBlend();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();
                return;
            }
        } else {
            this.renderName(entity, x, y, z);
        }
    }

    /**
     * @author
     */
    @Overwrite
    protected void renderLivingLabel(T entityIn, String str, double x, double y, double z, int maxDistance) {
        if (entityIn instanceof EntityLivingBase) {
            double d0 = entityIn.getDistanceSqToEntity(Minecraft.getMinecraft().getRenderManager().livingPlayer);

            if (!Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
                if (d0 <= (double) (maxDistance * maxDistance)) {
                    FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
                    float f = 1.6F;
                    float f1 = 0.016666668F * f;
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float) x + 0.0F, (float) y + entityIn.height + 0.5F, (float) z);
                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
                    GlStateManager.scale(-f1, -f1, f1);
                    GlStateManager.disableLighting();
                    GlStateManager.depthMask(false);
                    GlStateManager.disableDepth();
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                    Tessellator tessellator = Tessellator.getInstance();
                    WorldRenderer worldrenderer = tessellator.getWorldRenderer();
                    byte b0 = 0;

                    if (str.equals("deadmau5")) {
                        b0 = -10;
                    }

                    int i = fontrenderer.getStringWidth(str) / 2;
                    GlStateManager.disableTexture2D();
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    worldrenderer.pos(-i - 1, -1 + b0, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                    worldrenderer.pos(-i - 1, 8 + b0, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                    worldrenderer.pos(i + 1, 8 + b0, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                    worldrenderer.pos(i + 1, -1 + b0, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                    fontrenderer.drawString(str, -fontrenderer.getStringWidth(str) / 2, b0, 553648127);
                    GlStateManager.enableDepth();
                    GlStateManager.depthMask(true);
                    fontrenderer.drawString(str, -fontrenderer.getStringWidth(str) / 2, b0, -1);
                    GlStateManager.enableLighting();
                    GlStateManager.disableBlend();
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    GlStateManager.popMatrix();
                }
            }
        }
    }
}