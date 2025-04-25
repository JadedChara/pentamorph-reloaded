package io.github.jadedchara.pentamorph.common.util.component;


import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.SubcomponentRegistry;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class RPGComponentInitializer implements EntityComponentInitializer {
	public static final ComponentKey<RPGComponent> RPG_COMPONENT=
			ComponentRegistry.getOrCreate(Identifier.of(Pentamorph.MOD_ID,"rpg"),RPGComponent.class);
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(RPG_COMPONENT, RPGComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
		System.out.println("[[  Enabling registry...  ]]");
	}
}
