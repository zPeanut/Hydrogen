package tk.peanut.hydrogen.font;

import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

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
        setupTexture(antiAlias);
    }

    public H2Font(Font font, boolean antiAlias) {
        this.font = font;
        this.antiAlias = antiAlias;
        this.charOffset = 8;
        setupTexture(antiAlias);
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
        for (int i = 0; i < 2048; i++) {
            char ch = (char)i;
            BufferedImage fontImage = getFontImage(ch, antiAlias);
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
            if (newIntObject.height > this.fontHeight)
                this.fontHeight = newIntObject.height;
            if (newIntObject.height > rowHeight)
                rowHeight = newIntObject.height;
            this.chars[i] = newIntObject;
            g.drawImage(fontImage, positionX, positionY, (ImageObserver)null);
            positionX += newIntObject.width;
        }
        try {
            this.texID = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), img, true, true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getFontImage(char ch, boolean antiAlias) {
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
        if (charwidth <= 0)
            charwidth = 7;
        int charheight = fontMetrics.getHeight() + 3;
        if (charheight <= 0)
            charheight = this.font.getSize();
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
        int chary = 1;
        gt.drawString(String.valueOf(ch), 3, 1 + fontMetrics.getAscent());
        return fontImage;
    }

    public void drawChar(char c, float x, float y) throws ArrayIndexOutOfBoundsException {
        try {
            drawQuad(x, y, (this.chars[c]).width, (this.chars[c]).height, (this.chars[c]).storedX,
                    (this.chars[c]).storedY, (this.chars[c]).width, (this.chars[c]).height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawQuad(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
        float renderSRCX = srcX / this.IMAGE_WIDTH, renderSRCY = srcY / this.IMAGE_HEIGHT;
        float renderSRCWidth = srcWidth / this.IMAGE_WIDTH, renderSRCHeight = srcHeight / this.IMAGE_HEIGHT;
        GL11.glBegin(4);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d((x + width), y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, (y + height));
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, (y + height));
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        GL11.glVertex2d((x + width), (y + height));
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d((x + width), y);
        GL11.glEnd();
    }

    public void drawString(String text, double x, double y, Color color, boolean shadow) {
        x *= 2.0D;
        y = y * 2.0D - 2.0D;
        GL11.glPushMatrix();
        GL11.glScaled(0.25D, 0.25D, 0.25D);
        TextureUtil.bindTexture(this.texID);
        glColor(shadow ? new Color(0.05F, 0.05F, 0.05F, color.getAlpha() / 255.0F) : color);
        int size = text.length();
        for (int indexInString = 0; indexInString < size; indexInString++) {
            char character = text.charAt(indexInString);
            if (character < this.chars.length && character >= '\000') {
                drawChar(character, (float)x, (float)y);
                x += ((this.chars[character]).width - this.charOffset);
            }
        }
        GL11.glPopMatrix();
    }

    public void glColor(Color color) {
        float red = color.getRed() / 255.0F, green = color.getGreen() / 255.0F, blue = color.getBlue() / 255.0F;
        float alpha = color.getAlpha() / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public int getStringHeight(String text) {
        int lines = 1;
        byte b;
        int i;
        char[] arrayOfChar;
        for (i = (arrayOfChar = text.toCharArray()).length, b = 0; b < i; ) {
            char c = arrayOfChar[b];
            if (c == '\n')
                lines++;
            b = (byte)(b + 1);
        }
        return (this.fontHeight - this.charOffset) / 2 * lines;
    }

    public int getHeight() {
        return (this.fontHeight - this.charOffset) / 2;
    }

    public int getStringWidth(String text) {
        int width = 0;
        byte b;
        int i;
        char[] arrayOfChar;
        for (i = (arrayOfChar = text.toCharArray()).length, b = 0; b < i; ) {
            char c = arrayOfChar[b];
            if (c < this.chars.length && c >= '\000')
                width += (this.chars[c]).width - this.charOffset;
            b = (byte)(b + 1);
        }
        return width / 2;
    }

    public boolean isAntiAlias() {
        return this.antiAlias;
    }

    public void setAntiAlias(boolean antiAlias) {
        if (this.antiAlias != antiAlias) {
            this.antiAlias = antiAlias;
            setupTexture(antiAlias);
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

        private IntObject() {}
    }
}
