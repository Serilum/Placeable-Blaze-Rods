package com.natamus.placeableblazerods;

import com.natamus.collective.check.RegisterMod;
import com.natamus.placeableblazerods.blocks.BlazeRodBlock;
import com.natamus.placeableblazerods.data.Constants;
import com.natamus.placeableblazerods.neoforge.events.NeoForgeBlazeRodEvent;
import com.natamus.placeableblazerods.util.Reference;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Reference.MOD_ID)
public class ModNeoForge {

	private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Reference.MOD_ID);

	public static final DeferredBlock<Block> BLAZE_ROD_BLOCK_OBJECT = BLOCKS.register("blaze_rod", () -> new BlazeRodBlock(BlockBehaviour.Properties.of().strength(0.0F).lightLevel((p_235454_0_) -> { return 14; }).sound(SoundType.WOOD).noOcclusion()));

	public static Block BLAZE_ROD_BLOCK;
	
	public ModNeoForge(IEventBus modEventBus) {
		modEventBus.addListener(this::loadComplete);

		BLOCKS.register(modEventBus);

		setGlobalConstants();
		ModCommon.init();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		Constants.BLAZE_ROD_BLOCK = (BlazeRodBlock)BLAZE_ROD_BLOCK_OBJECT.get();

		NeoForge.EVENT_BUS.register(NeoForgeBlazeRodEvent.class);
	}

	private static void setGlobalConstants() {

	}
}