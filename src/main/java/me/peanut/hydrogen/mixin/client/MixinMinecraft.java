package me.peanut.hydrogen.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import me.peanut.hydrogen.event.EventKeyboard;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.event.EventUpdate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.main.GameConfiguration;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//
// Created by peanut on 06.01.2024
//
@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Shadow public GuiScreen currentScreen;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void h2$createInstance(GameConfiguration gameConfiguration, CallbackInfo ci) {
        new Hydrogen();
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.AFTER))
    private void h2$start(CallbackInfo ci) {
        Hydrogen.getInstance().start();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
    private void h2$onKeyPress(CallbackInfo ci) {
        if(Keyboard.getEventKeyState() && currentScreen == null) {
            int key = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
            EventManager.call(new EventKeyboard(key));
        }
    }
}
