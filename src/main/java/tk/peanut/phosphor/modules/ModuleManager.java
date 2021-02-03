package tk.peanut.phosphor.modules;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.events.EventKey;
import tk.peanut.phosphor.modules.modules.player.FastPlace;
import tk.peanut.phosphor.modules.modules.render.ClickGUI;
import tk.peanut.phosphor.modules.modules.render.HUD;
import tk.peanut.phosphor.modules.modules.movement.Eagle;
import tk.peanut.phosphor.modules.modules.render.TestModule2;
import tk.peanut.phosphor.modules.modules.render.TestModule3;
import tk.peanut.phosphor.scripting.ScriptModule;

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
        addModule(new HUD());
        addModule(new Eagle());
        addModule(new ClickGUI());
        addModule(new FastPlace());
        addModule(new TestModule2());
        addModule(new TestModule3());

        Collections.sort(Phosphor.getInstance().moduleManager.getModules(), new Comparator<Module>() {
            @Override
            public int compare(Module mod1, Module mod2) {
                if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod1.getName()) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod2.getName())) {
                    return -1;
                }
                if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod1.getName()) < Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod2.getName())) {
                    return 1;
                }
                return 0;
            }
        });

    }

    private void addModule(Module module) {
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
        addModule(module);
    }
}
