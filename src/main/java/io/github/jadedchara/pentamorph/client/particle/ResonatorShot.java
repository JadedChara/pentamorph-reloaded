package io.github.jadedchara.pentamorph.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

public class ResonatorShot extends BillboardParticle {
	public float rotX;
	public float rotY;
	public float rotZ;
	public Quaternionf rot;

	protected ResonatorShot(
		ClientWorld clientWorld,
		double x,
		double y,
		double z,
		double velX,
		double velY,
		double velZ,
		float rotX,
		float rotY,
		float rotZ,
		SpriteProvider provider
		) {
		super(clientWorld, x, y, z, velX, velY, velZ);
		this.rot = new Quaternionf().set(rotX,rotY,rotZ,1.0f);
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta){

	}

	@Override
	protected float getMinU() {
		return 0;
	}

	@Override
	protected float getMaxU() {
		return 0;
	}

	@Override
	protected float getMinV() {
		return 0;
	}

	@Override
	protected float getMaxV() {
		return 0;
	}

	@Environment(EnvType.CLIENT)
	public static class Factory implements ParticleFactory<DefaultParticleType>{
		private final SpriteProvider spriteProvider;
		public float rotX;
		public float rotY;
		public float rotZ;
		public Factory(SpriteProvider spriteProvider){
			this.spriteProvider = spriteProvider;
		}

		@Nullable
		@Override
		public Particle createParticle(
			DefaultParticleType particleEffect,
			ClientWorld clientWorld,
			double x,
			double y,
			double z,
			double velX,
			double velY,
			double velZ) {
			//MinecraftClient.getInstance().getCameraEntity().getRotationClient().x;
			return new ResonatorShot(
				clientWorld,
				x,
				y,
				z,
				velX,
				velY,
				velZ,
				MinecraftClient.getInstance().getCameraEntity().getRotationClient().x,
				MinecraftClient.getInstance().getCameraEntity().getRotationClient().y,
				0.0f,
				this.spriteProvider);
		}
	}

}
