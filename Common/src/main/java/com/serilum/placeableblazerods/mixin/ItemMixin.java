package com.serilum.placeableblazerods.mixin;

import com.serilum.placeableblazerods.events.BlazeRodEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Item.class, priority = 1001)
public class ItemMixin {
	@Inject(method = "useOn(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;", at = @At(value = "HEAD"), cancellable = true)
	public void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
		InteractionResult result = BlazeRodEvent.onItemUseOn(context);
		if (!result.equals(InteractionResult.PASS)) {
			cir.setReturnValue(result);
		}
	}
}
