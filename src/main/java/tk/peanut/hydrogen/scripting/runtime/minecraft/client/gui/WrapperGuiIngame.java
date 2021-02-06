package tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiIngame;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperIChatComponent;

public class WrapperGuiIngame extends WrapperGui {
    private GuiIngame real;

    public WrapperGuiIngame(GuiIngame var1) {
        super(var1);
        this.real = var1;
    }

    public GuiIngame unwrap() {
        return this.real;
    }


    public void renderGameOverlay(float var1) {
        this.real.renderGameOverlay(var1);
    }

    public void renderHorseJumpBar(WrapperScaledResolution var1, int var2) {
        this.real.renderHorseJumpBar(var1.unwrap(), var2);
    }

    public void renderExpBar(WrapperScaledResolution var1, int var2) {
        this.real.renderExpBar(var1.unwrap(), var2);
    }

    public void renderDemo(WrapperScaledResolution var1) {
        this.real.renderDemo(var1.unwrap());
    }

    public void renderStreamIndicator(WrapperScaledResolution var1) {
        this.real.renderStreamIndicator(var1.unwrap());
    }

    public void updateTick() {
        this.real.updateTick();
    }

    public void setRecordPlayingMessage(String var1) {
        this.real.setRecordPlayingMessage(var1);
    }

    public void setRecordPlaying(String var1, boolean var2) {
        this.real.setRecordPlaying(var1, var2);
    }

    public void displayTitle(String var1, String var2, int var3, int var4, int var5) {
        this.real.displayTitle(var1, var2, var3, var4, var5);
    }

    public void setRecordPlaying(WrapperIChatComponent var1, boolean var2) {
        this.real.setRecordPlaying(var1.unwrap(), var2);
    }

    public WrapperGuiNewChat getChatGUI() {
        return new WrapperGuiNewChat(this.real.getChatGUI());
    }

    public int getUpdateCounter() {
        return this.real.getUpdateCounter();
    }

    public WrapperFontRenderer getFontRenderer() {
        return new WrapperFontRenderer(this.real.getFontRenderer());
    }


    public float getPrevVignetteBrightness() {
        return this.real.prevVignetteBrightness;
    }

    public void setPrevVignetteBrightness(float var1) {
        this.real.prevVignetteBrightness = var1;
    }
}
