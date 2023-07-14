package io.github.jadedchara.pentamorph.common;


import io.github.jadedchara.pentamorph.Pentamorph;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.registry.api.event.RegistryMonitor;

import java.util.function.BiFunction;

public final class BlockRegistry {
	//preliminary stuff
	public static void init() {
		RegistryMonitor.create(Registry.BLOCK)
				.filter(context -> {
					var id = context.id();
					return false;
				})
				.forAll(context -> {
					context.register(Pentamorph.id("nest_pulp"), NEST_PULP);
					context.register(Pentamorph.id("calcified_nest_pulp"), CALCIFIED_NEST_PULP);
				});
		RegistryMonitor.create(Registry.ITEM)
				.forAll(context -> {
					//var item = (BlockItem) context.value();
					if ((context.rawId()+2) < blocks.length) {
						Item.BLOCK_ITEMS.put(blocks[context.rawId()+1], context.value());
					}
					//Item.BLOCK_ITEMS.put();
				});
	}

	//registration
	static <T extends Block> T blockitemRegister(String name, T block, Item.Settings settings) {
		return blockitemRegister(name, block, settings, BlockItem::new);
	}

	static <T extends Block> T blockitemRegister(String name, T block, Item.Settings settings, BiFunction<T,
			Item.Settings, BlockItem> factory) {
		itemRegister(name, factory.apply(blockRegister(name, block), settings));
		return block;
	}

	static <T extends Block> T blockRegister(String name, T block) {
		return Registry.register(Registry.BLOCK, Pentamorph.id(name), block);
	}

	static <T extends Item> T itemRegister(String name, T item) {
		var itemization = Registry.register(Registry.ITEM, Pentamorph.id(name), item);
		return itemization;
	}


	//Blocks

	public static final Block NEST_PULP = blockitemRegister("nest_pulp",
			new Block(QuiltBlockSettings.copyOf(Blocks.IRON_BLOCK).mapColor(MapColor.OAK_TAN)),
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);
	public static final Block CALCIFIED_NEST_PULP = blockitemRegister("calcified_nest_pulp",
			new Block(QuiltBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).mapColor(MapColor.IRON_GRAY)),
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);
	public static final Block[] blocks= {NEST_PULP, CALCIFIED_NEST_PULP};
}

