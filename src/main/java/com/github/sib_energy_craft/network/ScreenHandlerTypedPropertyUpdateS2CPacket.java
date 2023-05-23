package com.github.sib_energy_craft.network;

import com.github.sib_energy_craft.screen.property.ScreenPropertyRegistry;
import com.github.sib_energy_craft.screen.property.ScreenPropertyType;
import lombok.Getter;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.NotNull;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@Getter
public class ScreenHandlerTypedPropertyUpdateS2CPacket<T> implements Packet<ClientPlayPacketListener> {
    private final int syncId;
    private final int propertyId;
    private final ScreenPropertyType<T> propertyType;
    private final T propertyValue;

    public ScreenHandlerTypedPropertyUpdateS2CPacket(int syncId,
                                                     int propertyId,
                                                     @NotNull ScreenPropertyType<T> screenPropertyType,
                                                     @NotNull T propertyValue) {
        this.syncId = syncId;
        this.propertyId = propertyId;
        this.propertyType = screenPropertyType;
        this.propertyValue = propertyValue;
    }

    public ScreenHandlerTypedPropertyUpdateS2CPacket(@NotNull PacketByteBuf buf) {
        this.syncId = buf.readUnsignedByte();
        this.propertyId = buf.readShort();
        int propertyTypeIndex = buf.readShort();
        this.propertyType = ScreenPropertyRegistry.findValue(propertyTypeIndex);
        this.propertyValue = propertyType.read(buf);
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeByte(syncId);
        buf.writeShort(propertyId);
        int index = ScreenPropertyRegistry.getIndex(propertyType);
        buf.writeShort(index);
        propertyType.write(propertyValue, buf);
    }

    @Override
    public void apply(ClientPlayPacketListener listener) {

    }
}
