package com.natamus.weakerspiderwebs.forge.events;

import com.natamus.weakerspiderwebs.events.WebEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeWebEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeWebEvent.class);

		PlayerTickEvent.Post.BUS.addListener(ForgeWebEvent::onPlayerTick);
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post e) {
		Player player = e.player;
		Level level = player.level();
		if (level.isClientSide) {
			return;
		}

		WebEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}
}
