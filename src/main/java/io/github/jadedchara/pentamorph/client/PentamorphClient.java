package io.github.jadedchara.pentamorph.client;




import foundry.veil.api.event.VeilRenderLevelStageEvent;
import foundry.veil.fabric.event.FabricFreeNativeResourcesEvent;
import foundry.veil.fabric.event.FabricVeilRenderLevelStageEvent;
import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.client.render.blockentity.NestCoreRenderer;
import io.github.jadedchara.pentamorph.client.render.blockentity.SBPbeRenderer;
import io.github.jadedchara.pentamorph.client.render.quintesson.QuintessonLarvaRenderer;
import io.github.jadedchara.pentamorph.client.render.rpg.GeoReplacedPlayerRenderer;

import io.github.jadedchara.pentamorph.common.BlockRegistry;
import io.github.jadedchara.pentamorph.common.entity.registry.QuintessonRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import net.minecraft.block.BlockRenderType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.entity.EntityType;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import org.quiltmc.qsl.base.impl.event.EventRegistry;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntity;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.networking.api.client.ClientPlayConnectionEvents;



public class PentamorphClient implements ClientModInitializer {
	public void onInitializeClient(ModContainer container){
		EntityRendererRegistry.register(QuintessonRegistry.QUINTESSON_LARVA,QuintessonLarvaRenderer::new);
		EntityRendererRegistry.register(EntityType.PLAYER, GeoReplacedPlayerRenderer::new);
		BlockEntityRendererRegistry.register(
			BlockRegistry.NEST_CORE_BLOCK_ENTITY,
			context -> new NestCoreRenderer()
		);
		BlockEntityRendererRegistry.register(
			BlockRegistry.SPACE_BRIDGE_PORTAL_BLOCK_ENTITY,
			context -> new SBPbeRenderer(context)
		);
		BlockRenderLayerMap.put(RenderLayer.getTranslucent(), BlockRegistry.NEST_CORE_BLOCK);
/*`````````````````````````````*/
		//ParticleFactoryRegistry.getInstance().register(Pentamorph.RESONATOR_SHOT, );
/*`````````````````````````````*/

		FabricFreeNativeResourcesEvent.EVENT.register(()->{
			//
		});
		FabricVeilRenderLevelStageEvent.EVENT.register(
			(stage, levelRenderer, bufferSource, poseStack, projectionMatrix, renderTick, partialTicks, camera, frustum) -> {
				if (stage == VeilRenderLevelStageEvent.Stage.AFTER_LEVEL){

				}
			}
		);


	}
}
