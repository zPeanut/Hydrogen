package me.peanut.hydrogen.command;

import me.peanut.hydrogen.Hydrogen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

/**
 * Created by peanut on 13/03/2021
 */
public abstract class Command {

    public abstract void execute(String[] args);

    public abstract String getName();
    public abstract String getSyntax();
    public abstract String getDesc();

    public static void msg(String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Hydrogen.prefix + "§7 " + msg));
    }
}
