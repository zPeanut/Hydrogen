package tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiIngameMenu;

public class WrapperGuiIngameMenu extends WrapperGuiScreen {
    private GuiIngameMenu real;

    public WrapperGuiIngameMenu(GuiIngameMenu var1) {
        super(var1);
        this.real = var1;
    }

    public GuiIngameMenu unwrap() {
        return this.real;
    }

    public void initGui() {
        this.real.initGui();
    }

    public void updateScreen() {
        this.real.updateScreen();
    }

    public void drawScreen(int var1, int var2, float var3) {
        this.real.drawScreen(var1, var2, var3);
    }
}
