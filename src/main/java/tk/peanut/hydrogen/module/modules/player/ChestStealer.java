package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.injection.Inject;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.TimeHelper;

import java.awt.*;

/**
 * Created by peanut on 08/02/2021
 */
@Info(name = "ChestStealer", description = "Steals items from chests", category = Category.Player)
public class ChestStealer extends Module {
    public ChestStealer() {
        super(Keyboard.KEY_NONE);

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Delay", this, 100.0, 0.0, 1000.0, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Auto Close", this, false));
    }

    TimeHelper delay = new TimeHelper();

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
            if (empty && Hydrogen.getClient().settingsManager.getSettingByName("Auto Close").isEnabled()) {
                mc.thePlayer.closeScreen();
                return;
            }
            for (int i = 0; i < chest.lowerChestInventory.getSizeInventory(); i++) {
                ItemStack stack = chest.lowerChestInventory.getStackInSlot(i);
                if (stack != null) {
                    if ((delay.isDelayComplete((long) Hydrogen.getClient().settingsManager.getSettingByName(this, "Delay").getValDouble() / 2))) {
                        mc.playerController.windowClick(chest.inventorySlots.windowId, Integer.valueOf(i).intValue(), 0,
                                1, mc.thePlayer);
                        delay.setLastMS();
                    }
                }
            }
        }
    }
}
