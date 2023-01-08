package com.phoenixwb.kibble;

import com.phoenixwb.kibble.item.ItemInit;
import com.phoenixwb.kibble.potion.KibbleRecipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Kibble.MODID)
@SuppressWarnings("deprecation")
public class Kibble {
	public static final String MODID = "kibble";

	public Kibble() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ItemInit.ITEMS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::registerTabs);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			for (Potion potion : BuiltInRegistries.POTION) {
				BrewingRecipeRegistry.addRecipe(new KibbleRecipe(potion));
			}
		});
	}

	private void registerTabs(CreativeModeTabEvent.BuildContents event) {
		if (event.getTab() == CreativeModeTabs.FOOD_AND_DRINKS) {
			for (Potion potion : BuiltInRegistries.POTION) {
				if (potion != Potions.EMPTY) {
					event.accept(PotionUtils.setPotion(new ItemStack(ItemInit.KIBBLE_ITEM.get()), potion));
				}
			}
		}
	}
}
