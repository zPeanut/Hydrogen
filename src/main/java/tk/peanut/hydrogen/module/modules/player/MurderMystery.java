package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender3D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.modules.render.ESP;
import tk.peanut.hydrogen.module.modules.render.Tracers;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by peanut on 05/09/2021
 */
@Info(name = "MurderMystery", category = Category.Player, description = "Reveals you the murderer")

// TODO: finally finish this
public class MurderMystery extends Module {

    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public static boolean isMurderer = false;

    public MurderMystery() {
        super(0x00);

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
            Entity entity = (Entity)o;
            if (!entity.isEntityAlive() && entities.contains(entity)) {
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
                        } else if (entities.contains(entity)) {
                            entities.remove(entity);
                        }
                    }
                }
            }
        });

        mc.theWorld.loadedEntityList.forEach(o -> {
            Entity entity = (Entity)o;
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
