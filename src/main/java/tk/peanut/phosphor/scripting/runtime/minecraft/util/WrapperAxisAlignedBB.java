package tk.peanut.phosphor.scripting.runtime.minecraft.util;

import net.minecraft.util.AxisAlignedBB;

public class WrapperAxisAlignedBB {
    private AxisAlignedBB real;

    public WrapperAxisAlignedBB(AxisAlignedBB var1) {
        this.real = var1;
    }

    public AxisAlignedBB unwrap() {
        return this.real;
    }

    public WrapperAxisAlignedBB union(WrapperAxisAlignedBB var1) {
        return new WrapperAxisAlignedBB(this.real.union(var1.unwrap()));
    }

    public double calculateXOffset(WrapperAxisAlignedBB var1, double var2) {
        return this.real.calculateXOffset(var1.unwrap(), var2);
    }

    public double calculateYOffset(WrapperAxisAlignedBB var1, double var2) {
        return this.real.calculateYOffset(var1.unwrap(), var2);
    }

    public double calculateZOffset(WrapperAxisAlignedBB var1, double var2) {
        return this.real.calculateZOffset(var1.unwrap(), var2);
    }

    public boolean intersectsWith(WrapperAxisAlignedBB var1) {
        return this.real.intersectsWith(var1.unwrap());
    }

    public boolean isVecInside(WrapperVec3 var1) {
        return this.real.isVecInside(var1.unwrap());
    }

    public double getAverageEdgeLength() {
        return this.real.getAverageEdgeLength();
    }


    public String toString() {
        return this.real.toString();
    }

    public double getMinX() {
        return this.real.minX;
    }

    public double getMinY() {
        return this.real.minY;
    }

    public double getMinZ() {
        return this.real.minZ;
    }

    public double getMaxX() {
        return this.real.maxX;
    }

    public double getMaxY() {
        return this.real.maxY;
    }

    public double getMaxZ() {
        return this.real.maxZ;
    }
}
