package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender3D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import me.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by peanut on 05/09/2021
 */
@Info(name = "MurderMystery", category = Category.Player, description = "Reveals you the murderer")

public class MurderMystery extends Module {

    public final ArrayList<Entity> entities = new ArrayList<Entity>();

    public static boolean isMurderer = false;

    public MurderMystery() {
        addSetting(new Setting("Tracers", this, true));
        addSetting(new Setting("Red", this, 255, 0, 255, true));
        addSetting(new Setting("Blue", this, 255, 0, 255, true));
        addSetting(new Setting("Green", this, 255, 0, 255, true));
        addSetting(new Setting("Alpha", this, 120, 0, 255, true));
    }

    @EventTarget
    public void onRender(EventRender3D e) {
        boolean drawTracers = Hydrogen.getClient().settingsManager.getSettingByName(this, "Tracers").isEnabled();
        int red = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Red").getValDouble();
        int blue = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Blue").getValDouble();
        int green = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Green").getValDouble();
        int alpha = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Alpha").getValDouble();

        mc.theWorld.loadedEntityList.forEach(o -> {
            Entity entity = o;
            if (!entity.isEntityAlive()) {
                entities.remove(entity);
            }

            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;

                if (player != mc.thePlayer) {
                    if (player.getCurrentEquippedItem() != null) {
                        if (player.getCurrentEquippedItem().getItem() instanceof ItemSword) {
                            if (!entities.contains(entity)) {
                                isMurderer = true;
                                Utils.sendChatMessage(entity.getName() + " is the murderer!");
                                entities.add(entity);
                            }
                        } else entities.remove(entity);
                    }
                }
            }
        });

        mc.theWorld.loadedEntityList.forEach(o -> {
            Entity entity = o;
            if ((entity.isEntityAlive() && entities.contains(entity))) {

                final double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * e.getPartialTicks() - mc.getRenderManager().renderPosX;
                final double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * e.getPartialTicks() - mc.getRenderManager().renderPosY;
                final double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * e.getPartialTicks() - mc.getRenderManager().renderPosZ;

                mc.entityRenderer.setupCameraTransform(mc.timer.renderPartialTicks, 2);
                if(drawTracers) {
                    Utils.drawTracer(entity, new Color(red, green, blue, alpha));
                }
            }
        });
    }

    private boolean hasSword(EntityPlayer e) {
        for (int i = 0; i < 8; i++) {
            if (e.getInventory()[i].getItem() instanceof ItemSword) {
                return true;
            }
        }
        return false;
    }

}
