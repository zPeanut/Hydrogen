package tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiNewChat;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperIChatComponent;

import java.util.List;

public class WrapperGuiNewChat extends WrapperGui {
    private GuiNewChat real;

    public WrapperGuiNewChat(GuiNewChat var1) {
        super(var1);
        this.real = var1;
    }

    public static int calculateChatboxWidth(float var0) {
        return GuiNewChat.calculateChatboxWidth(var0);
    }

    public static int calculateChatboxHeight(float var0) {
        return GuiNewChat.calculateChatboxHeight(var0);
    }

    public GuiNewChat unwrap() {
        return this.real;
    }

    public void drawChat(int var1) {
        this.real.drawChat(var1);
    }

    public void clearChatMessages() {
        this.real.clearChatMessages();
    }

    public void printChatMessage(WrapperIChatComponent var1) {
        this.real.printChatMessage(var1.unwrap());
    }

    public void printChatMessageWithOptionalDeletion(WrapperIChatComponent var1, int var2) {
        this.real.printChatMessageWithOptionalDeletion(var1.unwrap(), var2);
    }

    public void refreshChat() {
        this.real.refreshChat();
    }

    public List getSentMessages() {
        return this.real.getSentMessages();
    }

    public void addToSentMessages(String var1) {
        this.real.addToSentMessages(var1);
    }

    public void resetScroll() {
        this.real.resetScroll();
    }

    public void scroll(int var1) {
        this.real.scroll(var1);
    }

    public WrapperIChatComponent getChatComponent(int var1, int var2) {
        return new WrapperIChatComponent(this.real.getChatComponent(var1, var2));
    }

    public boolean getChatOpen() {
        return this.real.getChatOpen();
    }

    public void deleteChatLine(int var1) {
        this.real.deleteChatLine(var1);
    }

    public int getChatWidth() {
        return this.real.getChatWidth();
    }

    public int getChatHeight() {
        return this.real.getChatHeight();
    }

    public float getChatScale() {
        return this.real.getChatScale();
    }

    public int getLineCount() {
        return this.real.getLineCount();
    }
}
