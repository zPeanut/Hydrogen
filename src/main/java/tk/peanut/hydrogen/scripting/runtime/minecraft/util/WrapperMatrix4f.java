package tk.peanut.hydrogen.scripting.runtime.minecraft.util;

import net.minecraft.util.Matrix4f;

public class WrapperMatrix4f {
    private Matrix4f real;

    public WrapperMatrix4f(Matrix4f var1) {
        this.real = var1;
    }

    public Matrix4f unwrap() {
        return this.real;
    }
}
