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
import tk.peanut.hydrogen.utils.TimeUtils;

import java.util.Random;

@Info(name = "TriggerBot", description = "Attacks when hovering over an enemy", category = Category.Combat)
public class TriggerBot extends Module {

    public static double delay;
    Random random = new Random();
    int randomD = this.random.nextInt(25);
    int randomInc = this.random.nextInt(15);
    TimeUtils time = new TimeUtils();

    public TriggerBot() {
        super(Keyboard.KEY_NONE);

        Hydrogen.getClient().settingsManager.rSetting(new Setting("CPS", this, 9, 1, 20, true));
    }


    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        delay = (int) Math.round(1000 / Hydrogen.getClient().settingsManager.getSettingByName(this, "CPS").getValDouble());
        int random = (int) (Math.random() * Hydrogen.getClient().settingsManager.getSettingByName(this, "Random ms").getValDouble());
        delay += random;
        this.time.setLastMS();
        try {
            if ((!(this.mc.currentScreen instanceof GuiContainer)) && (!(this.mc.currentScreen instanceof GuiChat)) && (!(this.mc.currentScreen instanceof GuiScreen)) && (((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword)) || ((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)) || ((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemAxe)))) {
                if ((this.mc.objectMouseOver != null) && (this.mc.objectMouseOver.entityHit != null) && ((this.mc.objectMouseOver.entityHit instanceof EntityLivingBase))) {
                    mc.thePlayer.swingItem();
                    this.mc.playerController.attackEntity(mc.thePlayer, this.mc.objectMouseOver.entityHit);
                }
            }
        } catch (Exception localException) {}
    }
}
