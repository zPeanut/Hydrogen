package tk.peanut.phosphor.scripting.runtime.minecraft.entity.player;

import net.minecraft.entity.player.PlayerCapabilities;

public class WrapperPlayerCapabilities {
    private PlayerCapabilities real;

    public WrapperPlayerCapabilities(PlayerCapabilities var1) {
        this.real = var1;
    }

    public PlayerCapabilities unwrap() {
        return this.real;
    }

    public float getFlySpeed() {
        return this.real.getFlySpeed();
    }

    public void setFlySpeed(float var1) {
        this.real.setFlySpeed(var1);
    }

    public float getWalkSpeed() {
        return this.real.getWalkSpeed();
    }

    public void setPlayerWalkSpeed(float var1) {
        this.real.setPlayerWalkSpeed(var1);
    }

    public boolean isDisableDamage() {
        return this.real.disableDamage;
    }

    public void setDisableDamage(boolean var1) {
        this.real.disableDamage = var1;
    }

    public boolean IsFlying() {
        return this.real.isFlying;
    }

    public void setIsFlying(boolean var1) {
        this.real.isFlying = var1;
    }

    public boolean isAllowFlying() {
        return this.real.allowFlying;
    }

    public void setAllowFlying(boolean var1) {
        this.real.allowFlying = var1;
    }

    public boolean IsCreativeMode() {
        return this.real.isCreativeMode;
    }

    public void setIsCreativeMode(boolean var1) {
        this.real.isCreativeMode = var1;
    }

    public boolean isAllowEdit() {
        return this.real.allowEdit;
    }

    public void setAllowEdit(boolean var1) {
        this.real.allowEdit = var1;
    }
}
