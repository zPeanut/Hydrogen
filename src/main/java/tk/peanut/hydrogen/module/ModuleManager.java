package tk.peanut.hydrogen.module;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.hydrogen.events.EventKey;
import tk.peanut.hydrogen.module.modules.combat.*;
import tk.peanut.hydrogen.ui.clickgui.ClickGuiModule;
import tk.peanut.hydrogen.ui.ingame.*;
import tk.peanut.hydrogen.module.modules.movement.*;
import tk.peanut.hydrogen.module.modules.player.*;
import tk.peanut.hydrogen.module.modules.render.*;
import tk.peanut.hydrogen.ui.ingame.Info;
import tk.peanut.hydrogen.ui.mainmenu.MainMenuModule;
import tk.peanut.hydrogen.utils.FontHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ModuleManager {

    private List<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        EventManager.register(this);
    }

    public void addModules() {

        // 1.0

        add(new Eagle());
        add(new ClickGuiModule());
        add(new FastPlace());
        add(new Sprint());
        add(new Chams());

        // hud

        add(new tk.peanut.hydrogen.ui.ingame.ArrayList());
        add(new Hotbar());
        add(new Info());
        add(new Watermark());
        add(new HUD());

        // 1.1

        add(new AutoClicker());
        add(new ESP());
        add(new TriggerBot());
        add(new NoBob());

        // 1.2

        add(new Fullbright());
        add(new SafeWalk());

        // 1.3

        add(new NameTags());
        add(new ChestStealer());
        add(new InventoryWalk());
        add(new NoSpeedFOV());
        add(new Trajectories());
        add(new AutoRespawn());
        add(new StorageESP());
        add(new AirStrafe());

        // 1.4

        add(new AntiAFK());
        add(new WTap());
        add(new Flight());
        add(new ItemESP());

        // 1.5

        add(new MainMenuModule());
        add(new NoHurtCam());
        add(new BedESP());
        add(new BedAura());

        // 1.8

        add(new AimBot());
        add(new TargetSelect());
        add(new FastBow());
        add(new Reach());
        add(new HitBox());

        // 1.9

        add(new BowAimbot());
        add(new STap());
        add(new ChatRect());

        // 1.10

        add(new PingSpoof());
        add(new MurderMystery());
        add(new Tracers());
        add(new TTFChat());
        add(new Panic());

        // alphabetically sort modules in clickgui

        if (modules.size() > 0) {
            Collections.sort(modules, new Comparator<Module>() {
                @Override
                public int compare(final Module mod1, final Module mod2) {
                    return mod1.getName().compareTo(mod2.getName());
                }
            });
        }
    }

    private void add(Module module) {
        this.modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) modules.stream().filter(mod -> mod.getClass() == clazz).findFirst().orElse(null);
    }

    public Module getModule(String name, boolean caseSensitive) {
        return modules.stream().filter(mod -> !caseSensitive && name.equalsIgnoreCase(mod.getName()) || name.equals(mod.getName())).findFirst().orElse(null);
    }

    @EventTarget
    private void onKey(EventKey event) {
        for (Module module : modules) if (module.getKeybind() == event.getKey()) module.toggle();
    }

    public ArrayList<Module> getModulesInCategory(Category categoryIn){
        ArrayList<Module> mods = new ArrayList<Module>();
        for(Module m : this.modules){
            if(m.getCategory() == categoryIn)
                mods.add(m);
        }
        return mods;
    }


    public Module getModulebyName(String moduleName) {
        for(Module mod : modules) {
            if((mod.getName().trim().equalsIgnoreCase(moduleName)) || (mod.toString().trim().equalsIgnoreCase(moduleName.trim()))) {
                return mod;
            }
        }
        return null;
    }

    public List<Module> getEnabledMods() {
        List<Module> modules = new ArrayList<>();

        for (Module mod : this.getModules()) {
            if (mod.isEnabled() || (mod.getSlide() != 0 && !mod.isEnabled())) {
                if (mod.getCategory() != Category.Gui) {
                    if (!modules.contains(mod)) {
                        modules.add(mod);
                    }
                }
            }
            Collections.sort(modules, new Comparator<Module>() {
                public int compare(Module m1, Module m2) {
                    if (FontHelper.sf_l.getStringWidth(m1.getName()) > FontHelper.sf_l.getStringWidth(m2.getName()))
                        return -1;
                    if (FontHelper.sf_l.getStringWidth(m1.getName()) < FontHelper.sf_l.getStringWidth(m2.getName()))
                        return 1;
                    return 0;
                }
            });
        }
        return modules;
    }

    public boolean get(int i) {
        return false;
    }
}
