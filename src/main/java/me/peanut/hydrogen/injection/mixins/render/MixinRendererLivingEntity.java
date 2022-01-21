package me.peanut.hydrogen.injection.mixins.render;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.render.ESP;
import me.peanut.hydrogen.module.modules.render.Animations;
import me.peanut.hydrogen.utils.OutlineUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.nio.FloatBuffer;
import java.util.List;

@SuppressWarnings("ALL")
@Mixin(RendererLivingEntity.class)
public abstract class MixinRendererLivingEntity<T extends EntityLivingBase> extends MixinRender<T> {

    @Shadow
    protected ModelBase mainModel;
    
    @Shadow
    public static float NAME_TAG_RANGE = 64.0F;
    
    @Shadow
    public static float NAME_TAG_RANGE_SNEAK = 32.0F;

    @Shadow protected abstract int getColorMultiplier(T entitylivingbaseIn, float lightBrightness, float partialTickTime);

    @Shadow protected FloatBuffer brightnessBuffer;

    @Shadow @Final private static DynamicTexture textureBrightness;

    @Shadow protected List<LayerRenderer<T>> layerRenderers;

    @Shadow protected abstract void unsetBrightness();

    @Inject(method = "doRender*", at = @At("HEAD"))
    private <T extends EntityLivingBase> void injectChamsPre(final T a, final double b, final double c, final double d, final float e, final float f, final CallbackInfo g) {
        if (Hydrogen.getClient().moduleManager.getModulebyName("Chams").isEnabled()) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -1000000.0f);
        }
    }

    @Inject(method = "doRender*", at = @At("RETURN"))
    private <T extends EntityLivingBase> void injectChamsPost(final T a, final double b, final double c, final double d, final float e, final float f, final CallbackInfo g) {
        if (Hydrogen.getClient().moduleManager.getModulebyName("Chams").isEnabled()) {
            GL11.glPolygonOffset(1.0f, 1000000.0f);
            GL11.glDisable(32823);
        }
    }


    @Overwrite
    protected void renderModel(T entitylivingbaseIn, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {

        boolean flag = !entitylivingbaseIn.isInvisible();
        boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer);

        if (flag || flag1) {
            if (!bindEntityTexture(entitylivingbaseIn)) {
                return;
            }

            if (flag1) {
                GlStateManager.pushMatrix();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
                GlStateManager.depthMask(false);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
                GlStateManager.alphaFunc(516, 0.003921569F);
            }

            if (Hydrogen.getClient().moduleManager.getModule(ESP.class) != null && Hydrogen.getClient().moduleManager.getModule(ESP.class).isEnabled()) {
                if (entitylivingbaseIn instanceof EntityPlayer && entitylivingbaseIn != Minecraft.getMinecraft().thePlayer) {
                    Color n = Color.WHITE;
                    OutlineUtils.setColor(n);
                    GL11.glLineWidth((float) Hydrogen.getClient().settingsManager.getSettingByName("LineWidth").getValue());
                    mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    OutlineUtils.renderOne((float) Hydrogen.getClient().settingsManager.getSettingByName("LineWidth").getValue());
                    mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    OutlineUtils.renderTwo();
                    mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour();
                    OutlineUtils.setColor(n);
                    mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    OutlineUtils.renderFive();
                    OutlineUtils.setColor(Color.WHITE);
                } else if (Hydrogen.getClient().settingsManager.getSettingByName("Entities").isEnabled()) {
                    GL11.glLineWidth((float) Hydrogen.getClient().settingsManager.getSettingByName("LineWidth").getValue());
                    mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    OutlineUtils.renderOne((float) Hydrogen.getClient().settingsManager.getSettingByName("LineWidth").getValue());
                    mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    OutlineUtils.renderTwo();
                    mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour();
                    mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    OutlineUtils.renderFive();
                }
            }

            this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);

            if (flag1) {
                GlStateManager.disableBlend();
                GlStateManager.alphaFunc(516, 0.1F);
                GlStateManager.popMatrix();
                GlStateManager.depthMask(true);
            }
        }
    }

    @Overwrite
    protected boolean setBrightness(T entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        float f = entitylivingbaseIn.getBrightness(partialTicks);
        int i = this.getColorMultiplier(entitylivingbaseIn, f, partialTicks);
        boolean flag = (i >> 24 & 255) > 0;
        boolean flag1 = entitylivingbaseIn.hurtTime > 0 || entitylivingbaseIn.deathTime > 0;
        if (!flag && !flag1) {
            return false;
        } else if (!flag && !combineTextures) {
            return false;
        } else {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.enableTexture2D();
            GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.enableTexture2D();
            GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND2_RGB, 770);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            this.brightnessBuffer.position(0);
            if (flag1) {
                Animations oldAnimations = Hydrogen.getClient().moduleManager.getModule(Animations.class);
                if(Hydrogen.getClient().settingsManager.getSettingByName("Armor Damage").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled()) {
                    this.brightnessBuffer.put(f);
                    this.brightnessBuffer.put(0.0F);
                    this.brightnessBuffer.put(0.0F);
                    this.brightnessBuffer.put(0.2F);
                } else {
                    this.brightnessBuffer.put(1.0F);
                    this.brightnessBuffer.put(0.0F);
                    this.brightnessBuffer.put(0.0F);
                    this.brightnessBuffer.put(0.3F);
                }
            } else {
                float f1 = (float)(i >> 24 & 255) / 255.0F;
                float f2 = (float)(i >> 16 & 255) / 255.0F;
                float f3 = (float)(i >> 8 & 255) / 255.0F;
                float f4 = (float)(i & 255) / 255.0F;
                this.brightnessBuffer.put(f2);
                this.brightnessBuffer.put(f3);
                this.brightnessBuffer.put(f4);
                this.brightnessBuffer.put(1.0F - f1);
            }

            this.brightnessBuffer.flip();
            GL11.glTexEnv(8960, 8705, this.brightnessBuffer);
            GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(textureBrightness.getGlTextureId());
            GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
            GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            return true;
        }
    }

    @Overwrite
    protected void renderLayers(T entitylivingbaseIn, float p_177093_2_, float p_177093_3_, float partialTicks, float p_177093_5_, float p_177093_6_, float p_177093_7_, float p_177093_8_) {
        for(LayerRenderer<T> layerrenderer : this.layerRenderers) {
            boolean flag = layerrenderer.shouldCombineTextures();

            Animations oldAnimations = Hydrogen.getClient().moduleManager.getModule(Animations.class);
            if(Hydrogen.getClient().settingsManager.getSettingByName(oldAnimations, "Armor Damage").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled() && layerrenderer instanceof LayerBipedArmor) {
                flag = true;
            }

            boolean flag1 = this.setBrightness(entitylivingbaseIn, partialTicks, flag);
            layerrenderer.doRenderLayer(entitylivingbaseIn, p_177093_2_, p_177093_3_, partialTicks, p_177093_5_, p_177093_6_, p_177093_7_, p_177093_8_);
            if (flag1) {
                this.unsetBrightness();
            }
        }
    }
}

