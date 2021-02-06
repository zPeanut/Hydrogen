package tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiChat;

import java.io.IOException;

public class WrapperGuiChat extends WrapperGuiScreen {
    private GuiChat real;

    public WrapperGuiChat(GuiChat var1) {
        super(var1);
        this.real = var1;
    }

    public GuiChat unwrap() {
        return this.real;
    }

    public void initGui() {
        this.real.initGui();
    }

    public void onGuiClosed() {
        this.real.onGuiClosed();
    }

    public void updateScreen() {
        this.real.updateScreen();
    }

    public void handleMouseInput() throws IOException {
        this.real.handleMouseInput();
    }

    public void autocompletePlayerNames() {
        this.real.autocompletePlayerNames();
    }

    public void getSentHistory(int var1) {
        this.real.getSentHistory(var1);
    }

    public void drawScreen(int var1, int var2, float var3) {
        this.real.drawScreen(var1, var2, var3);
    }

    public void onAutocompleteResponse(String[] var1) {
        this.real.onAutocompleteResponse(var1);
    }

    public boolean doesGuiPauseGame() {
        return this.real.doesGuiPauseGame();
    }
}
