package io.github.jadedchara.pentamorph.common;


import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.block.NestCoreBlock;
import io.github.jadedchara.pentamorph.common.block.SpaceBridgePortalBlock;
import io.github.jadedchara.pentamorph.common.blockentity.NestCoreBlockEntity;
import io.github.jadedchara.pentamorph.common.blockentity.SpaceBridgePortalBlockEntity;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
//import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
//import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
//import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.function.BiFunction;

public final class BlockRegistry {
	//preliminary stuff

	public static void init() {


		ItemGroup PENTAMORPH_BLOCKS = FabricItemGroup.builder()
				.name(Text.of("Pentamorph Blocks"))
				.icon(()->new ItemStack(NEST_PULP))
				.entries((params, col)->{
					col.addItem(NEST_PULP);
					col.addItem(CALCIFIED_NEST_PULP);
					col.addItem(NEST_CORE_BLOCK);
					col.addItem(SPACE_BRIDGE_PORTAL_BLOCK);
				})
				.build();
		Registry.register(Registries.ITEM_GROUP, "pentamorph:pentamorph_blocks", PENTAMORPH_BLOCKS);
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
		return Registry.register(
			Registries.BLOCK,
			Identifier.of(Pentamorph.MOD_ID,name),
			block
		);
	}
	static <T extends Item> T itemRegister(String name, T item) {
		return Registry.register(
			Registries.ITEM,
			Identifier.of(Pentamorph.MOD_ID,name),
			item
		);
	}

	//Nest Blocks
	public static final Block NEST_PULP = blockitemRegister("nest_pulp",
		new Block(
			QuiltBlockSettings
				.copy(Blocks.IRON_BLOCK)
				.mapColor(MapColor.BROWN_TERRACOTTA)
				.sounds(BlockSoundGroup.WART_BLOCK)
		),
		new QuiltItemSettings().fireproof()
	);
	public static final Block CALCIFIED_NEST_PULP = blockitemRegister("calcified_nest_pulp",
		new Block(
			QuiltBlockSettings
				.copy(Blocks.NETHERITE_BLOCK)
				.mapColor(MapColor.METAL)
				.sounds(BlockSoundGroup.STONE)
		),
		new QuiltItemSettings().fireproof()
	);
	public static final Block NEST_CORE_BLOCK = blockitemRegister("nest_core",
		new NestCoreBlock(
			QuiltBlockSettings
				.copy(Blocks.REINFORCED_DEEPSLATE)
				.mapColor(MapColor.SAND)
				.nonOpaque()
				.sounds(BlockSoundGroup.WART_BLOCK)
		),
		new QuiltItemSettings().fireproof()
	);
	public static final BlockEntityType<NestCoreBlockEntity> NEST_CORE_BLOCK_ENTITY =
		Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			/*RegistryKey.of(
				RegistryKeys.BLOCK_ENTITY_TYPE,*/
				Identifier.of(Pentamorph.MOD_ID,
					"nest_core_block_entity"
				),
			//),
			QuiltBlockEntityTypeBuilder.create(NestCoreBlockEntity::new,NEST_CORE_BLOCK).build()
		);

	//Space Bridge Blocks
	public static final Block SPACE_BRIDGE_PORTAL_BLOCK = blockitemRegister("space_bridge_portal_block",
		new SpaceBridgePortalBlock(
			QuiltBlockSettings
				.copy(Blocks.GLOWSTONE)
				.mapColor(MapColor.CYAN)
		),
		new QuiltItemSettings().fireproof()
	);

	public static final BlockEntityType<SpaceBridgePortalBlockEntity> SPACE_BRIDGE_PORTAL_BLOCK_ENTITY =
		Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			//RegistryKey.of(
			//	RegistryKeys.BLOCK_ENTITY_TYPE,
				Identifier.of(Pentamorph.MOD_ID,
					"space_bridge_portal_block_entity"
			//	)
			),
			QuiltBlockEntityTypeBuilder.create(SpaceBridgePortalBlockEntity::new,SPACE_BRIDGE_PORTAL_BLOCK).build()
		);

	//Overwriting registration set
	public static final RegistryKey<ItemGroup> PENTAMORPH_BLOCKS_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP,
		Identifier.of(Pentamorph.MOD_ID, "blocks"));
}

