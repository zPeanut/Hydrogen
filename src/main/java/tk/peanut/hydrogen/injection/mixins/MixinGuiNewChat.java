package tk.peanut.hydrogen.injection.mixins;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * Created by peanut on 11/02/2021
 */
@Mixin(GuiNewChat.class)
public abstract class MixinGuiNewChat extends Gui {

    @Shadow
    private int scrollPos;

    @Shadow
    private boolean isScrolled;

    @Shadow
    public abstract int getLineCount();

    @Shadow
    private final List<ChatLine> drawnChatLines = Lists.newArrayList();

    @Shadow
    public abstract int getChatWidth();

    @Shadow
    public abstract float getChatScale();

    @Shadow
    public abstract boolean getChatOpen();

}
