package com.natamus.placeableblazerods;


import com.natamus.collective.services.Services;
import com.natamus.placeableblazerods.blocks.BlazeRodBlock;
import com.natamus.placeableblazerods.data.Constants;
import com.natamus.placeableblazerods.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModCommon {

	public static void init() {
		load();
	}

	private static void load() {

	}

	public static void registerAssets(Object modEventBusObject) {
		Services.REGISTERBLOCK.registerBlockWithoutItem(modEventBusObject, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "blaze_rod"), (properties) -> new BlazeRodBlock(properties), BlockBehaviour.Properties.of().strength(0.0F).lightLevel((blockState) -> { return 14; }).sound(SoundType.WOOD).noOcclusion(), true);
	}

	public static void setAssets() {
		Constants.BLAZE_ROD_BLOCK = (BlazeRodBlock)Services.REGISTERBLOCK.getRegisteredBlockWithoutItem(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "blaze_rod"));
	}
}