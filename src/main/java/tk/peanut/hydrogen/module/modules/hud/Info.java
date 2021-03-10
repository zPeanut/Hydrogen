package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by peanut on 18/02/2021
 */
@tk.peanut.hydrogen.module.Info(name = "Info", description = "Shows FPS and Coordinates", category = Category.Gui)
public class Info extends Module {
    public Info() {
        super(0x00, Color.white);

        java.util.ArrayList<String> alignment = new ArrayList<>();
        alignment.add("Left");
        alignment.add("Right");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Alignment", this, "Right", alignment));
    }

    @EventTarget
    public void drawInfo(EventRender2D e) {
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
                return;
            if (Hydrogen.getClient().moduleManager.getModulebyName("Info").isEnabled() && !Hydrogen.getClient().moduleManager.getModulebyName("Hotbar").isEnabled()) {
                String x = String.valueOf((int) mc.thePlayer.posX);
                String y = String.valueOf((int) mc.thePlayer.posY);
                String z = String.valueOf((int) mc.thePlayer.posZ);
                String coordinates = String.format("XYZ §7(%s, %s, %s)", x, y, z);
                String fps = String.format("FPS §7%s", mc.getDebugFPS());

                if(Hydrogen.getClient().settingsManager.getSettingByName(this, "Alignment").getValString().equalsIgnoreCase("right")) {
                    if (!Boolean.toString(mc.ingameGUI.getChatGUI().getChatOpen()).equals("true")) {
                        FontHelper.fontnormal.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.fontnormal.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                        FontHelper.fontnormal.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.fontnormal.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 22, Color.white);
                    } else {
                        FontHelper.fontnormal.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.fontnormal.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                        FontHelper.fontnormal.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.fontnormal.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 36, Color.white);
                    }
                } else {
                    if (!Boolean.toString(mc.ingameGUI.getChatGUI().getChatOpen()).equals("true")) {
                        FontHelper.fontnormal.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                        FontHelper.fontnormal.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 22, Color.white);
                    } else {
                        FontHelper.fontnormal.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                        FontHelper.fontnormal.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 36, Color.white);
                    }
                }
            }
        }
    }
}
