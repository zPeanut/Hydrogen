package tk.peanut.hydrogen.scripting.runtime.minecraft.client.network;

import net.minecraft.client.network.NetworkPlayerInfo;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperIChatComponent;

public class WrapperNetworkPlayerInfo {
    private NetworkPlayerInfo real;

    public WrapperNetworkPlayerInfo(NetworkPlayerInfo var1) {
        this.real = var1;
    }

    public NetworkPlayerInfo unwrap() {
        return this.real;
    }

    public int getResponseTime() {
        return this.real.getResponseTime();
    }

    public boolean hasLocationSkin() {
        return this.real.hasLocationSkin();
    }

    public String getSkinType() {
        return this.real.getSkinType();
    }

    public WrapperIChatComponent getDisplayName() {
        return new WrapperIChatComponent(this.real.getDisplayName());
    }

    public void setDisplayName(WrapperIChatComponent var1) {
        this.real.setDisplayName(var1.unwrap());
    }

    public int func_178835_l() {
        return this.real.func_178835_l();
    }

    public void func_178836_b(int var1) {
        this.real.func_178836_b(var1);
    }

    public int func_178860_m() {
        return this.real.func_178860_m();
    }

    public void func_178857_c(int var1) {
        this.real.func_178857_c(var1);
    }

    public long func_178847_n() {
        return this.real.func_178847_n();
    }

    public void func_178846_a(long var1) {
        this.real.func_178846_a(var1);
    }

    public long func_178858_o() {
        return this.real.func_178858_o();
    }

    public void func_178844_b(long var1) {
        this.real.func_178844_b(var1);
    }

    public long func_178855_p() {
        return this.real.func_178855_p();
    }

    public void func_178843_c(long var1) {
        this.real.func_178843_c(var1);
    }
}
