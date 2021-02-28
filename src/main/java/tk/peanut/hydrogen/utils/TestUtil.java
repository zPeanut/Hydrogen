package tk.peanut.hydrogen.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Created by peanut on 28/02/2021
 */
public class TestUtil extends EntityLivingBase {

    public static TestUtil instance;

    public TestUtil(World worldIn) {
        super(worldIn);
        instance = this;
    }

    public ItemStack getHeldItem() {
        return null;
    }

    public ItemStack getEquipmentInSlot(int i) {
        return null;
    }

    public ItemStack getCurrentArmor(int i) {
        return null;
    }

    public void setCurrentItemOrArmor(int i, ItemStack itemStack) {

    }

    public ItemStack[] getInventory() {
        return new ItemStack[0];
    }

    public boolean canBlockBeSeen(BlockPos pos) {
        return (this.worldObj.rayTraceBlocks(new Vec3(this.posX, this.posY + getEyeHeight(), this.posZ), new Vec3(pos.getX(), pos.getY(), pos.getZ())) == null);
    }


}
