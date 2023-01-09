package com.phoenixwb.kibble.item;

import com.phoenixwb.kibble.Kibble;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class KibbleItemTags {
	public static final TagKey<Item> VEGETABLES = createItemTag("vegetables");

	private static TagKey<Item> createItemTag(String name) {
		return ItemTags.create(new ResourceLocation(Kibble.MODID, name));
	}
}
