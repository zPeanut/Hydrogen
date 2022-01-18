package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventText;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by peanut on 20/12/2021
 */
@Info(name = "NameProtect", category = Category.Player, description = "Changes your name clientside")
public class NameProtect extends Module {

    public NameProtect() {
        addSetting(new Setting("Your Name", this, "§cMe"));
        addSetting(new Setting("Other Names", this, "User"));
        addSetting(new Setting("All Players", this, false));
        addSetting(new Setting("SkinProtect", this, true));
    }

    @EventTarget
    public void onText(EventText event) {
        if (NameProtect.mc.thePlayer == null || event.getText().contains(Hydrogen.prefix)) {
            return;
        }
        if (!this.isEnabled()) {
            return;
        }
        event.setText(StringUtils.replace(event.getText(), mc.thePlayer.getName(), h2.settingsManager.getSettingByName(this, "Your Name").getText()));
        if (h2.settingsManager.getSettingByName(this, "All Players").isEnabled()) {
            for (NetworkPlayerInfo playerInfo : mc.getNetHandler().getPlayerInfoMap()) {
                event.setText(StringUtils.replace(event.getText(), playerInfo.getGameProfile().getName(), h2.settingsManager.getSettingByName(this, "Other Names").getText()));
            }
        }
    }
}
