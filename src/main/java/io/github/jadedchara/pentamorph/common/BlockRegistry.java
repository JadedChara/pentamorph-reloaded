package io.github.jadedchara.pentamorph.common;


import io.github.jadedchara.pentamorph.Pentamorph;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.registry.api.event.RegistryMonitor;

import java.util.ArrayList;
import java.util.function.BiFunction;

public final class BlockRegistry {
	//preliminary stuff

	/*public static ItemGroup PENTAMORPH_BLOCKS = FabricItemGroup.builder()
			.name(Text.of(""))
			.icon(()->new ItemStack(Items.NETHERITE_INGOT))
			.entries((params, col)->{
				//col.addItem(Item.BLOCK_ITEMS.get());
				//col.addItem();
			})
			.build();*/
	public static void init() {

		RegistryMonitor.create(Registries.BLOCK)
				.filter(context -> {
					var id = context.id();
					return false;
				})
				.forAll(context -> {
					context.register(Pentamorph.id("nest_pulp"), NEST_PULP);
					context.register(Pentamorph.id("calcified_nest_pulp"), CALCIFIED_NEST_PULP);
				});
		ArrayList<Item> blocklist = new ArrayList<>();
		RegistryMonitor.create(Registries.ITEM)
				.forAll(context -> {
					if ((context.rawId()+2) < blocks.length) {
						Item.BLOCK_ITEMS.put(blocks[context.rawId()+1], context.value());
					}
				});

		ItemGroup PENTAMORPH_BLOCKS = FabricItemGroup.builder()
				.name(Text.of("Pentamorph Blocks"))
				.icon(()->new ItemStack(NEST_PULP))
				.entries((params, col)->{
					col.addItem(NEST_PULP);
					col.addItem(CALCIFIED_NEST_PULP);
				})
				.build();
		Registry.register(Registries.ITEM_GROUP, PENTAMORPH_BLOCKS_KEY, PENTAMORPH_BLOCKS);
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
		return Registry.register(Registries.BLOCK, Pentamorph.id(name), block);
	}
	static <T extends Item> T itemRegister(String name, T item) {
		var itemization = Registry.register(Registries.ITEM, Pentamorph.id(name), item);
		return itemization;
	}


	//Blocks

	public static final Block NEST_PULP = blockitemRegister("nest_pulp",
			new Block(QuiltBlockSettings.copyOf(Blocks.IRON_BLOCK).mapColor(MapColor.BROWN_TERRACOTTA)),
			new QuiltItemSettings()
	);
	public static final Block CALCIFIED_NEST_PULP = blockitemRegister("calcified_nest_pulp",
			new Block(QuiltBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).mapColor(MapColor.METAL)),
			new QuiltItemSettings()
	);
	public static final Block[] blocks= {NEST_PULP, CALCIFIED_NEST_PULP};

	//Overwriting registration set
	public static final RegistryKey<ItemGroup> PENTAMORPH_BLOCKS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP,
			new Identifier(Pentamorph.MOD_ID, "blocks"));
}

