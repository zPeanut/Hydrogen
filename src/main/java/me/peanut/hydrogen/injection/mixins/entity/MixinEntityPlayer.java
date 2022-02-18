package me.peanut.hydrogen.injection.mixins.entity;

import com.mojang.authlib.GameProfile;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.render.Animations;
import me.peanut.hydrogen.utils.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by peanut on 07/02/2021
 */
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase {

    @Shadow public abstract GameProfile getGameProfile();

    @Shadow
    protected abstract boolean canTriggerWalking();

    @Shadow
    protected abstract String getSwimSound();

    @Shadow
    public abstract boolean isUsingItem();

    @Shadow
    public abstract ItemStack getItemInUse();

    @Shadow
    public abstract int getItemInUseDuration();

    @Shadow
    public PlayerCapabilities capabilities = new PlayerCapabilities();

    @Shadow public float eyeHeight;

    @Shadow public abstract boolean isPlayerSleeping();

    @Shadow public Container openContainer;

    @Shadow public abstract void onLivingUpdate();

    /**
     * @author
     */
    @Overwrite
    public float getEyeHeight() {
        float f = this.eyeHeight;
        if (this.isPlayerSleeping()) {
            f = 0.2F;
        }

        if (this.isSneaking()) {
            f -= 0.08F;
        }

        if(Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled()) {
            f = PlayerUtil.getCustomEyeHeight((EntityPlayer) (Object) this);
        }

        return f;
    }
}
