package org.confluence.mod.network;

import net.minecraft.network.FriendlyByteBuf;

public record PlayerFlyPacketS2C(int maxFlyTicks, double maxFlySpeed) {
    public static void encode(PlayerFlyPacketS2C packet, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(packet.maxFlyTicks);
        friendlyByteBuf.writeDouble(packet.maxFlySpeed);
    }

    public static PlayerFlyPacketS2C decode(FriendlyByteBuf friendlyByteBuf) {
        return new PlayerFlyPacketS2C(friendlyByteBuf.readInt(), friendlyByteBuf.readDouble());
    }
}