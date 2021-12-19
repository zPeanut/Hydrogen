package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C16PacketClientStatus;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventPacket;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.TimeUtils;
import tk.peanut.hydrogen.utils.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by peanut on 19/12/2021
 */
@Info(name = "PingSpoof", category = Category.Player, description = "Modifies your ping to a given value")
public class PingSpoof extends Module {

    private final HashMap<Packet<?>, Long> packetsMap;

    private List<Packet> packetList = new CopyOnWriteArrayList<>();

    public PingSpoof() {
        super(0x00);

        this.packetsMap = new HashMap<Packet<?>, Long>();
        addSetting(new Setting("Max. Delay", this, 1000, 0, 5000, true));
        addSetting(new Setting("Min. Delay", this, 500, 0, 5000, true));
    }

    @EventTarget
    public void onPacket(EventPacket event) {
        int minDelay = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Min. Delay").getValDouble();
        int maxDelay = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Max. Delay").getValDouble();
        final Packet packet = event.getPacket();
        if ((packet instanceof C00PacketKeepAlive || packet instanceof C16PacketClientStatus) && !this.packetsMap.keySet().contains(packet)) {
            event.setCancelled(true);
            synchronized (this.packetsMap) {
                this.packetsMap.put((Packet<?>)packet, System.currentTimeMillis() + TimeUtils.randomDelay(minDelay, maxDelay));
            }
        }
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        try {
            synchronized (this.packetsMap) {
                final Iterator<Map.Entry<Packet<?>, Long>> iterator = this.packetsMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    final Map.Entry<Packet<?>, Long> entry = iterator.next();
                    if (entry.getValue() < System.currentTimeMillis()) {
                        PingSpoof.mc.getNetHandler().addToSendQueue((Packet)entry.getKey());
                        iterator.remove();
                    }
                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        this.packetsMap.clear();
    }

}
