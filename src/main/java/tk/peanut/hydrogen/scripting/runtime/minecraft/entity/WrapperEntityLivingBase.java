package tk.peanut.hydrogen.scripting.runtime.minecraft.entity;

import net.minecraft.entity.EntityLivingBase;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperVec3;

import java.util.Collection;
import java.util.Random;

public class WrapperEntityLivingBase extends WrapperEntity {
    private EntityLivingBase real;

    public WrapperEntityLivingBase(EntityLivingBase var1) {
        super(var1);
        this.real = var1;
    }

    public EntityLivingBase unwrap() {
        return this.real;
    }

    public void onKillCommand() {
        this.real.onKillCommand();
    }

    public boolean canBreatheUnderwater() {
        return this.real.canBreatheUnderwater();
    }

    public void onEntityUpdate() {
        this.real.onEntityUpdate();
    }

    public boolean isChild() {
        return this.real.isChild();
    }

    public Random getRNG() {
        return this.real.getRNG();
    }

    public WrapperEntityLivingBase getAITarget() {
        return new WrapperEntityLivingBase(this.real.getAITarget());
    }

    public int getRevengeTimer() {
        return this.real.getRevengeTimer();
    }

    public void setRevengeTarget(WrapperEntityLivingBase var1) {
        this.real.setRevengeTarget(var1.unwrap());
    }

    public WrapperEntityLivingBase getLastAttacker() {
        return new WrapperEntityLivingBase(this.real.getLastAttacker());
    }

    public void setLastAttacker(WrapperEntity var1) {
        this.real.setLastAttacker(var1.unwrap());
    }

    public int getLastAttackerTime() {
        return this.real.getLastAttackerTime();
    }

    public int getAge() {
        return this.real.getAge();
    }

    public void clearActivePotions() {
        this.real.clearActivePotions();
    }

    public Collection getActivePotionEffects() {
        return this.real.getActivePotionEffects();
    }

    public boolean isPotionActive(int var1) {
        return this.real.isPotionActive(var1);
    }

    public boolean isEntityUndead() {
        return this.real.isEntityUndead();
    }

    public void removePotionEffectClient(int var1) {
        this.real.removePotionEffectClient(var1);
    }

    public void removePotionEffect(int var1) {
        this.real.removePotionEffect(var1);
    }

    public void heal(float var1) {
        this.real.heal(var1);
    }

    public final float getHealth() {
        return this.real.getHealth();
    }

    public void setHealth(float var1) {
        this.real.setHealth(var1);
    }


    public boolean isOnLadder() {
        return this.real.isOnLadder();
    }

    public boolean isEntityAlive() {
        return this.real.isEntityAlive();
    }

    public void fall(float var1, float var2) {
        this.real.fall(var1, var2);
    }

    public void performHurtAnimation() {
        this.real.performHurtAnimation();
    }

    public int getTotalArmorValue() {
        return this.real.getTotalArmorValue();
    }

    public final float getMaxHealth() {
        return this.real.getMaxHealth();
    }

    public final int getArrowCountInEntity() {
        return this.real.getArrowCountInEntity();
    }

    public final void setArrowCountInEntity(int var1) {
        this.real.setArrowCountInEntity(var1);
    }

    public void swingItem() {
        this.real.swingItem();
    }

    public void handleStatusUpdate(byte var1) {
        this.real.handleStatusUpdate(var1);
    }

    public void setSprinting(boolean var1) {
        this.real.setSprinting(var1);
    }

    public void dismountEntity(WrapperEntity var1) {
        this.real.dismountEntity(var1.unwrap());
    }

    public boolean getAlwaysRenderNameTagForRender() {
        return this.real.getAlwaysRenderNameTagForRender();
    }

    public void moveEntityWithHeading(float var1, float var2) {
        this.real.moveEntityWithHeading(var1, var2);
    }

    public float getAIMoveSpeed() {
        return this.real.getAIMoveSpeed();
    }

    public void setAIMoveSpeed(float var1) {
        this.real.setAIMoveSpeed(var1);
    }

    public boolean attackEntityAsMob(WrapperEntity var1) {
        return this.real.attackEntityAsMob(var1.unwrap());
    }

    public boolean isPlayerSleeping() {
        return this.real.isPlayerSleeping();
    }

    public void onUpdate() {
        this.real.onUpdate();
    }

    public void onLivingUpdate() {
        this.real.onLivingUpdate();
    }

    public void mountEntity(WrapperEntity var1) {
        this.real.mountEntity(var1.unwrap());
    }

    public void updateRidden() {
        this.real.updateRidden();
    }


    public void setJumping(boolean var1) {
        this.real.setJumping(var1);
    }

    public void onItemPickup(WrapperEntity var1, int var2) {
        this.real.onItemPickup(var1.unwrap(), var2);
    }

    public boolean canEntityBeSeen(WrapperEntity var1) {
        return this.real.canEntityBeSeen(var1.unwrap());
    }

    public WrapperVec3 getLookVec() {
        return new WrapperVec3(this.real.getLookVec());
    }

    public WrapperVec3 getLook(float var1) {
        return new WrapperVec3(this.real.getLook(var1));
    }

    public float getSwingProgress(float var1) {
        return this.real.getSwingProgress(var1);
    }

    public boolean isServerWorld() {
        return this.real.isServerWorld();
    }

    public boolean canBeCollidedWith() {
        return this.real.canBeCollidedWith();
    }

    public boolean canBePushed() {
        return this.real.canBePushed();
    }

    public float getRotationYawHead() {
        return this.real.getRotationYawHead();
    }

    public void setRotationYawHead(float var1) {
        this.real.setRotationYawHead(var1);
    }

    public float getAbsorptionAmount() {
        return this.real.getAbsorptionAmount();
    }

    public void setAbsorptionAmount(float var1) {
        this.real.setAbsorptionAmount(var1);
    }

    public boolean isOnSameTeam(WrapperEntityLivingBase var1) {
        return this.real.isOnSameTeam(var1.unwrap());
    }

    public void sendEnterCombat() {
        this.real.sendEnterCombat();
    }

    public void sendEndCombat() {
        this.real.sendEndCombat();
    }

    public boolean IsSwingInProgress() {
        return this.real.isSwingInProgress;
    }

    public void setIsSwingInProgress(boolean var1) {
        this.real.isSwingInProgress = var1;
    }

    public int getSwingProgressInt() {
        return this.real.swingProgressInt;
    }

    public void setSwingProgressInt(int var1) {
        this.real.swingProgressInt = var1;
    }

    public int getArrowHitTimer() {
        return this.real.arrowHitTimer;
    }

    public void setArrowHitTimer(int var1) {
        this.real.arrowHitTimer = var1;
    }

    public int getHurtTime() {
        return this.real.hurtTime;
    }

    public void setHurtTime(int var1) {
        this.real.hurtTime = var1;
    }

    public int getMaxHurtTime() {
        return this.real.maxHurtTime;
    }

    public void setMaxHurtTime(int var1) {
        this.real.maxHurtTime = var1;
    }

    public float getAttackedAtYaw() {
        return this.real.attackedAtYaw;
    }

    public void setAttackedAtYaw(float var1) {
        this.real.attackedAtYaw = var1;
    }

    public int getDeathTime() {
        return this.real.deathTime;
    }

    public void setDeathTime(int var1) {
        this.real.deathTime = var1;
    }

    public float getPrevSwingProgress() {
        return this.real.prevSwingProgress;
    }

    public void setPrevSwingProgress(float var1) {
        this.real.prevSwingProgress = var1;
    }

    public void setSwingProgress(float var1) {
        this.real.swingProgress = var1;
    }

    public float getPrevLimbSwingAmount() {
        return this.real.prevLimbSwingAmount;
    }

    public void setPrevLimbSwingAmount(float var1) {
        this.real.prevLimbSwingAmount = var1;
    }

    public float getLimbSwingAmount() {
        return this.real.limbSwingAmount;
    }

    public void setLimbSwingAmount(float var1) {
        this.real.limbSwingAmount = var1;
    }

    public float getLimbSwing() {
        return this.real.limbSwing;
    }

    public void setLimbSwing(float var1) {
        this.real.limbSwing = var1;
    }

    public int getMaxHurtResistantTime() {
        return this.real.maxHurtResistantTime;
    }

    public void setMaxHurtResistantTime(int var1) {
        this.real.maxHurtResistantTime = var1;
    }

    public float getPrevCameraPitch() {
        return this.real.prevCameraPitch;
    }

    public void setPrevCameraPitch(float var1) {
        this.real.prevCameraPitch = var1;
    }

    public float getCameraPitch() {
        return this.real.cameraPitch;
    }

    public void setCameraPitch(float var1) {
        this.real.cameraPitch = var1;
    }


    public float getRenderYawOffset() {
        return this.real.renderYawOffset;
    }

    public void setRenderYawOffset(float var1) {
        this.real.renderYawOffset = var1;
    }

    public float getPrevRenderYawOffset() {
        return this.real.prevRenderYawOffset;
    }

    public void setPrevRenderYawOffset(float var1) {
        this.real.prevRenderYawOffset = var1;
    }

    public float getPrevRotationYawHead() {
        return this.real.prevRotationYawHead;
    }

    public void setPrevRotationYawHead(float var1) {
        this.real.prevRotationYawHead = var1;
    }

    public float getJumpMovementFactor() {
        return this.real.jumpMovementFactor;
    }

    public void setJumpMovementFactor(float var1) {
        this.real.jumpMovementFactor = var1;
    }

    public float getMoveStrafing() {
        return this.real.moveStrafing;
    }

    public void setMoveStrafing(float var1) {
        this.real.moveStrafing = var1;
    }

    public float getMoveForward() {
        return this.real.moveForward;
    }

    public void setMoveForward(float var1) {
        this.real.moveForward = var1;
    }
}
