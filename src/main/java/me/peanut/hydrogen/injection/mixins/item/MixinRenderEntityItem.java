package me.peanut.hydrogen.injection.mixins.item;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.injection.mixins.render.MixinRender;
import me.peanut.hydrogen.module.modules.render.Animations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

/**
 * Created by peanut on 19/01/2022
 */
@Mixin(RenderEntityItem.class)
public abstract class MixinRenderEntityItem extends MixinRender<EntityItem> {

    @Shadow private Random field_177079_e;

    @Shadow protected abstract ResourceLocation getEntityTexture(Entity par1);

    @Shadow @Final private RenderItem itemRenderer;

    @Shadow public abstract boolean shouldBob();

    @Shadow protected abstract int func_177078_a(ItemStack stack);

    /**
     * @author
     */
    @Overwrite
    public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {
        ItemStack itemstack = entity.getEntityItem();
        this.field_177079_e.setSeed(187L);
        boolean flag = false;
        if (this.bindEntityTexture(entity)) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entity)).setBlurMipmap(false, false);
            flag = true;
        }

        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        IBakedModel ibakedmodel = this.itemRenderer.getItemModelMesher().getItemModel(itemstack);
        int i = this.func_177077_a(entity, x, y, z, partialTicks, ibakedmodel);

        if(Hydrogen.getClient().settingsManager.getSettingByName("Dropped Items").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled()) {
            GlStateManager.rotate(180.0F - Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.scale(1.0F, 1.0F, 0.001F);
        }

        for(int j = 0; j < i; ++j) {
            float f;
            float f1;
            float f2;
            if (ibakedmodel.isGui3d()) {
                GlStateManager.pushMatrix();
                if (j > 0) {
                    f = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    f1 = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    f2 = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    GlStateManager.translate(f, f1, f2);
                }

                GlStateManager.scale(0.5F, 0.5F, 0.5F);
                ibakedmodel = ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND);
                this.itemRenderer.renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
            } else {
                GlStateManager.pushMatrix();
                ibakedmodel = ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND);
                this.itemRenderer.renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
                f = ibakedmodel.getItemCameraTransforms().ground.scale.x;
                f1 = ibakedmodel.getItemCameraTransforms().ground.scale.y;
                f2 = ibakedmodel.getItemCameraTransforms().ground.scale.z;
                if(Hydrogen.getClient().settingsManager.getSettingByName("Dropped Items").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled()) {
                    GlStateManager.translate(-0.05000000074505806D, 0.05000000074505806D, -0.1D);
                } else {
                    GlStateManager.translate(0.0F * f, 0.0F * f1, 0.046875F * f2);
                }
            }
        }

        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        this.bindEntityTexture(entity);
        if (flag) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entity)).restoreLastBlurMipmap();
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * @author
     */
    @Overwrite
    private int func_177077_a(EntityItem itemIn, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
        ItemStack itemstack = itemIn.getEntityItem();
        Item item = itemstack.getItem();
        if (item == null) {
            return 0;
        } else {
            boolean flag = p_177077_9_.isGui3d();
            int i = this.func_177078_a(itemstack);
            float f = 0.25F;
            float f1 = this.shouldBob() ? MathHelper.sin(((float)itemIn.getAge() + p_177077_8_) / 10.0F + itemIn.hoverStart) * 0.1F + 0.1F : 0.0F;
            float f2 = p_177077_9_.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
            GlStateManager.translate((float)p_177077_2_, (float)p_177077_4_ + f1 + 0.25F * f2, (float)p_177077_6_);
            float f6;
            if (flag || Minecraft.getMinecraft().getRenderManager().options != null && !(Hydrogen.getClient().settingsManager.getSettingByName("Dropped Items").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled())) {
                f6 = (((float) itemIn.getAge() + p_177077_8_) / 20.0F + itemIn.hoverStart) * 57.295776F;
                GlStateManager.rotate(f6, 0.0F, 1.0F, 0.0F);
            }

            if (!flag) {
                f6 = -0.0F * (float)(i - 1) * 0.5F;
                float f4 = -0.0F * (float)(i - 1) * 0.5F;
                float f5 = -0.046875F * (float)(i - 1) * 0.5F;
                GlStateManager.translate(f6, f4, f5);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            return i;
        }
    }
}
