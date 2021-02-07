package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Created by peanut on 07/02/2021
 */

@Mixin(AbstractClientPlayer.class)
@SideOnly(Side.CLIENT)
public abstract class MixinAbstractClientPlayer extends MixinEntityPlayer {
}
