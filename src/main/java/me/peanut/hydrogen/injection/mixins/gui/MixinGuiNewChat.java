package me.peanut.hydrogen.injection.mixins.gui;

import com.google.common.collect.Lists;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.modules.render.ChatRect;
import me.peanut.hydrogen.module.modules.render.TTFChat;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;
import java.util.List;

/**
 * Created by peanut on 28/07/2021
 */

@Mixin(GuiNewChat.class)
public abstract class MixinGuiNewChat extends MixinGui {

    @Shadow
    public abstract int getLineCount();

    @Shadow
    @Final
    private Minecraft mc;

    @Final
    @Shadow
    private final List<ChatLine> drawnChatLines = Lists.newArrayList();

    @Shadow
    public abstract boolean getChatOpen();

    @Shadow
    public abstract float getChatScale();

    @Shadow
    public abstract int getChatWidth();

    @Shadow
    private int scrollPos;

    @Shadow
    private boolean isScrolled;

    /**
     * @author
     */
    @Overwrite
    public void drawChat(int updateCounter) {
        if (this.mc.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
            int i = this.getLineCount();
            boolean flag = false;
            int j = 0;
            int k = this.drawnChatLines.size();
            float f = this.mc.gameSettings.chatOpacity * 0.9F + 0.1F;
            if (k > 0) {
                if (this.getChatOpen()) {
                    flag = true;
                }

                float f1 = this.getChatScale();
                int l = MathHelper.ceiling_float_int((float)this.getChatWidth() / f1);
                GlStateManager.pushMatrix();
                GlStateManager.translate(2.0F, 20.0F, 0.0F);
                GlStateManager.scale(f1, f1, 1.0F);

                Module ttfchat = Hydrogen.getClient().moduleManager.getModulebyName("TTFChat");
                boolean ttf = Hydrogen.getClient().settingsManager.getSettingByName(ttfchat, "Type").getMode().equalsIgnoreCase("sf ui display");

                int i1;
                int j1;
                int l1;
                for(i1 = 0; i1 + this.scrollPos < this.drawnChatLines.size() && i1 < i; ++i1) {
                    ChatLine chatline = this.drawnChatLines.get(i1 + this.scrollPos);
                    if (chatline != null) {
                        j1 = updateCounter - chatline.getUpdatedCounter();
                        if (j1 < 200 || flag) {
                            double d0 = (double)j1 / 200.0D;
                            d0 = 1.0D - d0;
                            d0 *= 10.0D;
                            d0 = MathHelper.clamp_double(d0, 0.0D, 1.0D);
                            d0 *= d0;
                            l1 = (int)(255.0D * d0);
                            if (flag) {
                                l1 = 255;
                            }

                            l1 = (int)((float)l1 * f);
                            ++j;
                            if (l1 > 3) {
                                int i2 = 0;
                                int j2 = -i1 * 9;

                                if(!Hydrogen.getClient().moduleManager.getModule(ChatRect.class).isEnabled()) {
                                    RenderUtil.rect(i2, j2 - 9, i2 + l + 4, j2, l1 / 2 << 24);
                                }
                                String s = chatline.getChatComponent().getFormattedText();
                                GlStateManager.enableBlend();


                                if(Hydrogen.getClient().moduleManager.getModule(TTFChat.class).isEnabled()) {
                                    if(ttf) {
                                        FontHelper.sf_l.drawStringWithShadow(s, (float) i2 + 1, (float) (j2 - 10), Color.WHITE);
                                    } else {
                                        FontHelper.comfortaa_l.drawStringWithShadow(s, (float) i2 + 1, (float) (j2 - 9), Color.WHITE);
                                    }
                                } else {
                                    this.mc.fontRendererObj.drawStringWithShadow(s, (float) i2, (float) (j2 - 8), 16777215 + (l1 << 24));
                                }
                                GlStateManager.disableAlpha();
                                GlStateManager.disableBlend();
                            }
                        }
                    }
                }

                if (flag) {
                    i1 = Hydrogen.getClient().moduleManager.getModule(TTFChat.class).isEnabled() ? ttf ? FontHelper.sf_l.getFont().getHeight() : FontHelper.comfortaa_l.getFont().getHeight() : this.mc.fontRendererObj.FONT_HEIGHT;
                    GlStateManager.translate(-3.0F, 0.0F, 0.0F);
                    int l2 = k * i1 + k;
                    j1 = j * i1 + j;
                    int j3 = this.scrollPos * j1 / k;
                    int k1 = j1 * j1 / l2;
                    if (l2 != j1) {
                        l1 = j3 > 0 ? 170 : 96;
                        int l3 = this.isScrolled ? 13382451 : 3355562;
                        RenderUtil.rect(0, -j3, 2, -j3 - k1, l3 + (l1 << 24));
                        RenderUtil.rect(2, -j3, 1, -j3 - k1, 13421772 + (l1 << 24));
                    }
                }

                GlStateManager.popMatrix();
            }
        }

    }

}
