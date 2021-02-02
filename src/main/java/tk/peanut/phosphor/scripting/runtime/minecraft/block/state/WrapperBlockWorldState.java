package tk.peanut.phosphor.scripting.runtime.minecraft.block.state;

import net.minecraft.block.state.BlockWorldState;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperBlockPos;

public class WrapperBlockWorldState {
    private BlockWorldState real;

    public WrapperBlockWorldState(BlockWorldState var1) {
        this.real = var1;
    }

    public BlockWorldState unwrap() {
        return this.real;
    }

    public WrapperIBlockState getBlockState() {
        return new WrapperIBlockState(this.real.getBlockState());
    }

    public WrapperBlockPos getPos() {
        return new WrapperBlockPos(this.real.getPos());
    }
}
