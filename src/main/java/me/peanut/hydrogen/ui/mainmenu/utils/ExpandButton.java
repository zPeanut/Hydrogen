package me.peanut.hydrogen.ui.mainmenu.utils;

import me.peanut.hydrogen.font.FontUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

/**
 * Created by peanut on 25/02/2021
 */
public class ExpandButton extends GuiButton {
    public int x;
    public int y;
    private final int x1;
    private final int y1;
    private final String text;
    int alphaInc = 100;
    int alpha = 0;
    public int size = 0;
    public boolean visible = true;
    public String tooltipText;
    public boolean tooltipEnabled;
    public Tooltip tooltip;

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

    public ExpandButton(int par1, int left, int top, int right, int bot, String par6Str, boolean tooltipEnabled, String tooltip) {
        super(par1, left, top, right, bot, par6Str);
        this.tooltip = new Tooltip(par1, left, right, top, bot, par6Str);
        this.x = left;
        this.y = top;
        this.x1 = right;
        this.y1 = bot;
        this.text = par6Str;
        this.tooltipEnabled = tooltipEnabled;
        this.tooltipText = tooltip;
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
        if (this.visible) {
            if (this.tooltipEnabled && isOverButton) {
                tooltip.update(mouseX, mouseY);
                tooltip.render(tooltipText);
            } else {
                RenderUtil.rect(this.x - this.size, this.y - this.size, this.x + this.x1 + this.size, this.y + this.y1 + this.size, this.alpha);
                if (!this.tooltipEnabled) {
                    FontUtil.drawTotalCenteredStringWithShadowComfortaa(isOverButton && this.enabled ? "§7" + this.text : this.text, this.x + this.x1 / 2, this.y + this.y1 / 2, Color.white);
                }
            }
        }
    }
}
