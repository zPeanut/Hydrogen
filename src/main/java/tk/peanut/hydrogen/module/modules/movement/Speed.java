package tk.peanut.hydrogen.module.modules.movement;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.ReflectionUtil;

import java.awt.*;
import java.time.format.DateTimeFormatter;

@Info(name = "Speed", description = "speed go brrr", category = Category.Movement)
public class Speed extends Module {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");


    public Speed() {
        super(Keyboard.KEY_V, new Color(173, 234, 255))
    }
  
  
 @Override
    public void setup() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Y-Port");
        Tutorial.instance.settingsManager.rSetting(new Setting("Speed Mode", this, "Y-Port", options));
    }

    @EventTarget
    public void onPre(EventPreMotionUpdate event) {
        if(isOnLiquid())
            return;
        if(mc.thePlayer.onGround && (mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0)) {
            if(mc.thePlayer.ticksExisted % 2 != 0)
                event.y += .4;
            mc.thePlayer.setSpeed(mc.thePlayer.ticksExisted % 2 == 0 ? .45F : .2F);
            mc.timer.timerSpeed = 1.095F;
        }
    }

    private boolean isOnLiquid() {
        boolean onLiquid = false;
        final int y = (int)(mc.thePlayer.boundingBox.minY - .01);
        for(int x = MathHelper.floor_double(mc.thePlayer.boundingBox.minX); x < MathHelper.floor_double(mc.thePlayer.boundingBox.maxX) + 1; ++x) {
            for(int z = MathHelper.floor_double(mc.thePlayer.boundingBox.minZ); z < MathHelper.floor_double(mc.thePlayer.boundingBox.maxZ) + 1; ++z) {
                Block block = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                if(block != null && !(block instanceof BlockAir)) {
                    if(!(block instanceof BlockLiquid))
                        return false;
                    onLiquid = true;
                }
            }
        }
        return onLiquid;
    }
}  
  
