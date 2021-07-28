package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 28/07/2021
 */
@Info(name = "NoChatRect", category = Category.Combat, description = "Removes the chat rect.")
public class ChatRect extends Module {

    public ChatRect() {
        super(0x00);
    }

}
