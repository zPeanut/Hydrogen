package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.http.util.EntityUtils;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender3D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;

/**
 * Created by peanut on 19/12/2021
 */
@Info(name = "Tracers", description = "Draws a line to players", category = Category.Render)
public class Tracers extends Module {

    public Tracers() {

        // TODO: finish this

        super(0x00);
    }

    @EventTarget
    public void onRender3D(EventRender3D event) {
        boolean players = Hydrogen.getClient().settingsManager.getSettingByName(this, "Players").isEnabled();
        boolean entites = Hydrogen.getClient().settingsManager.getSettingByName(this, "Entites").isEnabled();
        boolean mobs = Hydrogen.getClient().settingsManager.getSettingByName(this, "Mobs").isEnabled();
        for (final Entity entity : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
            if (entity != null && entity != Minecraft.getMinecraft().thePlayer && entity instanceof EntityPlayer) {
                Utils.drawTracer(entity, new Color(255, 255, 255, 150));
            }
        }
    }
}
