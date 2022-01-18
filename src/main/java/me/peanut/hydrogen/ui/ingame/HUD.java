package me.peanut.hydrogen.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;
import me.peanut.hydrogen.ui.ingame.style.Style;
import me.peanut.hydrogen.ui.ingame.style.styles.Classic;
import me.peanut.hydrogen.ui.ingame.style.styles.New;

import java.util.ArrayList;

@Info(name = "HUD", description = "The overlay", category = Category.Gui)
public class HUD extends Module {

    public Style style;

    // this module basically only exists to have general settings, also to disable the hud alltogether

    public HUD() {
        ArrayList<String> style = new ArrayList<>();
        style.add("Classic");
        style.add("New");

        h2.settingsManager.rSetting(new Setting("Style", this, "New", style));
        this.style = new New();
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        this.updateStyles();
    }

    public void updateStyles() {
        switch(h2.settingsManager.getSettingByName(this, "Style").getMode()) {
            case "Classic":
                h2.hud.style = new Classic();
                break;
            case "New":
                h2.hud.style = new New();
                break;
        }
    }

}
