package me.peanut.hydrogen.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Mouse;

import java.awt.*;

/**
 * Created by peanut on 18/01/2022
 */
public class PlayerUtil {

    private static long sneak = 0L;
    private static boolean is = false;
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static double[] entityWorldPos(Entity e) {
        float p_147936_2_ = Minecraft.getMinecraft().timer.renderPartialTicks;

        double x = (e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)p_147936_2_);
        double y = (e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)p_147936_2_);
        double z = (e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)p_147936_2_);

        return new double[] {x, y, z};
    }

    public static float getCustomEyeHeight(Entity entity)
    {
        if (is != entity.isSneaking() || sneak <= 0L) {
            sneak = System.currentTimeMillis();
        }

        is = entity.isSneaking();
        float f = 1.62F;

        if (entity.isSneaking()) {
            int i = (int)(sneak + 8L - System.currentTimeMillis());

            if (i > -50) {
                f = f + (float)((double)i * 0.0017D);

                if (f < 0.0F || f > 10.0F) {
                    f = 1.54F;
                }
            } else {
                f = (float)((double)f - 0.08D);
            }
        } else {
            int j = (int)(sneak + 8L - System.currentTimeMillis());

            if (j > -50) {
                f = f - (float)((double)j * 0.0017D);
                f = (float)((double)f - 0.08D);

                if (f < 0.0F) {
                    f = 1.62F;
                }
            } else {
                f = f - 0.0F;
            }
        }
        return f;
    }

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


    public static float[] getRotationFromPosition(double x, double z, double y) {
        double xDiff = x - Minecraft.getMinecraft().thePlayer.posX;
        double zDiff = z - Minecraft.getMinecraft().thePlayer.posZ;
        double yDiff = y - Minecraft.getMinecraft().thePlayer.posY - 1.2;

        double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        float yaw = (float) (Math.atan2(zDiff, xDiff) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float) -(Math.atan2(yDiff, dist) * 180.0D / 3.141592653589793D);
        return new float[]{yaw, pitch};
    }

    public static float[] getRotations(EntityLivingBase ent) {
        double x = ent.posX;
        double z = ent.posZ;
        double y = ent.posY + ent.getEyeHeight() / 2.0F;
        return getRotationFromPosition(x, z, y);
    }

    public static float[] getBowAngles(final Entity entity) {
        final double xDelta = (entity.posX - entity.lastTickPosX) * 0.4;
        final double zDelta = (entity.posZ - entity.lastTickPosZ) * 0.4;
        double d = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity);
        d -= d % 0.8;
        double xMulti = 1.0;
        double zMulti = 1.0;
        final boolean sprint = entity.isSprinting();
        xMulti = d / 0.8 * xDelta * (sprint ? 1.25 : 1.0);
        zMulti = d / 0.8 * zDelta * (sprint ? 1.25 : 1.0);
        final double x = entity.posX + xMulti - Minecraft.getMinecraft().thePlayer.posX;
        final double z = entity.posZ + zMulti - Minecraft.getMinecraft().thePlayer.posZ;
        final double y = Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight() - (entity.posY + entity.getEyeHeight());
        final double dist = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity);
        final float yaw = (float) Math.toDegrees(Math.atan2(z, x)) - 90.0f;
        double d1 = MathHelper.sqrt_double(x * x + z * z);
        final float pitch =  (float) - (Math.atan2(y, d1) * 180.0D / Math.PI) + (float)dist*0.11f;

        return new float[]{yaw, -pitch};
    }

    public static float[] getAngles(Entity entity) {
        double y, x = entity.posX - mc.thePlayer.posX;
        double z = entity.posZ - mc.thePlayer.posZ;

        if (entity instanceof net.minecraft.entity.monster.EntityEnderman) {
            y = entity.posY - mc.thePlayer.posY;
        } else {
            y = entity.posY + entity.getEyeHeight() - 1.9D - mc.thePlayer.posY + mc.thePlayer.getEyeHeight() - 1.9D;
        }
        double helper = MathHelper.sqrt_double(x * x + z * z);

        float newYaw = (float) Math.toDegrees(-Math.atan(x / z));
        float newPitch = (float) -Math.toDegrees(Math.atan(y / helper));
        if (z < 0.0D && x < 0.0D) {
            newYaw = (float) (90.0D + Math.toDegrees(Math.atan(z / x)));
        } else if (z < 0.0D && x > 0.0D) {
            newYaw = (float) (-90.0D + Math.toDegrees(Math.atan(z / x)));
        }
        return new float[]{newPitch, newYaw};
    }

    public static double getDistanceBetweenAngles(float angle1, float angle2) {
        float distance = Math.abs(angle1 - angle2) % 360.0F;
        if (distance > 180.0F) {
            distance = 360.0F - distance;
        }
        return distance;
    }

    public static boolean canBlockBeSeen(BlockPos pos) {
        return Minecraft.getMinecraft().theWorld.rayTraceBlocks(new Vec3(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight(), Minecraft.getMinecraft().thePlayer.posZ), new Vec3(pos.getX(), pos.getY(), pos.getZ())) == null;
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
