package tk.peanut.hydrogen.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

/**
 * Created by peanut on 21/02/2021
 */
public class EventBoundingBox extends EventCancellable implements Event {
    private Block block;
    private BlockPos pos;
    private Entity entity;
    private AxisAlignedBB bounds;

    public EventBoundingBox(Entity entity, Block block, BlockPos pos, AxisAlignedBB bounds) {
        this.block = block;
        this.pos = pos;
        this.bounds = bounds;
        this.entity = entity;
    }

    public AxisAlignedBB getBounds() {
        return this.bounds;
    }

    public void setBounds(AxisAlignedBB bounds) {
        this.bounds = bounds;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public Block getBlock() {
        return this.block;
    }
}
