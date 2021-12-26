package me.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.utils.ReflectionUtil;

@Info(name = "FastBridge", description = "Automatically fastbridges for you", category = Category.Movement, keybind = Keyboard.KEY_Y)
public class Eagle extends Module {

    public Eagle() {}

    @EventTarget
    public void onUpdate(EventUpdate e) {

        try {
            if (((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock)) && (!mc.gameSettings.keyBindJump.isPressed())) {
                BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0D, mc.thePlayer.posZ);
                if (mc.theWorld.getBlockState(bp).getBlock() == Blocks.air) {
                    ReflectionUtil.pressed.set(Minecraft.getMinecraft().gameSettings.keyBindSneak, true);
                } else {
                    ReflectionUtil.pressed.set(Minecraft.getMinecraft().gameSettings.keyBindSneak, false);
                }
            }
        } catch (Exception localException) {
        }
    }
    @Override
    public void onEnable() {
        EventManager.register(this);
    }
    @Override
    public void onDisable() {
        EventManager.unregister(this);
    }
}
