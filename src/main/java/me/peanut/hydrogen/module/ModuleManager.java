package me.peanut.hydrogen.module;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.module.modules.combat.*;
import me.peanut.hydrogen.module.modules.gui.ClickGUI;
import me.peanut.hydrogen.ui.ingame.components.Hotbar;
import me.peanut.hydrogen.module.modules.gui.MainMenuModule;
import me.peanut.hydrogen.module.modules.movement.*;
import me.peanut.hydrogen.module.modules.player.*;
import me.peanut.hydrogen.module.modules.render.*;
import me.peanut.hydrogen.ui.ingame.*;
import me.peanut.hydrogen.ui.ingame.components.Info;
import me.peanut.hydrogen.events.EventKey;
import me.peanut.hydrogen.ui.ingame.components.PotionEffects;
import me.peanut.hydrogen.ui.ingame.components.Watermark;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        EventManager.register(this);
    }

    public void addModules() {

        // 1.0

        add(new Eagle());
        add(new ClickGUI());
        add(new FastPlace());
        add(new Sprint());
        add(new Chams());

        // hud

        add(new HUD());
        add(new me.peanut.hydrogen.ui.ingame.components.ArrayList());
        add(new Hotbar());
        add(new Info());
        add(new Watermark());
        add(new PotionEffects());

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

        // 1.4

        add(new AntiAFK());
        add(new WTap());
        add(new ItemESP());

        // 1.5

        add(new MainMenuModule());
        add(new NoHurtCam());
        add(new BedESP());
        add(new BedAura());

        // 1.8

        //add(new AimBot()); // TODO: recode
        //add(new TargetSelect());
        add(new FastBow());
        add(new Reach());
        add(new HitBox());

        // 1.9

        //add(new BowAimbot()); TODO: recode
        add(new STap());
        add(new ChatRect());

        // 1.10

        add(new PingSpoof());
        add(new MurderMystery());
        add(new Tracers());
        add(new TTFChat());
        add(new Panic());
        add(new NameProtect());

        // 1.11

        add(new AntiBlind());
        add(new Breadcrumbs());
        add(new CameraClip());
        add(new NoBowFOV());
        add(new AutoBow());

        // 1.12

        add(new AutoType());
        add(new Freecam());
        add(new NoSwing());
        add(new Animations());
        add(new HitAnimation());

        for(Module m : this.getModules()) {
            if(!(m.getCategory().equals(Category.Gui) || m.getCategory().equals(Category.Hud))) {
                m.visible = true;
            }
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

    public ArrayList<Module> getModulesInCategory(Category categoryIn) {
        ArrayList<Module> mods = new ArrayList<Module>();
        for(Module m : this.modules){
            if(m.getCategory() == categoryIn)
                mods.add(m);
        }
        mods.sort(Comparator.comparing(Module::getName));
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
            if ((mod.isEnabled() || ((mod.getSlideMC() != 0 || mod.getSlideTTF() != 0) && !mod.isEnabled())) && mod.isVisible()) {
                if (!modules.contains(mod)) {
                    modules.add(mod);
                }
            }
        }
        return modules;
    }

    public boolean get(int i) {
        return false;
    }
}
