package com.convallyria.taleofkingdoms.server.commands.debug;

import com.convallyria.taleofkingdoms.TaleOfKingdoms;
import com.convallyria.taleofkingdoms.common.world.ConquestInstance;
import com.convallyria.taleofkingdoms.server.world.ServerConquestInstance;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.EnvType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.UUID;

public class TaleOfKingdomsAddCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> commandContext) throws CommandSyntaxException {
        return 1;
    }

    public static int addCoins(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ConquestInstance instance = TaleOfKingdoms.getAPI().getConquestInstanceStorage().mostRecentInstance().get();
        ServerPlayerEntity player = context.getSource().getPlayer();
        UUID playerUuid = player.getUuid();

        instance.addCoins(playerUuid, context.getArgument("coins", Integer.class));
        if (TaleOfKingdoms.getAPI().getEnvironment() == EnvType.SERVER) {
            ServerConquestInstance.sync(player, instance);
        }

        player.sendMessage(Text.literal("Your new balance is: " + instance.getCoins(playerUuid)), false);
        return 1;
    }

    public static int addWorthiness(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ConquestInstance instance = TaleOfKingdoms.getAPI().getConquestInstanceStorage().mostRecentInstance().get();
        ServerPlayerEntity player = context.getSource().getPlayer();
        UUID playerUuid = player.getUuid();

        instance.addWorthiness(playerUuid, context.getArgument("worthiness", Integer.class));
        if (TaleOfKingdoms.getAPI().getEnvironment() == EnvType.SERVER) {
            ServerConquestInstance.sync(player, instance);
        }

        player.sendMessage(Text.literal("Your new worthiness is: " + instance.getWorthiness(playerUuid)), false);
        return 1;
    }
}
