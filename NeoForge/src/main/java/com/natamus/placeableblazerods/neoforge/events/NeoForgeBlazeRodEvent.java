package com.natamus.placeableblazerods.neoforge.events;

import com.natamus.placeableblazerods.events.BlazeRodEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class NeoForgeBlazeRodEvent {
	@SubscribeEvent
	public static void onBlockClick(PlayerInteractEvent.RightClickBlock e) {
		BlazeRodEvent.onBlockClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}