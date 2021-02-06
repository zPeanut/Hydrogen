package tk.peanut.hydrogen;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import tk.peanut.hydrogen.file.FileManager;
import tk.peanut.hydrogen.module.ModuleManager;
import tk.peanut.hydrogen.ui.clickgui.ClickGui;
import tk.peanut.hydrogen.settings.SettingsManager;
import tk.peanut.hydrogen.ui.ingame.uiHUD;
import tk.peanut.hydrogen.utils.KeybindManager;

import java.io.File;

@Mod(modid = Hydrogen.modid, name = Hydrogen.name, version = Hydrogen.version_number, useMetadata = true)
public class Hydrogen {

    public static final String modid = "hydrogen";
    public static final String name = "Hydrogen";
    public static final String devs = "zPeanut";

    public static final String version_number = "1.2";
    private static final String version_suffix = "";
    public static final String version = "v" + version_number + version_suffix;

    public String prefix = "[§e" + name + "§f] ";

    private static Hydrogen instance;

    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public KeybindManager keybindManager;
    public FileManager fileManager;
    public ClickGui clickgui;
    public File directory;

    public Hydrogen() {
        instance = this;
    }

    public void startClient() {
        directory = new File(Minecraft.getMinecraft().mcDataDir, name);
        if (!this.directory.exists()) {
            directory.mkdir();
        }
        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager();
        keybindManager = new KeybindManager();
        clickgui = new ClickGui();
        moduleManager.addModules();
        new uiHUD();
        moduleManager.getModulebyName("HUD").setEnabled();
    }

    public static Hydrogen getInstance() {
        return instance;
    }

    public void stopClient() {
    }

}
