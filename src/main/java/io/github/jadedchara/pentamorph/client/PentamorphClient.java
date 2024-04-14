package io.github.jadedchara.pentamorph.client;


import io.github.jadedchara.pentamorph.client.render.quintesson.QuintessonLarvaRenderer;
import io.github.jadedchara.pentamorph.client.render.rpg.GeoReplacedPlayerRenderer;

import io.github.jadedchara.pentamorph.common.entity.registry.QuintessonRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.base.impl.event.EventRegistry;
import org.quiltmc.qsl.networking.api.client.ClientPlayConnectionEvents;


public class PentamorphClient implements ClientModInitializer {
	public void onInitializeClient(ModContainer container){
		EntityRendererRegistry.register(QuintessonRegistry.QUINTESSON_LARVA,QuintessonLarvaRenderer::new);
		EntityRendererRegistry.register(EntityType.PLAYER,
				GeoReplacedPlayerRenderer::new);

	}
}
