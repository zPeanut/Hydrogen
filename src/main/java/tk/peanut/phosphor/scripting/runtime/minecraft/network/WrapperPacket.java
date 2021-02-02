package tk.peanut.phosphor.scripting.runtime.minecraft.network;

import net.minecraft.network.Packet;

public class WrapperPacket {
    private Packet real;

    public WrapperPacket(Packet var1) {
        this.real = var1;
    }

    public Packet unwrap() {
        return this.real;
    }
}
