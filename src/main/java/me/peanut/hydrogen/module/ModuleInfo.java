package me.peanut.hydrogen.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//
// Created by peanut on 07.01.2024
//
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {

    String name();
    String desc();
    Type type();
    int bind() default 0;

}
