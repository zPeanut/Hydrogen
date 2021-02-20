package tk.peanut.hydrogen.module.modules.player;

import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;

/**
 * Created by peanut on 10/02/2021
 */
@Info(name = "NoSpeedFOV", description = "Removes the FOV gained by speed effects", category = Category.Player)
public class NoSpeedFOV extends Module {
    public NoSpeedFOV() {
        super(Keyboard.KEY_NONE, new Color(252, 255, 199));
    }
}
