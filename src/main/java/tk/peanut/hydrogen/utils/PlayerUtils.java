package tk.peanut.hydrogen.utils;

import tk.peanut.hydrogen.Hydrogen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class PlayerUtils {
    public static Minecraft mc = Minecraft.getMinecraft();

    public static void SendMessage(String Message) {
        mc.thePlayer.sendChatMessage(Message);
    }

    public static void INFO(String msg) {
        mc.thePlayer.addChatMessage(new ChatComponentText(Hydrogen.getClient().prefix + "§7 " + msg));
    }

    public static void ERROR(String msg) {
        mc.thePlayer.addChatMessage(new ChatComponentText(Hydrogen.getClient().prefix + "§4 " + msg));
    }

    public static void Warning(String msg) {
        mc.thePlayer.addChatMessage(new ChatComponentText(Hydrogen.getClient().prefix + "§e " + msg));
    }
}
