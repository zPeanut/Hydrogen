package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender3D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peanut on 10/02/2021
 */
@Info(name = "Trajectories", description = "Shows the trajectory a projectile will have", category = Category.Render, color = -1)
public class Trajectories extends Module {

    public Trajectories() {
        super(0x00);
    }

    @EventTarget
    private void onRender(EventRender3D event) {
        boolean bow = false;
        if (this.mc.thePlayer.getHeldItem() == null) {
            return;
        }
        if ((!(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemBow))
                && (!(mc.thePlayer.getHeldItem().getItem() instanceof ItemSnowball))
                && (!(mc.thePlayer.getHeldItem().getItem() instanceof ItemEnderPearl))
                && (!(mc.thePlayer.getHeldItem().getItem() instanceof ItemEgg))) {
            return;
        }
        bow = mc.thePlayer.getHeldItem().getItem() instanceof ItemBow;
        double posX = Minecraft.getMinecraft().getRenderManager().renderPosX - MathHelper.cos(mc.thePlayer.rotationYaw / 180.0F * 3.141593F) * 0.16F;
        double posY = Minecraft.getMinecraft().getRenderManager().renderPosY + mc.thePlayer.getEyeHeight() - 0.1000000014901161D;
        double posZ = Minecraft.getMinecraft().getRenderManager().renderPosZ - MathHelper.sin(mc.thePlayer.rotationYaw / 180.0F * 3.141593F) * 0.16F;
        double motionX = -MathHelper.sin(mc.thePlayer.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(mc.thePlayer.rotationPitch / 180.0F * 3.141593F) * (bow ? 1.0D : 0.4D);
        double motionY = -MathHelper.sin(mc.thePlayer.rotationPitch / 180.0F * 3.141593F) * (bow ? 1.0D : 0.4D);
        double motionZ = MathHelper.cos(mc.thePlayer.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(mc.thePlayer.rotationPitch / 180.0F * 3.141593F) * (bow ? 1.0D : 0.4D);
        int var6 = 72000 - this.mc.thePlayer.getItemInUseCount();
        float power = var6 / 20.0F;
        power = (power * power + power * 2.0F) / 3.0F;
        if (power < 0.1D) {
            return;
        }

        if (power > 1.0F) {
            power = 1.0F;
        }

        GL11.glColor3f(1.0F - power, power, 0);

        float distance = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
        motionX /= distance;
        motionY /= distance;
        motionZ /= distance;
        motionX *= (bow ? power * 2.0F : 1.0F) * 1.5D;
        motionY *= (bow ? power * 2.0F : 1.0F) * 1.5D;
        motionZ *= (bow ? power * 2.0F : 1.0F) * 1.5D;
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 200.0F, 0.0F);
        GL11.glDisable(2896);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth((float) Hydrogen.getClient().settingsManager.getSettingByName("Width").getValDouble());
        GL11.glBegin(3);
        boolean hasLanded = false;
        boolean isEntity = false;
        Entity hitEntity = null;
        MovingObjectPosition landingPosition = null;
        for (; (!hasLanded) && (posY > 0.0D); GL11.glVertex3d(posX - Minecraft.getMinecraft().getRenderManager().renderPosX,
                posY - Minecraft.getMinecraft().getRenderManager().renderPosY, posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ)) {
            Vec3 present = new Vec3(posX, posY, posZ);
            Vec3 future = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);
            MovingObjectPosition possibleLandingStrip = this.mc.theWorld.rayTraceBlocks(present, future, false, true,
                    false);
            if ((possibleLandingStrip != null)
                    && (possibleLandingStrip.typeOfHit != MovingObjectPosition.MovingObjectType.MISS)) {
                landingPosition = possibleLandingStrip;
                hasLanded = true;
            }

            float size = (float)(bow ? 0.3D : 0.25D);
            AxisAlignedBB arrowBox = AxisAlignedBB.fromBounds(posX - size, posY - size, posZ - size, posX + size, posY + size, posZ + size);
            List entities = getEntitiesWithinAABB(arrowBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));

            for (int index = 0; index < entities.size(); index++) {
                Entity entity = (Entity)entities.get(index);

                if ((entity.canBeCollidedWith()) && (entity != mc.thePlayer)) {
                    float var11 = 0.3F;
                    AxisAlignedBB var12 = entity.getEntityBoundingBox().expand(var11, var11, var11);
                    MovingObjectPosition possibleEntityLanding = var12.calculateIntercept(present, future);
                    if (possibleEntityLanding != null) {
                        hasLanded = true;
                        isEntity = true;
                        landingPosition = possibleEntityLanding;
                    }
                }
            }
            if (isEntity) {
                GL11.glColor3f(0.0F, 1.0F, 0.0F);
            } else {
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }



            posX += motionX;
            posY += motionY;
            posZ += motionZ;
            float motionAdjustment = 0.99F;
            motionX *= 0.9900000095367432D;
            motionY *= 0.9900000095367432D;
            motionZ *= 0.9900000095367432D;
            motionY -= (bow ? 0.05D : 0.03D);
        }
        GL11.glEnd();
        GL11.glPushMatrix();
        GL11.glTranslated(posX - Minecraft.getMinecraft().getRenderManager().renderPosX, posY - Minecraft.getMinecraft().getRenderManager().renderPosY,posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ);
        if ((landingPosition != null) && (landingPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)) {
            int landingPositionIndex = landingPosition.sideHit.getIndex();
            if (landingPositionIndex == 2) {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            } else if (landingPositionIndex == 3) {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            } else if (landingPositionIndex == 4) {
                GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            } else if (landingPositionIndex == 5) {
                GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            }
            GL11.glBegin(1);
            GL11.glVertex3d(-0.4D, 0.0D, 0.0D);
            GL11.glVertex3d(0.0D, 0.0D, 0.0D);
            GL11.glVertex3d(0.0D, 0.0D, -0.4D);
            GL11.glVertex3d(0.0D, 0.0D, 0.0D);
            GL11.glVertex3d(0.4D, 0.0D, 0.0D);
            GL11.glVertex3d(0.0D, 0.0D, 0.0D);
            GL11.glVertex3d(0.0D, 0.0D, 0.4D);
            GL11.glVertex3d(0.0D, 0.0D, 0.0D);
            GL11.glEnd();
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        }
        GL11.glPopMatrix();
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }


    private List getEntitiesWithinAABB(AxisAlignedBB axisalignedBB) {
        List list = new ArrayList();
        int chunkMinX = (int) Math.floor((axisalignedBB.minX - 2.0D) / 16.0D);
        int chunkMaxX = (int) Math.floor((axisalignedBB.maxX + 2.0D) / 16.0D);
        int chunkMinZ = (int) Math.floor((axisalignedBB.minZ - 2.0D) / 16.0D);
        int chunkMaxZ = (int) Math.floor((axisalignedBB.maxZ + 2.0D) / 16.0D);

        for (int x = chunkMinX; x <= chunkMaxX; x++) {
            for (int z = chunkMinZ; z <= chunkMaxZ; z++) {
                if (Minecraft.getMinecraft().theWorld.getChunkProvider().chunkExists(x, z)) {
                    Minecraft.getMinecraft().theWorld.getChunkFromChunkCoords(x, z).getEntitiesWithinAABBForEntity(Minecraft.getMinecraft().thePlayer, axisalignedBB, list, null);
                }
            }
        }
        return list;
    }
}
