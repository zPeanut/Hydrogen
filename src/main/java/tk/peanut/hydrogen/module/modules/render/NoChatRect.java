package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 11/02/2021
 */
@Info(name = "NoChatRect", description = "Removes the chat background", color = -1, category = Category.Render)
public class NoChatRect extends Module {

    public NoChatRect() {
        super(0x00);
    }
}
