package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;

/**
 * Created by peanut on 27/02/2021
 */
@Info(name = "NoHurtCam", description = "Removes the hurt camera effect", category = Category.Render)
public class NoHurtCam extends Module {
    public NoHurtCam() {
        super(0x00, colorRender);
    }
}
