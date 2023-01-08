package com.phoenixwb.kibble.potion;

import com.phoenixwb.kibble.item.ItemInit;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class KibbleRecipe implements IBrewingRecipe {
	private Potion potion;

	public KibbleRecipe(Potion potion) {
		this.potion = potion;
	}

	@Override
	public boolean isInput(ItemStack input) {
		return input.getItem() == ItemInit.KIBBLE_ITEM.get();
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ingredient.getItem() == Items.POTION && PotionUtils.getPotion(ingredient) == potion;
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (!this.isInput(input) || !this.isIngredient(ingredient)) {
			return ItemStack.EMPTY;
		}

		ItemStack pot = ItemInit.KIBBLE_ITEM.get().getDefaultInstance();
		pot.setTag(new CompoundTag());
		PotionUtils.setPotion(pot, potion);

		return pot;
	}
}
