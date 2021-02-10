package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.modules.player.NoSpeedFOV;

/**
 * Created by peanut on 07/02/2021
 */

@Mixin(AbstractClientPlayer.class)
@SideOnly(Side.CLIENT)
public abstract class MixinAbstractClientPlayer extends MixinEntityPlayer {

    @Shadow
    private NetworkPlayerInfo playerInfo;

    @Shadow
    public PlayerCapabilities capabilities = new PlayerCapabilities();

    public float getFovModifier() {
        float f = 1.0F;
        if (this.capabilities.isFlying) {
            f *= 1.1F;
        }

        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        f = (float)((double)f * ((iattributeinstance.getAttributeValue() / (double)this.capabilities.getWalkSpeed() + 1.0D) / 2.0D));

        if(!Hydrogen.getClient().moduleManager.getModule(NoSpeedFOV.class).isEnabled()) {
            f = (float)((double)f * ((iattributeinstance.getAttributeValue() / (double)this.capabilities.getWalkSpeed() + 1.0D) / 2.0D));
        } else {
            double d0 = 0.10000000149011612D;
            if(isSprinting()) {
                d0 = 0.13000000312924387D;
            }
            f = (float)((double)f * ((d0 / (double)this.capabilities.getWalkSpeed() + 1.0D) / 2.0D));
        }



        if (this.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
            f = 1.0F;
        }

        if (this.isUsingItem() && this.getItemInUse().getItem() == Items.bow) {
            int i = this.getItemInUseDuration();
            float f1 = (float)i / 20.0F;
            if (f1 > 1.0F) {
                f1 = 1.0F;
            } else {
                f1 *= f1;
            }

            f *= 1.0F - f1 * 0.15F;
        }

        return ForgeHooksClient.getOffsetFOV((EntityPlayer) (Object) this, f);
    }
}
