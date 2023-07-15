package io.github.jadedchara.pentamorph.client;

import io.github.jadedchara.pentamorph.client.render.quintesson.QuintessonLarvaRenderer;
import io.github.jadedchara.pentamorph.common.entity.registry.QuintessonRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;


public class PentamorphClient implements ClientModInitializer {
	public void onInitializeClient(ModContainer container){
		EntityRendererRegistry.register(QuintessonRegistry.QUINTESSON_LARVA,QuintessonLarvaRenderer::new);
	}
}
