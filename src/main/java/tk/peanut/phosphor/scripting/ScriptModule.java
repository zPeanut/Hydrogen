package tk.peanut.phosphor.scripting;


import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.phosphor.events.EventMotionUpdate;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.modules.ModuleCategory;
import tk.peanut.phosphor.scripting.runtime.events.ScriptMotionUpdateEvent;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class ScriptModule extends Module {
    private ScriptEngine engine;

    ScriptModule(String name, String desc, ModuleCategory category) {
        super(name, desc, category);
    }

    public void setScriptEngine(ScriptEngine scriptEngine) {
        engine = scriptEngine;
    }


    @EventTarget
    public void onRender2D(EventRender2D event) {
        if (!getState()) return;
        try {
            ((Invocable) engine).invokeFunction("onRender2D");
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException ignored) {
        }
    }

    @EventTarget
    public void onMotionUpdate(EventMotionUpdate event) {
        if (!getState()) return;
        ScriptMotionUpdateEvent ev = new ScriptMotionUpdateEvent(event.getEventType(), event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround());

        try {
            ((Invocable) engine).invokeFunction("onMotionUpdate", ev);
        } catch (NoSuchMethodException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        ev.apply(event);
    }
}
