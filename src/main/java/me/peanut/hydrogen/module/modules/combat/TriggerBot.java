package me.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
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
    final Random random = new Random();
    int randomD = this.random.nextInt(25);
    int randomInc = this.random.nextInt(15);
    final TimeUtils time = new TimeUtils();

    public TriggerBot() {
        addSetting(new Setting("CPS", this, 9, 1, 20, true));
        addSetting(new Setting("Random MS", this, 5, 0, 250, true));
    }


    @EventTarget
    public void onUpdate(EventUpdate e)
    {
        delay = (int) Math.round(1000 / h2.settingsManager.getSettingByName(this, "CPS").getValue());
        int random = (int) (Math.random() * h2.settingsManager.getSettingByName(this, "Random MS").getValue());
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
