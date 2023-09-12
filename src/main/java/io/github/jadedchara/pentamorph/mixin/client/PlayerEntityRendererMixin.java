package io.github.jadedchara.pentamorph.mixin.client;

import com.mojang.authlib.legacy.LegacyMinecraftSessionService;
import io.github.jadedchara.pentamorph.client.render.rpg.GeoReplacedPlayerRenderer;
import io.github.jadedchara.pentamorph.common.util.GeoReplacedPlayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

//@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin<T extends PlayerEntity> {
	/*
	if (){}
	*/
	@Inject(method="render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "HEAD"), cancellable = true)
	public void reRender(
			AbstractClientPlayerEntity abstractClientPlayerEntity,
			float f,
			float g,
			MatrixStack matrixStack,
			VertexConsumerProvider vertexConsumerProvider,
			int i,
			CallbackInfo ci)
	{
		GeoReplacedPlayerRenderer geoPlayerRenderer = this.getGeoPlayerRenderer();
		if(geoPlayerRenderer == null){
			return;
		}
		geoPlayerRenderer.render(abstractClientPlayerEntity, f, g, matrixStack, vertexConsumerProvider, i);
		ci.cancel();
	}
	public GeoReplacedPlayerRenderer getGeoPlayerRenderer(){
		EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
		EntityRenderer<?> entityRenderer =
				((PlayerRenderAccess) entityRenderDispatcher).getRenderers().get(EntityType.PLAYER);
		if(!(entityRenderer instanceof GeoReplacedPlayerRenderer geoPlayerRenderer)){
			return null;
		}
		return geoPlayerRenderer;
	}
}
