package me.peanut.hydrogen.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by peanut on 12/02/2021
 */
public class BlurUtil {

    private static ShaderGroup blurShader;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static Framebuffer buffer;
    private static int lastScale;
    private static int lastScaleWidth;
    private static int lastScaleHeight;
    private static final ResourceLocation shader = new ResourceLocation("shaders/post/blur.json");

   public static void initFboAndShader() {
        try {

            blurShader = new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(), mc.getFramebuffer(), shader);
            blurShader.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
            buffer = blurShader.mainFramebuffer;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setShaderConfigs(float intensity, float blurWidth, float blurHeight, float opacity) {

        blurShader.listShaders.get(0).getShaderManager().getShaderUniform("BlurDir").set(blurWidth, blurHeight);
        blurShader.listShaders.get(1).getShaderManager().getShaderUniform("BlurDir").set(blurHeight, blurWidth);
    }


    public static void blurArea(int x, int y, int width, int height, float intensity, float blurWidth, float blurHeight) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;

        if (OpenGlHelper.isFramebufferEnabled()) {

            buffer.framebufferClear();

            GL11.glScissor(x * factor, (mc.displayHeight - (y * factor) - height * factor), width * factor,
                    height * factor);
            GL11.glEnable(GL11.GL_SCISSOR_TEST);

            setShaderConfigs(intensity, blurWidth, blurHeight, 1);
            buffer.bindFramebuffer(true);
            blurShader.loadShaderGroup(mc.timer.renderPartialTicks);

            mc.getFramebuffer().bindFramebuffer(true);

            GL11.glDisable(GL11.GL_SCISSOR_TEST);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ZERO,
                    GL11.GL_ONE);
            //buffer.framebufferRenderExt(mc.displayWidth, mc.displayHeight, false);
            GlStateManager.disableBlend();
            GL11.glScalef(factor, factor, 0);

        }
    }

    public static void blurArea(int x, int y, int width, int height, float intensity) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;


        buffer.framebufferClear();

        GL11.glScissor(x * factor, (mc.displayHeight - (y * factor) - height * factor), width * factor, (height) * factor);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        setShaderConfigs(intensity, 1, 0, 1);
        buffer.bindFramebuffer(true);
        blurShader.loadShaderGroup(mc.timer.renderPartialTicks);

        mc.getFramebuffer().bindFramebuffer(true);

        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ZERO, GL11.GL_ONE);
        //buffer.func_178038_a(mc.displayWidth, mc.displayHeight, false);
        GlStateManager.disableBlend();
        GL11.glScalef(factor, factor, 0);
        RenderHelper.enableGUIStandardItemLighting();
    }

    public static void blurAreaBorder(float x, float y, float width, float height, float intensity, float blurWidth, float blurHeight) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;

        GL11.glScissor((int)(x * factor), (int)((mc.displayHeight - (y * factor) - height * factor)) +1, (int)(width * factor), (int)(height * factor));
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        setShaderConfigs(intensity, blurWidth, blurHeight, 1);
        buffer.bindFramebuffer(true);

        blurShader.loadShaderGroup(mc.timer.renderPartialTicks);

        mc.getFramebuffer().bindFramebuffer(true);

        //Stencil.dispose();

        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void blurShape(float g, float f, float h, float height, float intensity, float blurWidth, float blurHeight) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null
                || blurShader == null) {
            initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;

        GL11.glScissor((int)(g * factor), (int)((mc.displayHeight - (f * factor) - height * factor)) +1, (int)(h * factor),
                (int)(height * factor));
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        setShaderConfigs(intensity, blurWidth, blurHeight, 1);
        buffer.bindFramebuffer(true);
        blurShader.loadShaderGroup(mc.timer.renderPartialTicks);

        mc.getFramebuffer().bindFramebuffer(true);

        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        //GlStateManager.enableBlend();
    }

    public static void blurAreaBorder(int x, int y, int width, int height, float intensity) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;

        GL11.glScissor(x * factor, (mc.displayHeight - (y * factor) - height * factor), width * factor, (height) * factor);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        setShaderConfigs(intensity, 1, 0, 1);
        buffer.bindFramebuffer(true);

        blurShader.loadShaderGroup(mc.timer.renderPartialTicks);

        mc.getFramebuffer().bindFramebuffer(true);

        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void blurAll(float intensity) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;

        setShaderConfigs(intensity, 1, 0, 1);
        blurShader.loadShaderGroup(mc.timer.renderPartialTicks);

        mc.getFramebuffer().bindFramebuffer(true);

    }

}
