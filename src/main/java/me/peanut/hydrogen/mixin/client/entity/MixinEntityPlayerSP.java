package me.peanut.hydrogen.mixin.client.entity;

import com.darkmagician6.eventapi.EventManager;
import me.peanut.hydrogen.event.EventUpdate;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//
// Created by peanut on 07.01.2024
//
@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {


    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void h2$onUpdateEventCall(CallbackInfo ci) {
        EventUpdate eventUpdate = new EventUpdate();
        EventManager.call(eventUpdate);
    }
}