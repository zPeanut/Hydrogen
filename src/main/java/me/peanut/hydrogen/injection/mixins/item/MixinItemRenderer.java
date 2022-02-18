package me.peanut.hydrogen.injection.mixins.item;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.modules.render.AntiBlind;
import me.peanut.hydrogen.module.modules.render.Animations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by peanut on 26/12/2021
 */

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Shadow
    private float equippedProgress;

    @Shadow
    public abstract void transformFirstPersonItem(float equipProgress, float swingProgress);

    @Shadow
    public abstract void rotateArroundXAndY(float angle, float angleY);

    @Shadow
    public abstract void renderItemMap(AbstractClientPlayer clientPlayer, float pitch, float equipmentProgress, float swingProgress);

    @Shadow
    private float prevEquippedProgress;

    @Shadow
    public abstract void setLightMapFromPlayer(AbstractClientPlayer clientPlayer);

    @Shadow
    private ItemStack itemToRender;

    @Shadow
    public abstract void rotateWithPlayerRotations(EntityPlayerSP entityplayerspIn, float partialTicks);

    @Shadow
    public abstract void performDrinking(AbstractClientPlayer clientPlayer, float partialTicks);

    @Shadow
    public abstract void doBlockTransformations();

    @Shadow
    public abstract void doBowTransformations(float partialTicks, AbstractClientPlayer clientPlayer);

    @Shadow
    public abstract void doItemUsedTransformations(float swingProgress);

    @Shadow
    public abstract void renderItem(EntityLivingBase entityIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform);

    @Shadow
    public abstract void renderPlayerArm(AbstractClientPlayer clientPlayer, float equipProgress, float swingProgress);

    @Inject(method = "renderFireInFirstPerson", at = @At("HEAD"), cancellable = true)
    private void renderFireInFirstPerson(CallbackInfo ci) {
        Module antiBlind = Hydrogen.getClient().moduleManager.getModule(AntiBlind.class);
        if (antiBlind.isEnabled() && Hydrogen.getClient().settingsManager.getSettingByName(antiBlind, "Fire").isEnabled()) {
            ci.cancel();
        }
    }

    /**
     * @author
     */
    @Overwrite
    public void renderItemInFirstPerson(float partialTicks) {
        float f = 1.0F - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks);
        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().thePlayer;
        float f2 = abstractclientplayer.getSwingProgress(partialTicks);
        float f3 = abstractclientplayer.prevRotationPitch + (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * partialTicks;
        float f4 = abstractclientplayer.prevRotationYaw + (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * partialTicks;
        this.rotateArroundXAndY(f3, f4);
        this.setLightMapFromPlayer(abstractclientplayer);
        this.rotateWithPlayerRotations((EntityPlayerSP) abstractclientplayer, partialTicks);
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        if (this.itemToRender != null) {
            if (this.itemToRender.getItem() instanceof ItemMap) {
                this.renderItemMap(abstractclientplayer, f4, f, f4);
            } else if (abstractclientplayer.getItemInUseCount() > 0) {
                EnumAction enumaction = this.itemToRender.getItemUseAction();
                boolean blockhit = Hydrogen.getClient().settingsManager.getSettingByName("BlockHit").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled();
                boolean bow = Hydrogen.getClient().settingsManager.getSettingByName("Bow").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled();
                switch (enumaction) {
                    case NONE:
                        this.transformFirstPersonItem(f, 0.0F);
                        break;
                    case EAT:
                    case DRINK:
                        this.performDrinking(abstractclientplayer, partialTicks);
                        this.transformFirstPersonItem(f, 0);
                        if (Hydrogen.getClient().settingsManager.getSettingByName("BlockHit").isEnabled() && Hydrogen.getClient().moduleManager.getModulebyName("Animations").isEnabled()) {
                            GlStateManager.translate(-0.5f, 0.2f, 0.0f);
                            GlStateManager.scale(0.83F, 0.88F, 0.85F);
                            GlStateManager.rotate(-0.3F, 0.1F, 0.0F, 0.0F);
                        }
                        break;
                    case BLOCK:
                        this.transformFirstPersonItem(f, (blockhit ? f2 : 0));
                        this.doBlockTransformations();
                        break;
                    case BOW:
                        this.transformFirstPersonItem(f, (bow ? f2 : 0));
                        this.doBowTransformations(partialTicks, abstractclientplayer);
                }
            } else {
                if (!Hydrogen.getClient().moduleManager.getModulebyName("HitAnimation").isEnabled()) {
                    this.doItemUsedTransformations(f2);
                }
                this.transformFirstPersonItem(f, f2);
            }
            this.renderItem(abstractclientplayer, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
        } else if (!abstractclientplayer.isInvisible()) {
            this.renderPlayerArm(abstractclientplayer, f, f2);
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
    }

    @Inject(method = "transformFirstPersonItem", at = @At("HEAD"))
    public void oldRodBowAnimations(float equipProgress, float swingProgress, CallbackInfo ci) {
        boolean bow = Hydrogen.getClient().settingsManager.getSettingByName("Bow").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled();
        boolean rod = Hydrogen.getClient().settingsManager.getSettingByName("Rod").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled();
        if (bow && Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getItemInUse() != null && Minecraft.getMinecraft().thePlayer.getItemInUse().getItem() != null && Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getItemInUse().getItem()) == 261) {
            GlStateManager.translate(0.0F, 0.0F, -0.08F);
        }

        if (rod && Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null && Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() != null && Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem()) == 346) {
            GlStateManager.translate(0.1F, -0.02F, -0.335F);
        }
    }

}
