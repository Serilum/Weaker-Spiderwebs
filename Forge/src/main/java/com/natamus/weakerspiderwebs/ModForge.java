package com.natamus.weakerspiderwebs;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.natamus.weakerspiderwebs.forge.config.IntegrateForgeConfig;
import com.natamus.weakerspiderwebs.forge.events.ForgeWebEvent;
import com.natamus.weakerspiderwebs.util.Reference;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class ModForge {
	
	public ModForge(FMLJavaModLoadingContext modLoadingContext) {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		BusGroup busGroup = modLoadingContext.getModBusGroup();
		FMLLoadCompleteEvent.getBus(busGroup).addListener(this::loadComplete);

		setGlobalConstants();
		ModCommon.init();

		IntegrateForgeConfig.registerScreen(modLoadingContext);

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
    	ForgeWebEvent.registerEventsInBus();
	}

	private static void setGlobalConstants() {

	}
}