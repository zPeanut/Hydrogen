package me.peanut.hydrogen.utils;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.render.NameTags;
import me.peanut.hydrogen.ui.ingame.components.Hotbar;
import me.peanut.hydrogen.ui.ingame.components.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

/**
 * Created by peanut on 18/01/2022
 */
public class RenderUtil {

    private static final Minecraft mc = Minecraft.getMinecraft();

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

    public static void drawPotionEffectsMC() {
        int offset = 0;
        for (Object var4 : Minecraft.getMinecraft().thePlayer.getActivePotionEffects()) {
            int posY = 11 * offset;
            PotionEffect effect = (PotionEffect) var4;
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            String name = I18n.format(potion.getName());
            switch (effect.getAmplifier()) {
                case 0:
                    name += " I";
                    break;
                case 1:
                    name += " II";
                    break;
                case 2:
                    name += " III";
                    break;
                case 3:
                    name += " IV";
                    break;
                case 4:
                    name += " V";
                    break;
                case 5:
                    name += " VI";
                    break;
                case 6:
                    name += " VII";
                    break;
                case 7:
                    name += " VIII";
                    break;
                case 8:
                    name += " IX";
                    break;
                case 9:
                    name += " X";
                    break;
                default:
                    name += " X+";
                    break;
            }
            name += " §7(§f" + Potion.getDurationString(effect) + "§7)";
            int color = Integer.MIN_VALUE;
            switch (effect.getEffectName()) {
                case "potion.weither":
                    color = -16777246;
                    break;
                case "potion.weakness":
                    color = -9864951;
                    break;
                case "potion.waterBreathing":
                    color = -16758065;
                    break;
                case "potion.saturation":
                    color = -11159217;
                    break;
                case "potion.resistance":
                    color = -5655199;
                    break;
                case "potion.regeneration":
                    color = -1145130;
                    break;
                case "potion.poison":
                    color = -14553374;
                    break;
                case "potion.nightVision":
                    color = -6735204;
                    break;
                case "potion.moveSpeed":
                    color = -7875870;
                    break;
                case "potion.moveSlowdown":
                    color = -16751493;
                    break;
                case "potion.jump":
                    color = -5375161;
                    break;
                case "potion.invisibility":
                    color = -9405272;
                    break;
                case "potion.hunger":
                    color = -16754448;
                    break;
                case "potion.heal":
                    color = -65556;
                    break;
                case "potion.harm":
                    color = -3735043;
                    break;
                case "potion.fireResistance":
                    color = -29656;
                    break;
                case "potion.healthBoost":
                    color = -40151;
                    break;
                case "potion.digSpeed":
                    color = -989556;
                    break;
                case "potion.digSlowdown":
                    color = -5655199;
                    break;
                case "potion.damageBoost":
                    color = -7665712;
                    break;
                case "potion.confusion":
                    color = -5195482;
                    break;
                case "potion.blindness":
                    color = -8355712;
                    break;
                case "potion.absorption":
                    color = -23256;
                    break;
            }
            Info info = new Info();
            boolean infoIsRight = Hydrogen.getClient().settingsManager.getSettingByName(info, "Alignment").getMode().equalsIgnoreCase("Right");
            boolean infoEnabled = Hydrogen.getClient().moduleManager.getModule(Info.class).isEnabled();
            boolean chatOpen = mc.ingameGUI.getChatGUI().getChatOpen();
            boolean hotbar = Hydrogen.getClient().moduleManager.getModule(Hotbar.class).isEnabled();
            boolean tephra = Hydrogen.getClient().settingsManager.getSettingByName("Style").getMode().equalsIgnoreCase("Tephra");
            if(hotbar) {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) - 2, Utils.getScaledRes().getScaledHeight() - posY - 34, color);
            } else {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) - 2, Utils.getScaledRes().getScaledHeight() - posY - 12 - (tephra ? 12 : (infoIsRight && infoEnabled ? 22 : 0)) - (chatOpen ? 14 : 0), color);
            }
            ++offset;
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

    public static void renderOutlines(double x, double y, double z, float width, float height, Color c) {
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
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y - halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z - halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x - halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        worldRenderer.pos(x + halfwidth, y + halfheight, z + halfwidth).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
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
        ColorUtil.glColor(color);
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

    public static void renderBoxWithOutline(double x, double y, double z, float width, float height, Color c) {
        renderBox(x, y, z, width, height, c);
        renderOutlines(x, y, z, width, height, c);
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
        rect(drawX, drawY, drawWidth, drawY + 0.5f, color);
        rect(drawX, drawY + 0.5f, drawX + 0.5f, drawHeight, color);
        rect(drawWidth - 0.5f, drawY + 0.5f, drawWidth, drawHeight - 0.5f, color);
        rect(drawX + 0.5f, drawHeight - 0.5f, drawWidth, drawHeight, color);
    }

    public static void drawVLine(float x2, float y2, float x1, int y1) {
        if (x1 < y2) {
            float var5 = y2;
            y2 = x1;
            x1 = var5;
        }
        rect(x2, y2 + 1.0f, x2 + 1.0f, x1, y1);
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

    public static void passSpecialRenderNameTags(EntityLivingBase entityLivingBaseIn, double x, double y, double z) {
        if(Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
            if((entityLivingBaseIn.getEntityId() != -3 ) && !(entityLivingBaseIn.isInvisible()) && entityLivingBaseIn instanceof EntityPlayer)
            {
                if (Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
                    String displayname = entityLivingBaseIn.getDisplayName().getFormattedText();


                    double[] pos = PlayerUtil.entityWorldPos(entityLivingBaseIn);
                    double[] pos2 = PlayerUtil.entityWorldPos(Minecraft.getMinecraft().thePlayer);
                    float xd = (float)(pos2[0]-pos[0]);
                    float yd = (float)(pos2[1]-pos[1]);
                    float zd = (float)(pos2[2]-pos[2]);
                    double dist = MathHelper.sqrt_float(xd*xd+yd*yd+zd*zd);

                    float distance = (float)dist;
                    float scaleFactor = distance < 10.0F ? 0.9F : distance / 11.11F;

                    int color = 16777215;
                    float height = 0.0F;
                    if (entityLivingBaseIn instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) entityLivingBaseIn;


                        if (distance >= 10.0F) {
                            height = (float) (height + (distance / 40.0F - 0.25D));
                        }
                    }
                    FontRenderer var12 = Minecraft.getMinecraft().fontRendererObj;

                    if (var12 == null) {
                        return;
                    }

                    displayname+= "";

                    float var13 = 1.6F;
                    float var14 = 0.016666668F * var13;
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float)x + 0.0F, (float)y + entityLivingBaseIn.height + 0.5F, (float)z);
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

                    int var18 = var12.getStringWidth(displayname) / 2;
                    float w = var18;
                    float h = var12.FONT_HEIGHT;
                    float offY = 0;


                    GlStateManager.enableTexture2D();
                    int co = -1;


                    if (Hydrogen.getClient().settingsManager.getSettingByName("Items").isEnabled() && entityLivingBaseIn != Minecraft.getMinecraft().thePlayer) {
                        NameTags.instance.renderArmorESP(entityLivingBaseIn);
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

    public static void drawBorderedCorneredRect(final float x, final float y, final float x2, final float y2, final float lineWidth, final int lineColor, final int bgColor) {
        rect(x, y, x2, y2, bgColor);
        final float f = (lineColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (lineColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (lineColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (lineColor & 0xFF) / 255.0f;
        rect(x - 1.0f, y - 1.0f, x2 + 1.0f, y, lineColor);
        rect(x - 1.0f, y, x, y2, lineColor);
        rect(x - 1.0f, y2, x2 + 1.0f, y2 + 1.0f, lineColor);
        rect(x2, y, x2 + 1.0f, y2, lineColor);
    }

}
