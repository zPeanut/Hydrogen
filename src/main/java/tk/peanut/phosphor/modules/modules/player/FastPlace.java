package tk.peanut.phosphor.modules.modules.player;

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
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.input.Keyboard;
import scala.collection.parallel.ParIterableLike;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.events.EventUpdate;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;

import java.awt.*;
import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;

public class FastPlace extends Module {


    public FastPlace() {
        super("FastPlace", "Automatic FastBridge", Keyboard.KEY_U, Category.Movement, -1);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(this.isToggled()) {
            Field delayTimer = ReflectionHelper.findField(Minecraft.class, "field_71467_ac", "rightClickDelayTimer");
            try {
                delayTimer.set(Minecraft.getMinecraft().thePlayer, 0);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}
