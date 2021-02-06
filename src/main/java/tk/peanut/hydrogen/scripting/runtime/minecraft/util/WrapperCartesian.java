package tk.peanut.hydrogen.scripting.runtime.minecraft.util;

import net.minecraft.util.Cartesian;

public class WrapperCartesian {
    private Cartesian real;

    public WrapperCartesian(Cartesian var1) {
        this.real = var1;
    }

    public static Iterable cartesianProduct(Class var0, Iterable var1) {
        return Cartesian.cartesianProduct(var0, var1);
    }

    public static Iterable cartesianProduct(Iterable var0) {
        return Cartesian.cartesianProduct(var0);
    }

    public Cartesian unwrap() {
        return this.real;
    }
}
