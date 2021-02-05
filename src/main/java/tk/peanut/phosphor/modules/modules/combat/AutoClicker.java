package tk.peanut.phosphor.modules.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.events.EventUpdate;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.settings.Setting;
import tk.peanut.phosphor.utils.ReflectionUtil;
import tk.peanut.phosphor.utils.TimeHelper;

import java.util.Random;

/**
 * Created by peanut on 05/02/2021
 */
public class AutoClicker extends Module {

    Random random = new Random();
    int randomD = this.random.nextInt(25);
    int randomInc = this.random.nextInt(15);
    TimeHelper time = new TimeHelper();
    int delay;

    public AutoClicker() {
        super("AutoClicker", "Automatically clicks for you", Keyboard.KEY_NONE, Category.Combat, -1);

        Phosphor.getInstance().settingsManager.rSetting(new Setting("CPS", this, 9, 1, 20, true));
    }


    @EventTarget
    public void onUpdate(EventUpdate e) {
        try
        {
            switch((int)Phosphor.getInstance().settingsManager.getSettingByName(this, "CPS").getValDouble()) {
                case 1:
                    delay = 500;
                    break;
                case 2:
                    delay = 333;
                    break;
                case 3:
                    delay = 250;
                    break;
                case 4:
                    delay = 200;
                    break;
                case 5:
                    delay = 166;
                    break;
                case 6:
                    delay = 142;
                    break;
                case 7:
                    delay = 125;
                    break;
                case 8:
                    delay = 111;
                    break;
                case 9:
                    delay = 100;
                    break;
                case 10:
                    delay = 90;
                    break;
                case 11:
                    delay = 83;
                    break;
                case 12:
                    delay = 76;
                    break;
                case 13:
                    delay = 71;
                    break;
                case 14:
                    delay = 66;
                    break;
                case 15:
                    delay = 62;
                    break;
                case 16:
                    delay = 58;
                    break;
                case 17:
                    delay = 55;
                    break;
                case 18:
                    delay = 52;
                    break;
                case 19:
                    delay = 50;
                    break;
                case 20:
                    delay = 47;
                    break;
            }
            if (ReflectionUtil.pressed.getBoolean(Minecraft.getMinecraft().gameSettings.keyBindAttack));
            {
                Random random = new Random();
                int randomD = random.nextInt(25);
                int randomInc = random.nextInt(15);
                if (this.time.hasDelayRun(delay - randomD + randomInc))
                {
                    this.time.resetAndAdd((long) delay);
                    mc.thePlayer.swingItem();
                    this.mc.playerController.attackEntity(mc.thePlayer, this.mc.objectMouseOver.entityHit);
                    this.time.reset();
                }
            }
        }
        catch (Exception localException) {}
    }
}
