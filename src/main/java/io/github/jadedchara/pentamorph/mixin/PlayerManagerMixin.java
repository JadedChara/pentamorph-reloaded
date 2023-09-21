package io.github.jadedchara.pentamorph.mixin;


import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
import net.minecraft.entity.EntityPose;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
	@Inject(at = @At("RETURN"),method = "onPlayerConnect", cancellable = true)
	private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci){
		player.setPose(EntityPose.SWIMMING);
		System.out.println(player.getName() + "'s ID component: [" + RPGComponent.getProvidedCharacter(player) + "]");

		//((PlayerRenderAccess) ).getRenderers().replace();


	}
}
