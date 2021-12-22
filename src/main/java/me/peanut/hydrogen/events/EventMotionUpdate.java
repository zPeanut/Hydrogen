package me.peanut.hydrogen.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.types.EventType;

public class EventMotionUpdate implements Event {
    private final EventType eventType;
    private double x;
    private double y;
    private double z;

    private float yaw;
    private float pitch;

    private boolean onGround;

    public EventMotionUpdate(EventType eventType, double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.eventType = eventType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public EventType getEventType() {
        return eventType;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
