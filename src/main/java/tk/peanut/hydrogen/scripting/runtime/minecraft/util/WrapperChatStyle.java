package tk.peanut.hydrogen.scripting.runtime.minecraft.util;

import net.minecraft.util.ChatStyle;

public class WrapperChatStyle {
    private ChatStyle real;

    public WrapperChatStyle(ChatStyle var1) {
        this.real = var1;
    }

    public ChatStyle unwrap() {
        return this.real;
    }

    public boolean getBold() {
        return this.real.getBold();
    }

    public WrapperChatStyle setBold(Boolean var1) {
        return new WrapperChatStyle(this.real.setBold(var1));
    }

    public boolean getItalic() {
        return this.real.getItalic();
    }

    public WrapperChatStyle setItalic(Boolean var1) {
        return new WrapperChatStyle(this.real.setItalic(var1));
    }

    public boolean getStrikethrough() {
        return this.real.getStrikethrough();
    }

    public WrapperChatStyle setStrikethrough(Boolean var1) {
        return new WrapperChatStyle(this.real.setStrikethrough(var1));
    }

    public boolean getUnderlined() {
        return this.real.getUnderlined();
    }

    public WrapperChatStyle setUnderlined(Boolean var1) {
        return new WrapperChatStyle(this.real.setUnderlined(var1));
    }

    public boolean getObfuscated() {
        return this.real.getObfuscated();
    }

    public WrapperChatStyle setObfuscated(Boolean var1) {
        return new WrapperChatStyle(this.real.setObfuscated(var1));
    }

    public boolean isEmpty() {
        return this.real.isEmpty();
    }

    public String getInsertion() {
        return this.real.getInsertion();
    }

    public WrapperChatStyle setInsertion(String var1) {
        return new WrapperChatStyle(this.real.setInsertion(var1));
    }

    public WrapperChatStyle setParentStyle(WrapperChatStyle var1) {
        return new WrapperChatStyle(this.real.setParentStyle(var1.unwrap()));
    }

    public String getFormattingCode() {
        return this.real.getFormattingCode();
    }

    public String toString() {
        return this.real.toString();
    }

    public boolean equals(Object var1) {
        return this.real.equals(var1);
    }

    public int hashCode() {
        return this.real.hashCode();
    }

    public WrapperChatStyle createShallowCopy() {
        return new WrapperChatStyle(this.real.createShallowCopy());
    }

    public WrapperChatStyle createDeepCopy() {
        return new WrapperChatStyle(this.real.createDeepCopy());
    }
}
