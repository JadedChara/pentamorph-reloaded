package io.github.jadedchara.pentamorph.client.render.blockentity;

import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.texture.NativeImage;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.framebuffer.AdvancedFbo;
import foundry.veil.api.client.render.VeilRenderer;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import foundry.veil.ext.LevelRendererExtension;
import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.blockentity.SpaceBridgePortalBlockEntity;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
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
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL30C;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.NativeResource;
import org.quiltmc.qsl.networking.api.client.ClientPlayConnectionEvents;

import java.io.IOException;
import java.nio.IntBuffer;

import static com.mojang.blaze3d.platform.GlConst.GL_TEXTURE_2D;


public class SBPbeRenderer implements BlockEntityRenderer<SpaceBridgePortalBlockEntity>, NativeResource {

	//private static final Identifier SBP_FBO = Pentamorph.id("space_bridge_portal");
	//private static final Matrix4f RENDER_MODELVIEW = new Matrix4f();
	//private static final Matrix4f RENDER_PROJECTION = new Matrix4f();
	//private static final Vector4f OBLIQUE_PLANE = new Vector4f();
	//private static final Quaternionf VIEW = new Quaternionf();
	private static final ObjectSet<BlockPos> RENDER_POSITIONS = new ObjectArraySet<>();
	private static final Long2ObjectMap<PortalTexture> TEXTURES = new Long2ObjectArrayMap<>();


	public SBPbeRenderer(BlockEntityRendererFactory.Context context){
		ClientPlayConnectionEvents.DISCONNECT.register(((handler, client) -> {
			client.execute(this::free);
		})); //VeilRenderer.getCullingFrustum().
	}

	@Override
	public void free() {
		try (MemoryStack memoryStack = MemoryStack.stackPush()){
			ObjectCollection<PortalTexture> values = TEXTURES.values();
			IntBuffer textures = memoryStack.mallocInt(values.size());
			values.forEach(texture -> textures.put(texture.getGlId()));
			textures.flip();
			GL11C.glDeleteTextures(textures);
		}
		TEXTURES.clear();
	}


	@Override
	public void render(SpaceBridgePortalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		//if(){
		//return;
		//}
		VeilRenderSystem.setShader(new Identifier(Pentamorph.MOD_ID,"spacebridge"));
		ShaderProgram.unbind();
	}

	@Override
	public int getRenderDistance() {
		return BlockEntityRenderer.super.getRenderDistance();
	}
	private static class PortalTexture extends AbstractTexture{
		private final ObjectSet<BlockPos> positions;
		private int width;
		private int height;
		boolean rendered;

		private PortalTexture(ObjectSet<BlockPos> positions) {
			this.positions = new ObjectArraySet<>();
			this.width = -1;
			this.height = -1;
		}

		@Override
		public void load(ResourceManager manager) throws IOException {
		}
		public void copy(AdvancedFbo fbo) {
			int id = this.getGlId();
			int width = fbo.getWidth();
			int height = fbo.getHeight();
			if (this.width != width || this.height != height) {
				this.width = width;
				this.height = height;
				TextureUtil.prepareImage(NativeImage.GLFormat.ABGR, id, 4, width, height);
			}

			RenderSystem.bindTexture(id);
			fbo.bindRead();
			GL11C.glCopyTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, 0, 0, width, height);
			AdvancedFbo.unbind();
			GL30C.glGenerateMipmap(GL_TEXTURE_2D);
		}
		public boolean hasRendered() {
			return this.rendered;
		}

		public void setRendered(boolean rendered) {
			this.rendered = rendered;
		}

	}
}
