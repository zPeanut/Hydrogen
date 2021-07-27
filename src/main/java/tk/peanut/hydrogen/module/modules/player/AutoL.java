package tk.peanut.hydrogen.module.modules.player;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 27/07/2021
 */

@Info(name = "AutoL", category = Category.Player, description = "Writes L in chat after someone dies.")
public class AutoL extends Module {

    public AutoL() {
        super(0x00);
    }
}
