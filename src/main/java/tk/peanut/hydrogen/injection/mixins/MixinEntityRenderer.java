package tk.peanut.hydrogen.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import jdk.nashorn.internal.codegen.CompilerConstants;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender3D;
import tk.peanut.hydrogen.module.modules.render.NameTags;
import tk.peanut.hydrogen.module.modules.render.NoHurtCam;

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

    @Inject(method = "hurtCameraEffect", at = @At("HEAD"), cancellable = true)
    private void injectHurtCameraEffect(CallbackInfo callbackInfo) {
        if (Hydrogen.getClient().moduleManager.getModule(NoHurtCam.class).isEnabled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.BEFORE))
    private void renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackInfo) {
        EventRender3D e = new EventRender3D(partialTicks);
        EventManager.call(e);
    }



}
