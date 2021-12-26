package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.network.play.client.C16PacketClientStatus;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 11/02/2021
 */
@Info(name = "AutoRespawn", description = "Automatically respawn when dead", category = Category.Player)
public class AutoRespawn extends Module {
    public AutoRespawn() {}

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mc.thePlayer.isDead || mc.thePlayer.getHealth() <= 0) {
            mc.thePlayer.respawnPlayer();
            mc.thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
        }
    }
}
