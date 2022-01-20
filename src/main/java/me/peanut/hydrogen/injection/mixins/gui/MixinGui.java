package me.peanut.hydrogen.injection.mixins.gui;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Gui.class)
public abstract class MixinGui {

    @Shadow public abstract void drawRect(int left, int top, int right, int bottom, int color);

    @Shadow
    protected float zLevel;

    @Shadow
    public abstract void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height);

}
