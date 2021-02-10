package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 10/02/2021
 */

@Info(name = "HitAnimation", description = "Changes the hitting Animation", category = Category.Render, color = -1)
public class HitAnimation extends Module {
    public HitAnimation(int keyBind) {
        super(0x00);
    }

}
