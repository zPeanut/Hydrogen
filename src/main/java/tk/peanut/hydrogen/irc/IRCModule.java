package tk.peanut.hydrogen.irc;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.PlayerUtils;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import javax.swing.*;

@Info(name = "IRC", description = "Private Chat", category = Category.Combat)
public class IRCModule extends Module {


    public IRCModule() {
        super(Keyboard.KEY_NONE);
        setDisabled();
    }

    @Override
    public void onEnable() {


        if (Hydrogen.Auth == 100) {

            if (IRC.connect("127.0.0.1",12313) == true) {
                Hydrogen.isircenable = true;
                if (Minecraft.getMinecraft().theWorld != null) {
                    PlayerUtils.INFO("Connected");
                } else {
                    JOptionPane.showMessageDialog(null, "Connected", Hydrogen.name, JOptionPane.ERROR);
                }


            } else {
                Hydrogen.isircenable = false;
                if (Minecraft.getMinecraft().theWorld != null) {
                    PlayerUtils.ERROR("Connect Failed");
                } else {
                    JOptionPane.showMessageDialog(null, "Connect Failed", Hydrogen.name, JOptionPane.ERROR);
                }

            }

        } else {
            if (Minecraft.getMinecraft().theWorld != null) {
                PlayerUtils.ERROR("Please Login Your Account First！");
            } else {
                JOptionPane.showMessageDialog(null, "Please Login Your Account First！", Hydrogen.name, JOptionPane.ERROR);
            }

        }

    }

    @Override
    public void onDisable() {
        if (Hydrogen.isircenable == true) {
            IRC.shutdownirc();
        }

    }
}
