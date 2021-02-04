package tk.peanut.phosphor.modules;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.phosphor.events.EventKey;
import tk.peanut.phosphor.modules.modules.movement.Sprint;
import tk.peanut.phosphor.modules.modules.player.FastPlace;
import tk.peanut.phosphor.modules.modules.render.Chams;
import tk.peanut.phosphor.modules.modules.render.ClickGUI;
import tk.peanut.phosphor.modules.modules.render.HUD;
import tk.peanut.phosphor.modules.modules.movement.Eagle;
import tk.peanut.phosphor.scripting.ScriptModule;

import java.util.ArrayList;
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
            if(mod.isEnabled() || (mod.getSlide() != 0 && !mod.isEnabled())) {
                modules.add(mod);
            }
        }
        return modules;
    }

    public boolean get(int i) {
        // TODO Auto-generated method stub
        return false;
    }

    public void addScriptModule(ScriptModule module) {
        add(module);
    }
}
