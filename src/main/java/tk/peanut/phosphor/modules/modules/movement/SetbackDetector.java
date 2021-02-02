package tk.peanut.phosphor.modules.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;
import tk.peanut.phosphor.events.EventMotionUpdate;
import tk.peanut.phosphor.events.EventPacket;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.modules.ModuleCategory;

import java.util.ArrayList;
import java.util.List;

public class SetbackDetector extends Module {
    private List<Vec3> lastLocations = new ArrayList<>();
    private List<Long> lastSetBacks = new ArrayList<>();

    public SetbackDetector() {
        super("FlagDetector", "Detects flags", ModuleCategory.MOVEMENT);
    }

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

        }
    }
}
