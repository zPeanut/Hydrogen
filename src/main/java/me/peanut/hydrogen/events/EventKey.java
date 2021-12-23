package me.peanut.hydrogen.events;

import com.darkmagician6.eventapi.events.Event;

public class EventKey implements Event {
    private final int key;

    public EventKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
