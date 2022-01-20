package me.peanut.hydrogen.injection.mixins.gui;

import com.darkmagician6.eventapi.EventManager;
import me.peanut.hydrogen.events.EventText;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Created by peanut on 24/12/2021
 */
@Mixin(FontRenderer.class)
public class MixinFontRenderer {
    @ModifyVariable(method = "renderString", at = @At("HEAD"), ordinal = 0)
    private String renderString(String string) {
        if (string == null) {
            return string;
        }
        EventText e = new EventText(string);
        EventManager.call(e);
        return e.getText();
    }

    @ModifyVariable(method = "getStringWidth", at = @At("HEAD"), ordinal = 0)
    private String getStringWidth(String string) {
        if (string == null) {
            return string;
        }
        EventText e = new EventText(string);
        EventManager.call(e);
        return e.getText();
    }
}
