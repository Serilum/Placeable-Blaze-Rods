package com.natamus.placeableblazerods.forge.events;

import com.natamus.placeableblazerods.events.BlazeRodEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForgeBlazeRodEvent {
	@SubscribeEvent
	public void onBlockClick(PlayerInteractEvent.RightClickBlock e) {
		BlazeRodEvent.onBlockClick(e.getWorld(), e.getPlayer(), e.getHand(), e.getPos(), e.getHitVec());
	}
}