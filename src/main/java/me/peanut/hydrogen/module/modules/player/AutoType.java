package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 12/01/2022
 */
@Info(name = "AutoType", description = "Executes a command or types something", category = Category.Player)
public class AutoType extends Module {

    public AutoType() {
        addSetting(new Setting("Text", this, "/spawn"));
        addSetting(new Setting("Health", this, false));
        addSetting(new Setting("Health Value", this, 4, 0, 20, false));
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        boolean lowHealth = Hydrogen.getClient().settingsManager.getSettingByName(this, "Health").isEnabled();
        String text = Hydrogen.getClient().settingsManager.getSettingByName(this, "Text").getText();
        float health_value = (float) Hydrogen.getClient().settingsManager.getSettingByName(this, "Health Value").getValue();
        if(lowHealth) {
            if(mc.thePlayer.getHealth() < health_value) {
                mc.thePlayer.sendChatMessage(text);
            }
        }
    }

    @Override
    public void onEnable() {
        boolean lowHealth = Hydrogen.getClient().settingsManager.getSettingByName(this, "Health").isEnabled();
        String text = Hydrogen.getClient().settingsManager.getSettingByName(this, "Text").getText();
        if(!lowHealth) {
            mc.thePlayer.sendChatMessage(text);
            toggle();
        }
    }




}
