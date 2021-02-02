package tk.peanut.phosphor.scripting.runtime.minecraft.block;

import net.minecraft.block.Block;
import tk.peanut.phosphor.scripting.runtime.minecraft.block.material.WrapperMaterial;
import tk.peanut.phosphor.scripting.runtime.minecraft.block.state.WrapperBlockState;
import tk.peanut.phosphor.scripting.runtime.minecraft.block.state.WrapperIBlockState;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.WrapperEntity;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.WrapperEntityLivingBase;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.player.WrapperEntityPlayer;
import tk.peanut.phosphor.scripting.runtime.minecraft.item.WrapperItem;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperAxisAlignedBB;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperBlockPos;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperEnumFacing;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperVec3;
import tk.peanut.phosphor.scripting.runtime.minecraft.world.WrapperWorld;

import java.util.List;
import java.util.Random;

public class WrapperBlock {
    private Block real;

    public WrapperBlock(Block var1) {
        this.real = var1;
    }

    public static int getIdFromBlock(WrapperBlock var0) {
        return Block.getIdFromBlock(var0.unwrap());
    }

    public static int getStateId(WrapperIBlockState var0) {
        return Block.getStateId(var0.unwrap());
    }

    public static WrapperBlock getBlockById(int var0) {
        return new WrapperBlock(Block.getBlockById(var0));
    }

    public static WrapperIBlockState getStateById(int var0) {
        return new WrapperIBlockState(Block.getStateById(var0));
    }

    public static WrapperBlock getBlockFromItem(WrapperItem var0) {
        return new WrapperBlock(Block.getBlockFromItem(var0.unwrap()));
    }

    public static WrapperBlock getBlockFromName(String var0) {
        return new WrapperBlock(Block.getBlockFromName(var0));
    }

    public static boolean isEqualTo(WrapperBlock var0, WrapperBlock var1) {
        return Block.isEqualTo(var0.unwrap(), var1.unwrap());
    }

    public static void registerBlocks() {
        Block.registerBlocks();
    }

    public Block unwrap() {
        return this.real;
    }

    public boolean isFullBlock() {
        return this.real.isFullBlock();
    }

    public int getLightOpacity() {
        return this.real.getLightOpacity();
    }

    public boolean isTranslucent() {
        return this.real.isTranslucent();
    }

    public int getLightValue() {
        return this.real.getLightValue();
    }

    public boolean getUseNeighborBrightness() {
        return this.real.getUseNeighborBrightness();
    }

    public WrapperMaterial getMaterial() {
        return new WrapperMaterial(this.real.getMaterial());
    }

    public WrapperIBlockState getStateFromMeta(int var1) {
        return new WrapperIBlockState(this.real.getStateFromMeta(var1));
    }

    public int getMetaFromState(WrapperIBlockState var1) {
        return this.real.getMetaFromState(var1.unwrap());
    }

    public boolean isBlockNormalCube() {
        return this.real.isBlockNormalCube();
    }

    public boolean isNormalCube() {
        return this.real.isNormalCube();
    }

    public boolean isVisuallyOpaque() {
        return this.real.isVisuallyOpaque();
    }

    public boolean isFullCube() {
        return this.real.isFullCube();
    }

    public int getRenderType() {
        return this.real.getRenderType();
    }

    public boolean isReplaceable(WrapperWorld var1, WrapperBlockPos var2) {
        return this.real.isReplaceable(var1.unwrap(), var2.unwrap());
    }

    public float getBlockHardness(WrapperWorld var1, WrapperBlockPos var2) {
        return this.real.getBlockHardness(var1.unwrap(), var2.unwrap());
    }

    public boolean getTickRandomly() {
        return this.real.getTickRandomly();
    }

    public boolean hasTileEntity() {
        return this.real.hasTileEntity();
    }

    public WrapperAxisAlignedBB getSelectedBoundingBox(WrapperWorld var1, WrapperBlockPos var2) {
        return new WrapperAxisAlignedBB(this.real.getSelectedBoundingBox(var1.unwrap(), var2.unwrap()));
    }

    public void addCollisionBoxesToList(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, WrapperAxisAlignedBB var4, List var5, WrapperEntity var6) {
        this.real.addCollisionBoxesToList(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4.unwrap(), var5, var6.unwrap());
    }

    public WrapperAxisAlignedBB getCollisionBoundingBox(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3) {
        return new WrapperAxisAlignedBB(this.real.getCollisionBoundingBox(var1.unwrap(), var2.unwrap(), var3.unwrap()));
    }

    public boolean isOpaqueCube() {
        return this.real.isOpaqueCube();
    }

    public boolean canCollideCheck(WrapperIBlockState var1, boolean var2) {
        return this.real.canCollideCheck(var1.unwrap(), var2);
    }

    public boolean isCollidable() {
        return this.real.isCollidable();
    }

    public void randomTick(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, Random var4) {
        this.real.randomTick(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4);
    }

    public void updateTick(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, Random var4) {
        this.real.updateTick(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4);
    }

    public void randomDisplayTick(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, Random var4) {
        this.real.randomDisplayTick(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4);
    }

    public void onBlockDestroyedByPlayer(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3) {
        this.real.onBlockDestroyedByPlayer(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public void onNeighborBlockChange(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, WrapperBlock var4) {
        this.real.onNeighborBlockChange(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4.unwrap());
    }

    public int tickRate(WrapperWorld var1) {
        return this.real.tickRate(var1.unwrap());
    }

    public void onBlockAdded(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3) {
        this.real.onBlockAdded(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public void breakBlock(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3) {
        this.real.breakBlock(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public int quantityDropped(Random var1) {
        return this.real.quantityDropped(var1);
    }

    public WrapperItem getItemDropped(WrapperIBlockState var1, Random var2, int var3) {
        return new WrapperItem(this.real.getItemDropped(var1.unwrap(), var2, var3));
    }

    public float getPlayerRelativeBlockHardness(WrapperEntityPlayer var1, WrapperWorld var2, WrapperBlockPos var3) {
        return this.real.getPlayerRelativeBlockHardness(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public final void dropBlockAsItem(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, int var4) {
        this.real.dropBlockAsItem(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4);
    }

    public void dropBlockAsItemWithChance(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, float var4, int var5) {
        this.real.dropBlockAsItemWithChance(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4, var5);
    }

    public int damageDropped(WrapperIBlockState var1) {
        return this.real.damageDropped(var1.unwrap());
    }

    public float getExplosionResistance(WrapperEntity var1) {
        return this.real.getExplosionResistance(var1.unwrap());
    }

    public boolean canPlaceBlockOnSide(WrapperWorld var1, WrapperBlockPos var2, WrapperEnumFacing var3) {
        return this.real.canPlaceBlockOnSide(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public boolean canPlaceBlockAt(WrapperWorld var1, WrapperBlockPos var2) {
        return this.real.canPlaceBlockAt(var1.unwrap(), var2.unwrap());
    }

    public boolean onBlockActivated(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, WrapperEntityPlayer var4, WrapperEnumFacing var5, float var6, float var7, float var8) {
        return this.real.onBlockActivated(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4.unwrap(), var5.unwrap(), var6, var7, var8);
    }

    public void onEntityCollidedWithBlock(WrapperWorld var1, WrapperBlockPos var2, WrapperEntity var3) {
        this.real.onEntityCollidedWithBlock(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public WrapperIBlockState onBlockPlaced(WrapperWorld var1, WrapperBlockPos var2, WrapperEnumFacing var3, float var4, float var5, float var6, int var7, WrapperEntityLivingBase var8) {
        return new WrapperIBlockState(this.real.onBlockPlaced(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4, var5, var6, var7, var8.unwrap()));
    }

    public void onBlockClicked(WrapperWorld var1, WrapperBlockPos var2, WrapperEntityPlayer var3) {
        this.real.onBlockClicked(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public WrapperVec3 modifyAcceleration(WrapperWorld var1, WrapperBlockPos var2, WrapperEntity var3, WrapperVec3 var4) {
        return new WrapperVec3(this.real.modifyAcceleration(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4.unwrap()));
    }

    public final double getBlockBoundsMinX() {
        return this.real.getBlockBoundsMinX();
    }

    public final double getBlockBoundsMaxX() {
        return this.real.getBlockBoundsMaxX();
    }

    public final double getBlockBoundsMinY() {
        return this.real.getBlockBoundsMinY();
    }

    public final double getBlockBoundsMaxY() {
        return this.real.getBlockBoundsMaxY();
    }

    public final double getBlockBoundsMinZ() {
        return this.real.getBlockBoundsMinZ();
    }

    public final double getBlockBoundsMaxZ() {
        return this.real.getBlockBoundsMaxZ();
    }

    public int getBlockColor() {
        return this.real.getBlockColor();
    }

    public int getRenderColor(WrapperIBlockState var1) {
        return this.real.getRenderColor(var1.unwrap());
    }

    public boolean canProvidePower() {
        return this.real.canProvidePower();
    }

    public void onEntityCollidedWithBlock(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, WrapperEntity var4) {
        this.real.onEntityCollidedWithBlock(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4.unwrap());
    }

    public void setBlockBoundsForItemRender() {
        this.real.setBlockBoundsForItemRender();
    }

    public int quantityDroppedWithBonus(int var1, Random var2) {
        return this.real.quantityDroppedWithBonus(var1, var2);
    }

    public String getLocalizedName() {
        return this.real.getLocalizedName();
    }

    public String getUnlocalizedName() {
        return this.real.getUnlocalizedName();
    }

    public WrapperBlock setUnlocalizedName(String var1) {
        return new WrapperBlock(this.real.setUnlocalizedName(var1));
    }

    public boolean onBlockEventReceived(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, int var4, int var5) {
        return this.real.onBlockEventReceived(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4, var5);
    }

    public boolean getEnableStats() {
        return this.real.getEnableStats();
    }

    public int getMobilityFlag() {
        return this.real.getMobilityFlag();
    }

    public float getAmbientOcclusionLightValue() {
        return this.real.getAmbientOcclusionLightValue();
    }

    public void onFallenUpon(WrapperWorld var1, WrapperBlockPos var2, WrapperEntity var3, float var4) {
        this.real.onFallenUpon(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4);
    }

    public void onLanded(WrapperWorld var1, WrapperEntity var2) {
        this.real.onLanded(var1.unwrap(), var2.unwrap());
    }

    public WrapperItem getItem(WrapperWorld var1, WrapperBlockPos var2) {
        return new WrapperItem(this.real.getItem(var1.unwrap(), var2.unwrap()));
    }

    public int getDamageValue(WrapperWorld var1, WrapperBlockPos var2) {
        return this.real.getDamageValue(var1.unwrap(), var2.unwrap());
    }

    public void onBlockHarvested(WrapperWorld var1, WrapperBlockPos var2, WrapperIBlockState var3, WrapperEntityPlayer var4) {
        this.real.onBlockHarvested(var1.unwrap(), var2.unwrap(), var3.unwrap(), var4.unwrap());
    }

    public void fillWithRain(WrapperWorld var1, WrapperBlockPos var2) {
        this.real.fillWithRain(var1.unwrap(), var2.unwrap());
    }

    public boolean isFlowerPot() {
        return this.real.isFlowerPot();
    }

    public boolean requiresUpdates() {
        return this.real.requiresUpdates();
    }

    public boolean isAssociatedBlock(WrapperBlock var1) {
        return this.real.isAssociatedBlock(var1.unwrap());
    }

    public boolean hasComparatorInputOverride() {
        return this.real.hasComparatorInputOverride();
    }

    public int getComparatorInputOverride(WrapperWorld var1, WrapperBlockPos var2) {
        return this.real.getComparatorInputOverride(var1.unwrap(), var2.unwrap());
    }

    public WrapperIBlockState getStateForEntityRender(WrapperIBlockState var1) {
        return new WrapperIBlockState(this.real.getStateForEntityRender(var1.unwrap()));
    }

    public WrapperBlockState getBlockState() {
        return new WrapperBlockState(this.real.getBlockState());
    }

    public final WrapperIBlockState getDefaultState() {
        return new WrapperIBlockState(this.real.getDefaultState());
    }

    public String toString() {
        return this.real.toString();
    }

    public float getBlockParticleGravity() {
        return this.real.blockParticleGravity;
    }

    public void setBlockParticleGravity(float var1) {
        this.real.blockParticleGravity = var1;
    }

    public float getSlipperiness() {
        return this.real.slipperiness;
    }

    public void setSlipperiness(float var1) {
        this.real.slipperiness = var1;
    }
}
