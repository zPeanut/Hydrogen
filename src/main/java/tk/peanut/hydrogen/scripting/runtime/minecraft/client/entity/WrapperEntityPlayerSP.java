package tk.peanut.hydrogen.scripting.runtime.minecraft.client.entity;

import net.minecraft.client.entity.EntityPlayerSP;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.network.WrapperNetHandlerPlayClient;
import tk.peanut.hydrogen.scripting.runtime.minecraft.entity.WrapperEntity;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperBlockPos;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperIChatComponent;

public class WrapperEntityPlayerSP extends WrapperAbstractClientPlayer {
    private EntityPlayerSP real;

    public WrapperEntityPlayerSP(EntityPlayerSP var1) {
        super(var1);
        this.real = var1;
    }

    public EntityPlayerSP unwrap() {
        return this.real;
    }

    public void heal(float var1) {
        this.real.heal(var1);
    }

    public void mountEntity(WrapperEntity var1) {
        this.real.mountEntity(var1.unwrap());
    }

    public void onUpdate() {
        this.real.onUpdate();
    }

    public void onUpdateWalkingPlayer() {
        this.real.onUpdateWalkingPlayer();
    }

    public void sendChatMessage(String var1) {
        this.real.sendChatMessage(var1);
    }

    public void swingItem() {
        this.real.swingItem();
    }

    public void respawnPlayer() {
        this.real.respawnPlayer();
    }

    public void closeScreen() {
        this.real.closeScreen();
    }

    public void closeScreenAndDropStack() {
        this.real.closeScreenAndDropStack();
    }

    public void setPlayerSPHealth(float var1) {
        this.real.setPlayerSPHealth(var1);
    }

    public void sendPlayerAbilities() {
        this.real.sendPlayerAbilities();
    }

    public boolean isUser() {
        return this.real.isUser();
    }

    public void sendHorseInventory() {
        this.real.sendHorseInventory();
    }

    public String getClientBrand() {
        return this.real.getClientBrand();
    }

    public void setClientBrand(String var1) {
        this.real.setClientBrand(var1);
    }

    public void addChatComponentMessage(WrapperIChatComponent var1) {
        this.real.addChatComponentMessage(var1.unwrap());
    }

    public void setSprinting(boolean var1) {
        this.real.setSprinting(var1);
    }

    public void setXPStats(float var1, int var2, int var3) {
        this.real.setXPStats(var1, var2, var3);
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

    public void playSound(String var1, float var2, float var3) {
        this.real.playSound(var1, var2, var3);
    }

    public boolean isServerWorld() {
        return this.real.isServerWorld();
    }

    public boolean isRidingHorse() {
        return this.real.isRidingHorse();
    }

    public float getHorseJumpPower() {
        return this.real.getHorseJumpPower();
    }

    public void onCriticalHit(WrapperEntity var1) {
        this.real.onCriticalHit(var1.unwrap());
    }

    public void onEnchantmentCritical(WrapperEntity var1) {
        this.real.onEnchantmentCritical(var1.unwrap());
    }

    public boolean isSneaking() {
        return this.real.isSneaking();
    }

    public void updateEntityActionState() {
        this.real.updateEntityActionState();
    }

    public void onLivingUpdate() {
        this.real.onLivingUpdate();
    }

    public WrapperNetHandlerPlayClient getSendQueue() {
        return new WrapperNetHandlerPlayClient(this.real.sendQueue);
    }

    public int getSprintingTicksLeft() {
        return this.real.sprintingTicksLeft;
    }

    public void setSprintingTicksLeft(int var1) {
        this.real.sprintingTicksLeft = var1;
    }

    public float getRenderArmYaw() {
        return this.real.renderArmYaw;
    }

    public void setRenderArmYaw(float var1) {
        this.real.renderArmYaw = var1;
    }

    public float getRenderArmPitch() {
        return this.real.renderArmPitch;
    }

    public void setRenderArmPitch(float var1) {
        this.real.renderArmPitch = var1;
    }

    public float getPrevRenderArmYaw() {
        return this.real.prevRenderArmYaw;
    }

    public void setPrevRenderArmYaw(float var1) {
        this.real.prevRenderArmYaw = var1;
    }

    public float getPrevRenderArmPitch() {
        return this.real.prevRenderArmPitch;
    }

    public void setPrevRenderArmPitch(float var1) {
        this.real.prevRenderArmPitch = var1;
    }

    public float getTimeInPortal() {
        return this.real.timeInPortal;
    }

    public void setTimeInPortal(float var1) {
        this.real.timeInPortal = var1;
    }

    public float getPrevTimeInPortal() {
        return this.real.prevTimeInPortal;
    }

    public void setPrevTimeInPortal(float var1) {
        this.real.prevTimeInPortal = var1;
    }
}
