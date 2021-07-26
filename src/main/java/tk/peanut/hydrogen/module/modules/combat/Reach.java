package tk.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import com.google.common.eventbus.Subscribe;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventMotionUpdate;
import tk.peanut.hydrogen.events.EventMouseClick;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.Utils;

/**
 * Created by peanut on 26/07/2021
 */

@Info(name = "Reach", description = "Extends your reach", category = Category.Combat)
public class Reach extends Module {

    public Reach() {
        super(0x00);
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Min Distance", this, 3.2, 3, 6, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Max Distance", this, 3.5, 3, 6, false));
    }

}
