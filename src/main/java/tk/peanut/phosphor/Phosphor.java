package tk.peanut.phosphor;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import tk.peanut.phosphor.events.ForgeEventHandler;
import tk.peanut.phosphor.modules.ModuleManager;

@Mod(modid = "Phosphor", name = "Phosphor", version = "1.0-alpha")
public class Phosphor {

    public final String name = "Phosphor";
    public final String devs = "zPeanut";

    private final double version_number = 1.0;
    private final String version_suffix = "-alpha";
    public final String version = "v" + version_number + version_suffix;

    private static Phosphor instance;

    public ModuleManager moduleManager;

    public Phosphor() {
        instance = this;
    }

    public void startClient() {
        MinecraftForge.EVENT_BUS.register(ForgeEventHandler.eventInstance);
        moduleManager = new ModuleManager();
        moduleManager.addModules();
        moduleManager.getModulebyName("HUD").setEnabled();
    }

    public static Phosphor getInstance() {
        return instance;
    }

    public void stopClient() {
    }

}
