package tk.peanut.phosphor.scripting.runtime.minecraft.block.state;

import net.minecraft.block.state.BlockStateBase;

public class WrapperBlockStateBase {
    private BlockStateBase real;

    public WrapperBlockStateBase(BlockStateBase var1) {
        this.real = var1;
    }

    public BlockStateBase unwrap() {
        return this.real;
    }

    public String toString() {
        return this.real.toString();
    }
}
