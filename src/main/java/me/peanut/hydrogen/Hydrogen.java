package me.peanut.hydrogen;

import com.thealtening.auth.AltService;
import com.vdurmont.semver4j.Semver;
import me.peanut.hydrogen.events.EventWorldListener;
import me.peanut.hydrogen.file.FileManager;
import me.peanut.hydrogen.module.ModuleManager;
import me.peanut.hydrogen.module.modules.ui.MainMenuModule;
import me.peanut.hydrogen.ui.clickgui.ClickGui;
import me.peanut.hydrogen.module.modules.ui.ArrayList;
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
import java.net.URL;

@Mod(modid = Hydrogen.modid, name = Hydrogen.name, version = Hydrogen.semantic_version, useMetadata = true)
public class Hydrogen {

    public static final String modid = "hydrogen";
    public static final String name = "Hydrogen";
    public static final String devs = "zPeanut & UltramoxX";
    public static final String prefix = "§7[§9" + name + "§7]";

    public static String version = "1.12 Dev";
    public static final String semantic_version = "1.12.0-dev";

    public static final String github = "https://github.com/zpeanut/hydrogen/";
    public static final String release = github + "releases/";
    public static final String tags = release + "tag/" + semantic_version + "/";
    public static final String currentCommitURL = github + "commit/" + Utils.getCurrentCommitHash();

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

    public boolean hasNewFiles;

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
        if(new File(directory, "modules.json").exists() || new File(directory, "settings.json").exists() || new File(directory, "clickgui.json").exists()) {
            hasNewFiles = true;
        } else {
            Utils.log("Old Files detected! Will be deleted after game shutdown.");
        }
        if(!isStableBuild) {
            // get commit dates
            Utils.getCurrentCommitDate();
            // add git commit hash to version
            version += String.format(" §7| %s", Utils.getCurrentCommitHash());
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
        ArrayList.arrayListThread();
        if(firstStart) {
            moduleManager.getModule(MainMenuModule.class).setEnabled();
        }
    }

    public static Hydrogen getClient() {
        return instance;
    }

    public static Utils getUtils() {
        return utils;
    }

    // removing in 1.12.1 or 1.13
    public void stopClient() {
        if(!hasNewFiles) {
            File oldVisibleFile = new File(directory, "visible.txt");
            if(oldVisibleFile.delete()) {
                Utils.log("Deleted old Visible Settings!");
            }
            File oldModuleFile = new File(directory, "modules.txt");
            if(oldModuleFile.delete()) {
                Utils.log("Deleted old Modules Settings!");
            }
            File oldKeybindFile = new File(directory, "binds.txt");
            if(oldKeybindFile.delete()) {
                Utils.log("Deleted old Binds Settings!");
            }
            File oldSliderFile = new File(directory, "slider.txt");
            if(oldSliderFile.delete()) {
                Utils.log("Deleted old Slider Settings!");
            }
            File oldButtonFile = new File(directory, "button.txt");
            if(oldButtonFile.delete()) {
                Utils.log("Deleted old Button Settings!");
            }
            File oldComboBoxFile = new File(directory, "combobox.txt");
            if(oldComboBoxFile.delete()) {
                Utils.log("Deleted old Combobox Settings!");
            }
            File oldTextFile = new File(directory, "text.txt");
            if(oldTextFile.delete()) {
                Utils.log("Deleted old Text Settings!");
            }
            File oldClickGuiFile = new File(directory, "clickgui.txt");
            if(oldClickGuiFile.delete()) {
                Utils.log("Deleted old ClickGui Settings!");
            }
        }
    }

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

