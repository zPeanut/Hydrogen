package tk.peanut.hydrogen.scripting.runtime.minecraft.util;

import net.minecraft.util.Util;

public class WrapperUtil {
    private Util real;

    public WrapperUtil(Util var1) {
        this.real = var1;
    }

    public Util unwrap() {
        return this.real;
    }
}
