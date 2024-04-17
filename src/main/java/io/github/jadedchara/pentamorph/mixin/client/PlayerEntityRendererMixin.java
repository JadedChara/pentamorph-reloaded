package io.github.jadedchara.pentamorph.mixin.client;

import io.github.jadedchara.pentamorph.client.model.rpg.GeoReplacedPlayerModel;
import io.github.jadedchara.pentamorph.client.render.rpg.GeoReplacedPlayerRenderer;
import io.github.jadedchara.pentamorph.common.SubcomponentRegistry;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponentInitializer;
import io.github.jadedchara.pentamorph.mixin.PlayerEntityMixin;
import io.github.jadedchara.pentamorph.mixin.client.PlayerRenderAccess;
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

//@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin<T extends PlayerEntity> {
	/*
	if (){}
	*/
	@Inject(method="render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "HEAD"), cancellable = true)
	public void reRender(
			AbstractClientPlayerEntity player,
			float f,
			float g,
			MatrixStack matrixStack,
			VertexConsumerProvider vertexConsumerProvider,
			int i,
			CallbackInfo ci)
	{ try {
		GeoReplacedPlayerRenderer geoPlayerRenderer = this.getGeoPlayerRenderer();

		if (geoPlayerRenderer == null) {
			return;
		}

		String chaCheck =
			//RPGComponentInitializer.RPG_COMPONENT.get(player).getCurrentCharacter();
			RPGComponent.getProvidedCharacter(player);
		//System.out.println(chaCheck);

		//player.getComponent(RPGComponentInitializer.RPG_COMPONENT).getCurrentCharacter();
		if (!chaCheck.equals("human")) {
			geoPlayerRenderer.render(player, f, g, matrixStack, vertexConsumerProvider, i);
			//System.out.print("Updating Player model...");
			ci.cancel();
		}

		//ci.cancel();
	}catch(NullPointerException err){
		System.out.println("Failed to hook into component:\n"+err+"\n------");
	}}


	public GeoReplacedPlayerRenderer getGeoPlayerRenderer(){
		EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
		EntityRenderer<?> entityRenderer =
				((PlayerRenderAccess) entityRenderDispatcher).getRenderers().get(EntityType.PLAYER);

		if(!(entityRenderer instanceof GeoReplacedPlayerRenderer geoPlayerRenderer)){
			return null;
		}
		return geoPlayerRenderer;
	}
	//public void setGeoModel(PlayerEntity player, String character){}
}
