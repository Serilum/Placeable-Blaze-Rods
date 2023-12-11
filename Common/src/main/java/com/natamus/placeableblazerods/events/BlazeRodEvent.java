package com.natamus.placeableblazerods.events;

import com.natamus.placeableblazerods.data.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BlazeRodEvent {
	public static boolean onBlockClick(Level world, Player player, InteractionHand hand, BlockPos pos, BlockHitResult hitVec) {
		if (world.isClientSide) {
			return true;
		}

		ItemStack handstack = player.getItemInHand(hand);
		if (!handstack.getItem().equals(Items.BLAZE_ROD)) {
			return true;
		}

		BlockPos placepos = pos.relative(hitVec.getDirection());
		BlockState targetstate = world.getBlockState(placepos);
		if (!targetstate.getBlock().equals(Blocks.AIR)) {
			return true;
		}

		Direction direction = hitVec.getDirection();
		BlockState blockState = world.getBlockState(placepos.relative(direction.getOpposite()));

		BlockState newstate;
		if (blockState.is(Constants.BLAZE_ROD_BLOCK) && blockState.getValue(DirectionalBlock.FACING) == direction)
			newstate = Constants.BLAZE_ROD_BLOCK.defaultBlockState().setValue(DirectionalBlock.FACING, direction.getOpposite());
		else {
			newstate = Constants.BLAZE_ROD_BLOCK.defaultBlockState().setValue(DirectionalBlock.FACING, direction);
		}

		world.setBlock(placepos, newstate, 2);

		if (!player.isCreative()) {
			handstack.shrink(1);
		}

		player.swing(hand);
		return true;
	}
}