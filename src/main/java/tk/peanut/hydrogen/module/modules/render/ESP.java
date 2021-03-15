package tk.peanut.hydrogen.module.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by peanut on 05/02/2021
 */

@Info(name = "ESP", description = "Draws an outline on entities through walls", category = Category.Render)
public class ESP extends Module {
    public ESP() {
        super(Keyboard.KEY_NONE, colorRender);

        ArrayList<String> esp = new ArrayList<>();
        esp.add("Outline");
        esp.add("2D");
        esp.add("Box");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Type", this, "Outline", esp));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("LineWidth", this, 3, 1, 10, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Entities", this, true));
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(Hydrogen.getClient().settingsManager.getSettingByName(this, "Type").getValString().equalsIgnoreCase("outline")) {
            this.setSuffix(" §7Outline");
        } else if (Hydrogen.getClient().settingsManager.getSettingByName(this, "Type").getValString().equalsIgnoreCase("box")) {
            this.setSuffix(" §7Box");
        } else if (Hydrogen.getClient().settingsManager.getSettingByName(this, "Type").getValString().equalsIgnoreCase("2d")) {
            this.setSuffix(" §72D");
        }
    }

}
