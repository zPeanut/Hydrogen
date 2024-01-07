package me.peanut.hydrogen.module;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.awt.*;

//
// Created by peanut on 07.01.2024
//
public class Module {

    private String name;

    private String suffix;
    private String desc;
    private Type type;
    private int bind;

    private boolean visible;
    private boolean enabled;

    public Minecraft mc = Minecraft.getMinecraft();

    public Module() {
        this.name = this.getClass().getAnnotation(ModuleInfo.class).name();
        this.desc = this.getClass().getAnnotation(ModuleInfo.class).desc();
        this.type = this.getClass().getAnnotation(ModuleInfo.class).type();
        this.bind = this.getClass().getAnnotation(ModuleInfo.class).bind();
    }

    public String getName() {
        return name;
    }

    public String getNameWithSuffix() {
        return name + " | " + suffix;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public void unbind() {
        this.bind = Keyboard.KEY_NONE;
    }

    public Color getColor() {

        Color colorCombat = new Color(255, 219, 171);
        Color colorMovement = new Color(173, 234, 255);
        Color colorPlayer = new Color(252, 255, 199);
        Color colorRender = new Color(199, 255, 201);
        Color colorDefault = Color.WHITE;

        switch(this.getType()) {
            case COMBAT:
                return colorCombat;
            case MOVEMENT:
                return colorMovement;
            case PLAYER:
                return colorPlayer;
            case RENDER:
                return colorRender;
            case UI:
            default:
                return colorDefault;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void toggle() {
        this.enabled = !this.enabled;

        if (this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void setEnabled() {
        this.enabled = true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setDisabled() {
        this.enabled = false;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void onEnable() {
        EventManager.register(this);
    }

    public void onDisable() {
        EventManager.unregister(this);
    }
}
