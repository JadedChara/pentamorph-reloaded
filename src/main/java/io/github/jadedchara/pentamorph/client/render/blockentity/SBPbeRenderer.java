package io.github.jadedchara.pentamorph.client.render.blockentity;

import foundry.veil.api.quasar.emitters.shape.Sphere;
import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.blockentity.SpaceBridgePortalBlockEntity;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.NativeResource;
import org.quiltmc.qsl.networking.api.client.ClientPlayConnectionEvents;

import java.io.IOException;


public class SBPbeRenderer implements BlockEntityRenderer<SpaceBridgePortalBlockEntity>, NativeResource {

	private static final Identifier SBP_FBO = Pentamorph.id("space_bridge_portal");
	private static final Matrix4f RENDER_MODELVIEW = new Matrix4f();
	private static final Matrix4f RENDER_PROJECTION = new Matrix4f();
	private static final Vector4f OBLIQUE_PLANE = new Vector4f();
	private static final Quaternionf VIEW = new Quaternionf();
	private static final ObjectSet<BlockPos> RENDER_POSITIONS = new ObjectArraySet<>();
	private static final Long2ObjectMap<PortalTexture> TEXTURES = new Long2ObjectArrayMap<>();


	public SBPbeRenderer(BlockEntityRendererFactory.Context context){
		ClientPlayConnectionEvents.DISCONNECT.register(((handler, client) -> {
			client.execute(this::free);
		}));
	}

	@Override
	public void free() {
		try (MemoryStack memoryStack = MemoryStack.stackPush()){
			//ObjectCollection<> values = TEXTURES.values();
		}

		TEXTURES.clear();
	}

	@Override
	public void render(SpaceBridgePortalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

	}

	@Override
	public int getRenderDistance() {
		return BlockEntityRenderer.super.getRenderDistance();
	}
	private static class PortalTexture extends AbstractTexture{

		@Override
		public void load(ResourceManager manager) throws IOException {
		}
		//private final ObjectSet<BlockPos> position
	}
}
