package me.peanut.hydrogen.events;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.ui.ClickGui;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

/**
 * Created by peanut on 26/12/2021
 */
public class EventWorldListener {

    @SubscribeEvent
    public void onWorld(WorldEvent.Load e) {
        World world = e.world;
        if (world != null && Hydrogen.getClient().firstStart) {
            Utils.sendChatMessage(EnumChatFormatting.GRAY + "Welcome to §fHydrogen " + Hydrogen.version + "§7!");
            Utils.sendChatMessage(EnumChatFormatting.GRAY + "To get started, press §f[" + Keyboard.getKeyName(Hydrogen.getClient().moduleManager.getModule(ClickGui.class).getKeybind()) + "]§7 to open the ClickGUI!");

            ChatComponentText String3 = new ChatComponentText(EnumChatFormatting.GRAY + Hydrogen.prefix + " Be sure to report any bugs at our §f§nGitHub§7!");
            String3.setChatStyle((new ChatStyle()).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Hydrogen.github)));

            Utils.sendChatMessage(String3);
        }
    }
}
