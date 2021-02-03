package tk.peanut.phosphor;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import tk.peanut.phosphor.events.ForgeEventHandler;
import tk.peanut.phosphor.modules.ModuleManager;
import tk.peanut.phosphor.ui.clickgui.settings.SettingsManager;
import tk.peanut.phosphor.ui.clickgui.ui.ClickGUI;
import tk.peanut.phosphor.utils.KeybindManager;

@Mod(modid = "phosphor", name = "Phosphor", version = "1.0", useMetadata = true)
public class Phosphor {

    public static final String name = "Phosphor";
    public static final String devs = "zPeanut";

    private static final double version_number = 1.0;
    private static final String version_suffix = "-alpha";
    public static final String version = "v" + version_number + version_suffix;

    public String prefix = "[§e" + name + "§f] ";

    private static Phosphor instance;

    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public KeybindManager keybindManager;
    public ClickGUI clickgui;

    public Phosphor() {
        instance = this;
    }

    public void startClient() {
        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager();
        keybindManager = new KeybindManager();
        clickgui = new ClickGUI();
        moduleManager.addModules();
        moduleManager.getModulebyName("HUD").setEnabled();
    }

    public static Phosphor getInstance() {
        return instance;
    }

    public void stopClient() {
    }

}
