package tk.peanut.phosphor.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiSlider;
import tk.peanut.phosphor.scripting.runtime.minecraft.client.WrapperMinecraft;

public class WrapperGuiSlider extends WrapperGuiButton {
    private GuiSlider real;

    public WrapperGuiSlider(GuiSlider var1) {
        super(var1);
        this.real = var1;
    }

    public GuiSlider unwrap() {
        return this.real;
    }

    public float func_175220_c() {
        return this.real.func_175220_c();
    }

    public void func_175218_a(float var1, boolean var2) {
        this.real.func_175218_a(var1, var2);
    }

    public float func_175217_d() {
        return this.real.func_175217_d();
    }

    public void func_175219_a(float var1) {
        this.real.func_175219_a(var1);
    }

    public boolean mousePressed(WrapperMinecraft var1, int var2, int var3) {
        return this.real.mousePressed(var1.unwrap(), var2, var3);
    }

    public void mouseReleased(int var1, int var2) {
        this.real.mouseReleased(var1, var2);
    }

    public boolean IsMouseDown() {
        return this.real.isMouseDown;
    }

    public void setIsMouseDown(boolean var1) {
        this.real.isMouseDown = var1;
    }
}
