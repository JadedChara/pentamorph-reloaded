package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.jadedchara.pentamorph.common.SubcomponentRegistry;

public class RPGComponentInitializer implements EntityComponentInitializer {
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(SubcomponentRegistry.RPG, player-> new RPGManage(), RespawnCopyStrategy.LOSSLESS_ONLY);
		System.out.print("[[  Enabling registry...  ]]");
	}
}
