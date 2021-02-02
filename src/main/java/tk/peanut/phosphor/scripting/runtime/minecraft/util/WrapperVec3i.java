package tk.peanut.phosphor.scripting.runtime.minecraft.util;

import net.minecraft.util.Vec3i;

public class WrapperVec3i {
    private Vec3i real;

    public WrapperVec3i(Vec3i var1) {
        this.real = var1;
    }

    public Vec3i unwrap() {
        return this.real;
    }

    public boolean equals(Object var1) {
        return this.real.equals(var1);
    }

    public int hashCode() {
        return this.real.hashCode();
    }

    public int compareTo(WrapperVec3i var1) {
        return this.real.compareTo(var1.unwrap());
    }

    public int getX() {
        return this.real.getX();
    }

    public int getY() {
        return this.real.getY();
    }

    public int getZ() {
        return this.real.getZ();
    }

    public WrapperVec3i crossProduct(WrapperVec3i var1) {
        return new WrapperVec3i(this.real.crossProduct(var1.unwrap()));
    }

    public double distanceSq(double param1, double param3, double param5) {
        return real.distanceSq(param1, param3, param5);
    }

    public double distanceSqToCenter(double param1, double param3, double param5) {
        return real.distanceSqToCenter(param1, param3, param5);
    }

    public double distanceSq(WrapperVec3i var1) {
        return this.real.distanceSq(var1.unwrap());
    }

    public String toString() {
        return this.real.toString();
    }

    public WrapperVec3i getNULL_VECTOR() {
        return new WrapperVec3i(Vec3i.NULL_VECTOR);
    }
}
