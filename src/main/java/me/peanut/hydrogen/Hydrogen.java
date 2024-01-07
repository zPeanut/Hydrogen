package me.peanut.hydrogen;

import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

//
// Created by peanut on 06.01.2024
//
public class Hydrogen {

    public final String name = "Hydrogen";
    public final String version = "1.13";
    public final String[] author = {"mjzpeanut", "UltramoxX"};

    public final String github = "https://github.com/zpeanut/hydrogen/";
    public final String release = github + "releases/";
    public final String tags = release + "tag/" + version + "/";

    private static Hydrogen instance;

    private ModuleManager moduleManager;

    public Hydrogen() {
        instance = this;
    }

    public static Hydrogen getInstance() {
        return instance;
    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public void start() {
        moduleManager = new ModuleManager();
        moduleManager.addModules();

        for (Module module : moduleManager.getModules()) {
            System.out.printf("Loaded %s!", module.getName());
        }

    }



}
