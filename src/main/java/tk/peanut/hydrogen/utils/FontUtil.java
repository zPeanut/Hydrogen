package tk.peanut.hydrogen.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StringUtils;

import java.awt.*;

/**
 * Created by peanut on 04/02/2021
 */
public class FontUtil {

    private static FontRenderer fontRenderer;


    public static void setupFontUtils() {
        fontRenderer = Minecraft.getMinecraft().fontRendererObj;
    }

    public static int getStringWidth(String text) {
        return fontRenderer.getStringWidth(StringUtils.stripControlCodes(text));
    }

    public static int getFontHeight() {
        return fontRenderer.FONT_HEIGHT;
    }

    public static void drawString(String text, double x, double y, int color) {
        fontRenderer.drawString(text, (int)x, (int)y, color);
    }

    public static void TTFdrawString(String text, double x, double y, Color color) {
        FontHelper.sf_l.drawStringWithShadow(text, (int)x, (int)y, color);
    }

    public static void drawStringWithShadow(String text, double x, double y, int color) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, (float) x, (float) y, color);
    }

    public static void drawStringSFL(String text, double x, double y, Color color) {
        FontHelper.sf_l.drawStringWithShadow(text, (float) x, (float) y, color);
    }

    public static void drawStringVerdana(String text, double x, double y, Color color) {
        FontHelper.verdana.drawStringWithShadow(text, (float) x, (float) y, color);
    }

    public static void drawStringComfortaa(String text, double x, double y, Color color) {
        FontHelper.comfortaa_r.drawStringWithShadowMainMenu(text, (float) x, (float) y, color);
    }

    public static void drawCenteredString(String text, double x, double y, int color) {
        drawString(text, x - fontRenderer.getStringWidth(text) / 2, y, color);
    }


    public static void drawCenteredString(String text, double x, double y, Color color) {
        TTFdrawString(text, x - FontHelper.sf_l.getStringWidth(text) / 2, y, color);
    }

    public static void drawCenteredStringWithShadow(String text, double x, double y, int color) {
        drawStringWithShadow(text, x - fontRenderer.getStringWidth(text) / 2, y, color);
    }

    public static void drawTotalCenteredString(String text, double x, double y, int color) {
        drawString(text, x - fontRenderer.getStringWidth(text) / 2, y - fontRenderer.FONT_HEIGHT / 2, color);
    }

    public static void drawTotalCenteredStringWithShadow(String text, double x, double y, int color) {
        drawStringWithShadow(text, x - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) / 2, y - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2F, color);
    }

    public static void drawTotalCenteredStringWithShadow2(String text, double x, double y, Color color) {
        drawStringSFL(text, x - FontHelper.sf_l.getStringWidth(text) / 2, y - FontHelper.sf_l.FONT_HEIGHT / 2F, color);
    }

    public static void drawTotalCenteredStringWithShadow3(String text, double x, double y, Color color) {
        drawStringComfortaa(text, x - FontHelper.comfortaa_r.getStringWidth(text) / 2, y - FontHelper.comfortaa_r.FONT_HEIGHT / 2F, color);
    }

    public static void drawTotalCenteredStringWithShadow4(String text, double x, double y, Color color) {
        drawStringVerdana(text, x - FontHelper.verdana.getStringWidth(text) / 2, y - FontHelper.verdana.FONT_HEIGHT / 2F, color);
    }

    }
