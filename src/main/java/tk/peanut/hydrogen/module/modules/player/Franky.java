package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;


import java.awt.*;

@Info(name = "Franky", description = "Calls the power from Notch and Herobrine", category = Category.Player)
public class FastPlace extends Module {

    public Franky() {
        super(Keyboard.KEY_NONE, colorPlayer);
 
      
      public Franky() {
        super(0x00, colorPlayer);

        addSetting(new Setting("Ultimate Exploit", this, false));
    }
  public void onEnable() {
		
		if (mc.player != null)
			mc.player.sendChatMessage("/ac Im a god now, duel me.");
		disable();
	}
}
