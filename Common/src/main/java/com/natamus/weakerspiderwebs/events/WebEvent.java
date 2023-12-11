package com.natamus.weakerspiderwebs.events;

import com.natamus.collective.functions.TaskFunctions;
import com.natamus.weakerspiderwebs.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class WebEvent {
	private static final Map<String, List<BlockPos>> todestroy = new HashMap<String, List<BlockPos>>();
	
	public static void onPlayerTick(ServerLevel world, ServerPlayer player) {
		if (player.isSpectator()) {
			return;
		}

		String playername = player.getName().getString();
		
		if (todestroy.get(playername) == null) {
			todestroy.put(playername, new ArrayList<BlockPos>());
		}
		else if (todestroy.get(playername).size() > 0) {
			List<BlockPos> tdcopy = Collections.unmodifiableList(todestroy.get(playername));
			todestroy.put(playername, new ArrayList<BlockPos>());
			for (BlockPos td : tdcopy) {
				try {
					world.destroyBlock(td, true);
				}
				catch (NullPointerException ignored) { }
			}
		}
		
		if (player.tickCount % 5 != 0) {
			return;
		}
		
		Vec3 pvec = player.position();
		int ypos = (int)Math.ceil(pvec.y);
		BlockPos pos = new BlockPos(pvec.x, ypos, pvec.z);
		
		if (world.getBlockState(pos.below()).getBlock() instanceof WebBlock || world.getBlockState(pos).getBlock() instanceof WebBlock || world.getBlockState(pos.above()).getBlock() instanceof WebBlock) {
			TaskFunctions.enqueueCollectiveTask(world.getServer(), () -> {
				BlockPos nowpos = player.blockPosition().immutable();
				if (pos.getX() != nowpos.getX() || pos.getZ() != nowpos.getZ()) {
					return;
				}
				if (world.getBlockState(pos.below()).getBlock() instanceof WebBlock) {
					todestroy.get(playername).add(pos.below().immutable());
				}
				if (world.getBlockState(pos).getBlock() instanceof WebBlock) {
					todestroy.get(playername).add(pos.immutable());
				}
				if (world.getBlockState(pos.above()).getBlock() instanceof WebBlock) {
					todestroy.get(playername).add(pos.above().immutable());
				}
			}, (ConfigHandler.breakSpiderwebDelay/1000)*20);
		}
	}
}