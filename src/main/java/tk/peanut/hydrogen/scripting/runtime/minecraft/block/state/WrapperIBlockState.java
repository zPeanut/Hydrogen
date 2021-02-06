package tk.peanut.hydrogen.scripting.runtime.minecraft.block.state;

import net.minecraft.block.state.IBlockState;

public class WrapperIBlockState {
    private IBlockState real;

    public WrapperIBlockState(IBlockState var1) {
        this.real = var1;
    }

    public IBlockState unwrap() {
        return this.real;
    }
}
