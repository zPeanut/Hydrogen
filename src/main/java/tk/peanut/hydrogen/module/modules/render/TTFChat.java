package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;

/**
 * Created by peanut on 21/12/2021
 */
@Info(name = "TTFChat", description = "Changes the chat font", category = Category.Render)
public class TTFChat extends Module {

    public TTFChat() {
        super(0x00);

        ArrayList<String> mode = new ArrayList<>();
        mode.add("SF UI Display");
        mode.add("Comfortaa");
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Type", this, "SF UI Display", mode));
    }
}
