package tk.peanut.hydrogen.font;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by peanut on 15/02/2021
 */
public class H2FontRenderer extends FontRenderer {

    public final Random fontRandom = new Random();
    private final Color[] customColorCodes = new Color[256];
    private final int[] colorCode = new int[32];
    private H2Font font;
    private H2Font boldFont;
    private H2Font italicFont;
    private H2Font boldItalicFont;
    private String colorcodeIdentifiers = "0123456789abcdefklmnor";
    private boolean bidi;

    public H2FontRenderer(Font font, boolean antiAlias, int charOffset) {
        super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
        this.setFont(font, antiAlias, charOffset);
        this.customColorCodes[113] = new Color(0, 90, 163);
        this.colorcodeIdentifiers = this.setupColorcodeIdentifier();
        this.setupMinecraftColorcodes();
        this.FONT_HEIGHT = this.getHeight();
    }

    public int drawString(String s, double x, double y, int color) {
        return this.drawString(s, x, y, color, false);
    }

    public int drawString(String s, double x, double y, Color color) {
        return this.drawString(s, x, y, color.getRGB(), false);
    }

    public int drawStringWithShadow(String s, double d, double e, int color) {
        return this.drawString(s, d, e, color, false);
    }

    public void drawCenteredString(String s, double d, double e, int color, boolean shadow) {
        if (shadow) {
            this.drawStringWithShadow(s, d - (double)(this.getStringWidth(s) / 2), e, color);
        } else {
            this.drawString(s, d - (double)(this.getStringWidth(s) / 2), e, color);
        }

    }

    public void drawCenteredString2(String s, double d, double e, int color, boolean shadow) {
        if (shadow) {
            this.drawStringWithShadow(s, d - (double)(this.getStringWidth(s) / 2), e, color);
        } else {
            this.drawString(s, d, e, color);
        }

    }

    public void drawCenteredString(String s, int x, int y, int color) {
        this.drawStringWithShadow(s, (float)(x - this.getStringWidth(s) / 2), (float)y, color);
    }

    public int drawString(String text, double d, double e, int color, boolean shadow) {
        int result = 0;

        try {
            if (text != null && text != "") {
                String[] lines = text.split("\n");

                for(int i = 0; i < lines.length; ++i) {
                    result = this.drawLine(lines[i], d, e + (double)(i * this.getHeight()), color, shadow);
                }
            }
        } catch (Exception var11) {
        }

        return result;
    }

    private int drawLine(String text, double d, double e, int color, boolean shadow) {
        if (text == null) {
            return 0;
        } else {
            GL11.glPushMatrix();
            GL11.glTranslated(d - 1.5D, e + 0.5D, 0.0D);
            boolean wasBlend = GL11.glGetBoolean(3042);
            GlStateManager.enableAlpha();
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3553);
            if ((color & -67108864) == 0) {
                color |= -16777216;
            }

            if (shadow) {
                color = (color & 16579836) >> 2 | color & -16777216;
            }

            float red = (float)(color >> 16 & 255) / 255.0F;
            float green = (float)(color >> 8 & 255) / 255.0F;
            float blue = (float)(color & 255) / 255.0F;
            float alpha = (float)(color >> 24 & 255) / 255.0F;
            Color c = new Color(red, green, blue, alpha);
            if (text.contains("§")) {
                String[] parts = text.split("§");
                Color currentColor = c;
                H2Font currentFont = this.font;
                int width = 0;
                boolean randomCase = false;
                boolean bold = false;
                boolean italic = false;
                boolean strikethrough = false;
                boolean underline = false;

                for(int index = 0; index < parts.length; ++index) {
                    if (parts[index].length() > 0) {
                        if (index == 0) {
                            currentFont.drawString(parts[index], (double)width, 0.0D, currentColor, shadow);
                            width += currentFont.getStringWidth(parts[index]);
                        } else {
                            String words = parts[index].substring(1);
                            char type = parts[index].charAt(0);
                            int colorIndex = this.colorcodeIdentifiers.indexOf(type);
                            if (colorIndex != -1) {
                                if (colorIndex < 16) {
                                    int colorcode = this.colorCode[colorIndex];
                                    currentColor = this.getColor(colorcode, alpha);
                                    bold = false;
                                    italic = false;
                                    randomCase = false;
                                    underline = false;
                                    strikethrough = false;
                                } else if (colorIndex == 16) {
                                    randomCase = true;
                                } else if (colorIndex == 17) {
                                    bold = true;
                                } else if (colorIndex == 18) {
                                    strikethrough = true;
                                } else if (colorIndex == 19) {
                                    underline = true;
                                } else if (colorIndex == 20) {
                                    italic = true;
                                } else if (colorIndex == 21) {
                                    bold = false;
                                    italic = false;
                                    randomCase = false;
                                    underline = false;
                                    strikethrough = false;
                                    currentColor = c;
                                } else if (colorIndex > 21) {
                                    Color customColor = this.customColorCodes[type];
                                    currentColor = new Color((float)customColor.getRed() / 255.0F, (float)customColor.getGreen() / 255.0F, (float)customColor.getBlue() / 255.0F, alpha);
                                }
                            }

                            if (bold && italic) {
                                this.boldItalicFont.drawString(randomCase ? this.toRandom(currentFont, words) : words, (double)width, 0.0D, currentColor, shadow);
                                currentFont = this.boldItalicFont;
                            } else if (bold) {
                                this.boldFont.drawString(randomCase ? this.toRandom(currentFont, words) : words, (double)width, 0.0D, currentColor, shadow);
                                currentFont = this.boldFont;
                            } else if (italic) {
                                this.italicFont.drawString(randomCase ? this.toRandom(currentFont, words) : words, (double)width, 0.0D, currentColor, shadow);
                                currentFont = this.italicFont;
                            } else {
                                this.font.drawString(randomCase ? this.toRandom(currentFont, words) : words, (double)width, 0.0D, currentColor, shadow);
                                currentFont = this.font;
                            }

                            float u = (float)this.font.getHeight() / 16.0F;
                            int h = currentFont.getStringHeight(words);
                            if (strikethrough) {
                                this.drawLine((double)width / 2.0D + 1.0D, (double)(h / 3), (double)(width + currentFont.getStringWidth(words)) / 2.0D + 1.0D, (double)(h / 3), u);
                            }

                            if (underline) {
                                this.drawLine((double)width / 2.0D + 1.0D, (double)(h / 2), (double)(width + currentFont.getStringWidth(words)) / 2.0D + 1.0D, (double)(h / 2), u);
                            }

                            width += currentFont.getStringWidth(words);
                        }
                    }
                }
            } else {
                this.font.drawString(text, 0.0D, 0.0D, c, shadow);
            }

            if (!wasBlend) {
                GL11.glDisable(3042);
            }

            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            return (int)(d + (double)this.getStringWidth(text));
        }
    }

    private String toRandom(H2Font font, String text) {
        String newText = "";
        String allowedCharacters = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000";
        char[] var8;
        int var7 = (var8 = text.toCharArray()).length;

        for(int var6 = 0; var6 < var7; ++var6) {
            char c = var8[var6];
            if (ChatAllowedCharacters.isAllowedCharacter(c)) {
                int index = this.fontRandom.nextInt("ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".length());
                newText = newText + "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".toCharArray()[index];
            }
        }

        return newText;
    }

    public int getStringHeight(String text) {
        return text == null ? 0 : this.font.getStringHeight(text) / 2;
    }

    public int getHeight() {
        return this.font.getHeight() / 2;
    }

    public static String getFormatFromString(String p_78282_0_) {
        String var1 = "";
        int var2 = -1;
        int var3 = p_78282_0_.length();

        while((var2 = p_78282_0_.indexOf(167, var2 + 1)) != -1) {
            if (var2 < var3 - 1) {
                char var4 = p_78282_0_.charAt(var2 + 1);
                if (isFormatColor(var4)) {
                    var1 = "§" + var4;
                } else if (isFormatSpecial(var4)) {
                    var1 = var1 + "§" + var4;
                }
            }
        }

        return var1;
    }

    private static boolean isFormatSpecial(char formatChar) {
        return formatChar >= 'k' && formatChar <= 'o' || formatChar >= 'K' && formatChar <= 'O' || formatChar == 'r' || formatChar == 'R';
    }

    public int getColorCode(char p_175064_1_) {
        return this.colorCode["0123456789abcdef".indexOf(p_175064_1_)];
    }

    public void setBidiFlag(boolean state) {
        this.bidi = state;
    }

    public boolean getBidiFlag() {
        return this.bidi;
    }

    private int sizeStringToWidth(String str, int wrapWidth) {
        int var3 = str.length();
        int var4 = 0;
        int var5 = 0;
        int var6 = -1;

        for(boolean var7 = false; var5 < var3; ++var5) {
            char var8 = str.charAt(var5);
            switch(var8) {
                case '\n':
                    --var5;
                    break;
                case ' ':
                    var6 = var5;
                default:
                    var4 += this.getStringWidth(Character.toString(var8));
                    if (var7) {
                        ++var4;
                    }
                    break;
                case '§':
                    if (var5 < var3 - 1) {
                        ++var5;
                        char var9 = str.charAt(var5);
                        if (var9 != 'l' && var9 != 'L') {
                            if (var9 == 'r' || var9 == 'R' || isFormatColor(var9)) {
                                var7 = false;
                            }
                        } else {
                            var7 = true;
                        }
                    }
            }

            if (var8 == '\n') {
                ++var5;
                var6 = var5;
                break;
            }

            if (var4 > wrapWidth) {
                break;
            }
        }

        return var5 != var3 && var6 != -1 && var6 < var5 ? var6 : var5;
    }

    private static boolean isFormatColor(char colorChar) {
        return colorChar >= '0' && colorChar <= '9' || colorChar >= 'a' && colorChar <= 'f' || colorChar >= 'A' && colorChar <= 'F';
    }

    public int getCharWidth(char c) {
        return this.getStringWidth(Character.toString(c));
    }

    public int getStringWidth(String text) {
        if (text == null) {
            return 0;
        } else if (!text.contains("§")) {
            return this.font.getStringWidth(text) / 2;
        } else {
            String[] parts = text.split("§");
            H2Font currentFont = this.font;
            int width = 0;
            boolean bold = false;
            boolean italic = false;

            for(int index = 0; index < parts.length; ++index) {
                if (parts[index].length() > 0) {
                    if (index == 0) {
                        width += currentFont.getStringWidth(parts[index]);
                    } else {
                        String words = parts[index].substring(1);
                        char type = parts[index].charAt(0);
                        int colorIndex = this.colorcodeIdentifiers.indexOf(type);
                        if (colorIndex != -1) {
                            if (colorIndex < 16) {
                                bold = false;
                                italic = false;
                            } else if (colorIndex != 16) {
                                if (colorIndex == 17) {
                                    bold = true;
                                } else if (colorIndex != 18 && colorIndex != 19) {
                                    if (colorIndex == 20) {
                                        italic = true;
                                    } else if (colorIndex == 21) {
                                        bold = false;
                                        italic = false;
                                    }
                                }
                            }
                        }

                        if (bold && italic) {
                            currentFont = this.boldItalicFont;
                        } else if (bold) {
                            currentFont = this.boldFont;
                        } else if (italic) {
                            currentFont = this.italicFont;
                        } else {
                            currentFont = this.font;
                        }

                        width += currentFont.getStringWidth(words);
                    }
                }
            }

            return width / 2;
        }
    }

    public void setFont(Font font, boolean antiAlias, int charOffset) {
        synchronized(this) {
            this.font = new H2Font(font, antiAlias, charOffset);
            this.boldFont = new H2Font(font.deriveFont(1), antiAlias, charOffset);
            this.italicFont = new H2Font(font.deriveFont(2), antiAlias, charOffset);
            this.boldItalicFont = new H2Font(font.deriveFont(3), antiAlias, charOffset);
            this.FONT_HEIGHT = this.getHeight();
        }
    }

    public H2Font getFont() {
        return this.font;
    }

    public String getFontName() {
        return this.font.getFont().getFontName();
    }

    public int getSize() {
        return this.font.getFont().getSize();
    }

    public void setSize(int size) {
        this.font.setFont(new Font(this.getFontName(), this.font.getFont().getStyle(), size));
    }

    public java.util.List<String> wrapWords(String text, double width) {
        java.util.List<String> finalWords = new ArrayList();
        if ((double)this.getStringWidth(text) > width) {
            String[] words = text.split(" ");
            String currentWord = "";
            char lastColorCode = '\uffff';
            String[] var11 = words;
            int var10 = words.length;

            String s;
            for(int var9 = 0; var9 < var10; ++var9) {
                s = var11[var9];

                for(int i = 0; i < s.toCharArray().length; ++i) {
                    char c = s.toCharArray()[i];
                    if (c == 167 && i < s.toCharArray().length - 1) {
                        lastColorCode = s.toCharArray()[i + 1];
                    }
                }

                if ((double)this.getStringWidth(currentWord + s + " ") < width) {
                    currentWord = currentWord + s + " ";
                } else {
                    finalWords.add(currentWord);
                    currentWord = lastColorCode == -1 ? s + " " : "§" + lastColorCode + s + " ";
                }
            }

            if (!currentWord.equals("")) {
                if ((double)this.getStringWidth(currentWord) < width) {
                    finalWords.add(lastColorCode == -1 ? currentWord + " " : "§" + lastColorCode + currentWord + " ");
                    currentWord = "";
                } else {
                    Iterator var14 = this.formatString(currentWord, width).iterator();

                    while(var14.hasNext()) {
                        s = (String)var14.next();
                        finalWords.add(s);
                    }
                }
            }
        } else {
            finalWords.add(text);
        }

        return finalWords;
    }

    public java.util.List<String> formatString(String s, double width) {
        java.util.List<String> finalWords = new ArrayList();
        String currentWord = "";
        char lastColorCode = '\uffff';

        for(int i = 0; i < s.toCharArray().length; ++i) {
            char c = s.toCharArray()[i];
            if (c == 167 && i < s.toCharArray().length - 1) {
                lastColorCode = s.toCharArray()[i + 1];
            }

            if ((double)this.getStringWidth(currentWord + c) < width) {
                currentWord = currentWord + c;
            } else {
                finalWords.add(currentWord);
                currentWord = lastColorCode == -1 ? String.valueOf(c) : "§" + lastColorCode + c;
            }
        }

        if (!currentWord.equals("")) {
            finalWords.add(currentWord);
        }

        return finalWords;
    }

    private void drawLine(double x, double y, double x1, double y1, float width) {
        GL11.glDisable(3553);
        GL11.glLineWidth(width);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glEnable(3553);
    }

    public boolean isAntiAliasing() {
        return this.font.isAntiAlias() && this.boldFont.isAntiAlias() && this.italicFont.isAntiAlias() && this.boldItalicFont.isAntiAlias();
    }

    public void setAntiAliasing(boolean antiAlias) {
        this.font.setAntiAlias(antiAlias);
        this.boldFont.setAntiAlias(antiAlias);
        this.italicFont.setAntiAlias(antiAlias);
        this.boldItalicFont.setAntiAlias(antiAlias);
    }

    private void setupMinecraftColorcodes() {
        for(int index = 0; index < 32; ++index) {
            int var6 = (index >> 3 & 1) * 85;
            int var7 = (index >> 2 & 1) * 170 + var6;
            int var8 = (index >> 1 & 1) * 170 + var6;
            int var9 = (index >> 0 & 1) * 170 + var6;
            if (index == 6) {
                var7 += 85;
            }

            if (index >= 16) {
                var7 /= 4;
                var8 /= 4;
                var9 /= 4;
            }

            this.colorCode[index] = (var7 & 255) << 16 | (var8 & 255) << 8 | var9 & 255;
        }

    }

    public String trimStringToWidth(String p_78269_1_, int p_78269_2_) {
        return this.trimStringToWidth(p_78269_1_, p_78269_2_, false);
    }

    public String trimStringToWidth(String p_78262_1_, int p_78262_2_, boolean p_78262_3_) {
        StringBuilder var4 = new StringBuilder();
        int var5 = 0;
        int var6 = p_78262_3_ ? p_78262_1_.length() - 1 : 0;
        int var7 = p_78262_3_ ? -1 : 1;
        boolean var8 = false;
        boolean var9 = false;

        for(int var10 = var6; var10 >= 0 && var10 < p_78262_1_.length() && var5 < p_78262_2_; var10 += var7) {
            char var11 = p_78262_1_.charAt(var10);
            int var12 = this.getStringWidth(Character.toString(var11));
            if (var8) {
                var8 = false;
                if (var11 != 'l' && var11 != 'L') {
                    if (var11 == 'r' || var11 == 'R') {
                        var9 = false;
                    }
                } else {
                    var9 = true;
                }
            } else if (var12 < 0) {
                var8 = true;
            } else {
                var5 += var12;
                if (var9) {
                    ++var5;
                }
            }

            if (var5 > p_78262_2_) {
                break;
            }

            if (p_78262_3_) {
                var4.insert(0, var11);
            } else {
                var4.append(var11);
            }
        }

        return var4.toString();
    }

    public List<String> listFormattedStringToWidth(String str, int wrapWidth) {
        return Arrays.asList(this.wrapFormattedStringToWidth(str, wrapWidth).split("\n"));
    }

    protected String wrapFormattedStringToWidth(String str, int wrapWidth) {
        int var3 = this.sizeStringToWidth(str, wrapWidth);
        if (str.length() <= var3) {
            return str;
        } else {
            String var4 = str.substring(0, var3);
            char var5 = str.charAt(var3);
            boolean var6 = var5 == ' ' || var5 == '\n';
            String var7 = getFormatFromString(var4) + str.substring(var3 + (var6 ? 1 : 0));
            return var4 + "\n" + this.wrapFormattedStringToWidth(var7, wrapWidth);
        }
    }

    public Color getColor(int colorCode, float alpha) {
        return new Color((float)(colorCode >> 16) / 255.0F, (float)(colorCode >> 8 & 255) / 255.0F, (float)(colorCode & 255) / 255.0F, alpha);
    }

    private String setupColorcodeIdentifier() {
        String minecraftColorCodes = "0123456789abcdefklmnor";

        for(int i = 0; i < this.customColorCodes.length; ++i) {
            if (this.customColorCodes[i] != null) {
                minecraftColorCodes = minecraftColorCodes + (char)i;
            }
        }

        return minecraftColorCodes;
    }

    public void onResourceManagerReload(IResourceManager p_110549_1_) {
    }
}

