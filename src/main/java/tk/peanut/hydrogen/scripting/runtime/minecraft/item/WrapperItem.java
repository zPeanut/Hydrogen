package tk.peanut.hydrogen.scripting.runtime.minecraft.item;

import net.minecraft.item.Item;
import tk.peanut.hydrogen.scripting.runtime.minecraft.block.WrapperBlock;

public class WrapperItem {
    private Item real;

    public WrapperItem(Item var1) {
        this.real = var1;
    }

    public static int getIdFromItem(WrapperItem var0) {
        return Item.getIdFromItem(var0.unwrap());
    }

    public static WrapperItem getItemById(int var0) {
        return new WrapperItem(Item.getItemById(var0));
    }

    public static WrapperItem getItemFromBlock(WrapperBlock var0) {
        return new WrapperItem(Item.getItemFromBlock(var0.unwrap()));
    }

    public static WrapperItem getByNameOrId(String var0) {
        return new WrapperItem(Item.getByNameOrId(var0));
    }

    public static void registerItems() {
        Item.registerItems();
    }

    public Item unwrap() {
        return this.real;
    }

    public WrapperItem setMaxStackSize(int var1) {
        return new WrapperItem(this.real.setMaxStackSize(var1));
    }

    public int getItemStackLimit() {
        return this.real.getItemStackLimit();
    }

    public int getMetadata(int var1) {
        return this.real.getMetadata(var1);
    }

    public boolean getHasSubtypes() {
        return this.real.getHasSubtypes();
    }

    public int getMaxDamage() {
        return this.real.getMaxDamage();
    }

    public boolean isDamageable() {
        return this.real.isDamageable();
    }

    public boolean canHarvestBlock(WrapperBlock var1) {
        return this.real.canHarvestBlock(var1.unwrap());
    }

    public WrapperItem setFull3D() {
        return new WrapperItem(this.real.setFull3D());
    }

    public boolean isFull3D() {
        return this.real.isFull3D();
    }

    public boolean shouldRotateAroundWhenRendering() {
        return this.real.shouldRotateAroundWhenRendering();
    }

    public String getUnlocalizedName() {
        return this.real.getUnlocalizedName();
    }

    public WrapperItem setUnlocalizedName(String var1) {
        return new WrapperItem(this.real.setUnlocalizedName(var1));
    }

    public boolean getShareTag() {
        return this.real.getShareTag();
    }

    public WrapperItem getContainerItem() {
        return new WrapperItem(this.real.getContainerItem());
    }

    public WrapperItem setContainerItem(WrapperItem var1) {
        return new WrapperItem(this.real.setContainerItem(var1.unwrap()));
    }

    public boolean hasContainerItem() {
        return this.real.hasContainerItem();
    }

    public boolean isMap() {
        return this.real.isMap();
    }

    public int getItemEnchantability() {
        return this.real.getItemEnchantability();
    }

    public boolean canItemEditBlocks() {
        return this.real.canItemEditBlocks();
    }
}
