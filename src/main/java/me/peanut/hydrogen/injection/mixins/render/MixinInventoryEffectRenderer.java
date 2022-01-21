package me.peanut.hydrogen.injection.mixins.render;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.injection.mixins.gui.MixinGuiContainer;
import me.peanut.hydrogen.module.modules.render.Animations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;

/**
 * Created by peanut on 19/01/2022
 */
@Mixin(InventoryEffectRenderer.class)
public class MixinInventoryEffectRenderer extends MixinGuiContainer {

    @Shadow private boolean hasActivePotionEffects;

    /**
     * @author
     */
    @Overwrite
    protected void updateActivePotionEffects() {
        boolean hasVisibleEffect = false;
        Iterator var2 = Minecraft.getMinecraft().thePlayer.getActivePotionEffects().iterator();

        while(var2.hasNext()) {
            PotionEffect potioneffect = (PotionEffect)var2.next();
            Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
            if (potion.shouldRender(potioneffect)) {
                hasVisibleEffect = true;
                break;
            }
        }

       if (!Minecraft.getMinecraft().thePlayer.getActivePotionEffects().isEmpty() && hasVisibleEffect) {
            if(!(Hydrogen.getClient().settingsManager.getSettingByName("Inventory Offset").isEnabled() && Hydrogen.getClient().moduleManager.getModule(Animations.class).isEnabled())) {
                this.guiLeft = 160 + (this.width - this.xSize - 200) / 2;
            }
            this.hasActivePotionEffects = true;
        } else {
            this.guiLeft = (this.width - this.xSize) / 2;
            this.hasActivePotionEffects = false;
        }
    }
}
