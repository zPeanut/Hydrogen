package me.peanut.hydrogen.ui.mainmenu.utils;

import me.peanut.hydrogen.utils.FontUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

/**
 * Created by peanut on 25/02/2021
 */
public class ExpandButton extends GuiButton {
    private final int x;
    public final int y;
    private final int x1;
    private final int y1;
    private final String text;
    int alphaInc = 100;
    int alpha = 0;
    public int size = 0;
    public boolean tooltipEnabled;
    public boolean visible = true;
    public String tText2;
    public boolean cfont;

    public ExpandButton(int par1, int left, int top, int right, int bot, String par6Str) {
        super(par1, left, top, right, bot, par6Str);
        this.x = left;
        this.y = top;
        this.x1 = right;
        this.y1 = bot;
        this.text = par6Str;
    }

    public ExpandButton(int par1, int left, int top, int right, int bot, String par6Str, boolean visible) {
        super(par1, left, top, right, bot, par6Str);
        this.x = left;
        this.y = top;
        this.x1 = right;
        this.y1 = bot;
        this.text = par6Str;
        this.visible = visible;
    }

    public ExpandButton(boolean enabled, int par1, int par2, int par3, int par4, int par5, String par6Str) {
        super(par1, par2, par3, par4, par5, par6Str);
        this.x = par2;
        this.y = par3;
        this.x1 = par4;
        this.y1 = par5;
        this.text = par6Str;
        this.enabled = enabled;
    }

    public ExpandButton(int i, int j, int k, String stringParams) {
        this(i, j, k, 200, 20, stringParams);
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        boolean isOverButton = (mouseX >= this.x) && (mouseX <= this.x + this.x1) && (mouseY >= this.y) && (mouseY <= this.y + this.y1);
        if (!this.tooltipEnabled) {
            if ((isOverButton) && (this.alphaInc <= 150) && (this.enabled)) {
                this.alphaInc += 5;
                this.alpha = (this.alphaInc << 24);
            } else if ((!isOverButton) && (this.alphaInc >= 100)) {
                this.alphaInc -= 5;
                this.alpha = (this.alphaInc << 24);
            }
            if (this.alphaInc > 150) {
                this.alphaInc = 150;
            } else if (this.alphaInc < 100) {
                this.alphaInc = 100;
            }
            if ((isOverButton) && (this.size <= 1) && (this.enabled)) {
                this.size += 1;
            } else if ((!isOverButton) && (this.size >= 0)) {
                this.size -= 1;
            }
        }
        if(this.visible) {
            Utils.rect(this.x - this.size, this.y - this.size, this.x + this.x1 + this.size, this.y + this.y1 + this.size, this.alpha);
            if (!this.tooltipEnabled) {
                FontUtil.drawTotalCenteredStringWithShadow3(isOverButton && this.enabled ? "§7" + this.text : this.text, this.x + this.x1 / 2, this.y + this.y1 / 2, Color.white);
            }
        }
    }
}
