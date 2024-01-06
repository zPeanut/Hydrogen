package me.peanut.hydrogen.mixin.client;

import me.peanut.hydrogen.Hydrogen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//
// Created by peanut on 06.01.2024
//
@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void createInstance(GameConfiguration gameConfiguration, CallbackInfo ci) {
        new Hydrogen();
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.AFTER))
    private void start(CallbackInfo ci) {
        Hydrogen.getInstance().start();
    }



}
