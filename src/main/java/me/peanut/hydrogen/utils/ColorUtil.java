package me.peanut.hydrogen.utils;

import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by peanut on 18/01/2022
 */
public class ColorUtil {

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

}
