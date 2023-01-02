package com.phoenixwb.kibble.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

public class KibbleItem extends PotionItem {
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
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		return InteractionResultHolder.pass(player.getItemInHand(hand));
	}
}
