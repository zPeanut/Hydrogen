package tk.peanut.hydrogen.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import tk.peanut.hydrogen.events.EventBoundingBox;

import java.util.List;

/**
 * Created by peanut on 21/02/2021
 */
@Mixin(Block.class)
public abstract class MixinBlock {

    @Shadow
    public abstract AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state);

    @Overwrite
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        AxisAlignedBB axisalignedbb = this.getCollisionBoundingBox(worldIn, pos, state);
        EventBoundingBox e = new EventBoundingBox(collidingEntity, (Block) (Object) this, pos, axisalignedbb);
        EventManager.call(e);
        axisalignedbb = e.getBounds();
        if ((!e.isCancelled()) && (axisalignedbb != null) && (mask.intersectsWith(axisalignedbb))) {
            list.add(axisalignedbb);
        }

    }


}
