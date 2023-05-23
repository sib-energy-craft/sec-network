package com.github.sib_energy_craft.network;

import com.github.sib_energy_craft.screen.property.TypedScreenProperty;
import lombok.AllArgsConstructor;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@AllArgsConstructor
public class PropertyUpdateSyncer implements Runnable {
    private final int syncId;
    private final ServerPlayerEntity serverPlayerEntity;
    private final List<TypedScreenProperty<?>> properties;

    @Override
    public synchronized void run() {
        for (var property : properties) {
            var packet = createPacket(property);
            var packetByteBuf = PacketByteBufs.create();
            packet.write(packetByteBuf);
            ServerPlayNetworking.send(serverPlayerEntity, NetworkPackets.UPDATE_SCREEN_TYPED_PROPERTY, packetByteBuf);
        }
    }

    @NotNull
    private<T> ScreenHandlerTypedPropertyUpdateS2CPacket<T> createPacket(@NotNull TypedScreenProperty<T> property) {
        return new ScreenHandlerTypedPropertyUpdateS2CPacket<>(
                syncId,
                property.id(),
                property.type(),
                property.value()
        );
    }
}
