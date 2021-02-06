package tk.peanut.phosphor.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import scala.collection.parallel.ParIterableLike;
import tk.peanut.phosphor.events.EventSafeWalk;

@Mixin(Entity.class)
public class MixinEntity {
    @Shadow
    public double posX;
    @Shadow
    public double posY;
    @Shadow
    public double posZ;

    @Shadow
    public float rotationYaw;
    @Shadow
    public float rotationPitch;

    @Shadow
    public boolean onGround;

    @Inject(method = "moveEntity", at = @At("HEAD"))
    public void moveEntity(Entity entity, double x, double y, double z, CallbackInfo ci) {
        boolean flag = entity.onGround && entity.isSneaking() && entity instanceof EntityPlayer;
        EventSafeWalk e = new EventSafeWalk();
        EventManager.call(e);
        if (e.isCancelled()) {
            flag = true;
        }
    }

}
