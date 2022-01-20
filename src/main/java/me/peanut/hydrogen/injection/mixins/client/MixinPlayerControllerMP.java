package me.peanut.hydrogen.injection.mixins.client;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.modules.combat.Reach;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Created by peanut on 03/01/2022
 */
@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {

    @Inject(method = "getBlockReachDistance", at = @At("HEAD"), cancellable = true)
    private void getReach(final CallbackInfoReturnable<Float> returnable) {
        final Module reach = Hydrogen.getClient().moduleManager.getModule(Reach.class);
        if (reach.isEnabled()) {
            returnable.setReturnValue((float) Hydrogen.getClient().settingsManager.getSettingByName(reach, "Distance").getValue());
        }
    }

}
