package com.github.sib_energy_craft.network;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.util.Identifier;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NetworkPackets {
    public static final Identifier UPDATE_SCREEN_TYPED_PROPERTY;

    static {
        UPDATE_SCREEN_TYPED_PROPERTY = Identifiers.of("update_screen_type_property");
    }
}
