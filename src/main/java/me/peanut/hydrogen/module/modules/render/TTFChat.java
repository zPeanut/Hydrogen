package me.peanut.hydrogen.module.modules.render;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;

/**
 * Created by peanut on 21/12/2021
 */
@Info(name = "TTFChat", description = "Changes the chat font", category = Category.Render)
public class TTFChat extends Module {

    public TTFChat() {
        ArrayList<String> mode = new ArrayList<>();
        mode.add("SF UI Display");
        mode.add("Comfortaa");
        addSetting(new Setting("Type", this, "SF UI Display", mode));
    }
}
