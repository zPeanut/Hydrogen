package tk.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventPacket;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.util.Random;

/**
 * Created by peanut on 21/02/2021
 */
@Info(name = "Velocity", description = "Customizes your knockback chances", category = Category.Combat)
public class Velocity extends Module {

    protected Random rand = new Random();

    public Velocity() {
        super(0x00, new Color(255, 219, 171));

        addSetting(new Setting("Combat-only", this, true));
        addSetting(new Setting("Horizontal %", this, 100, 0, 100, false));
        addSetting(new Setting("Vertical %", this, 100, 0, 100, false));
        addSetting(new Setting("Chance", this, 1, 0, 1, false));

    }

    protected boolean isTargetValid(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (!entity.isEntityAlive()) {
            return false;
        }
        if (!this.mc.thePlayer.canEntityBeSeen(entity)) {
            return false;
        }
        if (this.mc.thePlayer.getDistanceToEntity(entity) > 6.0D) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
        }
        return true;
    }

    @EventTarget
    public void Receive(EventPacket event) {
        if (!isEnabled()) {
            return;
        }
        if (this.mc.thePlayer == null) {
            return;
        }
        if (this.mc.theWorld == null) {
            return;
        }
        if (Hydrogen.getClient().settingsManager.getSettingByName("Combat-only").isEnabled()) {
            boolean combat = true;
            for (Object e : this.mc.theWorld.getLoadedEntityList()) {
                Entity en = (Entity)e;
                if (en != this.mc.thePlayer && isTargetValid(en)) {
                    float yaw = Utils.getAngles(en)[1];
                    double yawdistance = Utils.getDistanceBetweenAngles(yaw, this.mc.thePlayer.rotationYaw);
                    if (yawdistance < 50.0D) {
                        combat = true;
                        break;
                    }
                }
            }
            if (!combat)
                return;
        }
        if (Hydrogen.getClient().settingsManager.getSettingByName(this, "Chance").getValDouble() >= this.rand.nextDouble() && event.getPacket() instanceof S12PacketEntityVelocity) {
            double horizontalMultiplier = Hydrogen.getClient().settingsManager.getSettingByName("Horizontal %").getValDouble() / 100.0D;
            double verticalMultiplier = Hydrogen.getClient().settingsManager.getSettingByName("Vertical %").getValDouble() / 100.0D;
            S12PacketEntityVelocity packet = (S12PacketEntityVelocity)event.getPacket();
            if (packet.getEntityID() != this.mc.thePlayer.getEntityId())
                return;
            packet.motionX = (int)(packet.motionX * horizontalMultiplier);
            packet.motionZ = (int)(packet.motionZ * horizontalMultiplier);
            packet.motionY = (int)(packet.motionY * verticalMultiplier);
        }
    }


}
