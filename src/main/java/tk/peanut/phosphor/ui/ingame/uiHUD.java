package tk.peanut.phosphor.ui.ingame;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.events.EventRender2D;

public class uiHUD {

    public static Minecraft mc = Minecraft.getMinecraft();


    @EventTarget
    public static void render(EventRender2D e) {

        drawWatermark();

    }


    private static void drawWatermark() {
        mc.fontRendererObj.drawStringWithShadow(String.format("%s §7%s", Phosphor.getInstance().name, Phosphor.getInstance().version), 2, 2, -1);

    }


}
