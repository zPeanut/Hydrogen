package me.peanut.hydrogen.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.modules.render.AntiBlind;
import me.peanut.hydrogen.ui.ingame.HUD;
import me.peanut.hydrogen.utils.BlurUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
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

@SideOnly(Side.CLIENT)
@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame extends MixinGui {

    @Shadow
    protected abstract void renderHotbarItem(int index, int xPos, int yPos, float partialTicks, EntityPlayer player);

    @Shadow
    @Final
    protected static final ResourceLocation widgetsTexPath = new ResourceLocation("textures/gui/widgets.png");


    @Inject(method = "renderTooltip", at = @At("RETURN"))
    private void renderTooltip(ScaledResolution sr, float partialTicks, CallbackInfo ci) {
        EventRender2D e = new EventRender2D();
        EventManager.call(e);
    }

    @SuppressWarnings("OverwriteAuthorRequired")
    @Overwrite
    protected void renderTooltip(ScaledResolution sr, float partialTicks) {
        if (!(Hydrogen.getClient().moduleManager.getModulebyName("Hotbar").isEnabled() && Hydrogen.getClient().moduleManager.getModule(HUD.class).isEnabled()) || Hydrogen.getClient().panic) {
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
        } else {
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

                // blur drawn behind the rect

                BlurUtil.blurAreaBorder(0, Utils.getScaledRes().getScaledHeight() - 23, Utils.getScaledRes().getScaledWidth() - 7, Utils.getScaledRes().getScaledHeight(), 2, 0, 1);

                // actual hotbar rect

                RenderUtil.rect(0, Utils.getScaledRes().getScaledHeight() - 23, Utils.getScaledRes().getScaledWidth() - 7, Utils.getScaledRes().getScaledHeight(), 0x77000000);

                // right side white rect

                RenderUtil.rect(Utils.getScaledRes().getScaledWidth() - 7, Utils.getScaledRes().getScaledHeight() - 23, Utils.getScaledRes().getScaledWidth(), Utils.getScaledRes().getScaledHeight(), Integer.MAX_VALUE);

                // selected hotbar item

                RenderUtil.rect(Utils.slide, Utils.getScaledRes().getScaledHeight() - 23, Utils.slide + 22, Utils.getScaledRes().getScaledHeight(), Integer.MAX_VALUE);

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
