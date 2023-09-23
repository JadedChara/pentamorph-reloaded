package io.github.jadedchara.pentamorph.mixin;


import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponentInitializer;
import io.github.jadedchara.pentamorph.common.util.misc.HitboxAccess;
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
public abstract class PlayerEntityMixin extends LivingEntity implements HitboxAccess {

	@Shadow
	protected abstract void updatePose();

	private float newCrouchBoundingBoxHeight = 1.5F;
	private float newSwimmingBoundingBoxWidth = 0.6F;
	private float newSwimmingBoundingBoxHeight = 0.6F;
	private float newDefaultEyeHeight = 1.62F;
	private EntityDimensions newStandingDimensions = EntityDimensions.changing(0.6f,1.8f);
	private static Map<EntityPose, EntityDimensions> POSE_DIMENSIONS;

	@Inject(at = @At("RETURN"), method = "getDimensions", cancellable = true)
	private void getDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> ci){
		/*if(this.getWorld() != null) {
			SWIMMING_BOUNDING_BOX_WIDTH = RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getSwimWidth();
			SWIMMING_BOUNDING_BOX_HEIGHT = RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getSwimHeight();
			CROUCH_BOUNDING_BOX_HEIGHT = RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getCrouchHeight();
			STANDING_DIMENSIONS = RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getDimensions();
			ci.setReturnValue(RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getDimensions());
		}else{*/
			newStandingDimensions = RPGComponentInitializer.RPG_COMPONENT.get(this).getDimensions();
			ci.setReturnValue((EntityDimensions) newStandingDimensions);
		//}
	}

	@Inject(at = @At("RETURN"), method= "getActiveEyeHeight",cancellable = true)
	private void getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> ci){
		/*if(this.getWorld() != null) {
			DEFAULT_EYE_HEIGHT = RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getEyeHeight();
			ci.setReturnValue(RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getEyeHeight());
		}else{*/
			newDefaultEyeHeight = RPGComponentInitializer.RPG_COMPONENT.get(this).getEyeHeight();
			ci.setReturnValue(newDefaultEyeHeight);
		//}
	}
	protected PlayerEntityMixin(World world) {
		super(EntityType.PLAYER, world);
		super.setPose(EntityPose.STANDING);
		super.calculateDimensions();
		super.initDataTracker();

	}
	@Inject(at=@At("HEAD"),method="initDataTracker",cancellable = true)
	private void initDataTracker(CallbackInfo ci){
		//this.dataTracker.startTracking(CHARACTER,"quintLarva");
	}
	@Override
	public EntityDimensions getEntitySize(){
		return this.newStandingDimensions;
	}
	@Override
	public float getEntityEyeLevel(){
		return this.newDefaultEyeHeight;
	}
	@Override
	public float getEntityCrouchHeight(){
		return this.newCrouchBoundingBoxHeight;
	}
	@Override
	public float getEntitySwimHeight(){
		return this.newSwimmingBoundingBoxHeight;
	}
	@Override
	public float getEntitySwimWidth(){
		return this.newSwimmingBoundingBoxWidth;
	}

	@Override
	public void setEntitySize(EntityDimensions dimensions, float eyeHeight, float crouchHeight, float swimHeight,
							  float swimWidth){
		this.newStandingDimensions = dimensions;
		this.newDefaultEyeHeight = eyeHeight;
		this.newCrouchBoundingBoxHeight = crouchHeight;
		this.newSwimmingBoundingBoxHeight = swimHeight;
		this.newSwimmingBoundingBoxWidth = swimWidth;
	}
}
