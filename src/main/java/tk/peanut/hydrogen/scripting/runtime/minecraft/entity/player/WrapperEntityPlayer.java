package tk.peanut.hydrogen.scripting.runtime.minecraft.entity.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tk.peanut.hydrogen.scripting.runtime.minecraft.block.WrapperBlock;
import tk.peanut.hydrogen.scripting.runtime.minecraft.entity.WrapperEntity;
import tk.peanut.hydrogen.scripting.runtime.minecraft.entity.WrapperEntityLivingBase;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperBlockPos;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperIChatComponent;
import tk.peanut.hydrogen.scripting.runtime.minecraft.world.WrapperWorld;

import java.util.UUID;

public class WrapperEntityPlayer extends WrapperEntityLivingBase {
    private EntityPlayer real;

    public WrapperEntityPlayer(EntityPlayer var1) {
        super(var1);
        this.real = var1;
    }

    public static WrapperBlockPos getBedSpawnLocation(WrapperWorld var0, WrapperBlockPos var1, boolean var2) {
        return new WrapperBlockPos(EntityPlayer.getBedSpawnLocation(var0.unwrap(), var1.unwrap(), var2));
    }

    public static UUID getOfflineUUID(String var0) {
        return EntityPlayer.getOfflineUUID(var0);
    }

    public EntityPlayer unwrap() {
        return this.real;
    }

    public int getItemInUseCount() {
        return this.real.getItemInUseCount();
    }

    public boolean isUsingItem() {
        return this.real.isUsingItem();
    }

    public int getItemInUseDuration() {
        return this.real.getItemInUseDuration();
    }

    public void stopUsingItem() {
        this.real.stopUsingItem();
    }

    public void clearItemInUse() {
        this.real.clearItemInUse();
    }

    public boolean isBlocking() {
        return this.real.isBlocking();
    }

    public void onUpdate() {
        this.real.onUpdate();
    }

    public int getMaxInPortalTime() {
        return this.real.getMaxInPortalTime();
    }

    public int getPortalCooldown() {
        return this.real.getPortalCooldown();
    }

    public void playSound(String var1, float var2, float var3) {
        this.real.playSound(var1, var2, var3);
    }

    public void handleStatusUpdate(byte var1) {
        this.real.handleStatusUpdate(var1);
    }

    public void updateRidden() {
        this.real.updateRidden();
    }

    public void preparePlayerToSpawn() {
        this.real.preparePlayerToSpawn();
    }

    public void onLivingUpdate() {
        this.real.onLivingUpdate();
    }

    public int getScore() {
        return this.real.getScore();
    }

    public void setScore(int var1) {
        this.real.setScore(var1);
    }

    public void addScore(int var1) {
        this.real.addScore(var1);
    }

    public void addToPlayerScore(WrapperEntity var1, int var2) {
        this.real.addToPlayerScore(var1.unwrap(), var2);
    }

    public float getToolDigEfficiency(WrapperBlock var1) {
        return this.real.getToolDigEfficiency(var1.unwrap());
    }

    public boolean canHarvestBlock(WrapperBlock var1) {
        return this.real.canHarvestBlock(var1.unwrap());
    }

    public boolean canAttackPlayer(WrapperEntityPlayer var1) {
        return this.real.canAttackPlayer(var1.unwrap());
    }

    public int getTotalArmorValue() {
        return this.real.getTotalArmorValue();
    }

    public float getArmorVisibility() {
        return this.real.getArmorVisibility();
    }

    public boolean interactWith(WrapperEntity var1) {
        return this.real.interactWith(var1.unwrap());
    }

    public void destroyCurrentEquippedItem() {
        this.real.destroyCurrentEquippedItem();
    }

    public double getYOffset() {
        return this.real.getYOffset();
    }

    public void attackTargetEntityWithCurrentItem(WrapperEntity var1) {
        this.real.attackTargetEntityWithCurrentItem(var1.unwrap());
    }

    public void onCriticalHit(WrapperEntity var1) {
        this.real.onCriticalHit(var1.unwrap());
    }

    public void onEnchantmentCritical(WrapperEntity var1) {
        this.real.onEnchantmentCritical(var1.unwrap());
    }

    public void respawnPlayer() {
        this.real.respawnPlayer();
    }

    public void setDead() {
        this.real.setDead();
    }

    public boolean isEntityInsideOpaqueBlock() {
        return this.real.isEntityInsideOpaqueBlock();
    }

    public boolean isUser() {
        return this.real.isUser();
    }

    public void wakeUpPlayer(boolean var1, boolean var2, boolean var3) {
        this.real.wakeUpPlayer(var1, var2, var3);
    }

    public float getBedOrientationInDegrees() {
        return this.real.getBedOrientationInDegrees();
    }

    public boolean isPlayerSleeping() {
        return this.real.isPlayerSleeping();
    }

    public boolean isPlayerFullyAsleep() {
        return this.real.isPlayerFullyAsleep();
    }

    public int getSleepTimer() {
        return this.real.getSleepTimer();
    }

    public void addChatComponentMessage(WrapperIChatComponent var1) {
        this.real.addChatComponentMessage(var1.unwrap());
    }

    public WrapperBlockPos getBedLocation() {
        return new WrapperBlockPos(this.real.getBedLocation());
    }

    public boolean isSpawnForced() {
        return this.real.isSpawnForced();
    }

    public void setSpawnPoint(WrapperBlockPos var1, boolean var2) {
        this.real.setSpawnPoint(var1.unwrap(), var2);
    }

    public void jump() {
        this.real.jump();
    }

    public void moveEntityWithHeading(float var1, float var2) {
        this.real.moveEntityWithHeading(var1, var2);
    }

    public float getAIMoveSpeed() {
        return this.real.getAIMoveSpeed();
    }

    public void fall(float var1, float var2) {
        this.real.fall(var1, var2);
    }

    public void onKillEntity(WrapperEntityLivingBase var1) {
        this.real.onKillEntity(var1.unwrap());
    }

    public void setInWeb() {
        this.real.setInWeb();
    }

    public void addExperience(int var1) {
        this.real.addExperience(var1);
    }

    public int getXPSeed() {
        return this.real.getXPSeed();
    }

    public void removeExperienceLevel(int var1) {
        this.real.removeExperienceLevel(var1);
    }

    public void addExperienceLevel(int var1) {
        this.real.addExperienceLevel(var1);
    }

    public int xpBarCap() {
        return this.real.xpBarCap();
    }

    public void addExhaustion(float var1) {
        this.real.addExhaustion(var1);
    }

    public boolean canEat(boolean var1) {
        return this.real.canEat(var1);
    }

    public boolean shouldHeal() {
        return this.real.shouldHeal();
    }

    public boolean isAllowEdit() {
        return this.real.isAllowEdit();
    }

    public boolean getAlwaysRenderNameTagForRender() {
        return this.real.getAlwaysRenderNameTagForRender();
    }

    public void clonePlayer(WrapperEntityPlayer var1, boolean var2) {
        this.real.clonePlayer(var1.unwrap(), var2);
    }

    public void sendPlayerAbilities() {
        this.real.sendPlayerAbilities();
    }

    public String getName() {
        return this.real.getName();
    }

    public boolean isInvisibleToPlayer(WrapperEntityPlayer var1) {
        return this.real.isInvisibleToPlayer(var1.unwrap());
    }

    public ItemStack[] getInventory() {
        return this.real.getInventory();
    }

    public void setInventory(WrapperInventoryPlayer var1) {
        this.real.inventory = var1.unwrap();
    }

    public boolean isPushedByWater() {
        return this.real.isPushedByWater();
    }

    public WrapperIChatComponent getDisplayName() {
        return new WrapperIChatComponent(this.real.getDisplayName());
    }

    public float getEyeHeight() {
        return this.real.getEyeHeight();
    }

    public float getAbsorptionAmount() {
        return this.real.getAbsorptionAmount();
    }

    public void setAbsorptionAmount(float var1) {
        this.real.setAbsorptionAmount(var1);
    }

    public boolean sendCommandFeedback() {
        return this.real.sendCommandFeedback();
    }

    public boolean hasReducedDebug() {
        return this.real.hasReducedDebug();
    }

    public void setReducedDebug(boolean var1) {
        this.real.setReducedDebug(var1);
    }

    public float getPrevCameraYaw() {
        return this.real.prevCameraYaw;
    }

    public void setPrevCameraYaw(float var1) {
        this.real.prevCameraYaw = var1;
    }

    public float getCameraYaw() {
        return this.real.cameraYaw;
    }

    public void setCameraYaw(float var1) {
        this.real.cameraYaw = var1;
    }

    public int getXpCooldown() {
        return this.real.xpCooldown;
    }

    public void setXpCooldown(int var1) {
        this.real.xpCooldown = var1;
    }

    public double getPrevChasingPosX() {
        return this.real.prevChasingPosX;
    }

    public void setPrevChasingPosX(double var1) {
        this.real.prevChasingPosX = var1;
    }

    public double getPrevChasingPosY() {
        return this.real.prevChasingPosY;
    }

    public void setPrevChasingPosY(double var1) {
        this.real.prevChasingPosY = var1;
    }

    public double getPrevChasingPosZ() {
        return this.real.prevChasingPosZ;
    }

    public void setPrevChasingPosZ(double var1) {
        this.real.prevChasingPosZ = var1;
    }

    public double getChasingPosX() {
        return this.real.chasingPosX;
    }

    public void setChasingPosX(double var1) {
        this.real.chasingPosX = var1;
    }

    public double getChasingPosY() {
        return this.real.chasingPosY;
    }

    public void setChasingPosY(double var1) {
        this.real.chasingPosY = var1;
    }

    public double getChasingPosZ() {
        return this.real.chasingPosZ;
    }

    public void setChasingPosZ(double var1) {
        this.real.chasingPosZ = var1;
    }

    public WrapperBlockPos getPlayerLocation() {
        return new WrapperBlockPos(this.real.playerLocation);
    }

    public void setPlayerLocation(WrapperBlockPos var1) {
        this.real.playerLocation = var1.unwrap();
    }

    public float getRenderOffsetX() {
        return this.real.renderOffsetX;
    }

    public void setRenderOffsetX(float var1) {
        this.real.renderOffsetX = var1;
    }

    public float getRenderOffsetY() {
        return this.real.renderOffsetY;
    }

    public void setRenderOffsetY(float var1) {
        this.real.renderOffsetY = var1;
    }

    public float getRenderOffsetZ() {
        return this.real.renderOffsetZ;
    }

    public void setRenderOffsetZ(float var1) {
        this.real.renderOffsetZ = var1;
    }

    public WrapperPlayerCapabilities getCapabilities() {
        return new WrapperPlayerCapabilities(this.real.capabilities);
    }

    public void setCapabilities(WrapperPlayerCapabilities var1) {
        this.real.capabilities = var1.unwrap();
    }

    public int getExperienceLevel() {
        return this.real.experienceLevel;
    }

    public void setExperienceLevel(int var1) {
        this.real.experienceLevel = var1;
    }

    public int getExperienceTotal() {
        return this.real.experienceTotal;
    }

    public void setExperienceTotal(int var1) {
        this.real.experienceTotal = var1;
    }

    public float getExperience() {
        return this.real.experience;
    }

    public void setExperience(float var1) {
        this.real.experience = var1;
    }
}
