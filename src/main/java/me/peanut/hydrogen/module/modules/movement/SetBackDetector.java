//if you have speeds in the Future well need this
//created by Vanilla/Vanilling and Eumel
package me.zpeanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;
import me.zpeanut.hydrogen.events.EventMotionUpdate;
import me.zpeanut.hydrogen.events.EventPacket;
import me.zpeanut.hydrogen.module.Category;
import me.zpeanut.hydrogen.module.Info;
import me.zpeanut.hydrogen.module.Module;
import me.zpeanut.hydrogen.utils.Utils;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.List;

@Info(name = "SetBackDetector", description = "Tells you if you got flagged", category = Category.Movement)

public class SetBackDetector extends Module {
    private List<Vec3> lastLocations = new ArrayList<>();
    private List<Long> lastSetBacks = new ArrayList<>();

    public SetBackDetector() {}

    @EventTarget
    private void onMove(EventMotionUpdate event) {
        if (event.getEventType() != EventType.POST) return;

        List<Long> remove = new ArrayList<>();

        for (Long lastSetBack : lastSetBacks) {
            if (System.currentTimeMillis() - lastSetBack > 5000) {
                remove.add(lastSetBack);
            }
        }
        for (Long aLong : remove) {
            lastSetBacks.remove(aLong);
        }

//        System.out.println(lastSetBacks);

        lastLocations.add(new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ));

        while (lastLocations.size() > 30) {
            lastLocations.remove(0);
        }
    }

    @EventTarget
    private void onPacket(EventPacket event) {
        if (event.getPacket() instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook p = (S08PacketPlayerPosLook) event.getPacket();
            boolean setback = lastLocations.stream().anyMatch(loc -> p.getX() == loc.xCoord && p.getY() == loc.yCoord && p.getZ() == loc.zCoord);

            if (setback) {
                lastSetBacks.add(System.currentTimeMillis());
                if (lastSetBacks.size() < 3)
                    Utils.sendChatMessage("You got flagged");
            }
        }
    }
}
