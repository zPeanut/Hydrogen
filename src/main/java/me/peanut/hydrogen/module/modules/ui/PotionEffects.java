package me.peanut.hydrogen.module.modules.ui;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventRender2D;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.awt.*;

/**
 * Created by peanut on 15/01/2022
 */
@Info(name = "PotionEffects", description = "Shows active potion effects", category = Category.Gui)
public class PotionEffects extends Module {


    @EventTarget
    public void drawPotionEffects(EventRender2D e) {
        if(Hydrogen.getClient().panic) {
            return;
        }
        int offset = 0;
        for (Object var4 : Minecraft.getMinecraft().thePlayer.getActivePotionEffects()) {
            int posY = 11 * offset;
            PotionEffect effect = (PotionEffect) var4;
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            String name = I18n.format(potion.getName());
            switch (effect.getAmplifier()) {
                case 0:
                    name += " I";
                    break;
                case 1:
                    name += " II";
                    break;
                case 2:
                    name += " III";
                    break;
                case 3:
                    name += " IV";
                    break;
                case 4:
                    name += " V";
                    break;
                case 5:
                    name += " VI";
                    break;
                case 6:
                    name += " VII";
                    break;
                case 7:
                    name += " VIII";
                    break;
                case 8:
                    name += " IX";
                    break;
                case 9:
                    name += " X";
                    break;
                default:
                    name += " X+";
                    break;
            }
            name += " §7(§f" + Potion.getDurationString(effect) + "§7)";
            int color = Integer.MIN_VALUE;
            switch (effect.getEffectName()) {
                case "potion.weither":
                    color = -16777246;
                    break;
                case "potion.weakness":
                    color = -9864951;
                    break;
                case "potion.waterBreathing":
                    color = -16758065;
                    break;
                case "potion.saturation":
                    color = -11159217;
                    break;
                case "potion.resistance":
                    color = -5655199;
                    break;
                case "potion.regeneration":
                    color = -1145130;
                    break;
                case "potion.poison":
                    color = -14553374;
                    break;
                case "potion.nightVision":
                    color = -6735204;
                    break;
                case "potion.moveSpeed":
                    color = -7875870;
                    break;
                case "potion.moveSlowdown":
                    color = -16751493;
                    break;
                case "potion.jump":
                    color = -5375161;
                    break;
                case "potion.invisibility":
                    color = -9405272;
                    break;
                case "potion.hunger":
                    color = -16754448;
                    break;
                case "potion.heal":
                    color = -65556;
                    break;
                case "potion.harm":
                    color = -3735043;
                    break;
                case "potion.fireResistance":
                    color = -29656;
                    break;
                case "potion.healthBoost":
                    color = -40151;
                    break;
                case "potion.digSpeed":
                    color = -989556;
                    break;
                case "potion.digSlowdown":
                    color = -5655199;
                    break;
                case "potion.damageBoost":
                    color = -7665712;
                    break;
                case "potion.confusion":
                    color = -5195482;
                    break;
                case "potion.blindness":
                    color = -8355712;
                    break;
                case "potion.absorption":
                    color = -23256;
                    break;
            }
            me.peanut.hydrogen.module.modules.ui.Info info = new me.peanut.hydrogen.module.modules.ui.Info();
            boolean infoIsRight = Hydrogen.getClient().settingsManager.getSettingByName("Alignment").getMode().equalsIgnoreCase("Right");
            boolean ttf = Hydrogen.getClient().settingsManager.getSettingByName("Font").getMode().equalsIgnoreCase("TTF");
            boolean infoEnabled = Hydrogen.getClient().moduleManager.getModule(me.peanut.hydrogen.module.modules.ui.Info.class).isEnabled();
            boolean chatOpen = mc.ingameGUI.getChatGUI().getChatOpen();
            if (ttf) {
                FontHelper.sf_l.drawStringWithShadow(name, Utils.getScaledRes().getScaledWidth() - FontHelper.sf_l.getStringWidth(name) - 1, Utils.getScaledRes().getScaledHeight() - posY - 12 - (infoIsRight && infoEnabled ? 22 : 0) - (chatOpen ? 14 : 0), new Color(color, true));
            } else {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, Utils.getScaledRes().getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) - 1, Utils.getScaledRes().getScaledHeight() - posY - 12 - (infoIsRight && infoEnabled ? 22 : 0) - (chatOpen ? 14 : 0), color);
            }
            ++offset;
        }
    }

}
