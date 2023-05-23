package com.github.sib_energy_craft.screen;

/**
 * @author sibmaks
 * @since 0.0.1
 */
public interface TypedPropertyScreenHandler {
    /**
     * Handle changing of typed property value
     *
     * @param index property index
     * @param value property value
     */
    <V> void onTypedPropertyChanged(int index, V value);

}
