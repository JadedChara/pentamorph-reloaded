package io.github.jadedchara.pentamorph.common.blockentity;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;


import java.util.List;

public class NestCoreBlockEntity extends BlockEntity implements GeoBlockEntity {
	protected static final RawAnimation PASSIVE = RawAnimation.begin().thenLoop("animation.nest_core.passive");
	public static final Identifier SPORE_PARTICLE = new Identifier(Pentamorph.MOD_ID,"basic_smoke");
	public final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public static int activeLifetime = 0;
	public static ParticleSystemManager psm;
	public static ParticleEmitter sporeEmitter = null;
	public int posState = 0;

	public NestCoreBlockEntity(BlockPos pos, BlockState state) {
		super(BlockRegistry.NEST_CORE_BLOCK_ENTITY, pos, state);
		/*psm = VeilRenderSystem.renderer().getParticleManager();
		sporeEmitter = psm.createEmitter(SPORE_PARTICLE);
		try{
			if(sporeEmitter != null && !super.getWorld().isClient()) {
				sporeEmitter.setPosition(new Vec3d(
					(double) super.getPos().getX() + 0.5D,
					(double) super.getPos().getY() + 0.4D,
					(double) super.getPos().getZ() + 0.5D
				));
				psm.addParticleSystem(sporeEmitter);
			}
			//posState = 1;
		}catch(Exception e){
			System.out.println(e);

		}
		System.out.println("Current Emitter Number:" + psm.getEmitterCount());*/

	}

	public static void tick(World world, BlockPos pos, BlockState state, NestCoreBlockEntity entity){
		List<? extends PlayerEntity> playerEntities = world.getPlayers();

		if(entity.posState == 2){
			entity.getSporeEmitter().remove();

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
	}
	public ParticleSystemManager getPsm(){return this.psm;}

	public void markDeleted(){
		posState = 2;
	}


}
