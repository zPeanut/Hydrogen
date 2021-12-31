package me.peanut.hydrogen;

import com.thealtening.auth.AltService;
import com.vdurmont.semver4j.Semver;
import me.peanut.hydrogen.events.EventWorldListener;
import me.peanut.hydrogen.file.FileManager;
import me.peanut.hydrogen.module.ModuleManager;
import me.peanut.hydrogen.ui.clickgui.ClickGui;
import me.peanut.hydrogen.module.modules.ui.ArrayList;
import me.peanut.hydrogen.module.modules.ui.HUD;
import me.peanut.hydrogen.module.modules.ui.Watermark;
import me.peanut.hydrogen.utils.FontHelper;
import me.peanut.hydrogen.utils.KeybindManager;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import me.peanut.hydrogen.altmanager.account.AccountManager;
import me.peanut.hydrogen.command.CommandManager;
import me.peanut.hydrogen.settings.SettingsManager;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Mod(modid = Hydrogen.modid, name = Hydrogen.name, version = Hydrogen.semantic_version, useMetadata = true)
public class Hydrogen {

    public static final String modid = "hydrogen";
    public static final String name = "Hydrogen";
    public static final String devs = "zPeanut & UltramoxX";
    public static final String prefix = "§7[§9" + name + "§7]";

    public static final String version = "1.11";
    public static final String semantic_version = "1.11.0";

    public static final String github = "https://github.com/zpeanut/hydrogen/";
    public static final String release = github + "releases/";
    public static final String tags = release + "tag/" + semantic_version + "/";

    private static Hydrogen instance;
    private static Utils utils;

    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public KeybindManager keybindManager;
    public CommandManager commandManager;
    public FileManager fileManager;
    public AccountManager accountManager;
    public AltService altService;
    public ClickGui clickgui;
    public File directory;

    public boolean outdated;
    public boolean panic;
    public boolean firstStart;
    public boolean isStableBuild = false;
    public String newversion;

    public Hydrogen() {
        instance = this;
    }

    public void startClient() {
        MinecraftForge.EVENT_BUS.register(new EventWorldListener());
        panic = false;
        directory = new File(Minecraft.getMinecraft().mcDataDir, name);
        if (!this.directory.exists()) {
            this.firstStart = true;
            directory.mkdir();
        }
        moduleManager = new ModuleManager();
        settingsManager = new SettingsManager();
        keybindManager = new KeybindManager();
        commandManager = new CommandManager();
        accountManager = new AccountManager(new File(this.directory.toString()));
        altService = new AltService();
        utils = new Utils();
        clickgui = new ClickGui();
        FontHelper.loadFonts();
        moduleManager.addModules();
        this.isOutdated();
        if(settingsManager.getSettingByName("Startup Sound").isEnabled()) {
            Utils.playSound("startup.wav");
        }
        new ArrayList();
        if(firstStart) {
            moduleManager.getModule(HUD.class).setEnabled();
            moduleManager.getModule(Watermark.class).setEnabled();
        }
    }

    public static Hydrogen getClient() {
        return instance;
    }

    public static Utils getUtils() {
        return utils;
    }

    public void stopClient() {}

    public void isOutdated() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/zPeanut/Resources/master/semversion-hydrogen");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                Semver semver = new Semver(line);
                if (semver.isGreaterThan(semantic_version)) {
                    outdated = true;
                    newversion = line;
                } else {
                    outdated = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToMojang() {
        try {
            this.altService.switchService(AltService.EnumAltService.MOJANG);
        } catch (NoSuchFieldException e) {
            Utils.errorLog("Couldn't switch to Mojang AltService");
        } catch (IllegalAccessException e) {
            Utils.errorLog("Couldn't switch to Mojang AltService");
        }
    }

    public void switchToAltening() {
        try {
            this.altService.switchService(AltService.EnumAltService.THEALTENING);
        } catch (NoSuchFieldException e) {
            Utils.errorLog("Couldn't switch to TheAltening AltService");
        } catch (IllegalAccessException e) {
            Utils.errorLog("Couldn't switch to TheAltening AltService");
        }
    }
}

