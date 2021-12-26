package me.peanut.hydrogen.module.modules.ui;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.FontHelper;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by peanut on 18/02/2021
 */
@me.peanut.hydrogen.module.Info(name = "Info", description = "Shows FPS and Coordinates", category = Category.Gui)
public class Info extends Module {
    public Info() {
        java.util.ArrayList<String> alignment = new ArrayList<>();
        alignment.add("Left");
        alignment.add("Right");

        java.util.ArrayList<String> c_alignment = new ArrayList<>();
        c_alignment.add("1-Line");
        c_alignment.add("3-Line");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Alignment", this, "Right", alignment));
        addSetting(new Setting("XYZ Style", this, "1-Line", c_alignment));
    }

    @EventTarget
    public void drawInfo(EventRender2D e) {
        if(Hydrogen.getClient().panic) {
            return;
        }
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
                return;
            if (Hydrogen.getClient().moduleManager.getModulebyName("Info").isEnabled() && !Hydrogen.getClient().moduleManager.getModulebyName("Hotbar").isEnabled()) {
                String x = String.valueOf((int) mc.thePlayer.posX);
                String y = String.valueOf((int) mc.thePlayer.posY);
                String z = String.valueOf((int) mc.thePlayer.posZ);
                String coordinates = String.format("XYZ §7(%s, %s, %s)", x, y, z);
                String coordinates_x = String.format("X §7%s", x);
                String coordinates_y = String.format("Y §7%s", y);
                String coordinates_z = String.format("Z §7%s", z);

                String fps = String.format("FPS §7%s", Minecraft.getDebugFPS());
                boolean ttf = Hydrogen.getClient().settingsManager.getSettingByName("Font").getValString().equalsIgnoreCase("TTF");
                boolean lines = Hydrogen.getClient().settingsManager.getSettingByName(this, "XYZ Style").getValString().equalsIgnoreCase("1-Line");

                if(Hydrogen.getClient().settingsManager.getSettingByName(this, "Alignment").getValString().equalsIgnoreCase("right")) {
                    if (mc.ingameGUI.getChatGUI().getChatOpen()) {

                        if(ttf) {
                            if(lines) {
                                FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 37, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                            } else {
                                FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 59, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_x) - 3, Utils.getScaledRes().getScaledHeight() - 48, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_y) - 3, Utils.getScaledRes().getScaledHeight() - 37, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_z) - 3, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                            }
                        } else {
                            if(lines) {
                                mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 37, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                            } else {
                                mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 59, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_x) - 3, Utils.getScaledRes().getScaledHeight() - 48, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_y) - 3, Utils.getScaledRes().getScaledHeight() - 37, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_z) - 3, Utils.getScaledRes().getScaledHeight() - 26, -1);
                            }
                        }

                    } else {

                        if(ttf) {
                            if(lines) {
                                FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                            } else {
                                FontHelper.sf_l.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 45, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_x) - 2, Utils.getScaledRes().getScaledHeight() - 34, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_y) - 2, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(coordinates_z) - 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                            }
                        } else {
                            if(lines) {
                                mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 23, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                            } else {
                                mc.fontRendererObj.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fps) - 3, Utils.getScaledRes().getScaledHeight() - 45, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_x, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_x) - 2, Utils.getScaledRes().getScaledHeight() - 34, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_y, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_y) - 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_z, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(coordinates_z) - 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                            }
                        }
                    }

                } else {

                    if (mc.ingameGUI.getChatGUI().getChatOpen()) {

                        if(ttf) {
                            if(lines) {
                                FontHelper.sf_l.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 37, Color.white);
                            } else {
                                FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 59, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 48, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 37, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                            }

                        } else {

                            if(lines) {
                                mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                                mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 37, -1);
                            } else {
                                mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 59, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 48, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 37, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 26, -1);
                            }
                        }

                    } else {

                        if(ttf) {
                            if(lines) {
                                FontHelper.sf_l.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                            } else {
                                FontHelper.sf_l.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 45, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 34, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 23, Color.white);
                                FontHelper.sf_l.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                            }

                        } else {

                            if(lines) {
                                mc.fontRendererObj.drawStringWithShadow(coordinates, 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                                mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
                            } else {
                                mc.fontRendererObj.drawStringWithShadow(fps, 2, Utils.getScaledRes().getScaledHeight() - 45, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_x, 2, Utils.getScaledRes().getScaledHeight() - 34, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_y, 2, Utils.getScaledRes().getScaledHeight() - 23, -1);
                                mc.fontRendererObj.drawStringWithShadow(coordinates_z, 2, Utils.getScaledRes().getScaledHeight() - 12, -1);
                            }
                        }
                    }
                }
            }
        }
    }
}
