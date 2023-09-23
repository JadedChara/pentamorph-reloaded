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

	private float NEW_CROUCH_BOUNDING_BOX_HEIGHT = 1.5F;
	private float NEW_SWIMMING_BOUNDING_BOX_WIDTH = 0.6F;
	private float NEW_SWIMMING_BOUNDING_BOX_HEIGHT = 0.6F;
	private float NEW_DEFAULT_EYE_HEIGHT = 1.62F;
	private EntityDimensions NEW_STANDING_DIMENSIONS = EntityDimensions.changing(0.6f,1.8f);
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
			ci.setReturnValue((EntityDimensions) NEW_STANDING_DIMENSIONS);
		//}
	}

	@Inject(at = @At("RETURN"), method= "getActiveEyeHeight",cancellable = true)
	private void getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> ci){
		/*if(this.getWorld() != null) {
			DEFAULT_EYE_HEIGHT = RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getEyeHeight();
			ci.setReturnValue(RPGComponentInitializer.RPG_COMPONENT.maybeGet(this).get().getEyeHeight());
		}else{*/
			ci.setReturnValue(NEW_DEFAULT_EYE_HEIGHT);
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
		return this.NEW_STANDING_DIMENSIONS;
	}
	@Override
	public float getEntityEyeLevel(){
		return this.NEW_DEFAULT_EYE_HEIGHT;
	}
	@Override
	public float getEntityCrouchHeight(){
		return this.NEW_CROUCH_BOUNDING_BOX_HEIGHT;
	}
	@Override
	public float getEntitySwimHeight(){
		return this.NEW_SWIMMING_BOUNDING_BOX_HEIGHT;
	}
	@Override
	public float getEntitySwimWidth(){
		return this.NEW_SWIMMING_BOUNDING_BOX_WIDTH;
	}

	@Override
	public void setEntitySize(EntityDimensions dimensions, float eyeHeight, float crouchHeight, float swimHeight,
							  float swimWidth){
		this.NEW_STANDING_DIMENSIONS = dimensions;
		this.NEW_DEFAULT_EYE_HEIGHT = eyeHeight;
		this.NEW_CROUCH_BOUNDING_BOX_HEIGHT = crouchHeight;
		this.NEW_SWIMMING_BOUNDING_BOX_HEIGHT = swimHeight;
		this.NEW_SWIMMING_BOUNDING_BOX_WIDTH = swimWidth;
	}
}
