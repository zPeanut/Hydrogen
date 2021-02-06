package tk.peanut.hydrogen.scripting.runtime.minecraft.client.network;

import net.minecraft.client.network.NetHandlerHandshakeMemory;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperIChatComponent;

public class WrapperNetHandlerHandshakeMemory {
    private NetHandlerHandshakeMemory real;

    public WrapperNetHandlerHandshakeMemory(NetHandlerHandshakeMemory var1) {
        this.real = var1;
    }

    public NetHandlerHandshakeMemory unwrap() {
        return this.real;
    }

    public void onDisconnect(WrapperIChatComponent var1) {
        this.real.onDisconnect(var1.unwrap());
    }
}
