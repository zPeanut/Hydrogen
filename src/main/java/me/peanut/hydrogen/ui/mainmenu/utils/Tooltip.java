package me.peanut.hydrogen.ui.mainmenu.utils;

import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.clickgui.component.Frame;
import me.peanut.hydrogen.ui.clickgui.component.components.Button;
import me.peanut.hydrogen.ui.mainmenu.utils.ExpandButton;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Created by peanut on 25/12/2021
 */
public class Tooltip extends ExpandButton {
    private float padding = 6f;

    public Tooltip(int par1, int left, int top, int right, int bot, String par6Str) {
        super(par1, left, top, right, bot, par6Str);
    }

    public void update(int mouseX, int mouseY) {
        this.x = mouseX + 12;
        this.y = mouseY - 12;
    }

    public void render(String name) {
        this.width+= (-width);
        this.height = (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2);

        GlStateManager.color(0, 0, 0, 0);
        GL11.glColor4f(0, 0, 0, 0);

        RenderUtil.drawBorderedCorneredRect((int) (x - padding), (int) (y - padding) + 2, (int) (x + width + padding) + Minecraft.getMinecraft().fontRendererObj.getStringWidth(name), (int) (y + height + padding), 2, 0x90000000, 0x80000000);
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, (float) x, (float)y + (float)height - 4, -1);
        RenderUtil.startClip((int)(x - padding), y, (int)(x + width + padding), (int)(y + height + padding));
        RenderUtil.endClip();
    }
}
