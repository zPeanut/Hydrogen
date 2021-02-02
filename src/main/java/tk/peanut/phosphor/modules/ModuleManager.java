package tk.peanut.phosphor.modules;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.phosphor.events.EventKey;
import tk.peanut.phosphor.modules.modules.fun.DemoModeModule;
import tk.peanut.phosphor.modules.modules.movement.SetbackDetector;
import tk.peanut.phosphor.modules.modules.render.HUD;
import tk.peanut.phosphor.scripting.ScriptModule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    @NotNull
    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        EventManager.register(this);
    }


    public void addModules() {
        addModule(new DemoModeModule());
        addModule(new SetbackDetector());
        addModule(new HUD());
    }

    private void addModule(@NotNull Module module) {
        modules.add(module);
        EventManager.register(module);
    }

    @NotNull
    public List<Module> getModules() {
        return modules;
    }

    @NotNull
    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) modules.stream().filter(mod -> mod.getClass() == clazz).findFirst().orElse(null);
    }

    public Module getModule(@NotNull String name, boolean caseSensitive) {
        return modules.stream().filter(mod -> !caseSensitive && name.equalsIgnoreCase(mod.getName()) || name.equals(mod.getName())).findFirst().orElse(null);
    }

    @EventTarget
    private void onKey(@NotNull EventKey event) {
        for (Module module : modules) if (module.getKeybind() == event.getKey()) module.setState(!module.getState());
    }

    public void addScriptModule(ScriptModule module) {
        addModule(module);
    }
}
