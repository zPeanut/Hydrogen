package me.peanut.hydrogen.injection.mixins.render;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.render.Animations;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by peanut on 19/01/2022
 */
@Mixin(ModelBiped.class)
public class MixinModelBiped {

    @Shadow
    public int heldItemRight;

    @Shadow
    public ModelRenderer bipedRightArm;

    @Inject(method = "setRotationAngles", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelBiped;swingProgress:F"))
    public void oldThirdPersonBlock(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn, CallbackInfo ci) {
        if(this.heldItemRight == 3 && Hydrogen.getClient().settingsManager.getSettingByName("Third-person Block").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled()) {
            this.bipedRightArm.rotateAngleY = 0;
        }
    }

}
