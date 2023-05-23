package com.github.sib_energy_craft.screen.property;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.github.sib_energy_craft.screen.property.ScreenPropertyTypes.*;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenPropertyRegistry {
    private static final BiMap<Integer, ScreenPropertyType<?>> INDEXED_PROPERTY_TYPE;
    private static final Set<ScreenPropertyType<?>> VALUES;

    static {
        var indexedPropertyTypes = Map.of(
                1, SHORT,
                2, INT,
                3, FLOAT,
                4, DOUBLE,
                5, ITEM_STACK,
                6, DATE,
                7, BOOLEAN,
                8, STRING,
                9, CHAR
        );

        INDEXED_PROPERTY_TYPE = HashBiMap.create(indexedPropertyTypes);
        VALUES = new HashSet<>(INDEXED_PROPERTY_TYPE.values());
    }

    /**
     * Register new type of screen properties into registry
     *
     * @param index property type index
     * @param screenPropertyType screen property type
     */
    public synchronized static void registry(int index, @NotNull ScreenPropertyType<?> screenPropertyType) {
        if(INDEXED_PROPERTY_TYPE.containsKey(index)) {
            throw new IllegalStateException("Property type index %s already registered".formatted(index));
        }
        if(VALUES.contains(screenPropertyType)) {
            throw new IllegalStateException("Property type %s already registered".formatted(screenPropertyType));
        }
        VALUES.add(screenPropertyType);
    }

    /**
     * Get all supported property types
     *
     * @return set of property types
     */
    public static Set<ScreenPropertyType<?>> values() {
        return VALUES;
    }

    /**
     * Get property type index by reference
     *
     * @param type property type
     * @return index of property type
     */
    public static int getIndex(@NotNull ScreenPropertyType<?> type) {
        return INDEXED_PROPERTY_TYPE.inverse().get(type);
    }

    /**
     * Get property type by index
     *
     * @param index property type index
     * @return property type
     */
    public static <T> ScreenPropertyType<T> findValue(int index) {
        return (ScreenPropertyType<T>) INDEXED_PROPERTY_TYPE.get(index);
    }
}
