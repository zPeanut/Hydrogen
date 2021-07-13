/*
 * Copyright (C) 2019 TheAltening
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.thealtening.auth.service;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author Vladymyr
 * @since 10/08/2019
 */
public final class FieldAdapter {

    private final HashMap<String, MethodHandle> fields = new HashMap<>();
    private static final MethodHandles.Lookup LOOKUP;
    private static Field MODIFIERS;

    public FieldAdapter(String parent) {
        try {
            Class cls = Class.forName(parent);
            Field modifiers = FieldAdapter.MODIFIERS;
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                int accessFlags = field.getModifiers();
                if (Modifier.isFinal(accessFlags)) {
                    modifiers.setInt(field, accessFlags & ~Modifier.FINAL);
                }

                MethodHandle handler = LOOKUP.unreflectSetter(field);
                handler = handler.asType(handler.type().generic().changeReturnType(void.class));
                fields.put(field.getName(), handler);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load/find the specified class");
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create a method handler for the field");
        }
    }

    public void updateFieldIfPresent(String name, Object newValue) {
        Optional.ofNullable(fields.get(name)).ifPresent(setter -> {
            try {
                setter.invokeExact(newValue);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }

    static {
        try {
            MODIFIERS = Field.class.getDeclaredField("modifiers");
            MODIFIERS.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        MethodHandles.Lookup lookupObject;
        try {
            Field lookupImplField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            lookupImplField.setAccessible(true);
            lookupObject = (MethodHandles.Lookup) lookupImplField.get(null);
        } catch (ReflectiveOperationException e) {
            lookupObject = MethodHandles.lookup();
        }

        LOOKUP = lookupObject;
    }
}
