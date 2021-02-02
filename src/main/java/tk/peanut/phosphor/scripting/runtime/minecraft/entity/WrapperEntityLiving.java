package tk.peanut.phosphor.scripting.runtime.minecraft.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.player.WrapperEntityPlayer;
import tk.peanut.phosphor.scripting.runtime.minecraft.item.WrapperItem;

public class WrapperEntityLiving extends WrapperEntityLivingBase {
    private EntityLiving real;

    public WrapperEntityLiving(EntityLiving var1) {
        super(var1);
        this.real = var1;
    }

    public static WrapperItem getArmorItemForSlot(int var0, int var1) {
        return new WrapperItem(EntityLiving.getArmorItemForSlot(var0, var1));
    }

    public EntityLiving unwrap() {
        return this.real;
    }

    public WrapperEntityLivingBase getAttackTarget() {
        return new WrapperEntityLivingBase(this.real.getAttackTarget());
    }

    public void setAttackTarget(WrapperEntityLivingBase var1) {
        this.real.setAttackTarget(var1.unwrap());
    }

    public boolean canAttackClass(Class var1) {
        return this.real.canAttackClass(var1);
    }

    public void eatGrassBonus() {
        this.real.eatGrassBonus();
    }

    public int getTalkInterval() {
        return this.real.getTalkInterval();
    }

    public void playLivingSound() {
        this.real.playLivingSound();
    }

    public void onEntityUpdate() {
        this.real.onEntityUpdate();
    }

    public void spawnExplosionParticle() {
        this.real.spawnExplosionParticle();
    }

    public void handleStatusUpdate(byte var1) {
        this.real.handleStatusUpdate(var1);
    }

    public void onUpdate() {
        this.real.onUpdate();
    }

    public void setMoveForward(float var1) {
        this.real.setMoveForward(var1);
    }

    public void setAIMoveSpeed(float var1) {
        this.real.setAIMoveSpeed(var1);
    }

    public void onLivingUpdate() {
        this.real.onLivingUpdate();
    }

    public int getVerticalFaceSpeed() {
        return this.real.getVerticalFaceSpeed();
    }

    public void faceEntity(WrapperEntity var1, float var2, float var3) {
        this.real.faceEntity(var1.unwrap(), var2, var3);
    }

    public boolean getCanSpawnHere() {
        return this.real.getCanSpawnHere();
    }

    public boolean isNotColliding() {
        return this.real.isNotColliding();
    }

    public float getRenderSizeModifier() {
        return this.real.getRenderSizeModifier();
    }

    public int getMaxSpawnedInChunk() {
        return this.real.getMaxSpawnedInChunk();
    }

    public int getMaxFallHeight() {
        return this.real.getMaxFallHeight();
    }

    public ItemStack[] getInventory() {
        return this.real.getInventory();
    }

    public boolean canBeSteered() {
        return this.real.canBeSteered();
    }

    public void enablePersistence() {
        this.real.enablePersistence();
    }

    public void setEquipmentDropChance(int var1, float var2) {
        this.real.setEquipmentDropChance(var1, var2);
    }

    public boolean canPickUpLoot() {
        return this.real.canPickUpLoot();
    }

    public void setCanPickUpLoot(boolean var1) {
        this.real.setCanPickUpLoot(var1);
    }

    public boolean isNoDespawnRequired() {
        return this.real.isNoDespawnRequired();
    }

    public final boolean interactFirst(WrapperEntityPlayer var1) {
        return this.real.interactFirst(var1.unwrap());
    }

    public void clearLeashed(boolean var1, boolean var2) {
        this.real.clearLeashed(var1, var2);
    }

    public boolean allowLeashing() {
        return this.real.allowLeashing();
    }

    public boolean getLeashed() {
        return this.real.getLeashed();
    }

    public WrapperEntity getLeashedToEntity() {
        return new WrapperEntity(this.real.getLeashedToEntity());
    }

    public void setLeashedToEntity(WrapperEntity var1, boolean var2) {
        this.real.setLeashedToEntity(var1.unwrap(), var2);
    }

    public boolean isServerWorld() {
        return this.real.isServerWorld();
    }

    public void setNoAI(boolean var1) {
        this.real.setNoAI(var1);
    }

    public boolean isAIDisabled() {
        return this.real.isAIDisabled();
    }

    public int getLivingSoundTime() {
        return this.real.livingSoundTime;
    }

    public void setLivingSoundTime(int var1) {
        this.real.livingSoundTime = var1;
    }
}
