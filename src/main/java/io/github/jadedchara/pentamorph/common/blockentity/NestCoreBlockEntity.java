package io.github.jadedchara.pentamorph.common.blockentity;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.quasar.data.ParticleSettings;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;


import java.util.List;


public class NestCoreBlockEntity extends BlockEntity implements GeoBlockEntity {
	protected static final RawAnimation PASSIVE = RawAnimation.begin().thenLoop("animation.nest_core.passive");
	public static final Identifier SPORE_PARTICLE = Identifier.of(Pentamorph.MOD_ID,"basic_smoke");
	public final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public static int activeLifetime = 0;
	public World locale;
	public static ParticleSystemManager psm;
	public static ParticleEmitter sporeEmitter = null;
	public int posState = 0;

	public NestCoreBlockEntity(BlockPos pos, BlockState state) {
		super(BlockRegistry.NEST_CORE_BLOCK_ENTITY, pos, state);


		/*try{
			this.psm = VeilRenderSystem.renderer().getParticleManager();

			this.sporeEmitter = psm.createEmitter(SPORE_PARTICLE);

			this.sporeEmitter.setPosition(new Vec3d(
				(double) super.getPos().getX() + 0.5D,
				(double) super.getPos().getY() + 0.4D,
				(double) super.getPos().getZ() + 0.5D
			));
			this.sporeEmitter.setParticleSettings(
				new ParticleSettings(
					0.01f,
					0.1f,
					0.05f,
					5,
					0f,
					new Vector3f(
					super.getPos().getX() + 0.5F,
					super.getPos().getY() + 0.7F,
					super.getPos().getZ() + 0.5F
					),
				true,true,false,true,false));
			this.psm.addParticleSystem(this.sporeEmitter);


			//posState = 1;
		}catch(Exception e){
			System.out.println(e);

		}
		System.out.println("Current Emitter Number:" + this.psm.getEmitterCount());*/

	}

	public static void tick(World world, BlockPos pos, BlockState state, NestCoreBlockEntity entity){
		List<? extends PlayerEntity> playerEntities = world.getPlayers();

		try {
			entity.psm = VeilRenderSystem.renderer().getParticleManager();

			entity.sporeEmitter = psm.createEmitter(SPORE_PARTICLE);

			entity.sporeEmitter.setPosition(new Vec3d(
				(double) entity.getPos().getX() + 0.5D,
				(double) entity.getPos().getY() + 0.4D,
				(double) entity.getPos().getZ() + 0.5D
			));
			//entity.sporeEmitter.
			/*entity.sporeEmitter.setParticleSettings(
				new ParticleSettings(
					0.005f,
					0.1f,
					0.05f,
					5,
					0f,
					new Vector3f(
						(int)(Math.random() * 0.3)-0.1F,
						(int)(Math.random() * 0.3)-0.1F,
						(int)(Math.random() * 0.3)-0.1F
					),
					true, true, false, true, false));*/
			entity.psm.addParticleSystem(entity.sporeEmitter);
		}catch(Exception e){
			System.out.println(e);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
			new AnimationController<GeoAnimatable>(
				this,
				"controller",
				state -> state.setAndContinue(PASSIVE)
			)

		);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	public ParticleEmitter getSporeEmitter(){
		return this.sporeEmitter;
		//this.onSyncedBlockEvent()
	}
	public ParticleSystemManager getPsm(){return this.psm;}

	public void terminateEmitter(){
		this.getSporeEmitter().remove();

	}


}
