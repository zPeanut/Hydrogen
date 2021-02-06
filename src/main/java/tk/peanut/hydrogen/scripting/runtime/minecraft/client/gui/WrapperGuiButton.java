package tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.WrapperMinecraft;

public class WrapperGuiButton extends WrapperGui {
    private GuiButton real;

    public WrapperGuiButton(GuiButton var1) {
        super(var1);
        this.real = var1;
    }

    public GuiButton unwrap() {
        return this.real;
    }

    public void drawButton(WrapperMinecraft var1, int var2, int var3) {
        this.real.drawButton(var1.unwrap(), var2, var3);
    }

    public void mouseReleased(int var1, int var2) {
        this.real.mouseReleased(var1, var2);
    }

    public boolean mousePressed(WrapperMinecraft var1, int var2, int var3) {
        return this.real.mousePressed(var1.unwrap(), var2, var3);
    }

    public boolean isMouseOver() {
        return this.real.isMouseOver();
    }

    public void drawButtonForegroundLayer(int var1, int var2) {
        this.real.drawButtonForegroundLayer(var1, var2);
    }

    public int getButtonWidth() {
        return this.real.getButtonWidth();
    }

    public void setWidth(int var1) {
        this.real.setWidth(var1);
    }

    public int getXPosition() {
        return this.real.xPosition;
    }

    public void setXPosition(int var1) {
        this.real.xPosition = var1;
    }

    public int getYPosition() {
        return this.real.yPosition;
    }

    public void setYPosition(int var1) {
        this.real.yPosition = var1;
    }

    public String getDisplayString() {
        return this.real.displayString;
    }

    public void setDisplayString(String var1) {
        this.real.displayString = var1;
    }

    public int getId() {
        return this.real.id;
    }

    public void setId(int var1) {
        this.real.id = var1;
    }

    public boolean isEnabled() {
        return this.real.enabled;
    }

    public void setEnabled(boolean var1) {
        this.real.enabled = var1;
    }

    public boolean isVisible() {
        return this.real.visible;
    }

    public void setVisible(boolean var1) {
        this.real.visible = var1;
    }
}
