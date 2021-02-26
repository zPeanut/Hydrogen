package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;

/**
 * imported from tephra 
 */
@Info(name = "BedAura", description = "Destroys beds when near one", category = Category.Player)
public class BedAura extends Module {
    public BedAura() {
        super(0x00, new Color(252, 255, 199));
    }

    @EventTarget
  public void onUpdate(EventUpdate e) {
    if (mc.thePlayer == null || mc.theWorld == null)
      return; 
    for (int xOffset = -5; xOffset < 6; xOffset++) {
      for (int zOffset = -5; zOffset < 6; zOffset++) {
        for (int yOffset = 5; yOffset > -5; yOffset--) {
          double x = mc.thePlayer.posX + xOffset;
          double y = mc.thePlayer.posY + yOffset;
          double z = mc.thePlayer.posZ + zOffset;
          BlockPos pos = new BlockPos(x, y, z);
          int id = Block.getIdFromBlock(mc.theWorld.getBlockState(pos).getBlock());
          if (id == 26 && this.time.isDelayComplete(700L)) {
            mc.thePlayer.setSprinting(false);
            smashBlock(new BlockPos(x, y, z));
            mc.thePlayer.setSprinting(false);
            this.time.setLastMS();
            mc.thePlayer.setSprinting(false);
          } 
        } 
      } 
    } 
  }
  
  public void smashBlock(BlockPos pos) {
    mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(getRotations(pos)[0], getRotations(pos)[1], mc.thePlayer.onGround));
    mc.thePlayer.swingItem();
    mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(getRotations(pos)[0], getRotations(pos)[1], mc.thePlayer.onGround));
    mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, EnumFacing.UP));
    mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(getRotations(pos)[0], getRotations(pos)[1], mc.thePlayer.onGround));
    mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, EnumFacing.UP));
    mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(getRotations(pos)[0], getRotations(pos)[1], mc.thePlayer.onGround));
  }
  
  public static float[] getRotations(BlockPos pos) {
    if (pos == null)
      return null; 
    double diffX = pos.getX() - mc.thePlayer.posX;
    double diffZ = pos.getZ() - mc.thePlayer.posZ;
    double diffY = (pos.getY() - mc.thePlayer.getEyeHeight());
    double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
    float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
    float pitch = (float)(Math.atan2(diffY, dist) * 180.0D / Math.PI) / (float)dist;
    return new float[] { mc.thePlayer.rotationYaw + 
        
        MathHelper.wrapAngleTo180_float(yaw - (Minecraft.getMinecraft()).thePlayer.rotationYaw), mc.thePlayer.rotationPitch + 
        
        MathHelper.wrapAngleTo180_float(pitch - (Minecraft.getMinecraft()).thePlayer.rotationPitch) };
  }
}

        }
    }

}
