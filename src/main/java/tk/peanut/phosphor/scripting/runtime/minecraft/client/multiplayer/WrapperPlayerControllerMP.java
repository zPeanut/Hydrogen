package tk.peanut.phosphor.scripting.runtime.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import tk.peanut.phosphor.scripting.runtime.minecraft.client.WrapperMinecraft;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.WrapperEntity;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.player.WrapperEntityPlayer;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperBlockPos;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperEnumFacing;

public class WrapperPlayerControllerMP {
    private PlayerControllerMP real;

    public WrapperPlayerControllerMP(PlayerControllerMP var1) {
        this.real = var1;
    }

    public static void clickBlockCreative(WrapperMinecraft var0, WrapperPlayerControllerMP var1, WrapperBlockPos var2, WrapperEnumFacing var3) {
        PlayerControllerMP.clickBlockCreative(var0.unwrap(), var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public PlayerControllerMP unwrap() {
        return this.real;
    }

    public void setPlayerCapabilities(WrapperEntityPlayer var1) {
        this.real.setPlayerCapabilities(var1.unwrap());
    }

    public boolean isSpectator() {
        return this.real.isSpectator();
    }

    public void flipPlayer(WrapperEntityPlayer var1) {
        this.real.flipPlayer(var1.unwrap());
    }

    public boolean shouldDrawHUD() {
        return this.real.shouldDrawHUD();
    }

    public boolean onPlayerDestroyBlock(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.onPlayerDestroyBlock(var1.unwrap(), var2.unwrap());
    }

    public boolean clickBlock(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.clickBlock(var1.unwrap(), var2.unwrap());
    }

    public void resetBlockRemoving() {
        this.real.resetBlockRemoving();
    }

    public boolean onPlayerDamageBlock(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.onPlayerDamageBlock(var1.unwrap(), var2.unwrap());
    }

    public float getBlockReachDistance() {
        return this.real.getBlockReachDistance();
    }

    public void updateController() {
        this.real.updateController();
    }

    public void attackEntity(WrapperEntityPlayer var1, WrapperEntity var2) {
        this.real.attackEntity(var1.unwrap(), var2.unwrap());
    }

    public boolean interactWithEntitySendPacket(WrapperEntityPlayer var1, WrapperEntity var2) {
        return this.real.interactWithEntitySendPacket(var1.unwrap(), var2.unwrap());
    }

    public void sendEnchantPacket(int var1, int var2) {
        this.real.sendEnchantPacket(var1, var2);
    }

    public void onStoppedUsingItem(WrapperEntityPlayer var1) {
        this.real.onStoppedUsingItem(var1.unwrap());
    }

    public boolean gameIsSurvivalOrAdventure() {
        return this.real.gameIsSurvivalOrAdventure();
    }

    public boolean isNotCreative() {
        return this.real.isNotCreative();
    }

    public boolean isInCreativeMode() {
        return this.real.isInCreativeMode();
    }

    public boolean extendedReach() {
        return this.real.extendedReach();
    }

    public boolean isRidingHorse() {
        return this.real.isRidingHorse();
    }

    public boolean isSpectatorMode() {
        return this.real.isSpectatorMode();
    }

}
