package com.github.sib_energy_craft.screen.property;

import lombok.AllArgsConstructor;
import net.minecraft.network.PacketByteBuf;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@AllArgsConstructor
public final class ScreenPropertyType<T> {
    private final Function<PacketByteBuf, T> reader;
    private final BiConsumer<T, PacketByteBuf> writer;

    /**
     * Extract property value from buffer
     *
     * @param packetByteBuf packet buffer
     * @return extracted value
     */
    public T read(PacketByteBuf packetByteBuf) {
        return reader.apply(packetByteBuf);
    }

    /**
     * Write property value into buffer
     *
     * @param value value to write
     * @param packetByteBuf packet buffer
     */
    public void write(T value, PacketByteBuf packetByteBuf) {
        writer.accept(value, packetByteBuf);
    }

}
