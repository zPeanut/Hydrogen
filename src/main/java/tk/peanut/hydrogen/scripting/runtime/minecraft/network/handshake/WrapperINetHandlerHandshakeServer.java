package tk.peanut.hydrogen.scripting.runtime.minecraft.network.handshake;

import net.minecraft.network.handshake.INetHandlerHandshakeServer;

public class WrapperINetHandlerHandshakeServer {
    private INetHandlerHandshakeServer real;

    public WrapperINetHandlerHandshakeServer(INetHandlerHandshakeServer var1) {
        this.real = var1;
    }

    public INetHandlerHandshakeServer unwrap() {
        return this.real;
    }
}
