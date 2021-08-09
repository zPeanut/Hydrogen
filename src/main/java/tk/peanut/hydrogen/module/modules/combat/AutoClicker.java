package tk.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import scala.collection.parallel.ParIterableLike;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.TimeHelper;

import java.awt.*;
import java.util.Random;

/**
 * Created by peanut on 05/02/2021
 */

@Info(name = "AutoClicker", description = "Automatically left clicks", category = Category.Combat)
public class AutoClicker extends Module {

    Random random = new Random();
    int randomD = this.random.nextInt(25);
    int randomInc = this.random.nextInt(15);
    TimeHelper time = new TimeHelper();
    int delay;

    public AutoClicker() {
        super(Keyboard.KEY_NONE);

        Hydrogen.getClient().settingsManager.rSetting(new Setting("CPS", this, 9, 1, 20, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("on Click", this, false));
    }


    @EventTarget
    public void onUpdate(EventUpdate e) {
        try
        {
            delay = (int) Math.round(1000 / Hydrogen.getClient().settingsManager.getSettingByName(this, "CPS").getValDouble());

                Random random = new Random();
                int randomD = random.nextInt(25);
                int randomInc = random.nextInt(15);

                if (this.time.hasDelayRun(delay - randomD + randomInc))
                {
                    System.out.println(delay + " " +  randomD + " " + randomInc);
                    if(Hydrogen.getClient().settingsManager.getSettingByName("on Click").isEnabled()) {
                        if(Minecraft.getMinecraft().gameSettings.keyBindAttack.pressed) {
                            this.click();
                        }
                    } else {
                        this.click();
                    }
                }

        }
        catch (Exception localException) {}
    }

    private void click() {
        this.mc.clickMouse();
        this.mc.playerController.attackEntity(mc.thePlayer, this.mc.objectMouseOver.entityHit);
        this.time.reset();
    }
}
