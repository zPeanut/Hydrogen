package me.peanut.hydrogen.module;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import me.peanut.hydrogen.event.EventKeyboard;
import me.peanut.hydrogen.module.impl.movement.Sprint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//
// Created by peanut on 07.01.2024
//
public class ModuleManager {

    private final List<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        EventManager.register(this);
    }

    public void addModules() {
        add(new Sprint());
    }

    private void add(Module module) {
        this.modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public <module extends Module> module getModule(Class<module> moduleClass) {
        return (module) modules.stream().filter(mod -> mod.getClass() == moduleClass).findFirst().orElse(null);
    }

    @EventTarget
    public void onKey(EventKeyboard eventKeyboard) {
        for (Module module : modules) {
            if(module.getBind() == eventKeyboard.getKey()) {
                module.toggle();
            }
        }
    }

    public ArrayList<Module> getTypeModules(Type type) {
        ArrayList<Module> modulesType = new ArrayList<Module>();

        for (Module module : this.modules) {
            if(module.getType() == type) {
                modulesType.add(module);
            }
        }

        modulesType.sort(Comparator.comparing(Module::getNameWithSuffix));
        return modulesType;
    }

    public List<Module> getEnabledModules() {
        List<Module> modulesEnabled = new ArrayList<>();

        for (Module module : this.modules) {
            if((module.isEnabled()) && (module.isVisible())) {
                if(!modulesEnabled.contains(module)) {
                    modulesEnabled.add(module);
                }
            }
        }
        return modulesEnabled;
    }


}
