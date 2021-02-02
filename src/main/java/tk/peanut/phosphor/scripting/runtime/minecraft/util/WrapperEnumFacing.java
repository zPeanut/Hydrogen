package tk.peanut.phosphor.scripting.runtime.minecraft.util;

import net.minecraft.util.EnumFacing;

import java.util.Random;

public class WrapperEnumFacing {
    private EnumFacing real;

    public WrapperEnumFacing(EnumFacing var1) {
        this.real = var1;
    }

    public static EnumFacing[] values() {
        return EnumFacing.values();
    }

    public static WrapperEnumFacing valueOf(String var0) {
        return new WrapperEnumFacing(EnumFacing.valueOf(var0));
    }

    public static WrapperEnumFacing byName(String var0) {
        return new WrapperEnumFacing(EnumFacing.byName(var0));
    }

    public static WrapperEnumFacing getFront(int var0) {
        return new WrapperEnumFacing(EnumFacing.getFront(var0));
    }

    public static WrapperEnumFacing getHorizontal(int var0) {
        return new WrapperEnumFacing(EnumFacing.getHorizontal(var0));
    }

    public static WrapperEnumFacing fromAngle(double var0) {
        return new WrapperEnumFacing(EnumFacing.fromAngle(var0));
    }

    public static WrapperEnumFacing random(Random var0) {
        return new WrapperEnumFacing(EnumFacing.random(var0));
    }

    public static WrapperEnumFacing getFacingFromVector(float var0, float var1, float var2) {
        return new WrapperEnumFacing(EnumFacing.getFacingFromVector(var0, var1, var2));
    }

    public EnumFacing unwrap() {
        return this.real;
    }

    public int getIndex() {
        return this.real.getIndex();
    }

    public int getHorizontalIndex() {
        return this.real.getHorizontalIndex();
    }

    public WrapperEnumFacing getOpposite() {
        return new WrapperEnumFacing(this.real.getOpposite());
    }

    public WrapperEnumFacing rotateY() {
        return new WrapperEnumFacing(this.real.rotateY());
    }

    public WrapperEnumFacing rotateYCCW() {
        return new WrapperEnumFacing(this.real.rotateYCCW());
    }

    public int getFrontOffsetX() {
        return this.real.getFrontOffsetX();
    }

    public int getFrontOffsetY() {
        return this.real.getFrontOffsetY();
    }

    public int getFrontOffsetZ() {
        return this.real.getFrontOffsetZ();
    }

    public String getName2() {
        return this.real.getName2();
    }

    public String toString() {
        return this.real.toString();
    }

    public String getName() {
        return this.real.getName();
    }

    public WrapperVec3i getDirectionVec() {
        return new WrapperVec3i(this.real.getDirectionVec());
    }

    public WrapperEnumFacing getDOWN() {
        return new WrapperEnumFacing(EnumFacing.DOWN);
    }

    public WrapperEnumFacing getUP() {
        return new WrapperEnumFacing(EnumFacing.UP);
    }

    public WrapperEnumFacing getNORTH() {
        return new WrapperEnumFacing(EnumFacing.NORTH);
    }

    public WrapperEnumFacing getSOUTH() {
        return new WrapperEnumFacing(EnumFacing.SOUTH);
    }

    public WrapperEnumFacing getWEST() {
        return new WrapperEnumFacing(EnumFacing.WEST);
    }

    public WrapperEnumFacing getEAST() {
        return new WrapperEnumFacing(EnumFacing.EAST);
    }
}
