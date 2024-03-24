package org.confluence.mod.network;

import net.minecraft.network.FriendlyByteBuf;

public record PlayerJumpPacketS2C(int maxJump) {
    public static void encode(PlayerJumpPacketS2C packet, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(packet.maxJump);
    }

    public static PlayerJumpPacketS2C decode(FriendlyByteBuf friendlyByteBuf) {
        return new PlayerJumpPacketS2C(friendlyByteBuf.readInt());
    }
}