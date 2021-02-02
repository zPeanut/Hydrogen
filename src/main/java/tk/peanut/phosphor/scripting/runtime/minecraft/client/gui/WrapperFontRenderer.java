package tk.peanut.phosphor.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.FontRenderer;

import java.util.List;
import java.util.Random;

public class WrapperFontRenderer {
    private FontRenderer real;

    public WrapperFontRenderer(FontRenderer var1) {
        this.real = var1;
    }

    public static String getFormatFromString(String var0) {
        return FontRenderer.getFormatFromString(var0);
    }

    public FontRenderer unwrap() {
        return this.real;
    }

    public int drawStringWithShadow(String var1, float var2, float var3, int var4) {
        return this.real.drawStringWithShadow(var1, var2, var3, var4);
    }

    public int drawString(String var1, int var2, int var3, int var4) {
        return this.real.drawString(var1, var2, var3, var4);
    }

    public int drawString(String var1, float var2, float var3, int var4, boolean var5) {
        return this.real.drawString(var1, var2, var3, var4, var5);
    }

    public int getStringWidth(String var1) {
        return this.real.getStringWidth(var1);
    }

    public int getCharWidth(char var1) {
        return this.real.getCharWidth(var1);
    }

    public String trimStringToWidth(String var1, int var2) {
        return this.real.trimStringToWidth(var1, var2);
    }

    public String trimStringToWidth(String var1, int var2, boolean var3) {
        return this.real.trimStringToWidth(var1, var2, var3);
    }

    public void drawSplitString(String var1, int var2, int var3, int var4, int var5) {
        this.real.drawSplitString(var1, var2, var3, var4, var5);
    }

    public int splitStringWidth(String var1, int var2) {
        return this.real.splitStringWidth(var1, var2);
    }

    public boolean getUnicodeFlag() {
        return this.real.getUnicodeFlag();
    }

    public void setUnicodeFlag(boolean var1) {
        this.real.setUnicodeFlag(var1);
    }

    public List listFormattedStringToWidth(String var1, int var2) {
        return this.real.listFormattedStringToWidth(var1, var2);
    }

    public boolean getBidiFlag() {
        return this.real.getBidiFlag();
    }

    public void setBidiFlag(boolean var1) {
        this.real.setBidiFlag(var1);
    }

    public int getColorCode(char var1) {
        return this.real.getColorCode(var1);
    }

    public int getFONT_HEIGHT() {
        return this.real.FONT_HEIGHT;
    }

    public void setFONT_HEIGHT(int var1) {
        this.real.FONT_HEIGHT = var1;
    }

    public Random getFontRandom() {
        return this.real.fontRandom;
    }

    public void setFontRandom(Random var1) {
        this.real.fontRandom = var1;
    }
}
