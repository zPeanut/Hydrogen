package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.network.play.client.C16PacketClientStatus;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 11/02/2021
 */
@Info(name = "AutoRespawn", description = "Automatically respawn when dead", category = Category.Player, color = -1)
public class AutoRespawn extends Module {
    public AutoRespawn() {
        super(0x00);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mc.thePlayer.isDead || mc.thePlayer.getHealth() <= 0) {
            mc.thePlayer.respawnPlayer();
            mc.thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
        }
    }
}
