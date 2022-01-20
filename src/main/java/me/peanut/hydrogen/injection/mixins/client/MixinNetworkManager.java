package me.peanut.hydrogen.injection.mixins.client;

import com.darkmagician6.eventapi.EventManager;
import io.netty.channel.ChannelHandlerContext;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    @Inject(method = "channelRead0*", at = @At("HEAD"), cancellable = true)
    private void read(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callback) {
        if(!Hydrogen.getClient().panic) {
            EventPacket event = new EventPacket(packet);
            EventManager.call(event);
            if (event.isCancelled()) {
                callback.cancel();
            }
        }
    }

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void send(final Packet<?> packet, final CallbackInfo callback) {
        if(!Hydrogen.getClient().panic) {
            EventPacket event = new EventPacket(packet);
            EventManager.call(event);
            if (event.isCancelled()) {
                callback.cancel();
            }
        }
    }
}
