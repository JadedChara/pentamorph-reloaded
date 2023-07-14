package io.github.jadedchara.pentamorph.common;

import io.github.jadedchara.pentamorph.common.entity.registry.QuintessonRegistry;

public class EntityRegistry {

	public static void init(){
		QuintessonRegistry.init();
	}
	//Entities
	//public static final EntityType<QuintessonLarva> QUINTESSON_LARVA = generate("quintesson_larva",
	//		QuiltEntityTypeBuilder.createMob().entityFactory(QuintessonLarva::new).defaultAttributes(QuintessonLarva
	//		.createAttributes()).setDimensions(EntityDimensions.changing(0.5f,0.3f)).build());
}
