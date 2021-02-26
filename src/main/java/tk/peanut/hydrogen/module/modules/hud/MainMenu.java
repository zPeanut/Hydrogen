package tk.peanut.hydrogen.module.modules.hud;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;

/**
 * Created by peanut on 26/02/2021
 */
@Info(name = "MainMenu", category = Category.Gui, description = "Enables the custom main menu")
public class MainMenu extends Module {
    public MainMenu() {
        super(0x00, new Color(199, 255, 201));
    }
}
