package com.natamus.placeableblazerods;

import com.natamus.collective.check.RegisterMod;
import com.natamus.placeableblazerods.neoforge.events.NeoForgeBlazeRodEvent;
import com.natamus.placeableblazerods.util.Reference;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Reference.MOD_ID)
public class ModNeoForge {

	public ModNeoForge(IEventBus modEventBus) {
		modEventBus.addListener(this::loadComplete);

		setGlobalConstants();
		ModCommon.init();
		ModCommon.registerAssets(modEventBus);

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		NeoForge.EVENT_BUS.register(NeoForgeBlazeRodEvent.class);

		ModCommon.setAssets();
	}

	private static void setGlobalConstants() {

	}
}