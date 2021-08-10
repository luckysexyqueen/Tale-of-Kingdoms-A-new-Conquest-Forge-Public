package com.convallyria.taleofkingdoms.client.packet.outgoing;

import com.convallyria.taleofkingdoms.TaleOfKingdoms;
import com.convallyria.taleofkingdoms.client.packet.ClientPacketHandler;
import com.convallyria.taleofkingdoms.common.packet.context.PacketContext;
import io.netty.buffer.Unpooled;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class OutgoingHunterPacketHandler extends ClientPacketHandler {

    public OutgoingHunterPacketHandler() {
        super(TaleOfKingdoms.HUNTER_PACKET_ID);
    }

    @Override
    public void handleIncomingPacket(ResourceLocation identifier, PacketContext context, FriendlyByteBuf attachedData) {
        throw new IllegalArgumentException("Not supported");
    }

    @Override
    public void handleOutgoingPacket(ResourceLocation identifier, @NotNull Player player,
                                     @Nullable Connection connection, @Nullable Object... data) {
        FriendlyByteBuf passedData = new FriendlyByteBuf(Unpooled.buffer());
        passedData.writeBoolean((Boolean) data[0]); // False if hiring, true if retiring
        sendPacket(player, passedData);
    }
}
