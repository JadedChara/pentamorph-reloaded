package io.github.jadedchara.pentamorph.common.blockentity;

import io.github.jadedchara.pentamorph.common.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
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
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public NestCoreBlockEntity(BlockPos pos, BlockState state) {
		super(BlockRegistry.NEST_CORE_BLOCK_ENTITY, pos, state);
		
	}
	public static void tick(World world, BlockPos pos, BlockState state, SpaceBridgePortalBlockEntity entity){
		List<? extends PlayerEntity> playerEntities = world.getPlayers();
		for ( PlayerEntity p : playerEntities){
			if (p.getBlockPos().isWithinDistance(entity.getPos(),5)){
				//
			}
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
}
