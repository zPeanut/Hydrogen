package me.peanut.hydrogen.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.vdurmont.semver4j.Semver;
import jdk.nashorn.internal.parser.JSONParser;
import me.peanut.hydrogen.module.modules.render.NameTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import me.peanut.hydrogen.Hydrogen;
import scala.util.parsing.json.JSONObject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.*;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();
    public static Utils instance;

    private static ShaderGroup blurShader;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final ResourceLocation shader = new ResourceLocation("shaders/post/blur.json");

    public static int deltaTime;

    public static String commitDate;
    public static String commitTime;

    public Utils() {
        instance = this;
    }

    public static int random(int min, int max) {
        if (max <= min) return min;

        return RANDOM.nextInt(max - min) + min;
    }

    public static void sendChatMessage(final String message) {
        ChatComponentText chatcomponenttext = new ChatComponentText(String.format("%s %s", Hydrogen.prefix, message));
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(chatcomponenttext);
    }

    public static void sendChatMessage(final ChatComponentText chatComponentText) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(chatComponentText);
    }

    public static String abbreviateString(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return input;
        } else {
            return input.substring(0, maxLength - 2) + "...";
        }
    }

    public static void startClip(float x1, float y1, float x2, float y2) {
        float temp;
        if (y1 > y2) {
            temp = y2;
            y2 = y1;
            y1 = temp;
        }

        GL11.glScissor((int) x1, (int) (Display.getHeight() - y2), (int) (x2 - x1), (int) (y2 - y1));
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
    }

    public static Color blend(Color color1, Color color2, double ratio) {
        float r = (float) ratio;
        float ir = (float) 1.0 - r;

        float[] rgb1 = new float[3];
        float[] rgb2 = new float[3];

        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);

        Color color = new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r + rgb2[1] * ir, rgb1[2] * r + rgb2[2] * ir);

        return color;
    }

    public static synchronized void playSound(final String url) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(Utils.class.getResourceAsStream("/assets/hydrogen/" + url));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }, "sound play").start();
    }

    public static double slide = 1D;
    public static void addSlide(double needX, double steps) {
        if (slide != needX) {
            if (slide < needX)
                if (slide <= needX - steps) {
                    slide += steps;
                } else if (slide > needX - steps) {
                    slide = needX;
                }
            if (slide > needX)
                if (slide >= needX + steps) {
                    slide -= steps;
                } else if (slide < needX + steps) {
                    slide = needX;
                }
        }
    }

    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }

    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void endClip() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static ScaledResolution getScaledRes() {
        final ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());
        return scaledRes;
    }

    public static int getRainbowInt(float seconds, float saturation, float brightness, long index) {
        float hue = ((System.currentTimeMillis() + index) % (int) (seconds * 1000)) / (seconds * 1000);
        int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }

    public static Color getRainbowColor(float seconds, float saturation, float brightness, long index) {
        float hue = ((System.currentTimeMillis() + index) % (int) (seconds * 1000)) / (seconds * 1000);
        Color color = Color.getHSBColor(hue, saturation, brightness);
        return color;
    }

    public static float[] getAngles(Entity entity) {
        double y, x = entity.posX - mc.thePlayer.posX;
        double z = entity.posZ - mc.thePlayer.posZ;

        if (entity instanceof net.minecraft.entity.monster.EntityEnderman) {
            y = entity.posY - mc.thePlayer.posY;
        } else {
            y = entity.posY + entity.getEyeHeight() - 1.9D - mc.thePlayer.posY + mc.thePlayer.getEyeHeight() - 1.9D;
        }
        double helper = MathHelper.sqrt_double(x * x + z * z);

        float newYaw = (float) Math.toDegrees(-Math.atan(x / z));
        float newPitch = (float) -Math.toDegrees(Math.atan(y / helper));
        if (z < 0.0D && x < 0.0D) {
            newYaw = (float) (90.0D + Math.toDegrees(Math.atan(z / x)));
        } else if (z < 0.0D && x > 0.0D) {
            newYaw = (float) (-90.0D + Math.toDegrees(Math.atan(z / x)));
        }
        return new float[]{newPitch, newYaw};
    }

    public static double getDistanceBetweenAngles(float angle1, float angle2) {
        float distance = Math.abs(angle1 - angle2) % 360.0F;
        if (distance > 180.0F) {
            distance = 360.0F - distance;
        }
        return distance;
    }

    public static void rect(float x1, float y1, float x2, float y2, int fill) {
        GlStateManager.color(0, 0, 0);
        GL11.glColor4f(0, 0, 0, 0);

        float f = (fill >> 24 & 0xFF) / 255.0F;
        float f1 = (fill >> 16 & 0xFF) / 255.0F;
        float f2 = (fill >> 8 & 0xFF) / 255.0F;
        float f3 = (fill & 0xFF) / 255.0F;

        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);

        GL11.glPushMatrix();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(7);
        GL11.glVertex2d(x2, y1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public void renderOutlines(double x, double y, double z, float width, float height, Color c) {
        float halfwidth = width / 2.0F;
        float halfheight = height / 2.0F;
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(false);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        y++;
        GL11.glLineWidth(1.2F);
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth)
                .color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        tessellator.draw();
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.enableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawTracer(Entity entity, Color color) {
        final RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        final double x = entity.posX - renderManager.renderPosX;
        final double y = entity.posY - renderManager.renderPosY;
        final double z = entity.posZ - renderManager.renderPosZ;
        final Vec3 eyeVector = new Vec3(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().thePlayer.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().thePlayer.rotationYaw));
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        glColor(color);
        GL11.glBegin(1);
        GL11.glVertex3d(eyeVector.xCoord, Minecraft.getMinecraft().thePlayer.getEyeHeight() + eyeVector.yCoord, eyeVector.zCoord);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y + entity.height, z);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GlStateManager.resetColor();
    }

    public void renderBoxWithOutline(double x, double y, double z, float width, float height, Color c) {
        renderBox(x, y, z, width, height, c);
        renderOutlines(x, y, z, width, height, c);
    }

    public static void rect(double x1, double y1, double x2, double y2, int fill) {
        GlStateManager.color(0, 0, 0);
        GL11.glColor4f(0, 0, 0, 0);

        float f = (fill >> 24 & 0xFF) / 255.0F;
        float f1 = (fill >> 16 & 0xFF) / 255.0F;
        float f2 = (fill >> 8 & 0xFF) / 255.0F;
        float f3 = (fill & 0xFF) / 255.0F;

        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);

        GL11.glPushMatrix();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(7);
        GL11.glVertex2d(x2, y1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void draw2DPlayerESP(final EntityPlayer ep, final double d, final double d1, final double d2) {
        final float distance = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(ep);
        final float scale = (float) (0.09 + Minecraft.getMinecraft().thePlayer.getDistance(ep.posX, ep.posY, ep.posZ) / 10000.0);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(-scale, -scale, scale);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glScaled(0.5, 0.5, 0.5);
        drawOutlineRect(-13.0f, -45.0f, 13.0f, 5.0f, -65536);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }

    public static void drawOutlineRect(final float drawX, final float drawY, final float drawWidth, final float drawHeight, final int color) {
        drawRect(drawX, drawY, drawWidth, drawY + 0.5f, color);
        drawRect(drawX, drawY + 0.5f, drawX + 0.5f, drawHeight, color);
        drawRect(drawWidth - 0.5f, drawY + 0.5f, drawWidth, drawHeight - 0.5f, color);
        drawRect(drawX + 0.5f, drawHeight - 0.5f, drawWidth, drawHeight, color);
    }

    public static void drawVLine(float x2, float y2, float x1, int y1) {
        if (x1 < y2) {
            float var5 = y2;
            y2 = x1;
            x1 = var5;
        }
        drawRect(x2, y2 + 1.0f, x2 + 1.0f, x1, y1);
    }

    public static void drawHLine(float x2, float y2, float x1, int y1) {
        if (y2 < x2) {
            float var5 = x2;
            x2 = y2;
            y2 = var5;
        }
        rect(x2, x1, y2 + 1.0f, x1 + 1.0f, y1);
    }
    public static void drawBorderedRect(float x2, float y2, float x1, float y1, int insideC, int borderC) {
        enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawVLine(x2 *= 2.0f, y2 *= 2.0f, y1 *= 2.0f, borderC);
        drawVLine((x1 *= 2.0f) - 1.0f, y2, y1, borderC);
        drawHLine(x2, x1 - 1.0f, y2, borderC);
        drawHLine(x2, x1 - 2.0f, y1 - 1.0f, borderC);
        rect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
    }



    public static void rectBorder(float x1, float y1, float x2, float y2, int outline) {
        rect(x1 + 1, y2 - 1, x2, y2, outline);
        rect(x1 + 1, y1, x2, y1 + 1, outline);
        rect(x1, y1, x1 + 1, y2, outline);
        rect(x2 - 1, y1 + 1, x2, y2 - 1, outline);
    }

    public static void renderBox(double x, double y, double z, float width, float height, Color c) {
        float halfwidth = width / 2.0F;
        float halfheight = height / 2.0F;
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(false);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        y++;
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        tessellator.draw();
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.enableBlend();
        GlStateManager.popMatrix();
    }

    public static double distance(float x, float y, float x1, float y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    public boolean canBlockBeSeen(BlockPos pos) {
        return (Minecraft.getMinecraft().theWorld.rayTraceBlocks(new Vec3(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight(), Minecraft.getMinecraft().thePlayer.posZ), new Vec3(pos.getX(), pos.getY(), pos.getZ())) == null);
    }

    public static void passSpecialRenderNameTags(EntityLivingBase p_77033_1_, double x, double y, double z) {
        if(Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
            if((p_77033_1_.getEntityId() != -3 ) && !(p_77033_1_.isInvisible()) && p_77033_1_ instanceof EntityPlayer)
            {
                if (Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
                    String p_147906_2_ = p_77033_1_.getDisplayName().getFormattedText();


                    double[] pos = Utils.entityWorldPos(p_77033_1_);
                    double[] pos2 = Utils.entityWorldPos(Minecraft.getMinecraft().thePlayer);
                    float xd = (float)(pos2[0]-pos[0]);
                    float yd = (float)(pos2[1]-pos[1]);
                    float zd = (float)(pos2[2]-pos[2]);
                    double dist = MathHelper.sqrt_float(xd*xd+yd*yd+zd*zd);

                    float distance = (float)dist;
                    float scaleFactor = distance < 10.0F ? 0.9F : distance / 11.11F;

                    int color = 16777215;
                    float height = 0.0F;
                    if ((p_77033_1_ instanceof EntityPlayer)) {
                        EntityPlayer player = (EntityPlayer) p_77033_1_;


                        if (distance >= 10.0F) {
                            height = (float) (height + (distance / 40.0F - 0.25D));
                        }
                    }
                    FontRenderer var12 = Minecraft.getMinecraft().fontRendererObj;
                    if (var12 == null) {
                        return;
                    }

                    p_147906_2_+= "";

                    float var13 = 1.6F;
                    float var14 = 0.016666668F * var13;
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float)x + 0.0F, (float)y + p_77033_1_.height + 0.5F, (float)z);
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


                    GlStateManager.enableTexture2D();
                    int co = -1;


                    if (Hydrogen.getClient().settingsManager.getSettingByName("Items").isEnabled() && p_77033_1_ != Minecraft.getMinecraft().thePlayer) {
                        NameTags.instance.renderArmorESP(p_77033_1_);
                    }
                }

                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                GlStateManager.enableLighting();
                GlStateManager.disableBlend();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();
                return;
            }
        }
    }

    public static void drawOutlineForEntity(final Entity e, final AxisAlignedBB axisalignedbb, final float width, final float red, final float green, final float blue, final float alpha) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(width);
        GL11.glColor4f(red, green, blue, alpha);
        drawOutlinedBox(axisalignedbb);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }


    public static void drawBorderedCircle(int x, int y, float radius, int outsideC, int insideC) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        float scale = 0.1F;
        GL11.glScalef(scale, scale, scale);
        x = (int) (x * (1.0F / scale));
        y = (int) (y * (1.0F / scale));
        radius *= 1.0F / scale;
        drawCircle(x, y, radius, insideC);
        drawUnfilledCircle(x, y, radius, 1.0F, outsideC);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void drawUnfilledCircle(int x, int y, float radius, float lineWidth, int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glLineWidth(lineWidth);
        GL11.glEnable(2848);
        GL11.glBegin(2);
        for (int i = 0; i <= 360; i++) {
            GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * radius, y + Math.cos(i * 3.141526D / 180.0D) * radius);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
    }

    public static void drawCircle(int x, int y, float radius, int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(9);
        for (int i = 0; i <= 360; i++) {
            GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * radius, y + Math.cos(i * 3.141526D / 180.0D) * radius);
        }
        GL11.glEnd();
    }

    public static void drawFilledBBESP(final AxisAlignedBB axisalignedbb, final int color) {
        GL11.glPushMatrix();
        final float red = (color >> 24 & 0xFF) / 255.0f;
        final float green = (color >> 16 & 0xFF) / 255.0f;
        final float blue = (color >> 8 & 0xFF) / 255.0f;
        final float alpha = (color & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawFilledBox(axisalignedbb);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawFilledBox(final AxisAlignedBB boundingBox) {
        if (boundingBox == null) {
            return;
        }
        GL11.glBegin(7);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glEnd();
        GL11.glBegin(7);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        GL11.glEnd();
    }

    public static Color glColor(int color, float alpha) {
        int hex = color;
        float red = (hex >> 16 & 255) / 255.0F;
        float green = (hex >> 8 & 255) / 255.0F;
        float blue = (hex & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
        return new Color(red, green, blue, alpha);
    }

    public static void glColor(Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }

    public static void drawBoundingBoxESP(final AxisAlignedBB axisalignedbb, final float width, final int color) {
        GL11.glPushMatrix();
        final float red = (color >> 24 & 0xFF) / 255.0f;
        final float green = (color >> 16 & 0xFF) / 255.0f;
        final float blue = (color >> 8 & 0xFF) / 255.0f;
        final float alpha = (color & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(width);
        GL11.glColor4f(red, green, blue, alpha);
        drawOutlinedBox(axisalignedbb);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedBox(final AxisAlignedBB boundingBox) {
        if (boundingBox == null) {
            return;
        }
        GL11.glBegin(3);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        GL11.glEnd();
    }

    public static void errorLog(String message) {
        System.err.println("[Hydrogen] " + message);
    }

    public static void log(String message) {
        System.out.println("[Hydrogen] " + message);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        int j;
        if (left < right) {
            j = (int) left;
            left = right;
            right = j;
        }

        if (top < bottom) {
            j = (int) top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static double[] entityWorldPos(Entity e) {
        float p_147936_2_ = Minecraft.getMinecraft().timer.renderPartialTicks;

        double x = (e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)p_147936_2_);
        double y = (e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)p_147936_2_);
        double z = (e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)p_147936_2_);

        return new double[] {x, y, z};
    }

    public static Session createSession(String username, String password, @NotNull Proxy proxy) throws Exception {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(proxy, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service
                .createUserAuthentication(Agent.MINECRAFT);

        auth.setUsername(username);
        auth.setPassword(password);

        auth.logIn();
        return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(),
                auth.getAuthenticatedToken(), "mojang");
    }

    /**
     * @return The direction of the player's movement in radians
     */
    public static double getDirection() {
        Minecraft mc = Minecraft.getMinecraft();

        float yaw = mc.thePlayer.rotationYaw;

        if (mc.thePlayer.moveForward < 0.0F) yaw += 180.0F;

        float forward = 1.0F;

        if (mc.thePlayer.moveForward < 0.0F) forward = -0.5F;
        else if (mc.thePlayer.moveForward > 0.0F) forward = 0.5F;

        if (mc.thePlayer.moveStrafing > 0.0F) yaw -= 90.0F * forward;

        if (mc.thePlayer.moveStrafing < 0.0F) yaw += 90.0F * forward;

        return Math.toRadians(yaw);
    }

    public static void drawBorderedCorneredRect(final float x, final float y, final float x2, final float y2, final float lineWidth, final int lineColor, final int bgColor) {
        drawRect(x, y, x2, y2, bgColor);
        final float f = (lineColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (lineColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (lineColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (lineColor & 0xFF) / 255.0f;
        drawRect(x - 1.0f, y - 1.0f, x2 + 1.0f, y, lineColor);
        drawRect(x - 1.0f, y, x, y2, lineColor);
        drawRect(x - 1.0f, y2, x2 + 1.0f, y2 + 1.0f, lineColor);
        drawRect(x2, y, x2 + 1.0f, y2, lineColor);
    }

    /*
     * By DarkStorm
     */
    public static Point calculateMouseLocation() {
        Minecraft minecraft = Minecraft.getMinecraft();
        int scale = minecraft.gameSettings.guiScale;
        if (scale == 0)
            scale = 1000;
        int scaleFactor = 0;
        while (scaleFactor < scale && minecraft.displayWidth / (scaleFactor + 1) >= 320 && minecraft.displayHeight / (scaleFactor + 1) >= 240)
            scaleFactor++;
        return new Point(Mouse.getX() / scaleFactor, minecraft.displayHeight / scaleFactor - Mouse.getY() / scaleFactor - 1);
    }

    public static float[] getRotationFromPosition(double x, double z, double y) {
        double xDiff = x - Minecraft.getMinecraft().thePlayer.posX;
        double zDiff = z - Minecraft.getMinecraft().thePlayer.posZ;
        double yDiff = y - Minecraft.getMinecraft().thePlayer.posY - 1.2;

        double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        float yaw = (float) (Math.atan2(zDiff, xDiff) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float) -(Math.atan2(yDiff, dist) * 180.0D / 3.141592653589793D);
        return new float[]{yaw, pitch};
    }

    public static float[] getRotations(EntityLivingBase ent) {
        double x = ent.posX;
        double z = ent.posZ;
        double y = ent.posY + ent.getEyeHeight() / 2.0F;
        return getRotationFromPosition(x, z, y);
    }

    public static float[] getBowAngles(final Entity entity) {
        final double xDelta = (entity.posX - entity.lastTickPosX) * 0.4;
        final double zDelta = (entity.posZ - entity.lastTickPosZ) * 0.4;
        double d = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity);
        d -= d % 0.8;
        double xMulti = 1.0;
        double zMulti = 1.0;
        final boolean sprint = entity.isSprinting();
        xMulti = d / 0.8 * xDelta * (sprint ? 1.25 : 1.0);
        zMulti = d / 0.8 * zDelta * (sprint ? 1.25 : 1.0);
        final double x = entity.posX + xMulti - Minecraft.getMinecraft().thePlayer.posX;
        final double z = entity.posZ + zMulti - Minecraft.getMinecraft().thePlayer.posZ;
        final double y = Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight() - (entity.posY + entity.getEyeHeight());
        final double dist = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity);
        final float yaw = (float) Math.toDegrees(Math.atan2(z, x)) - 90.0f;
        double d1 = MathHelper.sqrt_double(x * x + z * z);
        final float pitch =  (float) - (Math.atan2(y, d1) * 180.0D / Math.PI) + (float)dist*0.11f;

        return new float[]{yaw, -pitch};
    }


    public static String getCurrentCommitHash() {
        try {
            URL url = new URL("https://api.github.com/repos/zpeanut/hydrogen/commits/1.12");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String readLine = br.readLine();
            String[] splitLine = readLine.split("\"");
            String fullCommitHash = splitLine[3];
            String shortCommitHash = fullCommitHash.substring(0, Math.min(fullCommitHash.length(), 7));
            return shortCommitHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getCurrentCommitDate() {
        try {

            // read github api line

            URL url = new URL("https://api.github.com/repos/zpeanut/hydrogen/commits/1.12");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String readLine = br.readLine();

            // get date

            String[] splitLine = readLine.split("\"");
            String fullDate = splitLine[23];

            // split date string into 2 -> date, time

            String[] splitDate = fullDate.split("T");
            String localDate = splitDate[0];
            String localTimeFull = splitDate[1];

            // remove the "Z" at end of time string

            String localTimeShort = localTimeFull.substring(0, Math.min(fullDate.length(), 8));

            // put values into class defined variables

            commitTime = localTimeShort;
            commitDate = localDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
