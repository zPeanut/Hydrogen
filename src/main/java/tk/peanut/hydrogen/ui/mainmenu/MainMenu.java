package tk.peanut.hydrogen.ui.mainmenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Created by peanut on 24/02/2021
 */
public class MainMenu extends GuiMainMenu {


    public void addRect() {

        drawRect(this.width - this.width, this.height / 2 - 80, this.width, this.height / 2 + 104, 0x60000000);
        drawRect(this.width - this.width, this.height / 2 + 102, this.width, this.height / 2 + 104, 0x60000000);
        drawRect(this.width - this.width, this.height / 2 - 80, this.width, this.height / 2 - 78, 0x60000000);

    }
}
