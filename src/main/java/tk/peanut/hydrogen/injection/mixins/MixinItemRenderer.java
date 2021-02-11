package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.modules.render.HitAnimation;

/**
 * Created by peanut on 11/02/2021
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

    @Overwrite
    public void renderItemInFirstPerson(float partialTicks) {
        float f = 1.0F - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks);
        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().thePlayer;
        float f1 = abstractclientplayer.getSwingProgress(partialTicks);
        float f2 = abstractclientplayer.prevRotationPitch + (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * partialTicks;
        float f3 = abstractclientplayer.prevRotationYaw + (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * partialTicks;
        this.rotateArroundXAndY(f2, f3);
        this.setLightMapFromPlayer(abstractclientplayer);
        this.rotateWithPlayerRotations((EntityPlayerSP)abstractclientplayer, partialTicks);
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        if (this.itemToRender != null) {
            if (this.itemToRender.getItem() instanceof ItemMap) {
                this.renderItemMap(abstractclientplayer, f2, f, f1);
            } else if (abstractclientplayer.getItemInUseCount() > 0) {
                EnumAction enumaction = this.itemToRender.getItemUseAction();
                switch(enumaction) {
                    case NONE:
                        this.transformFirstPersonItem(f, 0.0F);
                        break;
                    case EAT:
                    case DRINK:
                        this.performDrinking(abstractclientplayer, partialTicks);
                        this.transformFirstPersonItem(f, 0.0F);
                        break;
                    case BLOCK:
                        this.transformFirstPersonItem(f, 0.0F);
                        this.doBlockTransformations();
                        break;
                    case BOW:
                        this.transformFirstPersonItem(f, 0.0F);
                        this.doBowTransformations(partialTicks, abstractclientplayer);
                }
            } else {
                if (!Hydrogen.getClient().moduleManager.getModulebyName("HitAnimation").isEnabled()) {
                    this.doItemUsedTransformations(f1);
                }
                this.transformFirstPersonItem(f, f1);
            }

            this.renderItem(abstractclientplayer, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
        } else if (!abstractclientplayer.isInvisible()) {
            this.renderPlayerArm(abstractclientplayer, f, f1);
        }

        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
    }

    /*
if (this.itemToRender != null)
        {
            if (this.itemToRender.getItem() instanceof ItemMap)
            {
                this.renderItemMap(entityplayersp, f2, f, f1);
            }
            else if (entityplayersp.getItemInUseCount() > 0)
            {
                EnumAction enumaction = this.itemToRender.getItemUseAction();

                float abc = 0f;
                    abc = f1;


                switch (ItemRenderer.ItemRenderer$1.field_178094_a[enumaction.ordinal()])
                {
                    case 1:
                        this.transformFirstPersonItem(f, 0.0F);
                        break;

                    case 2:
                    case 3:
                        this.func_178104_a(entityplayersp, partialTicks);
                        this.transformFirstPersonItem(f, 0.0F);
                        if(Tephra.instance.settingsManager.getSettingByName("Old Blockhit").getValBoolean() && Tephra.instance.moduleManager.getModulebyName("OldAnimations").isEnabled()) {
                            GlStateManager.scale(0.83F, 0.88F, 0.85F);
                            GlStateManager.rotate(-0.3F, 0.1F, 0.0F, 0.0F);
                        }
                        break;

                    case 4:
                        // blockhit tephra
                       transformFirstPersonItem(f, Tephra.instance.settingsManager.getSettingByName("Old Blockhit").getValBoolean() && Tephra.instance.moduleManager.getModulebyName("OldAnimations").isEnabled() ? f1 : 0.0F);
                        this.func_178103_d();
                        break;

                    case 5:
                        this.transformFirstPersonItem(f, 0.0F);
                        this.func_178098_a(partialTicks, entityplayersp);
                }
            }
            else
            {
                // HITANIMATION INS.

                if(Tephra.instance.moduleManager.getModule(HitAnimation.class).isEnabled()) {
                    this.transformFirstPersonItem(f, f1);
                }else {

                    this.func_178105_d(f1);
                    this.transformFirstPersonItem(f, f1);
                }

            }

            this.renderItem(entityplayersp, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
        }
     */

}
