package tk.peanut.phosphor.utils;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.peanut.phosphor.Phosphor;

@SideOnly(Side.CLIENT)
public class ChatUtils {
    public static final String PRIMARY_COLOR = "§7";
    public static final String SECONDARY_COLOR = "§1";
    private static final String PREFIX = PRIMARY_COLOR + "[" + SECONDARY_COLOR + Phosphor.getInstance().name + PRIMARY_COLOR + "] ";

    public static void send(final String s) {
        JsonObject object = new JsonObject();
        object.addProperty("text", s);
        Minecraft.getMinecraft().thePlayer.addChatMessage(IChatComponent.Serializer.jsonToComponent(object.toString()));
    }

    public static void success(String s) {
        info(s);
    }

    public static void info(String s) {
        send(PREFIX + s);
    }
}
