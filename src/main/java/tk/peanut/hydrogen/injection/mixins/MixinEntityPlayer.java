package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by peanut on 07/02/2021
 */
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase {

    @Shadow
    protected abstract boolean canTriggerWalking();

    @Shadow
    protected abstract String getSwimSound();
}
