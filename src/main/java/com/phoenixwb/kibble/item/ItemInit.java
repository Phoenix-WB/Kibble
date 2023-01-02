package com.phoenixwb.kibble.item;

import com.phoenixwb.kibble.Kibble;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Kibble.MODID);

	public static final RegistryObject<Item> KIBBLE_ITEM = ITEMS.register("kibble_item",
			() -> new KibbleItem(new Item.Properties().stacksTo(1)));
}
