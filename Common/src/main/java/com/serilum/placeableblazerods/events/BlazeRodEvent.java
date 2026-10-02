package com.serilum.placeableblazerods.events;

import com.serilum.placeableblazerods.data.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlazeRodEvent {
	public static InteractionResult onItemUseOn(UseOnContext context) {
		ItemStack handstack = context.getItemInHand();
		if (!handstack.getItem().equals(Items.BLAZE_ROD)) {
			return InteractionResult.PASS;
		}

		Player player = context.getPlayer();
		if (player == null) {
			return InteractionResult.PASS;
		}

		Level level = context.getLevel();
		Direction direction = context.getClickedFace();
		BlockPos pos = context.getClickedPos();

		BlockPos placepos = pos.relative(direction);
		BlockState targetstate = level.getBlockState(placepos);
		if (!targetstate.getBlock().equals(Blocks.AIR)) {
			return InteractionResult.PASS;
		}

		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}

		BlockState blockState = level.getBlockState(pos);

		BlockState defaultBlazeRodState = Constants.BLAZE_ROD_BLOCK.defaultBlockState();

		BlockState newstate;
		if (blockState.is(Constants.BLAZE_ROD_BLOCK) && blockState.getValue(DirectionalBlock.FACING) == direction)
			newstate = defaultBlazeRodState.setValue(DirectionalBlock.FACING, direction.getOpposite());
		else {
			newstate = defaultBlazeRodState.setValue(DirectionalBlock.FACING, direction);
		}

		level.setBlock(placepos, newstate, 2);

		if (!player.isCreative()) {
			handstack.shrink(1);
		}

		level.playSound(null, player.getX(), player.getY(), player.getZ(), defaultBlazeRodState.getSoundType().getPlaceSound(), SoundSource.NEUTRAL, 1.0F, 1.0F);
		return InteractionResult.CONSUME;
	}
}
