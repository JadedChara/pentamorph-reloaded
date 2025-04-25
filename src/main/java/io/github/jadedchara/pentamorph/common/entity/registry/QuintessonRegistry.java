package io.github.jadedchara.pentamorph.common.entity.registry;

import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonLarva;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.entity.extensions.api.QuiltEntityTypeBuilder;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;

public class QuintessonRegistry {
	//preliminary stuff

	public static final LinkedHashMap<EntityType<?>, Identifier> QUINTESSONS = new LinkedHashMap<>();
	public static <T extends Entity> EntityType<T> generate(String name, EntityType<T> type){
		QUINTESSONS.put(
			type,
			Identifier.of(
				Pentamorph.MOD_ID,
				name
			)
		);
		return type;
	}
	public static void init(){
		QUINTESSONS.keySet().forEach(entityType -> {
			Registry.register(Registries.ENTITY_TYPE, QUINTESSONS.get(entityType),entityType);
		});
		Item QUINT_LARVA_SPAWN_EGG = new SpawnEggItem(QUINTESSON_LARVA,0x757470,0x9C3436,
				new QuiltItemSettings().maxCount(64));
		Registry.register(
			Registries.ITEM,
			Identifier.of(
				Pentamorph.MOD_ID,
				"quintesson_larva_spawn_egg"
			),
			QUINT_LARVA_SPAWN_EGG
		);

		ItemGroup QUINTESSON_SET = FabricItemGroup.builder()
				.name(Text.of("Pentamorph Creatures"))
				.icon(()->new ItemStack(QUINT_LARVA_SPAWN_EGG))
				.entries((params, col)->{
					col.addItem(QUINT_LARVA_SPAWN_EGG);
				})
				.build();
		Registry.register(Registries.ITEM_GROUP, "pentamorph:pentamorph_creatures", QUINTESSON_SET);
	}
	public static final RegistryKey<ItemGroup> QUINTESSON_SET_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP,
		Identifier.of(Pentamorph.MOD_ID, "quintessons"));


	//Entities
	public static final EntityType<QuintessonLarva> QUINTESSON_LARVA = generate("quintesson_larva",

			QuiltEntityTypeBuilder
				.create(SpawnGroup.MISC, QuintessonLarva::new)
				//.setEyeHeight(0.47f)
				.setDimensions(EntityDimensions.changing(0.5f,0.3f))
				.makeFireImmune()
				//.maxTrackingRange(50)
				.build());
	/*public static final EntityType<QuintessonJuror> QUINTESSON_JUROR = generate("quintesson_juror",
			QuiltEntityTypeBuilder.createMob().entityFactory(QuintessonJuror::new).defaultAttributes(QuintessonJuror
					.createAttributes()).setDimensions(EntityDimensions.changing(0.5f,0.3f)).build());*/


}
