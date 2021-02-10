package tk.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.TimeHelper;

import java.util.Random;

@Info(name = "TriggerBot", description = "Attacks when hovered over enemy", category = Category.Combat, color = -1)
public class TriggerBot extends Module {

    public static double delay;
    Random random = new Random();
    int randomD = this.random.nextInt(25);
    int randomInc = this.random.nextInt(15);
    TimeHelper time = new TimeHelper();

    public TriggerBot() {
        super(Keyboard.KEY_NONE);

        Hydrogen.getClient().settingsManager.rSetting(new Setting("CPS", this, 9, 1, 20, true));
    }


    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        switch((int) Hydrogen.getClient().settingsManager.getSettingByName(this, "CPS").getValDouble()) {
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
        try
        {
            if ((!(this.mc.currentScreen instanceof GuiContainer)) && (!(this.mc.currentScreen instanceof GuiChat)) && (!(this.mc.currentScreen instanceof GuiScreen)) && (
                    ((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword)) ||
                            ((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)) ||
                            ((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemAxe)))) {
                if ((this.mc.objectMouseOver != null) &&
                        (this.mc.objectMouseOver.entityHit != null) && ((this.mc.objectMouseOver.entityHit instanceof EntityLivingBase)) &&
                        (this.time.hasDelayRun(delay - this.randomD + this.randomInc)))
                {
                    this.time.resetAndAdd(new Random().nextInt((int) delay));

                    mc.thePlayer.swingItem();
                    this.mc.playerController.attackEntity(mc.thePlayer, this.mc.objectMouseOver.entityHit);
                }
            }
        }
        catch (Exception localException) {}
    }
}
