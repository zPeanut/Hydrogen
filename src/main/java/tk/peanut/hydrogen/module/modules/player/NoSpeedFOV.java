package tk.peanut.hydrogen.module.modules.player;

import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 10/02/2021
 */
@Info(name = "NoSpeedFOV", description = "", category = Category.Player, color = -1)
public class NoSpeedFOV extends Module {
    public NoSpeedFOV() {
        super(Keyboard.KEY_NONE);
    }
}
