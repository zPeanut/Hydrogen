package tk.peanut.hydrogen.module.modules.render;

import net.minecraft.util.BlockPos;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.util.ArrayList;

/**
 * Created by peanut on 27/02/2021
 */
@Info(name = "BedESP", category = Category.Render, description = "Draws a box around beds")
public class BedESP extends Module {

    private static ArrayList<Integer> blockIds = new ArrayList();
    private ArrayList<BlockPos> toRender = new ArrayList();

    public BedESP() {
        super(0x00, colorRender);
    }
}
