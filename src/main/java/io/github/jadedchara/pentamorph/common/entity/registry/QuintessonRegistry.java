package io.github.jadedchara.pentamorph.common.entity.registry;

import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonLarva;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

import java.util.LinkedHashMap;

public class QuintessonRegistry {
	//preliminary stuff
	public static final LinkedHashMap<EntityType<?>, Identifier> QUINTESSONS = new LinkedHashMap<>();
	public static <T extends Entity> EntityType<T> generate(String name, EntityType<T> type){
		QUINTESSONS.put(type, Pentamorph.id(name));
		return type;
	}
	public static void init(){
		QUINTESSONS.keySet().forEach(entityType -> {
			Registry.register(Registry.ENTITY_TYPE, QUINTESSONS.get(entityType),entityType);
		});
	}
	//Entities
	public static final EntityType<QuintessonLarva> QUINTESSON_LARVA = generate("quintesson_larva",
			QuiltEntityTypeBuilder.createMob().entityFactory(QuintessonLarva::new).defaultAttributes(QuintessonLarva.createAttributes()).setDimensions(EntityDimensions.changing(0.5f,0.3f)).build());
}
