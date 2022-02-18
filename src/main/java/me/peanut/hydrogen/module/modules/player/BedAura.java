package me.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.utils.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.events.EventUpdate;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.settings.Setting;
import me.peanut.hydrogen.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by peanut on 28/02/2021
 */
@Info(name = "BedAura", category = Category.Player, description = "Automatically destroys beds")
public class BedAura extends Module {

    private final TimeUtils time = new TimeUtils();

    public static final ArrayList<Integer> ids = new ArrayList<>();

    public static BlockPos pos;

    private int x;

    private int z;

    private int y;

    public BedAura() {

        // TODO: fix inconsistent breaking of beds and eggs
        // TODO: fix cakes not being destroyed

        addSetting(new Setting("Bed", this, true));
        addSetting(new Setting("Cake", this, false));
        addSetting(new Setting("Egg", this, false));
        addSetting(new Setting("ThroughWalls", this, false));
        addSetting(new Setting("Radius", this, 5, 1, 6, true));
        addSetting(new Setting("Delay", this, 1000, 1, 2000, true));
    }

    @Override
    public void onDisable() {
        pos = null;
    }


    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(this.isEnabled()) {
            boolean bypassWall = h2.settingsManager.getSettingByName(this, "ThroughWalls").isEnabled();
            int radius = (int) h2.settingsManager.getSettingByName("Radius").getValue();
            int delay = (int) h2.settingsManager.getSettingByName(this, "Delay").getValue();
            int startX = mc.thePlayer.getPosition().getX() - radius;
            int startY = mc.thePlayer.getPosition().getY() - radius;
            int startZ = mc.thePlayer.getPosition().getZ() - radius;
            int endX = mc.thePlayer.getPosition().getX() + radius;
            int endY = mc.thePlayer.getPosition().getY() + radius;
            int endZ = mc.thePlayer.getPosition().getZ() + radius;
            checkIds();
            this.x = startX;

            while (this.x < endX) {
                this.z = startZ;
                while (this.z < endZ) {
                    this.y = startY;
                    while (this.y < endY) {
                        BlockPos blockPos = new BlockPos(this.x, this.y, this.z);
                        if (mc.theWorld.getBlockState(blockPos) != null) {
                            int id = Block.getIdFromBlock(mc.theWorld.getBlockState(blockPos).getBlock());
                            if (ids.size() > 0 && ids.contains(id)) {
                                pos = blockPos;
                                if (this.time.hasReached(delay)) {
                                    if(bypassWall) {
                                        smashBlock(blockPos);
                                    } else {
                                        if(PlayerUtil.canBlockBeSeen(blockPos)) {
                                            smashBlock(blockPos);
                                        }
                                    }
                                    TimeUtils.reset();
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
        boolean bed = h2.settingsManager.getSettingByName(this, "Bed").isEnabled();
        boolean cake = h2.settingsManager.getSettingByName(this, "Cake").isEnabled();
        boolean egg = h2.settingsManager.getSettingByName(this, "Egg").isEnabled();
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
        mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, EnumFacing.UP));
        mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, EnumFacing.UP));
        mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
    }
}
