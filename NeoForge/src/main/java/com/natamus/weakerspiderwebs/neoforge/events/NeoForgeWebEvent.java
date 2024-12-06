package com.natamus.weakerspiderwebs.neoforge.events;

import com.natamus.weakerspiderwebs.events.WebEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeWebEvent {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post e) {
		Player player = e.getEntity();
		Level level = player.level();
		if (level.isClientSide) {
			return;
		}

		WebEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}
}
