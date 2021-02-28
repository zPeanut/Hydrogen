package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.BlockPos;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender3D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.modules.player.BedAura;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by peanut on 27/02/2021
 */
@Info(name = "BedESP", category = Category.Render, description = "Draws a box around beds")
public class BedESP extends Module {

    public BedESP() {
        super(0x00, colorRender);
    }

    @EventTarget
    public void onRender(EventRender3D e) {
            BlockPos blockPos = BedAura.pos;
            double x = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
            double y = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
            double z = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
            int id = Block.getIdFromBlock(this.mc.theWorld.getBlockState(blockPos).getBlock());
            if (id == 26) {
                Color c = new Color(255, 0, 0, 30);
                Utils.renderBox(x + 0.5D, y - 0.5D, z + 0.5D, 1.0F, 0.6F, c);
            }

    }
}
