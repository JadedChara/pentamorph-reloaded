package io.github.jadedchara.pentamorph.common;


import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.block.SpaceBridgePortalBlock;
import io.github.jadedchara.pentamorph.common.blockentity.SpaceBridgePortalBlockEntity;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
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

		ArrayList<Item> blocklist = new ArrayList<>();

		ItemGroup PENTAMORPH_BLOCKS = FabricItemGroup.builder()
				.name(Text.of("Pentamorph Blocks"))
				.icon(()->new ItemStack(NEST_PULP))
				.entries((params, col)->{
					col.addItem(NEST_PULP);
					col.addItem(CALCIFIED_NEST_PULP);
					col.addItem(SPACE_BRIDGE_PORTAL_BLOCK);
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

	//more standard blocks

	public static final Block NEST_PULP = blockitemRegister("nest_pulp",
			new Block(QuiltBlockSettings.copyOf(Blocks.IRON_BLOCK).mapColor(MapColor.BROWN_TERRACOTTA)),
			new QuiltItemSettings()
	);
	public static final Block CALCIFIED_NEST_PULP = blockitemRegister("calcified_nest_pulp",
			new Block(QuiltBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).mapColor(MapColor.METAL)),
			new QuiltItemSettings()
	);

	//portal stuff
	public static final Block SPACE_BRIDGE_PORTAL_BLOCK = blockitemRegister("space_bridge_portal_block",
		new Block(QuiltBlockSettings.copyOf(Blocks.GLOWSTONE).mapColor(MapColor.CYAN)),
		new QuiltItemSettings()
	);
	public static final BlockEntityType<SpaceBridgePortalBlockEntity> SPACE_BRIDGE_PORTAL_BLOCK_ENTITY =
		Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			Pentamorph.id("space_bridge_portal_block_entity"),
			QuiltBlockEntityTypeBuilder.create(SpaceBridgePortalBlockEntity::new,SPACE_BRIDGE_PORTAL_BLOCK).build()
		);

	//final registration
	public static final Block[] blocks= {NEST_PULP, CALCIFIED_NEST_PULP,SPACE_BRIDGE_PORTAL_BLOCK};


	//Overwriting registration set
	public static final RegistryKey<ItemGroup> PENTAMORPH_BLOCKS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP,
			new Identifier(Pentamorph.MOD_ID, "blocks"));
}

