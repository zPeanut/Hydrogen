package tk.peanut.hydrogen.scripting.runtime.minecraft.block.state;

import net.minecraft.block.state.BlockPistonStructureHelper;

import java.util.List;

public class WrapperBlockPistonStructureHelper {
    private BlockPistonStructureHelper real;

    public WrapperBlockPistonStructureHelper(BlockPistonStructureHelper var1) {
        this.real = var1;
    }

    public BlockPistonStructureHelper unwrap() {
        return this.real;
    }

    public boolean canMove() {
        return this.real.canMove();
    }

    public List getBlocksToMove() {
        return this.real.getBlocksToMove();
    }

    public List getBlocksToDestroy() {
        return this.real.getBlocksToDestroy();
    }
}
