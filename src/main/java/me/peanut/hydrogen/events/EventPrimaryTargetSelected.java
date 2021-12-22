package me.peanut.hydrogen.events;

import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.entity.EntityLivingBase;

public class EventPrimaryTargetSelected extends EventCancellable {

    private final EntityLivingBase target;

    public EventPrimaryTargetSelected(EntityLivingBase target) {
        this.target = target;
    }

    public EntityLivingBase getTarget() {
        return target;
    }

}
