package me.peanut.hydrogen.injection.mixins.gui;

import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.render.Animations;
import me.peanut.hydrogen.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.scoreboard.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

/**
 * Created by peanut on 20/01/2022
 */
@Mixin(GuiPlayerTabOverlay.class)
public abstract class MixinGuiPlayerTabOverlay extends MixinGui {

    @Shadow @Final private static Ordering<NetworkPlayerInfo> field_175252_a;

    @Shadow @Final private Minecraft mc;

    @Shadow public abstract String getPlayerName(NetworkPlayerInfo networkPlayerInfoIn);

    @Shadow private IChatComponent header;

    @Shadow private IChatComponent footer;

    @Shadow protected abstract void drawScoreboardValues(ScoreObjective p_175247_1_, int p_175247_2_, String p_175247_3_, int p_175247_4_, int p_175247_5_, NetworkPlayerInfo p_175247_6_);

    @Shadow protected abstract void drawPing(int p_175245_1_, int p_175245_2_, int p_175245_3_, NetworkPlayerInfo networkPlayerInfoIn);

    /**
     * @author
     */
    @Overwrite
    public void renderPlayerlist(int width, Scoreboard scoreboardIn, ScoreObjective scoreObjectiveIn) {
        boolean tabList = Hydrogen.getClient().settingsManager.getSettingByName("Tab List").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled();

        if(tabList) {
            try {
                NetHandlerPlayClient nethandlerplayclient = this.mc.thePlayer.sendQueue;
                List list = field_175252_a.sortedCopy(nethandlerplayclient.getPlayerInfoMap());
                int i = mc.thePlayer.sendQueue.currentServerMaxPlayers;
                int j = i;
                ScaledResolution scaledresolution = new ScaledResolution(mc);
                int k = 0;
                int l = scaledresolution.getScaledWidth();
                int i1 = 0;
                int j1 = 0;
                int k1 = 0;

                for (k = 1; j > 20; j = (i + k - 1) / k) {
                    ++k;
                }

                int l1 = 300 / k;

                if (l1 > 150) {
                    l1 = 150;
                }

                int i2 = (l - k * l1) / 2;
                byte b0 = 10;
                RenderUtil.rect(i2 - 1, b0 - 1, i2 + l1 * k, b0 + 9 * j, Integer.MIN_VALUE);

                for (i1 = 0; i1 < i; ++i1) {
                    j1 = i2 + i1 % k * l1;
                    k1 = b0 + i1 / k * 9;
                    RenderUtil.rect(j1, k1, j1 + l1 - 1, k1 + 8, 553648127);
                    GlStateManager.enableAlpha();

                    if (i1 < list.size()) {
                        NetworkPlayerInfo networkplayerinfo = (NetworkPlayerInfo)list.get(i1);
                        String s = networkplayerinfo.getGameProfile().getName();
                        ScorePlayerTeam scoreplayerteam = mc.theWorld.getScoreboard().getPlayersTeam(s);
                        String s1 = this.getPlayerName(networkplayerinfo);
                        mc.fontRendererObj.drawStringWithShadow(s1, j1, k1, -1);

                        if (scoreObjectiveIn != null) {
                            int j2 = j1 + mc.fontRendererObj.getStringWidth(s1) + 5;
                            int k2 = j1 + l1 - 12 - 5;

                            if (k2 - j2 > 5) {
                                Score score = scoreboardIn.getValueFromObjective(s, scoreObjectiveIn);
                                String s2 = EnumChatFormatting.YELLOW + "" + score.getScorePoints();
                                mc.fontRendererObj.drawString(s2, (k2 - mc.fontRendererObj.getStringWidth(s2)), k1, -1);
                            }
                        }
                        this.drawPing(50, j1 + l1 - 52, k1, networkplayerinfo);
                    }
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.disableLighting();
                GlStateManager.enableAlpha();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            NetHandlerPlayClient nethandlerplayclient = Minecraft.getMinecraft().thePlayer.sendQueue;
            List<NetworkPlayerInfo> list = field_175252_a.sortedCopy(nethandlerplayclient.getPlayerInfoMap());
            int i = 0;
            int j = 0;
            Iterator var8 = list.iterator();

            int j4;
            while (var8.hasNext()) {
                NetworkPlayerInfo networkplayerinfo = (NetworkPlayerInfo) var8.next();
                j4 = this.mc.fontRendererObj.getStringWidth(this.getPlayerName(networkplayerinfo));
                i = Math.max(i, j4);
                if (scoreObjectiveIn != null && scoreObjectiveIn.getRenderType() != IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
                    j4 = this.mc.fontRendererObj.getStringWidth(" " + scoreboardIn.getValueFromObjective(networkplayerinfo.getGameProfile().getName(), scoreObjectiveIn).getScorePoints());
                    j = Math.max(j, j4);
                }
            }

            list = list.subList(0, Math.min(list.size(), 80));
            int l3 = list.size();
            int i4 = l3;

            for (j4 = 1; i4 > 20; i4 = (l3 + j4 - 1) / j4) {
                ++j4;
            }

            boolean flag = this.mc.isIntegratedServerRunning() || this.mc.getNetHandler().getNetworkManager().getIsencrypted();
            int l;
            if (scoreObjectiveIn != null) {
                if (scoreObjectiveIn.getRenderType() == IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
                    l = 90;
                } else {
                    l = j;
                }
            } else {
                l = 0;
            }

            int i1 = Math.min(j4 * ((flag ? 9 : 0) + i + l + 13), width - 50) / j4;
            int j1 = width / 2 - (i1 * j4 + (j4 - 1) * 5) / 2;
            int k1 = 10;
            int l1 = i1 * j4 + (j4 - 1) * 5;
            List<String> list1 = null;
            List<String> list2 = null;
            Iterator var19;
            String s4;
            if (this.header != null) {
                list1 = this.mc.fontRendererObj.listFormattedStringToWidth(this.header.getFormattedText(), width - 50);

                for (var19 = list1.iterator(); var19.hasNext(); l1 = Math.max(l1, this.mc.fontRendererObj.getStringWidth(s4))) {
                    s4 = (String) var19.next();
                }
            }

            if (this.footer != null) {
                list2 = this.mc.fontRendererObj.listFormattedStringToWidth(this.footer.getFormattedText(), width - 50);

                for (var19 = list2.iterator(); var19.hasNext(); l1 = Math.max(l1, this.mc.fontRendererObj.getStringWidth(s4))) {
                    s4 = (String) var19.next();
                }
            }

            int j5;
            if (list1 != null) {
                RenderUtil.rect(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + list1.size() * this.mc.fontRendererObj.FONT_HEIGHT, -2147483648);

                for (var19 = list1.iterator(); var19.hasNext(); k1 += this.mc.fontRendererObj.FONT_HEIGHT) {
                    s4 = (String) var19.next();
                    j5 = this.mc.fontRendererObj.getStringWidth(s4);
                    this.mc.fontRendererObj.drawStringWithShadow(s4, (float) (width / 2 - j5 / 2), (float) k1, -1);
                }

                ++k1;
            }

            RenderUtil.rect(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + i4 * 9, -2147483648);

            for (int k4 = 0; k4 < l3; ++k4) {
                int l4 = k4 / i4;
                j5 = k4 % i4;
                int j2 = j1 + l4 * i1 + l4 * 5;
                int k2 = k1 + j5 * 9;
                RenderUtil.rect(j2, k2, j2 + i1, k2 + 8, 553648127);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.enableAlpha();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                if (k4 < list.size()) {
                    NetworkPlayerInfo networkplayerinfo1 = (NetworkPlayerInfo) list.get(k4);
                    String s1 = this.getPlayerName(networkplayerinfo1);
                    GameProfile gameprofile = networkplayerinfo1.getGameProfile();
                    if (flag) {
                        EntityPlayer entityplayer = this.mc.theWorld.getPlayerEntityByUUID(gameprofile.getId());
                        boolean flag1 = entityplayer != null && entityplayer.isWearing(EnumPlayerModelParts.CAPE) && (gameprofile.getName().equals("Dinnerbone") || gameprofile.getName().equals("Grumm"));
                        this.mc.getTextureManager().bindTexture(networkplayerinfo1.getLocationSkin());
                        int l2 = 8 + (flag1 ? 8 : 0);
                        int i3 = 8 * (flag1 ? -1 : 1);
                        Gui.drawScaledCustomSizeModalRect(j2, k2, 8.0F, (float) l2, 8, i3, 8, 8, 64.0F, 64.0F);
                        if (entityplayer != null && entityplayer.isWearing(EnumPlayerModelParts.HAT)) {
                            int j3 = 8 + (flag1 ? 8 : 0);
                            int k3 = 8 * (flag1 ? -1 : 1);
                            Gui.drawScaledCustomSizeModalRect(j2, k2, 40.0F, (float) j3, 8, k3, 8, 8, 64.0F, 64.0F);
                        }

                        j2 += 9;
                    }

                    if (networkplayerinfo1.getGameType() == WorldSettings.GameType.SPECTATOR) {
                        s1 = EnumChatFormatting.ITALIC + s1;
                        this.mc.fontRendererObj.drawStringWithShadow(s1, (float) j2, (float) k2, -1862270977);
                    } else {
                        this.mc.fontRendererObj.drawStringWithShadow(s1, (float) j2, (float) k2, -1);
                    }

                    if (scoreObjectiveIn != null && networkplayerinfo1.getGameType() != WorldSettings.GameType.SPECTATOR) {
                        int k5 = j2 + i + 1;
                        int l5 = k5 + l;
                        if (l5 - k5 > 5) {
                            this.drawScoreboardValues(scoreObjectiveIn, k2, gameprofile.getName(), k5, l5, networkplayerinfo1);
                        }
                    }

                    this.drawPing(i1, j2 - (flag ? 9 : 0), k2, networkplayerinfo1);
                }
            }

            if (list2 != null) {
                k1 = k1 + i4 * 9 + 1;
                RenderUtil.rect(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + list2.size() * this.mc.fontRendererObj.FONT_HEIGHT, -2147483648);

                for (var19 = list2.iterator(); var19.hasNext(); k1 += this.mc.fontRendererObj.FONT_HEIGHT) {
                    s4 = (String) var19.next();
                    j5 = this.mc.fontRendererObj.getStringWidth(s4);
                    this.mc.fontRendererObj.drawStringWithShadow(s4, (float) (width / 2 - j5 / 2), (float) k1, -1);
                }
            }
        }
    }
}
