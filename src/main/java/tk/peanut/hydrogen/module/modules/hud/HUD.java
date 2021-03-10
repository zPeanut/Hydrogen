package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.Category;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.FontHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Info(name = "HUD", description = "The overlay", category = Category.Gui)
public class HUD extends Module {

    private static java.util.ArrayList<Module> activemodules;

    public HUD() {
        super(Keyboard.KEY_NONE, Color.white);
        activemodules = new java.util.ArrayList<>();

        ArrayList<String> time = new ArrayList<>();
        time.add("24H");
        time.add("12H");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("Time Format", this, "24H", time));
    }
}
