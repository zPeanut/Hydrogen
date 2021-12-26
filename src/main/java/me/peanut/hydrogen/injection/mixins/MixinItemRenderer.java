package me.peanut.hydrogen.injection.mixins;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.modules.render.AntiBlind;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by peanut on 26/12/2021
 */

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

    @Inject(method = "renderFireInFirstPerson", at = @At("HEAD"), cancellable = true)
    private void renderFireInFirstPerson(CallbackInfo ci) {
        Module antiBlind = Hydrogen.getClient().moduleManager.getModule(AntiBlind.class);
        if (antiBlind.isEnabled() && Hydrogen.getClient().settingsManager.getSettingByName(antiBlind, "Fire").isEnabled()) {
            ci.cancel();
        }
    }
}
