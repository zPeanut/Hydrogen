package tk.peanut.phosphor.scripting.runtime.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.ServerAddress;

public class WrapperServerAddress {
    private ServerAddress real;

    public WrapperServerAddress(ServerAddress var1) {
        this.real = var1;
    }

    public ServerAddress unwrap() {
        return this.real;
    }

    public String getIP() {
        return this.real.getIP();
    }

    public int getPort() {
        return this.real.getPort();
    }

}
