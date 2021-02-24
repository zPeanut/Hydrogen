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

/**
 * Created by peanut on 22/02/2021
 */
@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends GuiScreen {

    @Shadow
    public abstract void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_);

    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.renderSkybox(mouseX, mouseY, partialTicks);
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
    }

}
