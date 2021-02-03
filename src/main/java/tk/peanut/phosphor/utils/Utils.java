package tk.peanut.phosphor.utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Session;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.net.Proxy;
import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    /**
     * This function returns a random value between min and max
     * If <code>min >= max</code> the function will return min
     *
     * @param min Minimal
     * @param max Maximal
     * @return The value
     */
    public static int random(int min, int max) {
        if (max <= min) return min;

        return RANDOM.nextInt(max - min) + min;
    }

    public static final ScaledResolution getScaledRes() {
        final ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());
        return scaledRes;
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        int j;
        if (left < right) {
            j = (int) left;
            left = right;
            right = j;
        }

        if (top < bottom) {
            j = (int) top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double)left, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)top, 0.0D).endVertex();
        worldrenderer.pos((double)left, (double)top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static Session createSession(String username, String password, @NotNull Proxy proxy) throws Exception {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(proxy, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service
                .createUserAuthentication(Agent.MINECRAFT);

        auth.setUsername(username);
        auth.setPassword(password);

        auth.logIn();
        return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(),
                auth.getAuthenticatedToken(), "mojang");
    }

    /**
     * @return The direction of the player's movement in radians
     */
    public static double getDirection() {
        Minecraft mc = Minecraft.getMinecraft();

        float yaw = mc.thePlayer.rotationYaw;

        if (mc.thePlayer.moveForward < 0.0F) yaw += 180.0F;

        float forward = 1.0F;

        if (mc.thePlayer.moveForward < 0.0F) forward = -0.5F;
        else if (mc.thePlayer.moveForward > 0.0F) forward = 0.5F;

        if (mc.thePlayer.moveStrafing > 0.0F) yaw -= 90.0F * forward;

        if (mc.thePlayer.moveStrafing < 0.0F) yaw += 90.0F * forward;

        return Math.toRadians(yaw);
    }

    /*
     * By DarkStorm
     */
    public static Point calculateMouseLocation() {
        Minecraft minecraft = Minecraft.getMinecraft();
        int scale = minecraft.gameSettings.guiScale;
        if (scale == 0)
            scale = 1000;
        int scaleFactor = 0;
        while (scaleFactor < scale && minecraft.displayWidth / (scaleFactor + 1) >= 320 && minecraft.displayHeight / (scaleFactor + 1) >= 240)
            scaleFactor++;
        return new Point(Mouse.getX() / scaleFactor, minecraft.displayHeight / scaleFactor - Mouse.getY() / scaleFactor - 1);
    }
}
