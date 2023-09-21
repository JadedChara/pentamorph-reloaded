package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.SubcomponentRegistry;
import net.minecraft.util.Identifier;

public class RPGComponentInitializer implements EntityComponentInitializer {
	public static final ComponentKey<RPGComponent> RPG_COMPONENT=
			ComponentRegistry.getOrCreate(new Identifier(Pentamorph.MOD_ID,"rpg"),RPGComponent.class);
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(RPG_COMPONENT, RPGComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
		System.out.println("[[  Enabling registry...  ]]");
	}
}
