package me.peanut.hydrogen.events;

import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.gui.ClickGUI;
import me.peanut.hydrogen.ui.ingame.HUD;
import me.peanut.hydrogen.ui.ingame.components.ArrayList;
import me.peanut.hydrogen.ui.ingame.components.Watermark;
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

            String text1 = String.format("%s Welcome to §fHydrogen %s§7!", Hydrogen.prefix, Hydrogen.version);
            String text2 = String.format("%s To get started, press §f[%s]§7 to open the ClickGUI!", Hydrogen.prefix, Keyboard.getKeyName(Hydrogen.getClient().moduleManager.getModule(ClickGUI.class).getKeybind()));
            String text3 = String.format("%s Be sure to report any bugs at our §f§nGitHub§7!", Hydrogen.prefix);
            ChatComponentText String1 = new ChatComponentText(EnumChatFormatting.GRAY + text1);
            ChatComponentText String2 = new ChatComponentText(EnumChatFormatting.GRAY + text2);
            ChatComponentText String3 = new ChatComponentText(EnumChatFormatting.GRAY + text3);

            String3.setChatStyle((new ChatStyle()).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Hydrogen.github)));
            Utils.sendChatMessage(String1);
            Utils.sendChatMessage(String2);
            Utils.sendChatMessage(String3);

        }
    }
}
