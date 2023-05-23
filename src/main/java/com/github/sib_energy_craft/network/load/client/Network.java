package com.github.sib_energy_craft.network.load.client;

import com.github.sib_energy_craft.network.ScreenHandlerTypedPropertyUpdateS2CPacket;
import com.github.sib_energy_craft.screen.TypedPropertyScreenHandler;
import com.github.sib_energy_craft.sec_utils.load.DefaultClientModInitializer;
import lombok.extern.slf4j.Slf4j;

import static com.github.sib_energy_craft.network.NetworkPackets.UPDATE_SCREEN_TYPED_PROPERTY;
import static net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.registerGlobalReceiver;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@Slf4j
public final class Network implements DefaultClientModInitializer {
    static {
        registerGlobalReceiver(UPDATE_SCREEN_TYPED_PROPERTY, (client, handler, buf, responseSender) -> {
            try {
                var playerEntity = client.player;
                if (playerEntity == null) {
                    return;
                }
                var currentScreenHandler = playerEntity.currentScreenHandler;

                if (!(currentScreenHandler instanceof TypedPropertyScreenHandler propertyListener)) {
                    return;
                }

                var packet = new ScreenHandlerTypedPropertyUpdateS2CPacket<>(buf);
                if (currentScreenHandler.syncId == packet.getSyncId()) {
                    propertyListener.onTypedPropertyChanged(packet.getPropertyId(), packet.getPropertyValue());
                }
            } catch (Exception e) {
                log.error("Int property process error", e);
            }
        });
    }
}
