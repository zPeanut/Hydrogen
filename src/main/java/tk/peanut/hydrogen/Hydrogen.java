package tk.peanut.hydrogen;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import tk.peanut.hydrogen.command.CommandManager;
import tk.peanut.hydrogen.events.ForgeEventHandler;
import tk.peanut.hydrogen.file.FileManager;
import tk.peanut.hydrogen.module.ModuleManager;
import tk.peanut.hydrogen.module.modules.hud.ArrayList;
import tk.peanut.hydrogen.ui.clickgui.ClickGui;
import tk.peanut.hydrogen.settings.SettingsManager;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.KeybindManager;
import tk.peanut.hydrogen.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Mod(modid = Hydrogen.modid, name = Hydrogen.name, version = Hydrogen.version_number, useMetadata = true)
public class Hydrogen {

    public static final String modid = "hydrogen";
    public static final String name = "Hydrogen";
    public static final String devs = "zPeanut & UltramoxX";
    public static final String version_number = "1.6";
    private static final String version_suffix = "";
    public static final String version = "v" + version_number + version_suffix;

    public String prefix = "§7[§b" + name + "§7]";

    private static Hydrogen instance;
    private static Utils utils;

    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public KeybindManager keybindManager;
    public CommandManager commandManager;
    public FileManager fileManager;
    public ClickGui clickgui;
    public File directory;


    public boolean outdated = false;
    public String newversion;

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
        commandManager = new CommandManager();
        utils = new Utils();
        clickgui = new ClickGui();
        FontHelper.loadFonts();
        moduleManager.addModules();
        new ArrayList();
        this.isOutdated();
    }

    public static Hydrogen getClient() {
        return instance;
    }

    public static Utils getUtils() {
        return utils;
    }

    public void stopClient() {
    }

    public void isOutdated() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/zPeanut/Resources/master/version-hydrogen");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                if (Float.parseFloat(line) > Float.parseFloat(version_number)) {
                    outdated = true;
                    newversion = line;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
      TODO LIST

      TODO: Blur on ClickGUI Buttons (i.e. see Impact)
      TODO: Visibility Option on Module in ClickGUI
      TODO: Freecam Module
      TODO: Tracers Module
      TODO: Add more Player modules
     */

}

