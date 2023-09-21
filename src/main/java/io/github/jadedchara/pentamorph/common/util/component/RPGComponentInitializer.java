package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.jadedchara.pentamorph.common.SubcomponentRegistry;

public class RPGComponentInitializer implements EntityComponentInitializer {
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(RPGManage.RPG, player-> new RPGManage(), RespawnCopyStrategy.ALWAYS_COPY);
		System.out.println("[[  Enabling registry...  ]]");
	}
}
