package io.github.jadedchara.pentamorph.mixin;

import io.github.jadedchara.pentamorph.common.util.component.RPGComponentInitializer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayer{


	public ServerPlayer() {
		super();
		//RPGComponentInitializer.RPG_COMPONENT.sync(this);
	}

}
