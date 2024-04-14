package io.github.jadedchara.pentamorph.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayMixin {
	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(at=@At("HEAD"),method="onGameJoin",cancellable = true)
	private void gameJoin(GameJoinS2CPacket packet, CallbackInfo ci){
		//this.client.player.setSneaking(true);
		//this.client.player.setSneaking(false);
	}
}
