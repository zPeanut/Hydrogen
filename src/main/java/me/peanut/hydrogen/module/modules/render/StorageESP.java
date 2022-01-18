package me.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.utils.RenderUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender3D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 11/02/2021
 */
@Info(name = "StorageESP", description = "Draws an outline on storage containers", category = Category.Render)
public class StorageESP extends Module {
    public StorageESP() {
        addSetting(new Setting("Filling", this, true));
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        if (this.isEnabled()) {
            for (final Object o : mc.theWorld.loadedTileEntityList) {
                final TileEntity tileEntity = (TileEntity)o;
                final double n = tileEntity.getPos().getX();
                final double x = n - Minecraft.getMinecraft().getRenderManager().renderPosX;
                final double n2 = tileEntity.getPos().getY();
                final double y = n2 - Minecraft.getMinecraft().getRenderManager().renderPosY;
                final double n3 = tileEntity.getPos().getZ();
                final double z = n3 - Minecraft.getMinecraft().getRenderManager().renderPosZ;
                if (tileEntity instanceof TileEntityFurnace) {
                    RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, 1717987071);
                    if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                    RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1717986848);
                }
                if (tileEntity instanceof TileEntityHopper) {
                    RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -2004317953);
                    if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                    RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -2004318176);
                }
                if (tileEntity instanceof TileEntityDropper) {
                    RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -2004317953);
                    if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                    RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -2004318176);
                }
                if (tileEntity instanceof TileEntityDispenser) {
                    RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -2004317953);
                    if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                    RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -2004318176);
                }
                if (tileEntity instanceof TileEntityEnderChest) {
                    RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, 294134527);
                    if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                    RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 294134304);
                }
                if (tileEntity instanceof TileEntityBrewingStand) {
                    RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, 288585727);
                    if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                    RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 288585504);
                }
                if (tileEntity instanceof TileEntityChest) {
                    final TileEntityChest tileChest = (TileEntityChest)tileEntity;
                    final Block chest = StorageESP.mc.theWorld.getBlockState(tileChest.getPos()).getBlock();
                    final Block border = StorageESP.mc.theWorld.getBlockState(new BlockPos(tileChest.getPos().getX(), tileChest.getPos().getY(), tileChest.getPos().getZ() - 1)).getBlock();
                    final Block border2 = StorageESP.mc.theWorld.getBlockState(new BlockPos(tileChest.getPos().getX(), tileChest.getPos().getY(), tileChest.getPos().getZ() + 1)).getBlock();
                    final Block border3 = StorageESP.mc.theWorld.getBlockState(new BlockPos(tileChest.getPos().getX() - 1, tileChest.getPos().getY(), tileChest.getPos().getZ())).getBlock();
                    final Block border4 = StorageESP.mc.theWorld.getBlockState(new BlockPos(tileChest.getPos().getX() + 1, tileChest.getPos().getY(), tileChest.getPos().getZ())).getBlock();
                    if (chest == Blocks.chest && border != Blocks.chest) {
                        if (border2 == Blocks.chest) {
                            RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 2.0), 1.5f, -2006576743);
                            if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                            RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 2.0), -2006576864);
                        }
                        else if (border4 == Blocks.chest) {
                            RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 2.0, y + 1.0, z + 1.0), 1.5f, -2006576743);
                            if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                            RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 2.0, y + 1.0, z + 1.0), -2006576864);
                        }
                        else if (border4 == Blocks.chest) {
                            RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -2006576743);
                            if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                            RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -2006576864);
                        }
                        else if (border != Blocks.chest && border2 != Blocks.chest && border3 != Blocks.chest && border4 != Blocks.chest) {
                            RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, -2006576743);
                            if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                            RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -2006576864);
                        }
                    }
                    if (chest != Blocks.trapped_chest || border == Blocks.trapped_chest) {
                        continue;
                    }
                    if (border2 == Blocks.trapped_chest) {
                        if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                        RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 2.0), 1.5f, 1997603071);
                        RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 2.0), 1997602848);
                    }
                    else if (border4 == Blocks.trapped_chest) {
                        RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 2.0, y + 1.0, z + 1.0), 1.5f, 1997603071);
                        if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                        RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 2.0, y + 1.0, z + 1.0), 1997602848);
                    }
                    else if (border4 == Blocks.trapped_chest) {
                        RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, 1997603071);
                        if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                        RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1997602848);
                    }
                    else {
                        if (border == Blocks.trapped_chest || border2 == Blocks.trapped_chest || border3 == Blocks.trapped_chest || border4 == Blocks.trapped_chest) {
                            continue;
                        }
                        RenderUtil.drawBoundingBoxESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1.5f, 1997603071);
                        if(h2.settingsManager.getSettingByName("Filling").isEnabled())
                        RenderUtil.drawFilledBBESP(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 1997602848);
                    }
                }
            }
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
