package me.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import me.peanut.hydrogen.utils.TimeUtils;

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
            if ((!(mc.currentScreen instanceof GuiContainer)) && (!(mc.currentScreen instanceof GuiChat)) && (!(mc.currentScreen instanceof GuiScreen)) && (((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword)) || ((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)) || ((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemAxe)))) {
                if ((mc.objectMouseOver != null) && (mc.objectMouseOver.entityHit != null) && ((mc.objectMouseOver.entityHit instanceof EntityLivingBase))) {
                    mc.thePlayer.swingItem();
                    mc.playerController.attackEntity(mc.thePlayer, mc.objectMouseOver.entityHit);
                }
            }
        } catch (Exception localException) {}
    }
}
