package tk.peanut.hydrogen.scripting.runtime.minecraft.block.state;

import net.minecraft.block.state.BlockState;
import tk.peanut.hydrogen.scripting.runtime.minecraft.block.WrapperBlock;

import java.util.Collection;

public class WrapperBlockState {
    private BlockState real;

    public WrapperBlockState(BlockState var1) {
        this.real = var1;
    }

    public BlockState unwrap() {
        return this.real;
    }

    public WrapperIBlockState getBaseState() {
        return new WrapperIBlockState(this.real.getBaseState());
    }

    public WrapperBlock getBlock() {
        return new WrapperBlock(this.real.getBlock());
    }

    public Collection getProperties() {
        return this.real.getProperties();
    }

    public String toString() {
        return this.real.toString();
    }
}
