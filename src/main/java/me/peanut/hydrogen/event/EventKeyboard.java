package me.peanut.hydrogen.event;

import com.darkmagician6.eventapi.events.Event;

//
// Created by peanut on 07.01.2024
//
public class EventKeyboard implements Event {

    int key;

    public EventKeyboard(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
