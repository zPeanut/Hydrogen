package tk.peanut.hydrogen.ui.ingame;

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
        super(0x00);

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
                boolean ttf = Hydrogen.getClient().settingsManager.getSettingByName("Font").getValString().equalsIgnoreCase("TTF");

                if(Hydrogen.getClient().settingsManager.getSettingByName(this, "Alignment").getValString().equalsIgnoreCase("right")) {
                    if (mc.ingameGUI.getChatGUI().getChatOpen()) {

                        if(ttf) {
                            FontHelper.sf_l.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                            FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 36, Color.white);
                        } else {
                            mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 3, Utils.getScaledRes().getScaledHeight() - 26, -1);
                            mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 4, Utils.getScaledRes().getScaledHeight() - 36, -1);
                        }

                    } else {

                        if(ttf) {
                            FontHelper.sf_l.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                            FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 22, Color.white);
                        } else {
                            mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 3, Utils.getScaledRes().getScaledHeight() - 12, -1);
                            mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 4, Utils.getScaledRes().getScaledHeight() - 22,- 1);
                        }

                    }
                } else {
                    if (mc.ingameGUI.getChatGUI().getChatOpen()) {

                        if(ttf) {
                            FontHelper.sf_l.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                            FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 36, Color.white);
                        } else {
                            mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                            mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 36,-1);
                        }
                    } else {

                        if(ttf) {
                            FontHelper.sf_l.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                            FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 22, Color.white);
                        } else {
                            mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                            mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 22, -1);
                        }
                    }
                }
            }
        }
    }
}
