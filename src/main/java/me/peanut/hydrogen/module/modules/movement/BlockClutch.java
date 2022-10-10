package me.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.*;
import org.lwjgl.input.Keyboard;

/**
 * @author kambing
 * on 10/10/2022
 */
@Info(name = "BlockClutch", category = Category.Movement, description = "Block clutches you")
public class BlockClutch extends Module
{
    //TODO: add hold mode, auto switch
    public BlockClutch() {
        addSetting(new Setting("Range", this, 4, 0, 10, false));

    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        this.placeBlock((int) h2.settingsManager.getSettingByName(this, "Range").getValue(), true);
    }


    public void placeBlock(final int range, final boolean place) {
        if (this.isAirBlock(getBlock(new BlockPos(mc.thePlayer).down()))) {
            return;
        }
        if (this.placeBlockSimple(new BlockPos(mc.thePlayer).down(), place)) {
            return;
        }
        int dist = 0;
        while (dist <= range) {
            for (int blockDist = 0; dist != blockDist; ++blockDist) {
                for (int x = blockDist; x >= 0; --x) {
                    final int z = blockDist - x;
                    final int y = dist - blockDist;
                    if (this.placeBlockSimple(new BlockPos(mc.thePlayer).down(y).north(x).west(z), place)) {
                        return;
                    }
                    if (this.placeBlockSimple(new BlockPos(mc.thePlayer).down(y).north(x).west(-z), place)) {
                        return;
                    }
                    if (this.placeBlockSimple(new BlockPos(mc.thePlayer).down(y).north(-x).west(z), place)) {
                        return;
                    }
                    if (this.placeBlockSimple(new BlockPos(mc.thePlayer).down(y).north(-x).west(-z), place)) {
                        return;
                    }
                }
            }
            ++dist;
        }
    }

    public boolean isAirBlock(final Block block) {
        return !block.getMaterial().isReplaceable() || (block instanceof BlockSnow && !(block.getBlockBoundsMaxY() <= 0.125));
    }

    public boolean placeBlockSimple(final BlockPos pos, final boolean place) {
        final Minecraft mc = Minecraft.getMinecraft();
        final Entity entity = mc.getRenderViewEntity();
        final double d0 = entity.posX;
        final double d2 = entity.posY;
        final double d3 = entity.posZ;
        final Vec3 eyesPos = new Vec3(d0, d2 + mc.thePlayer.getEyeHeight(), d3);
        for (final EnumFacing side : EnumFacing.values()) {
            if (!side.equals(EnumFacing.UP)) {
                if (!side.equals(EnumFacing.DOWN) || Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
                    final BlockPos neighbor = pos.offset(side);
                    final EnumFacing side2 = side.getOpposite();
                    if (getBlock(neighbor).canCollideCheck(mc.theWorld.getBlockState(neighbor), false)) {
                        final Vec3 hitVec = new Vec3(neighbor).addVector(0.5, 0.5, 0.5).add(new Vec3(side2.getDirectionVec()));
                        if (eyesPos.squareDistanceTo(hitVec) <= 36.0) {
                            final float[] angles = this.getRotations(neighbor, side2);
                            mc.getRenderViewEntity().rotationYaw = angles[0];
                            mc.getRenderViewEntity().rotationPitch = angles[1];
                            if (place) {
                                mc.thePlayer.rotationYaw = angles[0];
                                mc.thePlayer.rotationPitch = angles[1];
                                mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getCurrentEquippedItem(), neighbor, side2, hitVec);
                                mc.thePlayer.swingItem();
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static Block getBlock(final BlockPos pos) {
        return Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock();
    }

    public float[] getRotations(final BlockPos block, final EnumFacing face) {
        final Entity entity = mc.getRenderViewEntity();
        final double posX = entity.posX;
        final double posY = entity.posY;
        final double posZ = entity.posZ;
        final double x = block.getX() + 0.5 - posX + face.getFrontOffsetX() / 2.0;
        final double z = block.getZ() + 0.5 - posZ + face.getFrontOffsetZ() / 2.0;
        final double y = block.getY() + 0.5;
        final double d1 = posY + mc.thePlayer.getEyeHeight() - y;
        final double d2 = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(Math.atan2(d1, d2) * 180.0 / 3.141592653589793);
        if (yaw < 0.0f) {
            yaw += 360.0f;
        }
        return new float[] { yaw, pitch };
    }
}
