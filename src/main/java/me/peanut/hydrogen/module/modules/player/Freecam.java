package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventPacket;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0BPacketEntityAction;

/**
 * Created by peanut on 16/01/2022
 */
@Info(name = "Freecam", description = "Lets you look around freely", category = Category.Player)

public class Freecam extends Module {

    private EntityOtherPlayerMP fakePlayer = null;

    private double oldX;

    private double oldY;

    private double oldZ;

    public float sprintModifier = 1f;

    public Freecam() {
        addSetting(new Setting("Speed", this, 0.7, 0, 5, false));
        addSetting(new Setting("Packet Suspend", this, true));
    }

    public void onEnable() {
        this.oldX = mc.thePlayer.posX;
        this.oldY = mc.thePlayer.posY;
        this.oldZ = mc.thePlayer.posZ;
        WorldClient worldClient = mc.theWorld;
        this.fakePlayer = new EntityOtherPlayerMP(worldClient, mc.thePlayer.getGameProfile());
        EntityOtherPlayerMP entityOtherPlayerMP = this.fakePlayer;
        entityOtherPlayerMP.clonePlayer(mc.thePlayer, true);
        entityOtherPlayerMP = this.fakePlayer;
        entityOtherPlayerMP.copyLocationAndAnglesFrom(mc.thePlayer);
        entityOtherPlayerMP = this.fakePlayer;
        entityOtherPlayerMP.rotationYawHead = mc.thePlayer.rotationYawHead;
        mc.theWorld.addEntityToWorld(-69, this.fakePlayer);
        super.onEnable();
    }

    public void onDisable() {
        mc.thePlayer.capabilities.isCreativeMode = false;
        mc.thePlayer.noClip = false;
        mc.thePlayer.capabilities.isFlying = false;
        EntityPlayerSP entityPlayerSP = mc.thePlayer;
        double oldX = this.oldX;
        double oldY = this.oldY;
        double oldZ = this.oldZ;
        float rotationYaw = mc.thePlayer.rotationYaw;
        entityPlayerSP.setPositionAndRotation(oldX, oldY, oldZ, rotationYaw, mc.thePlayer.rotationPitch);
        mc.theWorld.removeEntityFromWorld(-69);
        this.fakePlayer = null;
        mc.renderGlobal.loadRenderers();
        super.onDisable();
    }

    @EventTarget
    public void onPacket(EventPacket e) {
        if (h2.settingsManager.getSettingByName(this, "Packet Suspend").isEnabled() && e.getPacket() instanceof C03PacketPlayer || e.getPacket() instanceof C0BPacketEntityAction)
            e.setCancelled(true);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (isEnabled()) {
            if (mc.thePlayer.isSprinting()) {
                this.sprintModifier = 1.5F;
            } else {
                this.sprintModifier = 1.0F;
            }
            mc.thePlayer.noClip = true;
            mc.thePlayer.fallDistance = 0.0F;
            mc.thePlayer.onGround = false;
            mc.thePlayer.capabilities.isFlying = false;
            mc.thePlayer.motionX = 0.0D;
            mc.thePlayer.motionY = 0.0D;
            mc.thePlayer.motionZ = 0.0D;
            float speed = (float) h2.settingsManager.getSettingByName(this, "Speed").getValue() * sprintModifier;
            mc.thePlayer.jumpMovementFactor = speed;
            if (mc.gameSettings.keyBindJump.pressed) {
                mc.thePlayer.motionY += speed;
            }
            if (mc.gameSettings.keyBindSneak.pressed) {
                mc.thePlayer.motionY -= speed;
            }
        }
    }

}
