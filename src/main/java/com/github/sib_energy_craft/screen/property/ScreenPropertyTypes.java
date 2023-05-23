package com.github.sib_energy_craft.screen.property;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;

import java.util.Date;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenPropertyTypes {
    public static final ScreenPropertyType<Short> SHORT = new ScreenPropertyType<>(
            PacketByteBuf::readShort,
            (value, buffer) -> buffer.writeShort(value)
    );
    public static final ScreenPropertyType<Integer> INT = new ScreenPropertyType<>(
            PacketByteBuf::readInt,
            (value, buffer) -> buffer.writeInt(value)
    );
    public static final ScreenPropertyType<Float> FLOAT = new ScreenPropertyType<>(
            PacketByteBuf::readFloat,
            (value, buffer) -> buffer.writeFloat(value)
    );
    public static final ScreenPropertyType<Double> DOUBLE = new ScreenPropertyType<>(
            PacketByteBuf::readDouble,
            (value, buffer) -> buffer.writeDouble(value)
    );
    public static final ScreenPropertyType<ItemStack> ITEM_STACK = new ScreenPropertyType<>(
            PacketByteBuf::readItemStack,
            (value, buffer) -> buffer.writeItemStack(value)
    );
    public static final ScreenPropertyType<Date> DATE = new ScreenPropertyType<>(
            PacketByteBuf::readDate,
            (value, buffer) -> buffer.writeDate(value)
    );
    public static final ScreenPropertyType<Boolean> BOOLEAN = new ScreenPropertyType<>(
            PacketByteBuf::readBoolean,
            (value, buffer) -> buffer.writeBoolean(value)
    );
    public static final ScreenPropertyType<String> STRING = new ScreenPropertyType<>(
            PacketByteBuf::readString,
            (value, buffer) -> buffer.writeString(value)
    );
    public static final ScreenPropertyType<Character> CHAR = new ScreenPropertyType<>(
            PacketByteBuf::readChar,
            (value, buffer) -> buffer.writeChar(value)
    );
}
