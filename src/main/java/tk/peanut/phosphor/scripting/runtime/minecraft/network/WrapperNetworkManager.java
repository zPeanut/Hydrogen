package tk.peanut.phosphor.scripting.runtime.minecraft.network;

import net.minecraft.network.NetworkManager;
import tk.peanut.phosphor.scripting.runtime.minecraft.util.WrapperIChatComponent;

import java.net.SocketAddress;

public class WrapperNetworkManager {
    private NetworkManager real;

    public WrapperNetworkManager(NetworkManager var1) {
        this.real = var1;
    }

    public static WrapperNetworkManager provideLocalClient(SocketAddress var0) {
        return new WrapperNetworkManager(NetworkManager.provideLocalClient(var0));
    }

    public NetworkManager unwrap() {
        return this.real;
    }

    public void sendPacket(WrapperPacket var1) {
        this.real.sendPacket(var1.unwrap());
    }

    public void processReceivedPackets() {
        this.real.processReceivedPackets();
    }

    public SocketAddress getRemoteAddress() {
        return this.real.getRemoteAddress();
    }

    public void closeChannel(WrapperIChatComponent var1) {
        this.real.closeChannel(var1.unwrap());
    }

    public boolean isLocalChannel() {
        return this.real.isLocalChannel();
    }

    public boolean getIsencrypted() {
        return this.real.getIsencrypted();
    }

    public boolean isChannelOpen() {
        return this.real.isChannelOpen();
    }

    public boolean hasNoChannel() {
        return this.real.hasNoChannel();
    }

    public WrapperINetHandler getNetHandler() {
        return new WrapperINetHandler(this.real.getNetHandler());
    }

    public void setNetHandler(WrapperINetHandler var1) {
        this.real.setNetHandler(var1.unwrap());
    }

    public WrapperIChatComponent getExitMessage() {
        return new WrapperIChatComponent(this.real.getExitMessage());
    }

    public void disableAutoRead() {
        this.real.disableAutoRead();
    }

    public void setCompressionTreshold(int var1) {
        this.real.setCompressionTreshold(var1);
    }

    public void checkDisconnected() {
        this.real.checkDisconnected();
    }
}
