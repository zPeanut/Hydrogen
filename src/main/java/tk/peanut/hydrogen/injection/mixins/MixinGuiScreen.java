package tk.peanut.hydrogen.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.peanut.hydrogen.Hydrogen;

@Mixin(GuiScreen.class)
@SideOnly(Side.CLIENT)
public class MixinGuiScreen {
    @Shadow
    public Minecraft mc;

    @Inject(method = "sendChatMessage(Ljava/lang/String;Z)V", at = @At("HEAD"), cancellable = true)
    private void onChat(String msg, boolean addToChat, @NotNull CallbackInfo ci) {
        if (msg.startsWith(".") && msg.length() > 1) {
            if (Hydrogen.getClient().commandManager.execute(msg)) {
                this.mc.ingameGUI.getChatGUI().addToSentMessages(msg);
            }
            ci.cancel();
        }
    }
}
