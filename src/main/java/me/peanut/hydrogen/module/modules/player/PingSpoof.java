package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventPacket;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.TimeUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C16PacketClientStatus;
import me.peanut.hydrogen.settings.Setting;

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

    private final List<Packet> packetList = new CopyOnWriteArrayList<>();

    public PingSpoof() {
        this.packetsMap = new HashMap<Packet<?>, Long>();
        addSetting(new Setting("Max. Delay", this, 1000, 0, 5000, true));
        addSetting(new Setting("Min. Delay", this, 500, 0, 5000, true));
    }

    @EventTarget
    public void onPacket(EventPacket event) {
        int minDelay = (int) h2.settingsManager.getSettingByName(this, "Min. Delay").getValue();
        int maxDelay = (int) h2.settingsManager.getSettingByName(this, "Max. Delay").getValue();
        final Packet packet = event.getPacket();
        if ((packet instanceof C00PacketKeepAlive || packet instanceof C16PacketClientStatus) && !this.packetsMap.containsKey(packet)) {
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
                        PingSpoof.mc.getNetHandler().addToSendQueue(entry.getKey());
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
