package me.peanut.hydrogen;

import com.thealtening.auth.AltService;
import com.vdurmont.semver4j.Semver;
import me.peanut.hydrogen.events.EventWorldListener;
import me.peanut.hydrogen.file.files.ClickGuiConfig;
import me.peanut.hydrogen.file.files.ModuleConfig;
import me.peanut.hydrogen.file.files.SettingsConfig;
import me.peanut.hydrogen.module.ModuleManager;
import me.peanut.hydrogen.module.modules.player.Freecam;
import me.peanut.hydrogen.module.modules.player.Panic;
import me.peanut.hydrogen.ui.ingame.HUD;
import me.peanut.hydrogen.module.modules.gui.MainMenuModule;
import me.peanut.hydrogen.ui.clickgui.ClickGui;
import me.peanut.hydrogen.ui.ingame.components.ArrayList;
import me.peanut.hydrogen.ui.ingame.style.styles.Classic;
import me.peanut.hydrogen.ui.ingame.style.styles.New;
import me.peanut.hydrogen.ui.mainmenu.MainMenu;
import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.utils.HTTPUtil;
import me.peanut.hydrogen.utils.KeybindUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import me.peanut.hydrogen.altmanager.account.AccountManager;
import me.peanut.hydrogen.command.CommandManager;
import me.peanut.hydrogen.settings.SettingsManager;

import java.io.*;

@Mod(modid = Hydrogen.modid, name = Hydrogen.name, version = Hydrogen.semantic_version, useMetadata = true)
public class Hydrogen {

    public static final String modid = "hydrogen";
    public static final String name = "Hydrogen";
    public static final String devs[] = {"zPeanut", "UltramoxX"};
    public static final String prefix = "§7[§9" + name + "§7]";

    public static String version = "1.12 Dev";
    public static final String semantic_version = "1.12.0-dev";

    public static final String github = "https://github.com/zpeanut/hydrogen/";
    public static final String release = github + "releases/";
    public static final String tags = release + "tag/" + semantic_version + "/";
    public static final String currentCommitURL = github + "commit/" + HTTPUtil.getCurrentCommitHash();

    private static Hydrogen instance;

    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public KeybindUtil keybindManager;
    public CommandManager commandManager;
    public AccountManager accountManager;
    public AltService altService;
    public ClickGui clickgui;
    public File directory;
    public HUD hud;

    public boolean outdated;
    public boolean panic = false;
    public boolean firstStart;
    public boolean isStableBuild = false;
    public String newversion;

    public boolean hasNewFiles;

    public Hydrogen() {
        instance = this;
    }

    public void startClient() {
        MinecraftForge.EVENT_BUS.register(new EventWorldListener());
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
            HTTPUtil.getCurrentCommitDate();
            // add git commit hash to version
            version += String.format(" §7| %s", HTTPUtil.getCurrentCommitHash());
        }
        moduleManager = new ModuleManager();
        settingsManager = new SettingsManager();
        keybindManager = new KeybindUtil();
        commandManager = new CommandManager();
        accountManager = new AccountManager(new File(this.directory.toString()));
        altService = new AltService();
        clickgui = new ClickGui();
        FontHelper.loadFonts();
        moduleManager.addModules();
        hud = new HUD();

        this.isOutdated();
        new MainMenu();
        Classic.classicArrayThread();
        New.newArrayThread();

        if(settingsManager.getSettingByName("Startup Sound").isEnabled()) {
            Utils.playSound("startup.wav");
        }

        if(firstStart) {
            moduleManager.getModule(MainMenuModule.class).setEnabled();
        }
    }

    public static Hydrogen getClient() {
        return instance;
    }

    // removing in 1.12.1 or 1.13
    public void stopClient() {
        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.saveConfig();
        SettingsConfig settingsConfig = new SettingsConfig();
        settingsConfig.saveConfig();
        ClickGuiConfig clickGuiConfig = new ClickGuiConfig();
        clickGuiConfig.saveConfig();
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
        panic = false;
    }

    public void isOutdated() {
        String version = HTTPUtil.getWebsiteLine("https://raw.githubusercontent.com/zPeanut/Resources/master/semversion-hydrogen");
        Semver semver = new Semver(version);
        if (semver.isGreaterThan(semantic_version)) {
            outdated = true;
            newversion = version;
        }
    }
}

