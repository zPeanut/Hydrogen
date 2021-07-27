package tk.peanut.hydrogen.module.modules.player;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;

import java.util.ArrayList;

/**
 * Created by peanut on 27/07/2021
 */
@Info(name = "AutoGG", category = Category.Player, description = "Automatically says GG after a game.")
public class AutoGG extends Module {

    public AutoGG() {
        super(0x00);

        ArrayList<String> gg = new ArrayList<>();
        gg.add("Hypixel");
        gg.add("Mineplex");
        gg.add("Cubecraft");
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Server", this, "Hypixel", gg));

    }


}
