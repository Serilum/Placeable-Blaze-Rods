package com.natamus.placeableblazerods.forge.events;

import com.natamus.placeableblazerods.events.BlazeRodEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

public class ForgeBlazeRodEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeBlazeRodEvent.class);

		PlayerInteractEvent.RightClickBlock.BUS.addListener(ForgeBlazeRodEvent::onBlockClick);
	}

	@SubscribeEvent
	public static void onBlockClick(PlayerInteractEvent.RightClickBlock e) {
		BlazeRodEvent.onBlockClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}