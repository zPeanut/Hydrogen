package tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.Gui;

public class WrapperGui {
    private Gui real;

    public WrapperGui(Gui var1) {
        this.real = var1;
    }

    public static void drawRect(int var0, int var1, int var2, int var3, int var4) {
        Gui.drawRect(var0, var1, var2, var3, var4);
    }

    public static void drawModalRectWithCustomSizedTexture(int var0, int var1, float var2, float var3, int var4, int var5, float var6, float var7) {
        Gui.drawModalRectWithCustomSizedTexture(var0, var1, var2, var3, var4, var5, var6, var7);
    }

    public static void drawScaledCustomSizeModalRect(int var0, int var1, float var2, float var3, int var4, int var5, int var6, int var7, float var8, float var9) {
        Gui.drawScaledCustomSizeModalRect(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9);
    }

    public Gui unwrap() {
        return this.real;
    }

    public void drawCenteredString(WrapperFontRenderer var1, String var2, int var3, int var4, int var5) {
        this.real.drawCenteredString(var1.unwrap(), var2, var3, var4, var5);
    }

    public void drawString(WrapperFontRenderer var1, String var2, int var3, int var4, int var5) {
        this.real.drawString(var1.unwrap(), var2, var3, var4, var5);
    }

    public void drawTexturedModalRect(int var1, int var2, int var3, int var4, int var5, int var6) {
        this.real.drawTexturedModalRect(var1, var2, var3, var4, var5, var6);
    }

    public void drawTexturedModalRect(float var1, float var2, int var3, int var4, int var5, int var6) {
        this.real.drawTexturedModalRect(var1, var2, var3, var4, var5, var6);
    }
}
