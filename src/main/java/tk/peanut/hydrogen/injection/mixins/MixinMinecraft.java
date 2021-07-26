package tk.peanut.hydrogen.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.util.Session;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventKey;
import tk.peanut.hydrogen.events.EventMouseClick;
import tk.peanut.hydrogen.events.EventTick;
import tk.peanut.hydrogen.file.files.*;
import tk.peanut.hydrogen.injection.interfaces.IMixinMinecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
@SideOnly(Side.CLIENT)
public class MixinMinecraft implements IMixinMinecraft {
    @Shadow
    public GuiScreen currentScreen;

    @Shadow
    @Mutable
    @Final
    private Session session;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void minecraftConstructor(GameConfiguration gameConfig, CallbackInfo ci) {
        new Hydrogen();
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.AFTER))
    private void startGame(CallbackInfo ci) {
        Hydrogen.getClient().startClient();
        KeybindFile.loadKeybinds();
        SettingsButtonFile.loadState();
        SettingsComboBoxFile.loadState();
        SettingsSliderFile.loadState();
        ClickGuiFile.loadClickGui();
        ModuleFile.loadModules();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
    private void onKey(CallbackInfo ci) {
        if (Keyboard.getEventKeyState() && currentScreen == null)
            EventManager.call(new EventKey(Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey()));
    }

    @Inject(method = "clickMouse", at = @At("HEAD"))
    public void clickMouse(CallbackInfo ci) {
        EventMouseClick e = new EventMouseClick();
        EventManager.register(e);
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    private void onShutdown(CallbackInfo ci) {
        Hydrogen.getClient().stopClient();
        KeybindFile.saveKeybinds();
        SettingsButtonFile.saveState();
        SettingsComboBoxFile.saveState();
        SettingsSliderFile.saveState();
        ClickGuiFile.saveClickGui();
        ModuleFile.saveModules();
    }

    @Inject(method = "runTick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;joinPlayerCounter:I", shift = At.Shift.BEFORE))
    private void onTick(final CallbackInfo callbackInfo) {
        EventTick e = new EventTick();
        EventManager.call(e);
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }


}
