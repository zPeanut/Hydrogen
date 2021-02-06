package tk.peanut.hydrogen.scripting.runtime.minecraft.util;

import net.minecraft.util.StringUtils;

public class WrapperStringUtils {
    private StringUtils real;

    public WrapperStringUtils(StringUtils var1) {
        this.real = var1;
    }

    public static String ticksToElapsedTime(int var0) {
        return StringUtils.ticksToElapsedTime(var0);
    }

    public static String stripControlCodes(String var0) {
        return StringUtils.stripControlCodes(var0);
    }

    public static boolean isNullOrEmpty(String var0) {
        return StringUtils.isNullOrEmpty(var0);
    }

    public StringUtils unwrap() {
        return this.real;
    }
}
