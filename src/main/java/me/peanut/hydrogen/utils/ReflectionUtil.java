package me.peanut.hydrogen.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

/**
 * Created by peanut on 05/02/2021
 */
public class ReflectionUtil {

    public static final Field delayTimer = ReflectionHelper.findField(Minecraft.class, "field_71467_ac", "rightClickDelayTimer");
    public static final Field running = ReflectionHelper.findField(Minecraft.class, "field_71425_J", "running");

    public static final Field pressed = ReflectionHelper.findField(KeyBinding.class, "field_74513_e", "pressed");

    public static final Field theShaderGroup = ReflectionHelper.findField(EntityRenderer.class, "field_147707_d", "theShaderGroup");

    public static final Field listShaders = ReflectionHelper.findField(ShaderGroup.class, "field_148031_d", "listShaders");

    static {
        delayTimer.setAccessible(true);
        running.setAccessible(true);
        pressed.setAccessible(true);
        theShaderGroup.setAccessible(true);
        listShaders.setAccessible(true);


    }
}

