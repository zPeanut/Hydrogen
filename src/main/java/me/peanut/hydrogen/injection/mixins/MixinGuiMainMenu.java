package me.peanut.hydrogen.injection.mixins;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.altmanager.GuiAltManager;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.clickgui.ClickGui;
import me.peanut.hydrogen.ui.mainmenu.MainMenu;
import me.peanut.hydrogen.ui.mainmenu.utils.ExpandButton;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.ColorUtil;
import me.peanut.hydrogen.utils.RenderUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
 import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by peanut on 22/02/2021
 */
@SuppressWarnings("ALL")
@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends GuiScreen {

    @Shadow
    public abstract void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_);

    @Shadow
    protected abstract void addDemoButtons(int p_73972_1_, int p_73972_2_);

    @Shadow
    private DynamicTexture viewportTexture;

    @Shadow
    private ResourceLocation backgroundTexture;

    @Shadow
    private String splashText;

    public ClickGui clickgui;

    @Shadow
    @Final
    private final Object threadLock = new Object();

    @Shadow
    private int field_92024_r;

    @Shadow
    private int field_92023_s;

    @Shadow
    private int field_92022_t;

    @Shadow
    private int field_92021_u;

    @Shadow
    private int field_92020_v;

    @Shadow
    private int field_92019_w;

    @Shadow
    private boolean field_183502_L;

    @Shadow
    private GuiScreen field_183503_M;

    @Shadow
    private String openGLWarning1;

    @Shadow
    private String openGLWarning2;

    @Final
    @Shadow
    private static final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");

    @Shadow
    private float updateCounter;

    @Shadow
    protected abstract void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_);

    @Shadow private String openGLWarningLink;

    @Overwrite
    public void initGui() {
        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (calendar.get(2) + 1 == 12 && calendar.get(5) == 24) {
            this.splashText = "Merry X-mas!";
        } else if (calendar.get(2) + 1 == 1 && calendar.get(5) == 1) {
            this.splashText = "Happy new year!";
        } else if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
            this.splashText = "OOoooOOOoooo! Spooky!";
        }

        int j = this.height / 4 + 48;

        if(Hydrogen.getClient().moduleManager.getModulebyName("MainMenu").isEnabled()) {

            this.buttonList.add(new ExpandButton(1, 45, Utils.getScaledRes().getScaledHeight() / 2 - 70, 90, 20, "Singleplayer"));
            this.buttonList.add(new ExpandButton(2, 45, Utils.getScaledRes().getScaledHeight() / 2 - 48, 90, 20, "Multiplayer"));
            this.buttonList.add(new ExpandButton(0, 45, Utils.getScaledRes().getScaledHeight() / 2 - 26, 90, 20, "Settings"));
            this.buttonList.add(new ExpandButton(3, 45, Utils.getScaledRes().getScaledHeight() / 2 - 4, 90, 20, "Alt Manager"));
            this.buttonList.add(new ExpandButton(6, 45, Utils.getScaledRes().getScaledHeight() / 2 + 40, 43, 20, "Mods"));
            this.buttonList.add(new ExpandButton(36, 91, Utils.getScaledRes().getScaledHeight() / 2 + 40, 44, 20, "Credits"));
            this.buttonList.add(new ExpandButton(4, 45, Utils.getScaledRes().getScaledHeight() / 2 + 72, 90, 20, "Quit"));

            if(Hydrogen.getClient().isStableBuild) {
                this.buttonList.add(new ExpandButton(97, 45, Utils.getScaledRes().getScaledHeight() / 2 + 18, 90, 20, "Changelog"));
            } else {
                this.buttonList.add(new ExpandButton(96, 45, Utils.getScaledRes().getScaledHeight() / 2 + 18, 90, 20, "Current Commit"));
                this.buttonList.add(new ExpandButton(98, 144, Utils.getScaledRes().getScaledHeight() - 14, FontHelper.sf_l.getStringWidth("§7Please report any issues at our §f§n§lGitHub!") + 34, 20, "", false));
            }

            if (Hydrogen.getClient().outdated) {
                this.buttonList.add(new ExpandButton(99, 45, Utils.getScaledRes().getScaledHeight() - 24, 90, 20, "Update"));
            }

            // version button string

            this.buttonList.add(new ExpandButton(97, Utils.getScaledRes().getScaledWidth() / 2 + FontHelper.sf_l_mm.getStringWidth("hydrogen") - 49, Utils.getScaledRes().getScaledHeight() / 2 - 38, FontHelper.sf_l2.getStringWidth(Hydrogen.version) + 6, 20, "", true, Hydrogen.getClient().semantic_version));

        } else {
            if (this.mc.isDemo()) {
                this.addDemoButtons(j, 24);
            } else {
                this.addSingleplayerMultiplayerButtons(j, 24);
            }

            this.buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
            this.buttonList.add(new GuiButton(4, this.width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit")));
            this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, j + 72 + 12));
        }



        synchronized (this.threadLock) {
            this.field_92023_s = this.fontRendererObj.getStringWidth(this.openGLWarning1);
            this.field_92024_r = this.fontRendererObj.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.field_92023_s, this.field_92024_r);
            this.field_92022_t = (this.width - k) / 2;
            this.field_92021_u = (this.buttonList.get(0)).yPosition - 24;
            this.field_92020_v = this.field_92022_t + k;
            this.field_92019_w = this.field_92021_u + 24;
        }

        this.mc.setConnectedToRealms(false);
        if (Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && !this.field_183502_L) {
            RealmsBridge realmsbridge = new RealmsBridge();
            this.field_183503_M = realmsbridge.getNotificationScreen(this);
            this.field_183502_L = true;
        }

    }

    @Inject(method = "actionPerformed", at = @At("HEAD"))
    public void actionPerformedNewButtons(GuiButton button, CallbackInfo ci) throws IOException {
        if(button.id == 3) {
            this.mc.displayGuiScreen(new GuiAltManager());
        }
        if(button.id == 36) {
            try {
                URL url = new URL("https://github.com/zpeanut/hydrogen#credits");
                String link = url.toString();
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                Desktop.getDesktop().browse((new URL(link)).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(button.id == 99) {
            try {
                URL url = new URL(Hydrogen.release);
                String link = url.toString();
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                Desktop.getDesktop().browse((new URL(link)).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(button.id == 98) {
            try {
                URL url = new URL(Hydrogen.github);
                String link = url.toString();
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                Desktop.getDesktop().browse((new URL(link)).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(button.id == 97) {
            try {
                URL url;
                if(Hydrogen.getClient().isStableBuild) {
                    url = new URL(Hydrogen.tags);
                } else {
                    url = new URL(Hydrogen.currentCommitURL);
                }
                String link = url.toString();
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                Desktop.getDesktop().browse((new URL(link)).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(button.id == 96) {
            try {
                URL url = new URL(Hydrogen.currentCommitURL);
                String link = url.toString();
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                Desktop.getDesktop().browse((new URL(link)).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (Hydrogen.getClient().moduleManager.getModulebyName("MainMenu").isEnabled()) {
            Module m = Hydrogen.getClient().moduleManager.getModulebyName("MainMenu");
            renderSkybox(mouseX, mouseY, partialTicks);
            GlStateManager.disableAlpha();
            GlStateManager.enableAlpha();
            drawGradientRect(0, 0, this.width, this.height, 0, Hydrogen.getClient().settingsManager.getSettingByName(m, "Rainbow").isEnabled() ? ColorUtil.getRainbowInt(10, 0.5f, 1, 1) : -1610612736);
            drawGradientRect(0, 0, this.width, this.height, 0x804DB3FF, 0x80000000);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPushMatrix();
            GL11.glTranslatef(this.width / 2, this.height - 50, 0.0F);
            float var8 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
            GL11.glScalef(var8, var8, var8);
            GL11.glPopMatrix();
            MainMenu.drawMenu(mouseX, mouseY);
            super.drawScreen(mouseX, mouseY, partialTicks);
        } else {
            GlStateManager.disableAlpha();
            this.renderSkybox(mouseX, mouseY, partialTicks);
            GlStateManager.enableAlpha();
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            int i = 274;
            int j = this.width / 2 - i / 2;
            int k = 30;
            this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
            this.drawGradientRect(0, 0, this.width, this.height, 0, -2147483648);
            this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            if ((double)this.updateCounter < 1.0E-4D) {
                this.drawTexturedModalRect(j + 0, k + 0, 0, 0, 99, 44);
                this.drawTexturedModalRect(j + 99, k + 0, 129, 0, 27, 44);
                this.drawTexturedModalRect(j + 99 + 26, k + 0, 126, 0, 3, 44);
                this.drawTexturedModalRect(j + 99 + 26 + 3, k + 0, 99, 0, 26, 44);
                this.drawTexturedModalRect(j + 155, k + 0, 0, 45, 155, 44);
            } else {
                this.drawTexturedModalRect(j + 0, k + 0, 0, 0, 155, 44);
                this.drawTexturedModalRect(j + 155, k + 0, 0, 45, 155, 44);
            }

            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(this.width / 2 + 90), 70.0F, 0.0F);
            GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
            float f = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
            f = f * 100.0F / (float)(this.fontRendererObj.getStringWidth(this.splashText) + 32);
            GlStateManager.scale(f, f, f);
            this.drawCenteredString(this.fontRendererObj, this.splashText, 0, -8, -256);
            GlStateManager.popMatrix();
            String s = "Minecraft 1.8.9";
            if (this.mc.isDemo()) {
                s += " Demo";
            }

            List<String> brandings = Lists.reverse(FMLCommonHandler.instance().getBrandings(true));

            for(int brdline = 0; brdline < brandings.size(); ++brdline) {
                String brd = brandings.get(brdline);
                if (!Strings.isNullOrEmpty(brd)) {
                    this.drawString(this.fontRendererObj, brd, 2, this.height - (10 + brdline * (this.fontRendererObj.FONT_HEIGHT + 1)), 16777215);
                }
            }

            ForgeHooksClient.renderMainMenu((GuiMainMenu) (Object) this, this.fontRendererObj, this.width, this.height);
            String s1 = "Copyright Mojang AB. Do not distribute!";
            this.drawString(this.fontRendererObj, s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);
            if (this.openGLWarning1 != null && this.openGLWarning1.length() > 0) {
                RenderUtil.rect(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1, 1428160512);
                this.drawString(this.fontRendererObj, this.openGLWarning1, this.field_92022_t, this.field_92021_u, -1);
                this.drawString(this.fontRendererObj, this.openGLWarning2, (this.width - this.field_92024_r) / 2, (this.buttonList.get(0)).yPosition - 12, -1);
            }

            super.drawScreen(mouseX, mouseY, partialTicks);
        }
    }
}
