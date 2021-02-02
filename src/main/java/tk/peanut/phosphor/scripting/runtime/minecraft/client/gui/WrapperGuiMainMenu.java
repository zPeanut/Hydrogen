package tk.peanut.phosphor.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiMainMenu;

public class WrapperGuiMainMenu extends WrapperGuiScreen {
    private GuiMainMenu real;

    public WrapperGuiMainMenu(GuiMainMenu var1) {
        super(var1);
        this.real = var1;
    }

    public GuiMainMenu unwrap() {
        return this.real;
    }

    public void updateScreen() {
        this.real.updateScreen();
    }

    public boolean doesGuiPauseGame() {
        return this.real.doesGuiPauseGame();
    }

    public void initGui() {
        this.real.initGui();
    }

    public void confirmClicked(boolean var1, int var2) {
        this.real.confirmClicked(var1, var2);
    }

    public void drawScreen(int var1, int var2, float var3) {
        this.real.drawScreen(var1, var2, var3);
    }

    public String getField_96138_a() {
        return GuiMainMenu.field_96138_a;
    }
}
