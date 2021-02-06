package tk.peanut.hydrogen.scripting.runtime.minecraft.util;

import net.minecraft.util.IChatComponent;

public class WrapperIChatComponent {
    private IChatComponent real;

    public WrapperIChatComponent(IChatComponent var1) {
        this.real = var1;
    }

    public IChatComponent unwrap() {
        return this.real;
    }
}
