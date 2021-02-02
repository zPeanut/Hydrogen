package tk.peanut.phosphor.scripting.runtime.minecraft.entity.player;

import net.minecraft.entity.player.EntityPlayerMP;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.WrapperEntity;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperBlockPos;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperIChatComponent;
import tk.peanut.phosphor.scripting.runtime.minecraft.world.WrapperWorldServer;

import java.util.List;

public class WrapperEntityPlayerMP extends WrapperEntityPlayer {
    private EntityPlayerMP real;

    public WrapperEntityPlayerMP(EntityPlayerMP var1) {
        super(var1);
        this.real = var1;
    }

    public EntityPlayerMP unwrap() {
        return this.real;
    }

    public void addExperienceLevel(int var1) {
        this.real.addExperienceLevel(var1);
    }

    public void removeExperienceLevel(int var1) {
        this.real.removeExperienceLevel(var1);
    }

    public void addSelfToInternalCraftingInventory() {
        this.real.addSelfToInternalCraftingInventory();
    }

    public void sendEnterCombat() {
        this.real.sendEnterCombat();
    }

    public void sendEndCombat() {
        this.real.sendEndCombat();
    }

    public void onUpdate() {
        this.real.onUpdate();
    }

    public void onUpdateEntity() {
        this.real.onUpdateEntity();
    }

    public boolean canAttackPlayer(WrapperEntityPlayer var1) {
        return this.real.canAttackPlayer(var1.unwrap());
    }

    public void travelToDimension(int var1) {
        this.real.travelToDimension(var1);
    }

    public boolean isSpectatedByPlayer(WrapperEntityPlayerMP var1) {
        return this.real.isSpectatedByPlayer(var1.unwrap());
    }

    public void onItemPickup(WrapperEntity var1, int var2) {
        this.real.onItemPickup(var1.unwrap(), var2);
    }

    public void wakeUpPlayer(boolean var1, boolean var2, boolean var3) {
        this.real.wakeUpPlayer(var1, var2, var3);
    }

    public void mountEntity(WrapperEntity var1) {
        this.real.mountEntity(var1.unwrap());
    }

    public void closeScreen() {
        this.real.closeScreen();
    }

    public void updateHeldItem() {
        this.real.updateHeldItem();
    }

    public void closeContainer() {
        this.real.closeContainer();
    }

    public void setEntityActionState(float var1, float var2, boolean var3, boolean var4) {
        this.real.setEntityActionState(var1, var2, var3, var4);
    }

    public void mountEntityAndWakeUp() {
        this.real.mountEntityAndWakeUp();
    }

    public void setPlayerHealthUpdated() {
        this.real.setPlayerHealthUpdated();
    }

    public void addChatComponentMessage(WrapperIChatComponent var1) {
        this.real.addChatComponentMessage(var1.unwrap());
    }

    public void clonePlayer(WrapperEntityPlayer var1, boolean var2) {
        this.real.clonePlayer(var1.unwrap(), var2);
    }


    public void onCriticalHit(WrapperEntity var1) {
        this.real.onCriticalHit(var1.unwrap());
    }

    public void onEnchantmentCritical(WrapperEntity var1) {
        this.real.onEnchantmentCritical(var1.unwrap());
    }

    public void sendPlayerAbilities() {
        this.real.sendPlayerAbilities();
    }

    public WrapperWorldServer getServerForPlayer() {
        return new WrapperWorldServer(this.real.getServerForPlayer());
    }

    public boolean isSpectator() {
        return this.real.isSpectator();
    }

    public void addChatMessage(WrapperIChatComponent var1) {
        this.real.addChatMessage(var1.unwrap());
    }

    public boolean canCommandSenderUseCommand(int var1, String var2) {
        return this.real.canCommandSenderUseCommand(var1, var2);
    }

    public String getPlayerIP() {
        return this.real.getPlayerIP();
    }

    public void loadResourcePack(String var1, String var2) {
        this.real.loadResourcePack(var1, var2);
    }

    public WrapperBlockPos getPosition() {
        return new WrapperBlockPos(this.real.getPosition());
    }

    public void markPlayerActive() {
        this.real.markPlayerActive();
    }

    public void removeEntity(WrapperEntity var1) {
        this.real.removeEntity(var1.unwrap());
    }

    public WrapperEntity getSpectatingEntity() {
        return new WrapperEntity(this.real.getSpectatingEntity());
    }

    public void setSpectatingEntity(WrapperEntity var1) {
        this.real.setSpectatingEntity(var1.unwrap());
    }

    public void attackTargetEntityWithCurrentItem(WrapperEntity var1) {
        this.real.attackTargetEntityWithCurrentItem(var1.unwrap());
    }

    public long getLastActiveTime() {
        return this.real.getLastActiveTime();
    }

    public WrapperIChatComponent getTabListDisplayName() {
        return new WrapperIChatComponent(this.real.getTabListDisplayName());
    }

    public double getManagedPosX() {
        return this.real.managedPosX;
    }

    public void setManagedPosX(double var1) {
        this.real.managedPosX = var1;
    }

    public double getManagedPosZ() {
        return this.real.managedPosZ;
    }

    public void setManagedPosZ(double var1) {
        this.real.managedPosZ = var1;
    }

    public List getLoadedChunks() {
        return this.real.loadedChunks;
    }

    public boolean IsChangingQuantityOnly() {
        return this.real.isChangingQuantityOnly;
    }

    public void setIsChangingQuantityOnly(boolean var1) {
        this.real.isChangingQuantityOnly = var1;
    }

    public int getPing() {
        return this.real.ping;
    }

    public void setPing(int var1) {
        this.real.ping = var1;
    }

    public boolean isPlayerConqueredTheEnd() {
        return this.real.playerConqueredTheEnd;
    }

    public void setPlayerConqueredTheEnd(boolean var1) {
        this.real.playerConqueredTheEnd = var1;
    }
}
