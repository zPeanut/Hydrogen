package me.peanut.hydrogen.injection.mixins.entity;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.types.EventType;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventMotionUpdate;
import me.peanut.hydrogen.events.EventPreMotion;
import me.peanut.hydrogen.events.EventSafeWalk;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.injection.mixins.gui.MixinAbstractClientPlayer;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.modules.player.NoSwing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EntityPlayerSP.class)
@SideOnly(Side.CLIENT)
public abstract class MixinEntityPlayerSP extends MixinAbstractClientPlayer {
    private double cachedX;
    private double cachedY;
    private double cachedZ;

    private float cachedRotationPitch;
    private float cachedRotationYaw;

    @Shadow
    private boolean serverSprintState;

    @Shadow
    public abstract void playSound(String name, float volume, float pitch);

    @Shadow
    public int sprintingTicksLeft;

    @Shadow
    protected int sprintToggleTimer;

    @Shadow
    public float timeInPortal;

    @Shadow
    public float prevTimeInPortal;

    @Shadow
    protected Minecraft mc;

    @Shadow
    public MovementInput movementInput;

    @Shadow
    public abstract void setSprinting(boolean sprinting);

    @Shadow
    public abstract void sendPlayerAbilities();

    @Shadow
    private float horseJumpPower;

    @Shadow
    private int horseJumpPowerCounter;

    @Shadow
    protected abstract void sendHorseJump();

    @Shadow
    public abstract boolean isRidingHorse();

    @Shadow
    @Final
    public NetHandlerPlayClient sendQueue;

    @Shadow
    private boolean serverSneakState;

    @Shadow
    public abstract boolean isSneaking();

    @Shadow
    protected abstract boolean isCurrentViewEntity();

    @Shadow
    private double lastReportedPosX;

    @Shadow
    private int positionUpdateTicks;

    @Shadow
    private double lastReportedPosY;

    @Shadow
    private double lastReportedPosZ;

    @Shadow
    private float lastReportedYaw;

    @Shadow
    private float lastReportedPitch;

    @Shadow
    protected abstract boolean pushOutOfBlocks(double x, double y, double z);

    @SuppressWarnings("OverwriteAuthorRequired")
    @Overwrite
    public void sendChatMessage(String message) {
        if(!Hydrogen.getClient().panic) {
            if (Hydrogen.getClient().commandManager.execute(message)) {
                return;
            }
            if (message.startsWith(".")) {
                Hydrogen.getClient().commandManager.execute(message.replace(".", " "));
                return;
            }
        }
        this.sendQueue.addToSendQueue(new C01PacketChatMessage(message));
    }

    @Inject(method = "swingItem", at = @At("HEAD"), cancellable = true)
    private void swingItem(CallbackInfo callbackInfo) {
        Module noSwing = Hydrogen.getClient().moduleManager.getModule(NoSwing.class);
        if (noSwing.isEnabled()) {
            callbackInfo.cancel();
            if(!Hydrogen.getClient().settingsManager.getSettingByName(noSwing, "Server-side").isEnabled()) {
                this.sendQueue.addToSendQueue(new C0APacketAnimation());
            }
        }
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"))
    private void onUpdateWalkingPlayerPre(CallbackInfo ci) {
        if(!Hydrogen.getClient().panic) {
            EventPreMotion e = new EventPreMotion();
            EventManager.call(e);


            cachedX = posX;
            cachedY = posY;
            cachedZ = posZ;

            cachedRotationYaw = rotationYaw;
            cachedRotationPitch = rotationPitch;
        }

        if(!Hydrogen.getClient().panic) {
            EventMotionUpdate event = new EventMotionUpdate(EventType.PRE, posX, posY, posZ, rotationYaw, rotationPitch, onGround);
            EventManager.call(event);


            posX = event.getX();
            posY = event.getY();
            posZ = event.getZ();

            rotationYaw = event.getYaw();
            rotationPitch = event.getPitch();
        }
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onUpdate(CallbackInfo ci) {
        if(!Hydrogen.getClient().panic) {
            EventUpdate event = new EventUpdate();
            EventManager.call(event);
        }
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("RETURN"))
    private void onUpdateWalkingPlayerPost(CallbackInfo ci) {
        if(!Hydrogen.getClient().panic) {
            posX = cachedX;
            posY = cachedY;
            posZ = cachedZ;

            rotationYaw = cachedRotationYaw;
            rotationPitch = cachedRotationPitch;

            EventManager.call(new EventMotionUpdate(EventType.POST, posX, posY, posZ, rotationYaw, rotationPitch, onGround));
        }
    }

    @Override
    public void moveEntity(double x, double y, double z)
    {
        if (this.noClip)
        {
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(x, y, z));
            this.posX = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
            this.posY = this.getEntityBoundingBox().minY;
            this.posZ = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
        }
        else
        {
            this.worldObj.theProfiler.startSection("move");
            double d0 = this.posX;
            double d1 = this.posY;
            double d2 = this.posZ;

            if (this.isInWeb)
            {
                this.isInWeb = false;
                x *= 0.25D;
                y *= 0.05000000074505806D;
                z *= 0.25D;
                this.motionX = 0.0D;
                this.motionY = 0.0D;
                this.motionZ = 0.0D;
            }

            double d3 = x;
            double d4 = y;
            double d5 = z;
            boolean flag = this.onGround && this.isSneaking() && (Entity) (Object) this instanceof EntityPlayer;

            if(!Hydrogen.getClient().panic) {
                EventSafeWalk e = new EventSafeWalk();
                EventManager.call(e);

                if (e.isCancelled()) {
                    flag = true;
                }
            }

            if (flag)
            {
                double d6;

                for (d6 = 0.05D; x != 0.0D && this.worldObj.getCollidingBoundingBoxes((Entity) (Object) this, this.getEntityBoundingBox().offset(x, -1.0D, 0.0D)).isEmpty(); d3 = x)
                {
                    if (x < d6 && x >= -d6)
                    {
                        x = 0.0D;
                    }
                    else if (x > 0.0D)
                    {
                        x -= d6;
                    }
                    else
                    {
                        x += d6;
                    }
                }

                for (; z != 0.0D && this.worldObj.getCollidingBoundingBoxes((Entity) (Object) this, this.getEntityBoundingBox().offset(0.0D, -1.0D, z)).isEmpty(); d5 = z)
                {
                    if (z < d6 && z >= -d6)
                    {
                        z = 0.0D;
                    }
                    else if (z > 0.0D)
                    {
                        z -= d6;
                    }
                    else
                    {
                        z += d6;
                    }
                }

                for (; x != 0.0D && z != 0.0D && this.worldObj.getCollidingBoundingBoxes((Entity) (Object) this, this.getEntityBoundingBox().offset(x, -1.0D, z)).isEmpty(); d5 = z)
                {
                    if (x < d6 && x >= -d6)
                    {
                        x = 0.0D;
                    }
                    else if (x > 0.0D)
                    {
                        x -= d6;
                    }
                    else
                    {
                        x += d6;
                    }

                    d3 = x;

                    if (z < d6 && z >= -d6)
                    {
                        z = 0.0D;
                    }
                    else if (z > 0.0D)
                    {
                        z -= d6;
                    }
                    else
                    {
                        z += d6;
                    }
                }
            }

            List<AxisAlignedBB> list1 = this.worldObj.getCollidingBoundingBoxes((Entity) (Object) this, this.getEntityBoundingBox().addCoord(x, y, z));
            AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();

            for (AxisAlignedBB axisalignedbb1 : list1)
            {
                y = axisalignedbb1.calculateYOffset(this.getEntityBoundingBox(), y);
            }

            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, y, 0.0D));
            boolean flag1 = this.onGround || d4 != y && d4 < 0.0D;

            for (AxisAlignedBB axisalignedbb2 : list1)
            {
                x = axisalignedbb2.calculateXOffset(this.getEntityBoundingBox(), x);
            }

            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(x, 0.0D, 0.0D));

            for (AxisAlignedBB axisalignedbb13 : list1)
            {
                z = axisalignedbb13.calculateZOffset(this.getEntityBoundingBox(), z);
            }

            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, 0.0D, z));

            if (this.stepHeight > 0.0F && flag1 && (d3 != x || d5 != z))
            {
                double d11 = x;
                double d7 = y;
                double d8 = z;
                AxisAlignedBB axisalignedbb3 = this.getEntityBoundingBox();
                this.setEntityBoundingBox(axisalignedbb);
                y = this.stepHeight;
                List<AxisAlignedBB> list = this.worldObj.getCollidingBoundingBoxes((Entity) (Object) this, this.getEntityBoundingBox().addCoord(d3, y, d5));
                AxisAlignedBB axisalignedbb4 = this.getEntityBoundingBox();
                AxisAlignedBB axisalignedbb5 = axisalignedbb4.addCoord(d3, 0.0D, d5);
                double d9 = y;

                for (AxisAlignedBB axisalignedbb6 : list)
                {
                    d9 = axisalignedbb6.calculateYOffset(axisalignedbb5, d9);
                }

                axisalignedbb4 = axisalignedbb4.offset(0.0D, d9, 0.0D);
                double d15 = d3;

                for (AxisAlignedBB axisalignedbb7 : list)
                {
                    d15 = axisalignedbb7.calculateXOffset(axisalignedbb4, d15);
                }

                axisalignedbb4 = axisalignedbb4.offset(d15, 0.0D, 0.0D);
                double d16 = d5;

                for (AxisAlignedBB axisalignedbb8 : list)
                {
                    d16 = axisalignedbb8.calculateZOffset(axisalignedbb4, d16);
                }

                axisalignedbb4 = axisalignedbb4.offset(0.0D, 0.0D, d16);
                AxisAlignedBB axisalignedbb14 = this.getEntityBoundingBox();
                double d17 = y;

                for (AxisAlignedBB axisalignedbb9 : list)
                {
                    d17 = axisalignedbb9.calculateYOffset(axisalignedbb14, d17);
                }

                axisalignedbb14 = axisalignedbb14.offset(0.0D, d17, 0.0D);
                double d18 = d3;

                for (AxisAlignedBB axisalignedbb10 : list)
                {
                    d18 = axisalignedbb10.calculateXOffset(axisalignedbb14, d18);
                }

                axisalignedbb14 = axisalignedbb14.offset(d18, 0.0D, 0.0D);
                double d19 = d5;

                for (AxisAlignedBB axisalignedbb11 : list)
                {
                    d19 = axisalignedbb11.calculateZOffset(axisalignedbb14, d19);
                }

                axisalignedbb14 = axisalignedbb14.offset(0.0D, 0.0D, d19);
                double d20 = d15 * d15 + d16 * d16;
                double d10 = d18 * d18 + d19 * d19;

                if (d20 > d10)
                {
                    x = d15;
                    z = d16;
                    y = -d9;
                    this.setEntityBoundingBox(axisalignedbb4);
                }
                else
                {
                    x = d18;
                    z = d19;
                    y = -d17;
                    this.setEntityBoundingBox(axisalignedbb14);
                }

                for (AxisAlignedBB axisalignedbb12 : list)
                {
                    y = axisalignedbb12.calculateYOffset(this.getEntityBoundingBox(), y);
                }

                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, y, 0.0D));

                if (d11 * d11 + d8 * d8 >= x * x + z * z)
                {
                    x = d11;
                    y = d7;
                    z = d8;
                    this.setEntityBoundingBox(axisalignedbb3);
                }
            }

            this.worldObj.theProfiler.endSection();
            this.worldObj.theProfiler.startSection("rest");
            this.posX = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
            this.posY = this.getEntityBoundingBox().minY;
            this.posZ = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
            this.isCollidedHorizontally = d3 != x || d5 != z;
            this.isCollidedVertically = d4 != y;
            this.onGround = this.isCollidedVertically && d4 < 0.0D;
            this.isCollided = this.isCollidedHorizontally || this.isCollidedVertically;
            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY - 0.20000000298023224D);
            int k = MathHelper.floor_double(this.posZ);
            BlockPos blockpos = new BlockPos(i, j, k);
            Block block1 = this.worldObj.getBlockState(blockpos).getBlock();

            if (block1.getMaterial() == Material.air)
            {
                Block block = this.worldObj.getBlockState(blockpos.down()).getBlock();

                if (block instanceof BlockFence || block instanceof BlockWall || block instanceof BlockFenceGate)
                {
                    block1 = block;
                    blockpos = blockpos.down();
                }
            }

            this.updateFallState(y, this.onGround, block1, blockpos);

            if (d3 != x)
            {
                this.motionX = 0.0D;
            }

            if (d5 != z)
            {
                this.motionZ = 0.0D;
            }

            if (d4 != y)
            {
                block1.onLanded(this.worldObj, (Entity) (Object) this);
            }

            if (this.canTriggerWalking() && !flag && this.ridingEntity == null)
            {
                double d12 = this.posX - d0;
                double d13 = this.posY - d1;
                double d14 = this.posZ - d2;

                if (block1 != Blocks.ladder)
                {
                    d13 = 0.0D;
                }

                if (block1 != null && this.onGround)
                {
                    block1.onEntityCollidedWithBlock(this.worldObj, blockpos, (Entity) (Object) this);
                }

                this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)MathHelper.sqrt_double(d12 * d12 + d14 * d14) * 0.6D);
                this.distanceWalkedOnStepModified = (float)((double)this.distanceWalkedOnStepModified + (double)MathHelper.sqrt_double(d12 * d12 + d13 * d13 + d14 * d14) * 0.6D);

                if (this.distanceWalkedOnStepModified > (float)this.getNextStepDistance() && block1.getMaterial() != Material.air)
                {
                    this.setNextStepDistance((int)this.distanceWalkedOnStepModified + 1);

                    if (this.isInWater())
                    {
                        float f = MathHelper.sqrt_double(this.motionX * this.motionX * 0.20000000298023224D + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224D) * 0.35F;

                        if (f > 1.0F)
                        {
                            f = 1.0F;
                        }

                        this.playSound(this.getSwimSound(), f, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                    }

                    this.playStepSound(blockpos, block1);
                }
            }

            try
            {
                this.doBlockCollisions();
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Checking entity block collision");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Entity being checked for collision");
                this.addEntityCrashInfo(crashreportcategory);
                throw new ReportedException(crashreport);
            }

            boolean flag2 = this.isWet();

            if (this.worldObj.isFlammableWithin(this.getEntityBoundingBox().contract(0.001D, 0.001D, 0.001D)))
            {
                this.dealFireDamage(1);

                if (!flag2)
                {
                    this.setFire(getFire() + 1);

                    if (this.getFire() == 0)
                    {
                        this.setFire(8);
                    }
                }
            }
            else if (this.getFire() <= 0)
            {
                this.setFire(-this.fireResistance);
            }

            if (flag2 && this.getFire() > 0)
            {
                this.playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                this.setFire(-this.fireResistance);
            }

            this.worldObj.theProfiler.endSection();
        }
    }
}