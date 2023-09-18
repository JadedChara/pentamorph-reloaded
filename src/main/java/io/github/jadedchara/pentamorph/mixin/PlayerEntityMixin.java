package io.github.jadedchara.pentamorph.mixin;


import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	@Shadow
	protected abstract void updatePose();

	private static float CROUCH_BOUNDING_BOX_HEIGHT = 0.6F;
	private static float SWIMMING_BOUNDING_BOX_WIDTH = 0.6F;
	private static float SWIMMING_BOUNDING_BOX_HEIGHT = 0.6F;
	private static float DEFAULT_EYE_HEIGHT = 0.5F;
	private static EntityDimensions STANDING_DIMENSIONS = EntityDimensions.changing(0.6f,0.6f);
	private static Map<EntityPose, EntityDimensions> POSE_DIMENSIONS;

	private static TrackedData<String> CHARACTER = DataTracker.registerData(PlayerEntityMixin.class,
			TrackedDataHandlerRegistry.STRING);

	@Inject(at = @At("RETURN"), method = "getDimensions", cancellable = true)
	private void getDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> ci){
		ci.setReturnValue((EntityDimensions) STANDING_DIMENSIONS);

	}

	@Inject(at = @At("RETURN"), method= "getActiveEyeHeight",cancellable = true)
	private void getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> ci){
		ci.setReturnValue(0.5f);
	}
	protected PlayerEntityMixin(World world) {
		super(EntityType.PLAYER, world);
		super.setPose(EntityPose.STANDING);
		super.calculateDimensions();
		super.initDataTracker();

	}
	@Inject(at=@At("HEAD"),method="initDataTracker",cancellable = true)
	private void initDataTracker(CallbackInfo ci){
		this.dataTracker.startTracking(CHARACTER,"quintLarva");
	}


}
