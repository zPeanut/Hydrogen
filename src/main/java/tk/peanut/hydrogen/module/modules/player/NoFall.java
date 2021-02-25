  
package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

import java.awt.*;

/**
 * Created by ProfKambing on 25/02/2021
 */
@Info(name = "NoFall", description = "Prevents you from getting fall damage", category = Category.Player)
public class NoFall extends Module {
    public NoFall() {
        super(0x00, new Color(252, 255, 199));
    }

   public void onEvent(Event e) {
     if (e instanceof EventUpdate && e.isPre()) {
       if (mc.thePlayer.fallDistance > 2)
         mc.thePlayer.sendQueue.addToSendQueue (new C03PacketPlayer(true));
        }
    }

}
© 2021 Gi
