package com.phoenixwb.kibble.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

public class KibbleItem extends Item {
	public KibbleItem(Properties p_42979_) {
		super(p_42979_);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity,
			InteractionHand hand) {
		if (entity instanceof Animal) {
			if (!player.getLevel().isClientSide() && entity.isAlive()) {
				for (MobEffectInstance effect : PotionUtils.getMobEffects(stack)) {
					if (effect.getEffect().isInstantenous()) {
						effect.getEffect().applyInstantenousEffect(entity, entity, entity, effect.getAmplifier(), 1.0D);
					} else {
						entity.addEffect(new MobEffectInstance(effect));
					}
				}
				stack.shrink(1);
				return InteractionResult.CONSUME;
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public void appendHoverText(ItemStack p_42988_, @Nullable Level p_42989_, List<Component> p_42990_,
			TooltipFlag p_42991_) {
		PotionUtils.addPotionTooltip(p_42988_, p_42990_, 1.0F);
	}

	@Override
	public boolean isFoil(ItemStack p_42999_) {
		return super.isFoil(p_42999_) || PotionUtils.getPotion(p_42999_).isFoil(p_42999_);
	}
}
