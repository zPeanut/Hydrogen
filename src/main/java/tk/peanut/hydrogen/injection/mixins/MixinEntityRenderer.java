package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.modules.render.NameTags;

/**
 * Created by peanut on 08/02/2021
 */

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Shadow
    public abstract void setupCameraTransform(float partialTicks, int pass);

    @Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderWorld(FJ)V", shift = At.Shift.AFTER))
    public void nameTagsIns(float partialTicks, long nanoTime, CallbackInfo ci) {
        if(Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
            this.setupCameraTransform(partialTicks, 2);
            NameTags.instance.render3DPost();
        }
    }


}
