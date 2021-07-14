package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.TimeHelper;
import tk.peanut.hydrogen.utils.Utils;

import java.util.ArrayList;

/**
 * Created by peanut on 28/02/2021
 */
@Info(name = "BedAura", category = Category.Player, description = "Automatically destroys beds")
public class BedAura extends Module {

    private TimeHelper time = new TimeHelper();

    public static ArrayList<Integer> ids = new ArrayList<>();

    public static BlockPos pos;

    private int x;

    private int z;

    private int y;

    public BedAura() {
        super(0x00);

        addSetting(new Setting("Bed", this, true));
        addSetting(new Setting("Cake", this, false));
        addSetting(new Setting("Egg", this, false));
        addSetting(new Setting("ThroughWalls", this, false));
        addSetting(new Setting("Radius", this, 5.0D, 1.0D, 6.0D, true));
        addSetting(new Setting("Delay", this, 1000.0D, 1.0D, 2000.0D, true));
    }

    @Override
    public void onDisable() {
        pos = null;
    }


    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(this.isEnabled()) {
            boolean bypassWall = Hydrogen.getClient().settingsManager.getSettingByName(this, "ThroughWalls").isEnabled();
            int radius = (int) Hydrogen.getClient().settingsManager.getSettingByName("Radius").getValDouble();
            int delay = (int) Hydrogen.getClient().settingsManager.getSettingByName(this, "Delay").getValDouble();
            int startX = this.mc.thePlayer.getPosition().getX() - radius;
            int startY = this.mc.thePlayer.getPosition().getY() - radius;
            int startZ = this.mc.thePlayer.getPosition().getZ() - radius;
            int endX = this.mc.thePlayer.getPosition().getX() + radius;
            int endY = this.mc.thePlayer.getPosition().getY() + radius;
            int endZ = this.mc.thePlayer.getPosition().getZ() + radius;
            checkIds();
            this.x = startX;

            while (this.x < endX) {
                this.z = startZ;
                while (this.z < endZ) {
                    this.y = startY;
                    while (this.y < endY) {
                        BlockPos blockPos = new BlockPos(this.x, this.y, this.z);
                        if (this.mc.theWorld.getBlockState(blockPos) != null) {
                            int id = Block.getIdFromBlock(this.mc.theWorld.getBlockState(blockPos).getBlock());
                            if (ids.size() > 0 && ids.contains(Integer.valueOf(id))) {
                                this.pos = blockPos;
                                if (this.time.hasReached(delay)) {
                                    if(bypassWall) {
                                        smashBlock(blockPos);
                                    } else {
                                        if(Hydrogen.getUtils().canBlockBeSeen(blockPos)) {
                                            smashBlock(blockPos);
                                        }
                                    }
                                    this.time.reset();
                                    break;
                                }
                            }
                        }
                        this.y++;
                    }
                    this.z++;
                }
                this.x++;
            }
        }
    }

    private void checkIds() {
        boolean bed = Hydrogen.getClient().settingsManager.getSettingByName(this, "Bed").isEnabled();
        boolean cake = Hydrogen.getClient().settingsManager.getSettingByName(this, "Cake").isEnabled();
        boolean egg = Hydrogen.getClient().settingsManager.getSettingByName(this, "Egg").isEnabled();
        Integer i = new Integer(26);
        if (ids.contains(i) && !bed)
            ids.remove(i);
        if (!ids.contains(i) && bed)
            ids.add(i);
        i = new Integer(354);
        if (ids.contains(i) && !cake)
            ids.remove(i);
        if (!ids.contains(i) && cake)
            ids.add(i);
        i = new Integer(122);
        if (ids.contains(i) && !egg)
            ids.remove(i);
        if (!ids.contains(i) && egg)
            ids.add(i);
    }

    public void smashBlock(BlockPos pos) {
        this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, EnumFacing.UP));
        this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, EnumFacing.UP));
        this.mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
    }
}
