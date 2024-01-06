package me.peanut.hydrogen;

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

    public Hydrogen() {
        instance = this;
    }

    public static Hydrogen getInstance() {
        return instance;
    }

    public void start() {
        System.out.println("Hello World!");
    }


}
