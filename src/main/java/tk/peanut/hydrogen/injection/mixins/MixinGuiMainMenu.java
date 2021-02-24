package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import tk.peanut.hydrogen.ui.mainmenu.MainMenu;
import tk.peanut.hydrogen.utils.FontHelper;

import java.awt.*;

/**
 * Created by peanut on 22/02/2021
 */
@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends GuiScreen {

    @Shadow
    public abstract void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_);

    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        renderSkybox(mouseX, mouseY, partialTicks);
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        drawGradientRect(0, 0, this.width, this.height, 0, -1610612736);
        drawGradientRect(0, 0, this.width, this.height, 0x804DB3FF, 0x80000000);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.width / 2, this.height - 50, 0.0F);
        float var8 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
        GL11.glScalef(var8, var8, var8);
        GL11.glPopMatrix();

        // border



        drawRect(this.width / 4, this.height / 2 - 80, this.width / 4 + 2, this.height / 2 + 104, 0x60000000);
        drawRect(this.width - (this.width / 4) - 2, this.height / 2 - 80, this.width - (this.width / 4), this.height / 2 + 104, 0x60000000);

        drawRect(this.width / 4 , this.height / 2 - 80, this.width - (this.width / 4), this.height / 2 + 104, 0x60000000);


        drawRect(this.width / 4 + 2, this.height / 2 + 102, this.width - (this.width / 4) - 2, this.height / 2 + 104, 0x60000000);
        drawRect(this.width / 4 + 2, this.height / 2 - 80,this.width - (this.width / 4) - 2, this.height / 2 - 78, 0x60000000);

        // Strings at bottom

        FontHelper.hfontnormal.drawStringWithShadow("Hydrogen v1.5-dev", 4, 4, Color.white);
        FontHelper.hfontnormal.drawStringWithShadow("Developed by §7zPeanut §fand §7UltramoxX", 4, 16, Color.white);
        FontHelper.hfontnormal.drawStringWithShadow("Logged in as §7zPeanut", 4, 28, Color.white);

        FontHelper.hfontnormal.drawStringWithShadow("Minecraft 1.8.9", this.width - mc.fontRendererObj.getStringWidth("Minecraft 1.8.9") - 4, 4, Color.white);
        FontHelper.hfontnormal.drawStringWithShadow("MCP 9.19", this.width - mc.fontRendererObj.getStringWidth("MCP 9.19") - 4, 16, Color.white);
        FontHelper.hfontnormal.drawStringWithShadow("Powered by Forge 11.5.1.1722", this.width - mc.fontRendererObj.getStringWidth("Powered by Forge 11.5.1.1722") - 4, 28, Color.white);
        FontHelper.hfontnormal.drawStringWithShadow("6 mods loaded, 6 mods active", this.width - mc.fontRendererObj.getStringWidth("6 mods loaded, 6 mods active") - 4, 40, Color.white);


        float scale = 7.0F;

        // MAIN MENU

        GL11.glScalef(scale, scale, scale);
        FontHelper.hfontnormal.drawStringWithShadow("H", this.width / 2 / scale - 7, this.height / 2 / scale - 10.5F, Color.white);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        float scale2 = 3f;

        GL11.glScalef(scale2, scale2, scale2);
        FontHelper.hfontnormal.drawStringWithShadow("2", this.width / 2 / scale2 - 2, this.height / 2 / scale2 - 11F, Color.white);
        GL11.glScalef(1.0F / scale2, 1.0F / scale2, 1.0F / scale2);

        float scalever = 2.0F;
        GL11.glScalef(scalever, scalever, scalever);
        FontHelper.hfontnormal.drawStringWithShadow("§7v1.5-dev", this.width / 2 / scalever + 6, this.height / 2 / scalever - 34F, Color.white);
        GL11.glScalef(1.0F / scalever, 1.0F / scalever, 1.0F / scalever);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
