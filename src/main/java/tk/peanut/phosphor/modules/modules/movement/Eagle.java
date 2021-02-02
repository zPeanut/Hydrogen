package tk.peanut.phosphor.modules.modules.movement;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.events.EventUpdate;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;

import java.awt.*;
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
            if (((mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock))
                    && (!this.mc.gameSettings.keyBindJump.isPressed())) {
                BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0D,
                        mc.thePlayer.posZ);
                if (this.mc.theWorld.getBlockState(bp).getBlock() == Blocks.air) {

                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);
                } else {
                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
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
