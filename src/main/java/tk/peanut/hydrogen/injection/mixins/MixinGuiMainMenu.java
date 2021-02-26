package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import tk.peanut.hydrogen.ui.mainmenu.utils.ExpandButton;
import tk.peanut.hydrogen.utils.Utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by peanut on 22/02/2021
 */
@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends GuiScreen {

    @Shadow
    public abstract void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_);

    @Shadow
    public abstract void addDemoButtons(int p_73972_1_, int p_73972_2_);

    @Shadow
    private DynamicTexture viewportTexture;

    @Shadow
    private ResourceLocation backgroundTexture;

    @Shadow
    private String splashText;

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

    @Shadow
    public abstract boolean func_183501_a();

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
        this.buttonList.add(new ExpandButton(1, 45, Utils.getScaledRes().getScaledHeight() / 2 - 40, 90, 20, "Singleplayer"));
        this.buttonList.add(new ExpandButton(2, 45, Utils.getScaledRes().getScaledHeight() / 2 - 18, 90, 20, "Multiplayer"));
        this.buttonList.add(new ExpandButton(0, 45, Utils.getScaledRes().getScaledHeight() / 2 + 4, 90, 20, "Settings"));
        this.buttonList.add(new ExpandButton(6, 45, Utils.getScaledRes().getScaledHeight() / 2 + 26, 90, 20, "Mods"));
        this.buttonList.add(new ExpandButton(4, 45, Utils.getScaledRes().getScaledHeight() / 2 + 70, 90, 20, "Quit"));

        this.buttonList.add(new ExpandButton(99, 45, 37, 90, 20, "Update!"));


        synchronized (this.threadLock) {
            this.field_92023_s = this.fontRendererObj.getStringWidth(this.openGLWarning1);
            this.field_92024_r = this.fontRendererObj.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.field_92023_s, this.field_92024_r);
            this.field_92022_t = (this.width - k) / 2;
            this.field_92021_u = ((GuiButton) this.buttonList.get(0)).yPosition - 24;
            this.field_92020_v = this.field_92022_t + k;
            this.field_92019_w = this.field_92021_u + 24;
        }

        this.mc.setConnectedToRealms(false);
        if (Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && !this.field_183502_L) {
            RealmsBridge realmsbridge = new RealmsBridge();
            this.field_183503_M = realmsbridge.getNotificationScreen(this);
            this.field_183502_L = true;
        }

        if (this.func_183501_a()) {
            this.field_183503_M.setGuiSize(this.width, this.height);
            this.field_183503_M.initGui();
        }

    }

    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        renderSkybox(mouseX, mouseY, partialTicks);
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        drawGradientRect(0, 0, this.width, this.height, 0, -1610612736);
        drawGradientRect(0, 0, this.width, this.height, 0x804DB3FF, 0x80000000);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.width / 2, this.height - 50, 0.0F);
        float var8 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
        GL11.glScalef(var8, var8, var8);
        GL11.glPopMatrix();

        Utils.drawMenu(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
