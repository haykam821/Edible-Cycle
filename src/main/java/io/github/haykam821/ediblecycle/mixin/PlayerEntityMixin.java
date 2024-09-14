package io.github.haykam821.ediblecycle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
	@Redirect(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;copy()Lnet/minecraft/item/ItemStack;"))
	private ItemStack ediblecycle$fixUsingConvertsToStackComponents(ItemStack usingConvertsTo) {
		return usingConvertsTo.withItem(usingConvertsTo.getItem());
	}
}
