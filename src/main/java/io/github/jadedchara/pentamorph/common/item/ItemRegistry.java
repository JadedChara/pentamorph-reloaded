package io.github.jadedchara.pentamorph.common.item;

import io.github.jadedchara.pentamorph.Pentamorph;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.lang.Thread;

public class ItemRegistry {
	private static final Map<Identifier, List<ItemConvertible>> itemsByGroup = new LinkedHashMap<>();

	public static void init(){
		//
	}
	public static <T extends Item> void registerItem(T item, String name, ItemGroup... groups) {
		Registry.register(Registries.ITEM, Pentamorph.id(name), item);

		//for (ItemGroup group : groups) {
		//	itemsByGroup.computeIfAbsent(group., g -> new ArrayList<>()).add(item);
		//}
	}
}
