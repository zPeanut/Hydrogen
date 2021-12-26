package me.peanut.hydrogen.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by peanut on 07/02/2021
 */
@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Info {

    String name();

    String description();

    Category category();

    int keybind() default 0;

}
