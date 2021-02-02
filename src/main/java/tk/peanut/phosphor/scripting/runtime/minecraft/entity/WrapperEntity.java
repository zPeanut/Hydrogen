package tk.peanut.phosphor.scripting.runtime.minecraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tk.peanut.phosphor.scripting.runtime.minecraft.block.material.WrapperMaterial;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.player.WrapperEntityPlayer;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.player.WrapperEntityPlayerMP;
import tk.peanut.phosphor.scripting.runtime.minecraft.world.WrapperWorld;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.*;

import java.util.UUID;

public class WrapperEntity {
    private Entity real;

    public WrapperEntity(Entity var1) {
        this.real = var1;
    }

    public Entity unwrap() {
        return this.real;
    }

    public int getEntityId() {
        return this.real.getEntityId();
    }

    public void setEntityId(int var1) {
        this.real.setEntityId(var1);
    }

    public void onKillCommand() {
        this.real.onKillCommand();
    }

    public boolean equals(Object var1) {
        return this.real.equals(var1);
    }

    public int hashCode() {
        return this.real.hashCode();
    }

    public void setDead() {
        this.real.setDead();
    }


    public void setAngles(float var1, float var2) {
        this.real.setAngles(var1, var2);
    }

    public void onUpdate() {
        this.real.onUpdate();
    }

    public void onEntityUpdate() {
        this.real.onEntityUpdate();
    }

    public int getMaxInPortalTime() {
        return this.real.getMaxInPortalTime();
    }

    public void setFire(int var1) {
        this.real.setFire(var1);
    }

    public void extinguish() {
        this.real.extinguish();
    }


    public void playSound(String var1, float var2, float var3) {
        this.real.playSound(var1, var2, var3);
    }

    public boolean isSilent() {
        return this.real.isSilent();
    }

    public void setSilent(boolean var1) {
        this.real.setSilent(var1);
    }

    public WrapperAxisAlignedBB getCollisionBoundingBox() {
        return new WrapperAxisAlignedBB(this.real.getCollisionBoundingBox());
    }

    public final boolean isImmuneToFire() {
        return this.real.isImmuneToFire();
    }

    public void fall(float var1, float var2) {
        this.real.fall(var1, var2);
    }

    public boolean isWet() {
        return this.real.isWet();
    }

    public boolean isInWater() {
        return this.real.isInWater();
    }

    public boolean handleWaterMovement() {
        return this.real.handleWaterMovement();
    }

    public void spawnRunningParticles() {
        this.real.spawnRunningParticles();
    }

    public boolean isInsideOfMaterial(WrapperMaterial var1) {
        return this.real.isInsideOfMaterial(var1.unwrap());
    }

    public boolean isInLava() {
        return this.real.isInLava();
    }

    public void moveFlying(float var1, float var2, float var3) {
        this.real.moveFlying(var1, var2, var3);
    }

    public int getBrightnessForRender(float var1) {
        return this.real.getBrightnessForRender(var1);
    }

    public float getBrightness(float var1) {
        return this.real.getBrightness(var1);
    }

    public void setWorld(WrapperWorld var1) {
        this.real.setWorld(var1.unwrap());
    }


    public void moveToBlockPosAndAngles(WrapperBlockPos var1, float var2, float var3) {
        this.real.moveToBlockPosAndAngles(var1.unwrap(), var2, var3);
    }

    public float getDistanceToEntity(WrapperEntity var1) {
        return this.real.getDistanceToEntity(var1.unwrap());
    }

    public double getDistanceSq(WrapperBlockPos var1) {
        return this.real.getDistanceSq(var1.unwrap());
    }

    public double getDistanceSqToCenter(WrapperBlockPos var1) {
        return this.real.getDistanceSqToCenter(var1.unwrap());
    }

    public double getDistanceSqToEntity(WrapperEntity var1) {
        return this.real.getDistanceSqToEntity(var1.unwrap());
    }

    public void onCollideWithPlayer(WrapperEntityPlayer var1) {
        this.real.onCollideWithPlayer(var1.unwrap());
    }

    public void applyEntityCollision(WrapperEntity var1) {
        this.real.applyEntityCollision(var1.unwrap());
    }


    public WrapperVec3 getLook(float var1) {
        return new WrapperVec3(this.real.getLook(var1));
    }

    public WrapperVec3 getPositionEyes(float var1) {
        return new WrapperVec3(this.real.getPositionEyes(var1));
    }

    public boolean canBeCollidedWith() {
        return this.real.canBeCollidedWith();
    }

    public boolean canBePushed() {
        return this.real.canBePushed();
    }

    public void addToPlayerScore(WrapperEntity var1, int var2) {
        this.real.addToPlayerScore(var1.unwrap(), var2);
    }

    public boolean isInRangeToRenderDist(double var1) {
        return this.real.isInRangeToRenderDist(var1);
    }

    public void onChunkLoad() {
        this.real.onChunkLoad();
    }

    public boolean isEntityAlive() {
        return this.real.isEntityAlive();
    }

    public boolean isEntityInsideOpaqueBlock() {
        return this.real.isEntityInsideOpaqueBlock();
    }

    public boolean interactFirst(WrapperEntityPlayer var1) {
        return this.real.interactFirst(var1.unwrap());
    }

    public WrapperAxisAlignedBB getCollisionBox(WrapperEntity var1) {
        return new WrapperAxisAlignedBB(this.real.getCollisionBox(var1.unwrap()));
    }

    public void updateRidden() {
        this.real.updateRidden();
    }

    public void updateRiderPosition() {
        this.real.updateRiderPosition();
    }

    public double getYOffset() {
        return this.real.getYOffset();
    }

    public double getMountedYOffset() {
        return this.real.getMountedYOffset();
    }

    public void mountEntity(WrapperEntity var1) {
        this.real.mountEntity(var1.unwrap());
    }

    public float getCollisionBorderSize() {
        return this.real.getCollisionBorderSize();
    }

    public WrapperVec3 getLookVec() {
        return new WrapperVec3(this.real.getLookVec());
    }

    public int getPortalCooldown() {
        return this.real.getPortalCooldown();
    }

    public void handleStatusUpdate(byte var1) {
        this.real.handleStatusUpdate(var1);
    }

    public void performHurtAnimation() {
        this.real.performHurtAnimation();
    }

    public ItemStack[] getInventory() {
        return this.real.getInventory();
    }

    public boolean isBurning() {
        return this.real.isBurning();
    }

    public boolean isRiding() {
        return this.real.isRiding();
    }

    public boolean isSneaking() {
        return this.real.isSneaking();
    }

    public void setSneaking(boolean var1) {
        this.real.setSneaking(var1);
    }

    public boolean isSprinting() {
        return this.real.isSprinting();
    }

    public void setSprinting(boolean var1) {
        this.real.setSprinting(var1);
    }

    public boolean isInvisible() {
        return this.real.isInvisible();
    }

    public void setInvisible(boolean var1) {
        this.real.setInvisible(var1);
    }

    public boolean isInvisibleToPlayer(WrapperEntityPlayer var1) {
        return this.real.isInvisibleToPlayer(var1.unwrap());
    }

    public boolean isEating() {
        return this.real.isEating();
    }

    public void setEating(boolean var1) {
        this.real.setEating(var1);
    }

    public int getAir() {
        return this.real.getAir();
    }

    public void setAir(int var1) {
        this.real.setAir(var1);
    }

    public void onKillEntity(WrapperEntityLivingBase var1) {
        this.real.onKillEntity(var1.unwrap());
    }

    public void setInWeb() {
        this.real.setInWeb();
    }

    public String getName() {
        return this.real.getName();
    }

    public Entity[] getParts() {
        return this.real.getParts();
    }

    public boolean isEntityEqual(WrapperEntity var1) {
        return this.real.isEntityEqual(var1.unwrap());
    }

    public float getRotationYawHead() {
        return this.real.getRotationYawHead();
    }

    public void setRotationYawHead(float var1) {
        this.real.setRotationYawHead(var1);
    }


    public boolean canAttackWithItem() {
        return this.real.canAttackWithItem();
    }

    public boolean hitByEntity(WrapperEntity var1) {
        return this.real.hitByEntity(var1.unwrap());
    }

    public String toString() {
        return this.real.toString();
    }

    public void copyLocationAndAnglesFrom(WrapperEntity var1) {
        this.real.copyLocationAndAnglesFrom(var1.unwrap());
    }

    public void copyDataFromOld(WrapperEntity var1) {
        this.real.copyDataFromOld(var1.unwrap());
    }

    public void travelToDimension(int var1) {
        this.real.travelToDimension(var1);
    }

    public int getMaxFallHeight() {
        return this.real.getMaxFallHeight();
    }

    public WrapperVec3 func_181014_aG() {
        return new WrapperVec3(this.real.func_181014_aG());
    }


    public boolean doesEntityNotTriggerPressurePlate() {
        return this.real.doesEntityNotTriggerPressurePlate();
    }

    public boolean canRenderOnFire() {
        return this.real.canRenderOnFire();
    }

    public UUID getUniqueID() {
        return this.real.getUniqueID();
    }

    public boolean isPushedByWater() {
        return this.real.isPushedByWater();
    }

    public WrapperIChatComponent getDisplayName() {
        return new WrapperIChatComponent(this.real.getDisplayName());
    }

    public String getCustomNameTag() {
        return this.real.getCustomNameTag();
    }

    public void setCustomNameTag(String var1) {
        this.real.setCustomNameTag(var1);
    }

    public boolean hasCustomName() {
        return this.real.hasCustomName();
    }

    public boolean getAlwaysRenderNameTag() {
        return this.real.getAlwaysRenderNameTag();
    }

    public void setAlwaysRenderNameTag(boolean var1) {
        this.real.setAlwaysRenderNameTag(var1);
    }

    public boolean getAlwaysRenderNameTagForRender() {
        return this.real.getAlwaysRenderNameTagForRender();
    }

    public void onDataWatcherUpdate(int var1) {
        this.real.onDataWatcherUpdate(var1);
    }

    public WrapperEnumFacing getHorizontalFacing() {
        return new WrapperEnumFacing(this.real.getHorizontalFacing());
    }

    public boolean isSpectatedByPlayer(WrapperEntityPlayerMP var1) {
        return this.real.isSpectatedByPlayer(var1.unwrap());
    }

    public WrapperAxisAlignedBB getEntityBoundingBox() {
        return new WrapperAxisAlignedBB(this.real.getEntityBoundingBox());
    }

    public void setEntityBoundingBox(WrapperAxisAlignedBB var1) {
        this.real.setEntityBoundingBox(var1.unwrap());
    }

    public float getEyeHeight() {
        return this.real.getEyeHeight();
    }

    public boolean isOutsideBorder() {
        return this.real.isOutsideBorder();
    }

    public void setOutsideBorder(boolean var1) {
        this.real.setOutsideBorder(var1);
    }

    public void addChatMessage(WrapperIChatComponent var1) {
        this.real.addChatMessage(var1.unwrap());
    }

    public boolean canCommandSenderUseCommand(int var1, String var2) {
        return this.real.canCommandSenderUseCommand(var1, var2);
    }

    public WrapperBlockPos getPosition() {
        return new WrapperBlockPos(this.real.getPosition());
    }

    public WrapperVec3 getPositionVector() {
        return new WrapperVec3(this.real.getPositionVector());
    }

    public WrapperWorld getEntityWorld() {
        return new WrapperWorld(this.real.getEntityWorld());
    }

    public WrapperEntity getCommandSenderEntity() {
        return new WrapperEntity(this.real.getCommandSenderEntity());
    }

    public boolean sendCommandFeedback() {
        return this.real.sendCommandFeedback();
    }


    public boolean interactAt(WrapperEntityPlayer var1, WrapperVec3 var2) {
        return this.real.interactAt(var1.unwrap(), var2.unwrap());
    }

    public boolean isImmuneToExplosions() {
        return this.real.isImmuneToExplosions();
    }

    public double getRenderDistanceWeight() {
        return this.real.renderDistanceWeight;
    }

    public void setRenderDistanceWeight(double var1) {
        this.real.renderDistanceWeight = var1;
    }

    public boolean isPreventEntitySpawning() {
        return this.real.preventEntitySpawning;
    }

    public void setPreventEntitySpawning(boolean var1) {
        this.real.preventEntitySpawning = var1;
    }

    public WrapperEntity getRiddenByEntity() {
        return new WrapperEntity(this.real.riddenByEntity);
    }

    public void setRiddenByEntity(WrapperEntity var1) {
        this.real.riddenByEntity = var1.unwrap();
    }

    public WrapperEntity getRidingEntity() {
        return new WrapperEntity(this.real.ridingEntity);
    }

    public void setRidingEntity(WrapperEntity var1) {
        this.real.ridingEntity = var1.unwrap();
    }

    public boolean isForceSpawn() {
        return this.real.forceSpawn;
    }

    public void setForceSpawn(boolean var1) {
        this.real.forceSpawn = var1;
    }

    public WrapperWorld getWorldObj() {
        return new WrapperWorld(this.real.worldObj);
    }

    public void setWorldObj(WrapperWorld var1) {
        this.real.worldObj = var1.unwrap();
    }

    public double getPrevPosX() {
        return this.real.prevPosX;
    }

    public void setPrevPosX(double var1) {
        this.real.prevPosX = var1;
    }

    public double getPrevPosY() {
        return this.real.prevPosY;
    }

    public void setPrevPosY(double var1) {
        this.real.prevPosY = var1;
    }

    public double getPrevPosZ() {
        return this.real.prevPosZ;
    }

    public void setPrevPosZ(double var1) {
        this.real.prevPosZ = var1;
    }

    public double getPosX() {
        return this.real.posX;
    }

    public void setPosX(double var1) {
        this.real.posX = var1;
    }

    public double getPosY() {
        return this.real.posY;
    }

    public void setPosY(double var1) {
        this.real.posY = var1;
    }

    public double getPosZ() {
        return this.real.posZ;
    }

    public void setPosZ(double var1) {
        this.real.posZ = var1;
    }

    public double getMotionX() {
        return this.real.motionX;
    }

    public void setMotionX(double var1) {
        this.real.motionX = var1;
    }

    public double getMotionY() {
        return this.real.motionY;
    }

    public void setMotionY(double var1) {
        this.real.motionY = var1;
    }

    public double getMotionZ() {
        return this.real.motionZ;
    }

    public void setMotionZ(double var1) {
        this.real.motionZ = var1;
    }

    public float getRotationYaw() {
        return this.real.rotationYaw;
    }

    public void setRotationYaw(float var1) {
        this.real.rotationYaw = var1;
    }

    public float getRotationPitch() {
        return this.real.rotationPitch;
    }

    public void setRotationPitch(float var1) {
        this.real.rotationPitch = var1;
    }

    public float getPrevRotationYaw() {
        return this.real.prevRotationYaw;
    }

    public void setPrevRotationYaw(float var1) {
        this.real.prevRotationYaw = var1;
    }

    public float getPrevRotationPitch() {
        return this.real.prevRotationPitch;
    }

    public void setPrevRotationPitch(float var1) {
        this.real.prevRotationPitch = var1;
    }

    public boolean isOnGround() {
        return this.real.onGround;
    }

    public void setOnGround(boolean var1) {
        this.real.onGround = var1;
    }

    public boolean IsCollidedHorizontally() {
        return this.real.isCollidedHorizontally;
    }

    public void setIsCollidedHorizontally(boolean var1) {
        this.real.isCollidedHorizontally = var1;
    }

    public boolean IsCollidedVertically() {
        return this.real.isCollidedVertically;
    }

    public void setIsCollidedVertically(boolean var1) {
        this.real.isCollidedVertically = var1;
    }

    public boolean IsCollided() {
        return this.real.isCollided;
    }

    public void setIsCollided(boolean var1) {
        this.real.isCollided = var1;
    }

    public boolean isVelocityChanged() {
        return this.real.velocityChanged;
    }

    public void setVelocityChanged(boolean var1) {
        this.real.velocityChanged = var1;
    }

    public boolean IsDead() {
        return this.real.isDead;
    }

    public void setIsDead(boolean var1) {
        this.real.isDead = var1;
    }

    public float getWidth() {
        return this.real.width;
    }

    public void setWidth(float var1) {
        this.real.width = var1;
    }

    public float getHeight() {
        return this.real.height;
    }

    public void setHeight(float var1) {
        this.real.height = var1;
    }

    public float getPrevDistanceWalkedModified() {
        return this.real.prevDistanceWalkedModified;
    }

    public void setPrevDistanceWalkedModified(float var1) {
        this.real.prevDistanceWalkedModified = var1;
    }

    public float getDistanceWalkedModified() {
        return this.real.distanceWalkedModified;
    }

    public void setDistanceWalkedModified(float var1) {
        this.real.distanceWalkedModified = var1;
    }

    public float getDistanceWalkedOnStepModified() {
        return this.real.distanceWalkedOnStepModified;
    }

    public void setDistanceWalkedOnStepModified(float var1) {
        this.real.distanceWalkedOnStepModified = var1;
    }

    public float getFallDistance() {
        return this.real.fallDistance;
    }

    public void setFallDistance(float var1) {
        this.real.fallDistance = var1;
    }

    public double getLastTickPosX() {
        return this.real.lastTickPosX;
    }

    public void setLastTickPosX(double var1) {
        this.real.lastTickPosX = var1;
    }

    public double getLastTickPosY() {
        return this.real.lastTickPosY;
    }

    public void setLastTickPosY(double var1) {
        this.real.lastTickPosY = var1;
    }

    public double getLastTickPosZ() {
        return this.real.lastTickPosZ;
    }

    public void setLastTickPosZ(double var1) {
        this.real.lastTickPosZ = var1;
    }

    public float getStepHeight() {
        return this.real.stepHeight;
    }

    public void setStepHeight(float var1) {
        this.real.stepHeight = var1;
    }

    public boolean isNoClip() {
        return this.real.noClip;
    }

    public void setNoClip(boolean var1) {
        this.real.noClip = var1;
    }

    public float getEntityCollisionReduction() {
        return this.real.entityCollisionReduction;
    }

    public void setEntityCollisionReduction(float var1) {
        this.real.entityCollisionReduction = var1;
    }

    public int getTicksExisted() {
        return this.real.ticksExisted;
    }

    public void setTicksExisted(int var1) {
        this.real.ticksExisted = var1;
    }

    public int getFireResistance() {
        return this.real.fireResistance;
    }

    public void setFireResistance(int var1) {
        this.real.fireResistance = var1;
    }

    public int getHurtResistantTime() {
        return this.real.hurtResistantTime;
    }

    public void setHurtResistantTime(int var1) {
        this.real.hurtResistantTime = var1;
    }

    public boolean isAddedToChunk() {
        return this.real.addedToChunk;
    }

    public void setAddedToChunk(boolean var1) {
        this.real.addedToChunk = var1;
    }

    public int getChunkCoordX() {
        return this.real.chunkCoordX;
    }

    public void setChunkCoordX(int var1) {
        this.real.chunkCoordX = var1;
    }

    public int getChunkCoordY() {
        return this.real.chunkCoordY;
    }

    public void setChunkCoordY(int var1) {
        this.real.chunkCoordY = var1;
    }

    public int getChunkCoordZ() {
        return this.real.chunkCoordZ;
    }

    public void setChunkCoordZ(int var1) {
        this.real.chunkCoordZ = var1;
    }

    public int getServerPosX() {
        return this.real.serverPosX;
    }

    public void setServerPosX(int var1) {
        this.real.serverPosX = var1;
    }

    public int getServerPosY() {
        return this.real.serverPosY;
    }

    public void setServerPosY(int var1) {
        this.real.serverPosY = var1;
    }

    public int getServerPosZ() {
        return this.real.serverPosZ;
    }

    public void setServerPosZ(int var1) {
        this.real.serverPosZ = var1;
    }

    public boolean isIgnoreFrustumCheck() {
        return this.real.ignoreFrustumCheck;
    }

    public void setIgnoreFrustumCheck(boolean var1) {
        this.real.ignoreFrustumCheck = var1;
    }

    public boolean IsAirBorne() {
        return this.real.isAirBorne;
    }

    public void setIsAirBorne(boolean var1) {
        this.real.isAirBorne = var1;
    }

    public int getTimeUntilPortal() {
        return this.real.timeUntilPortal;
    }

    public void setTimeUntilPortal(int var1) {
        this.real.timeUntilPortal = var1;
    }

    public int getDimension() {
        return this.real.dimension;
    }

    public void setDimension(int var1) {
        this.real.dimension = var1;
    }

    public void setPosition(double x, double y, double z) {
        real.setPosition(x, y, z);
    }
}
