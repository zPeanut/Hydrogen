package me.peanut.hydrogen.injection.mixins.gui;

import com.darkmagician6.eventapi.EventManager;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.modules.render.Animations;
import me.peanut.hydrogen.module.modules.render.AntiBlind;
import me.peanut.hydrogen.ui.ingame.HUD;
import me.peanut.hydrogen.utils.BlurUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@SideOnly(Side.CLIENT)
@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame extends MixinGui {

    @Shadow
    protected abstract void renderHotbarItem(int index, int xPos, int yPos, float partialTicks, EntityPlayer player);

    @Shadow
    @Final
    protected static final ResourceLocation widgetsTexPath = new ResourceLocation("textures/gui/widgets.png");


    @Shadow @Final protected Minecraft mc;

    @Shadow protected long healthUpdateCounter;

    @Shadow protected int updateCounter;

    @Shadow protected int playerHealth;

    @Shadow protected long lastSystemTime;

    @Shadow protected int lastPlayerHealth;

    @Shadow @Final protected Random rand;

    @Inject(method = "renderTooltip", at = @At("RETURN"))
    private void renderTooltip(ScaledResolution sr, float partialTicks, CallbackInfo ci) {
        EventRender2D e = new EventRender2D();
        EventManager.call(e);
    }

    @SuppressWarnings("OverwriteAuthorRequired")
    @Overwrite
    protected void renderTooltip(ScaledResolution sr, float partialTicks) {
        if (Hydrogen.getClient().moduleManager.getModulebyName("Hotbar").isEnabled() && !(Hydrogen.getClient().panic)) {
            if (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer) {
                GlStateManager.enableRescaleNormal();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(widgetsTexPath);
                EntityPlayer entityplayer = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();

                int i = sr.getScaledWidth() / 2;
                float f = this.zLevel;

                this.zLevel = -90.0F;

                this.zLevel = f;
                GlStateManager.enableRescaleNormal();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                RenderHelper.enableGUIStandardItemLighting();
                for (int j = 0; j < 9; j++) {
                    int k = sr.getScaledWidth() / 2 - 90 + j * 20 + 2;
                    int l = sr.getScaledHeight() - 16 - 3;
                    renderHotbarItem(j, k, l - 1, partialTicks, entityplayer);
                }

                // right side white rect

                RenderUtil.rect(Utils.getScaledRes().getScaledWidth() - 7, Utils.getScaledRes().getScaledHeight() - 23, Utils.getScaledRes().getScaledWidth(), Utils.getScaledRes().getScaledHeight(), Integer.MAX_VALUE);

                // actual hotbar rect

                RenderUtil.rect(0, Utils.getScaledRes().getScaledHeight() - 23, Utils.getScaledRes().getScaledWidth() - 7, Utils.getScaledRes().getScaledHeight(), 0x77000000);

                // selected hotbar item

                RenderUtil.rect(Utils.slide, Utils.getScaledRes().getScaledHeight() - 23, Utils.slide + 22, Utils.getScaledRes().getScaledHeight(), Integer.MAX_VALUE);

                RenderHelper.disableStandardItemLighting();
                GlStateManager.disableRescaleNormal();
                GlStateManager.disableBlend();
            }
        } else {
            if (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(widgetsTexPath);
                EntityPlayer entityplayer = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();
                int i = sr.getScaledWidth() / 2;
                float f = this.zLevel;
                this.zLevel = -90.0F;
                this.drawTexturedModalRect(i - 91, sr.getScaledHeight() - 22, 0, 0, 182, 22);
                this.drawTexturedModalRect(i - 91 - 1 + entityplayer.inventory.currentItem * 20, sr.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
                this.zLevel = f;
                GlStateManager.enableRescaleNormal();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                RenderHelper.enableGUIStandardItemLighting();

                for (int j = 0; j < 9; ++j) {
                    int k = sr.getScaledWidth() / 2 - 90 + j * 20 + 2;
                    int l = sr.getScaledHeight() - 16 - 3;
                    this.renderHotbarItem(j, k, l, partialTicks, entityplayer);
                }

                RenderHelper.disableStandardItemLighting();
                GlStateManager.disableRescaleNormal();
                GlStateManager.disableBlend();
            }
        }
    }

    @Inject(method = "renderPumpkinOverlay", at = @At("HEAD"), cancellable = true)
    private void renderPumpkinOverlay(CallbackInfo ci) {
        Module antiBlind = Hydrogen.getClient().moduleManager.getModule(AntiBlind.class);
        if (antiBlind.isEnabled() && Hydrogen.getClient().settingsManager.getSettingByName(antiBlind, "Pumpkin").isEnabled()) {
            ci.cancel();
        }
    }
}
