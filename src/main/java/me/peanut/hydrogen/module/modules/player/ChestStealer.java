package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemStack;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import me.peanut.hydrogen.utils.TimeUtils;

/**
 * Created by peanut on 08/02/2021
 */
@Info(name = "ChestStealer", description = "Steals items from chests", category = Category.Player)
public class ChestStealer extends Module {
    public ChestStealer() {
        addSetting(new Setting("Delay", this, 100.0, 0.0, 1000.0, false));
        addSetting(new Setting("Auto Close", this, false));
    }

    final TimeUtils delay = new TimeUtils();

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mc.currentScreen instanceof GuiChest && !(mc.currentScreen instanceof GuiInventory) && !(mc.currentScreen instanceof GuiContainerCreative)) {

            GuiChest chest = (GuiChest) mc.currentScreen;

            boolean empty = true;
            for (int i = 0; i < chest.lowerChestInventory.getSizeInventory(); i++) {
                ItemStack stack = chest.lowerChestInventory.getStackInSlot(i);
                if (stack != null) {
                    empty = false;
                    break;
                }
            }
            if (empty && h2.settingsManager.getSettingByName("Auto Close").isEnabled()) {
                mc.thePlayer.closeScreen();
                return;
            }
            for (int i = 0; i < chest.lowerChestInventory.getSizeInventory(); i++) {
                ItemStack stack = chest.lowerChestInventory.getStackInSlot(i);
                if (stack != null) {
                    if ((delay.isDelayComplete((long) h2.settingsManager.getSettingByName(this, "Delay").getValue() / 2))) {
                        mc.playerController.windowClick(chest.inventorySlots.windowId, Integer.valueOf(i).intValue(), 0,
                                1, mc.thePlayer);
                        delay.setLastMS();
                    }
                }
            }
        }
    }
}
