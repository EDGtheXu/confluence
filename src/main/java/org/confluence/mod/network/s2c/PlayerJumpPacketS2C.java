package org.confluence.mod.network.s2c;

import net.minecraft.network.FriendlyByteBuf;

public record PlayerJumpPacketS2C(int maxJumpCount, double multiY, boolean fart) {
    public static void encode(PlayerJumpPacketS2C packet, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(packet.maxJumpCount);
        friendlyByteBuf.writeDouble(packet.multiY);
        friendlyByteBuf.writeBoolean(packet.fart);
    }

    public static PlayerJumpPacketS2C decode(FriendlyByteBuf friendlyByteBuf) {
        return new PlayerJumpPacketS2C(friendlyByteBuf.readInt(), friendlyByteBuf.readDouble(), friendlyByteBuf.readBoolean());
    }
}
