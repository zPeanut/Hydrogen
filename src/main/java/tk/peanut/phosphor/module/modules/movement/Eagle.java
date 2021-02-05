package tk.peanut.phosphor.module.modules.movement;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.events.EventUpdate;
import tk.peanut.phosphor.module.Category;
import tk.peanut.phosphor.module.Module;
import tk.peanut.phosphor.utils.ReflectionUtil;

import java.time.format.DateTimeFormatter;

public class Eagle extends Module {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");


    public Eagle() {
        super("Eagle", "Automatic FastBridge", Keyboard.KEY_Y, Category.Movement, -1);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {

        try {
            if (((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock)) && (!this.mc.gameSettings.keyBindJump.isPressed())) {
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
