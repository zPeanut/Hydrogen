package me.peanut.hydrogen.font;

import com.darkmagician6.eventapi.EventManager;
import me.peanut.hydrogen.events.EventText;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by peanut on 15/02/2021
 */
public class H2Font {
    public int IMAGE_WIDTH = 1024;
    public int IMAGE_HEIGHT = 1024;
    private int texID;
    private final IntObject[] chars = new IntObject[2048];
    private Font font;
    private boolean antiAlias;
    private int fontHeight = -1;
    private int charOffset = 8;

    public H2Font(Font font, boolean antiAlias, int charOffset) {
        this.font = font;
        this.antiAlias = antiAlias;
        this.charOffset = charOffset;
        this.setupTexture(antiAlias);
    }

    public H2Font(Font font, boolean antiAlias) {
        this.font = font;
        this.antiAlias = antiAlias;
        this.charOffset = 8;
        this.setupTexture(antiAlias);
    }

    public void setFont(Font font) {
        this.font = font;
    }

    private void setupTexture(boolean antiAlias) {
        if (this.font.getSize() <= 15) {
            this.IMAGE_WIDTH = 256;
            this.IMAGE_HEIGHT = 256;
        }
        if (this.font.getSize() <= 43) {
            this.IMAGE_WIDTH = 512;
            this.IMAGE_HEIGHT = 512;
        } else if (this.font.getSize() <= 91) {
            this.IMAGE_WIDTH = 1024;
            this.IMAGE_HEIGHT = 1024;
        } else {
            this.IMAGE_WIDTH = 2048;
            this.IMAGE_HEIGHT = 2048;
        }
        BufferedImage img = new BufferedImage(this.IMAGE_WIDTH, this.IMAGE_HEIGHT, 2);
        Graphics2D g = (Graphics2D)img.getGraphics();
        g.setFont(this.font);
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, this.IMAGE_WIDTH, this.IMAGE_HEIGHT);
        g.setColor(Color.white);
        int rowHeight = 0;
        int positionX = 0;
        int positionY = 0;
        for (int i = 0; i < 2048; ++i) {
            char ch = (char)i;
            BufferedImage fontImage = this.getFontImage(ch, antiAlias);
            IntObject newIntObject = new IntObject();
            newIntObject.width = fontImage.getWidth();
            newIntObject.height = fontImage.getHeight();
            if (positionX + newIntObject.width >= this.IMAGE_WIDTH) {
                positionX = 0;
                positionY += rowHeight;
                rowHeight = 0;
            }
            newIntObject.storedX = positionX;
            newIntObject.storedY = positionY;
            if (newIntObject.height > this.fontHeight) {
                this.fontHeight = newIntObject.height;
            }
            if (newIntObject.height > rowHeight) {
                rowHeight = newIntObject.height;
            }
            this.chars[i] = newIntObject;
            g.drawImage(fontImage, positionX, positionY, null);
            positionX += newIntObject.width;
        }
        try {
            this.texID = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), img, true, true);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getFontImage(char ch, boolean antiAlias) {
        int charheight;
        BufferedImage tempfontImage = new BufferedImage(1, 1, 2);
        Graphics2D g = (Graphics2D)tempfontImage.getGraphics();
        if (antiAlias) {
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else {
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
        g.setFont(this.font);
        FontMetrics fontMetrics = g.getFontMetrics();
        int charwidth = fontMetrics.charWidth(ch) + 8;
        if (charwidth <= 0) {
            charwidth = 7;
        }
        if ((charheight = fontMetrics.getHeight() + 3) <= 0) {
            charheight = this.font.getSize();
        }
        BufferedImage fontImage = new BufferedImage(charwidth, charheight, 2);
        Graphics2D gt = (Graphics2D)fontImage.getGraphics();
        if (antiAlias) {
            gt.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else {
            gt.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
        gt.setFont(this.font);
        gt.setColor(Color.WHITE);
        int charx = 3;
        boolean chary = true;
        gt.drawString(String.valueOf(ch), 3, 1 + fontMetrics.getAscent());
        return fontImage;
    }

    public void drawChar(char c, float x, float y) throws ArrayIndexOutOfBoundsException {
        try {
            this.drawQuad(x, y, this.chars[c].width, this.chars[c].height, this.chars[c].storedX, this.chars[c].storedY, this.chars[c].width, this.chars[c].height);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawQuad(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
        float renderSRCX = srcX / (float)this.IMAGE_WIDTH;
        float renderSRCY = srcY / (float)this.IMAGE_HEIGHT;
        float renderSRCWidth = srcWidth / (float)this.IMAGE_WIDTH;
        float renderSRCHeight = srcHeight / (float)this.IMAGE_HEIGHT;
        GL11.glBegin(4);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x + width, y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x + width, y + height);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x + width, y);
        GL11.glEnd();
    }

    public void drawString(String text, double x, double y, Color color, boolean shadow) {
        EventText e = new EventText(text);
        EventManager.call(e);
        text = e.getText();
        x *= 2.0;
        y = y * 2.0 - 2.0;
        GL11.glPushMatrix();
        GL11.glScaled(0.25, 0.25, 0.25);
        TextureUtil.bindTexture(this.texID);
        this.glColor(shadow ? new Color(0.05f, 0.05f, 0.05f, (float)color.getAlpha() / 255.0f) : color);
        int size = text.length();
        for (int indexInString = 0; indexInString < size; ++indexInString) {
            char character = text.charAt(indexInString);
            if (character >= this.chars.length || character < '\u0000') continue;
            this.drawChar(character, (float)x, (float)y);
            x += (this.chars[character].width - this.charOffset);
        }
        GL11.glPopMatrix();
    }



    public void glColor(Color color) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public int getStringHeight(String text) {
        int lines = 1;
        char[] arrayOfChar = text.toCharArray();
        int i = arrayOfChar.length;
        for (int b = 0; b < i; b = ((byte)(b + 1))) {
            char c = arrayOfChar[b];
            if (c != '\n') continue;
            ++lines;
        }
        return (this.fontHeight - this.charOffset) / 2 * lines;
    }

    public int getHeight() {
        return (this.fontHeight - this.charOffset) / 2;
    }

    public int getStringWidth(String text) {
        EventText e = new EventText(text);
        EventManager.call(e);
        text = e.getText();
        int width = 0;
        char[] arrayOfChar = text.toCharArray();
        int i = arrayOfChar.length;
        for (int b = 0; b < i; b = ((byte)(b + 1))) {
            char c = arrayOfChar[b];
            if (c >= this.chars.length || c < '\u0000') continue;
            width += this.chars[c].width - this.charOffset;
        }
        return width / 2;
    }

    public boolean isAntiAlias() {
        return this.antiAlias;
    }

    public void setAntiAlias(boolean antiAlias) {
        if (this.antiAlias != antiAlias) {
            this.antiAlias = antiAlias;
            this.setupTexture(antiAlias);
        }
    }

    public Font getFont() {
        return this.font;
    }

    private class IntObject {
        public int width;
        public int height;
        public int storedX;
        public int storedY;

        private IntObject() {
        }
    }
}
