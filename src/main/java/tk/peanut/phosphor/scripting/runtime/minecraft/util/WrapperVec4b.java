package tk.peanut.phosphor.scripting.runtime.minecraft.util;

import net.minecraft.util.Vec4b;

public class WrapperVec4b {
    private Vec4b real;

    public WrapperVec4b(Vec4b var1) {
        this.real = var1;
    }

    public Vec4b unwrap() {
        return this.real;
    }

    public byte func_176110_a() {
        return this.real.func_176110_a();
    }

    public byte func_176112_b() {
        return this.real.func_176112_b();
    }

    public byte func_176113_c() {
        return this.real.func_176113_c();
    }

    public byte func_176111_d() {
        return this.real.func_176111_d();
    }

    public boolean equals(Object var1) {
        return this.real.equals(var1);
    }

    public int hashCode() {
        return this.real.hashCode();
    }
}
