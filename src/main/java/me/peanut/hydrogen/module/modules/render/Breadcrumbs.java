package me.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender3D;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import me.peanut.hydrogen.utils.ColorUtil;
import me.peanut.hydrogen.utils.Utils;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by peanut on 26/12/2021
 */
@Info(name = "Breadcrumbs", category = Category.Render, description = "Leaves a rendered trail behind you")
public class Breadcrumbs extends Module {

    private final LinkedList<double[]> position;

    public Breadcrumbs() {
        this.position = new LinkedList<double[]>();
        addSetting(new Setting("Red", this, 255, 0, 255, true));
        addSetting(new Setting("Green", this, 255, 0, 255, true));
        addSetting(new Setting("Blue", this, 255, 0, 255, true));
        addSetting(new Setting("Alpha", this, 180, 0, 255, true));
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        synchronized (this.position) {
            this.position.add(new double[] { mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ });
        }
    }

    @Override
    public void onEnable() {
        if (mc.thePlayer == null) {
            return;
        }
        synchronized (this.position) {
            this.position.add(new double[] { mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY + mc.thePlayer.getEyeHeight() / 2.0f, mc.thePlayer.posZ });
            this.position.add(new double[] { mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ });
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        synchronized (this.position) {
            this.position.clear();
        }
        super.onDisable();
    }

    @EventTarget
    public void onRender(EventRender3D e) {
        int r = (int) h2.settingsManager.getSettingByName(this, "Red").getValue();
        int g = (int) h2.settingsManager.getSettingByName(this, "Green").getValue();
        int b = (int) h2.settingsManager.getSettingByName(this, "Blue").getValue();
        int a = (int) h2.settingsManager.getSettingByName(this, "Alpha").getValue();
        Color color = new Color(r, g, b, a);

        synchronized (this.position) {
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GL11.glDisable(2929);
            mc.entityRenderer.disableLightmap();
            GL11.glBegin(3);
            ColorUtil.glColor(color);
            double renderPosX = mc.getRenderManager().renderPosX;
            double renderPosY = mc.getRenderManager().renderPosY;
            double renderPosZ = mc.getRenderManager().renderPosZ;
            for (double[] pos : this.position) {
                GL11.glVertex3d(pos[0] - renderPosX, pos[1] - renderPosY, pos[2] - renderPosZ);
            }
            GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
            GL11.glEnd();
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
        }
    }

}
