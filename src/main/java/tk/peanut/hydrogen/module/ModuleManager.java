package tk.peanut.hydrogen.module;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.hydrogen.events.EventKey;
import tk.peanut.hydrogen.module.modules.combat.AutoClicker;
import tk.peanut.hydrogen.module.modules.combat.TriggerBot;
import tk.peanut.hydrogen.module.modules.movement.Sprint;
import tk.peanut.hydrogen.module.modules.player.ChestStealer;
import tk.peanut.hydrogen.module.modules.player.FastPlace;
import tk.peanut.hydrogen.module.modules.player.InventoryWalk;
import tk.peanut.hydrogen.module.modules.player.SafeWalk;
import tk.peanut.hydrogen.module.modules.render.*;
import tk.peanut.hydrogen.module.modules.movement.Eagle;

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
        add(new HUD());
        add(new Eagle());
        add(new ClickGUI());
        add(new FastPlace());
        add(new Sprint());
        add(new Chams());
        add(new AutoClicker());
        add(new ESP());
        add(new TriggerBot());
        add(new NoBob());
        add(new Fullbright());
        add(new SafeWalk());
        add(new NameTags());
        add(new ChestStealer());
        add(new InventoryWalk());

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
            if(mod.isEnabled() || (mod.getSlide() != 0 && !mod.isEnabled()) && !(mod.getName().equalsIgnoreCase("hud"))) {
                modules.add(mod);
            }
        }
        return modules;
    }

    public boolean get(int i) {
        // TODO Auto-generated method stub
        return false;
    }
}
