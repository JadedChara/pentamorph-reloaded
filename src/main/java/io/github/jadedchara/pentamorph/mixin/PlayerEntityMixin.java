package io.github.jadedchara.pentamorph.mixin;


import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponentInitializer;
import io.github.jadedchara.pentamorph.common.util.misc.HitboxAccess;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements HitboxAccess{
	@Shadow
	public abstract boolean isSwimming();

	@Shadow
	public abstract EntityDimensions getDimensions(EntityPose pose);

	@Shadow
	protected abstract void initDataTracker();

	private float defaultEyeHeight = 1.62F;
	private EntityDimensions defaultStandingDimensions = EntityDimensions.changing(0.6f,1.8f);
	private static Map<EntityPose, EntityDimensions> POSE_DIMENSIONS;


	@Inject(at=@At("HEAD"), method="initDataTracker",cancellable = true)
	private void initData(CallbackInfo ci){
		//
	}


	protected PlayerEntityMixin(World world) {
		super(EntityType.PLAYER, world);
		//RPGComponentInitializer.RPG_COMPONENT.sync(this);
		super.initDataTracker();
	}


	@Inject(at = @At("RETURN"), method = "getDimensions", cancellable = true)
	private void tweakDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> ci){
		try{
			ci.setReturnValue(new EntityDimensions(
					(RPGComponent.getDimensionsFromProvider(this).width/0.6F)*ci.getReturnValue().width,
					(RPGComponent.getDimensionsFromProvider(this).height/1.8F)*ci.getReturnValue().height,
					false
			));
		}catch(NullPointerException err){
			//fail
		}
	}
	@Inject(at = @At("RETURN"), method= "getActiveEyeHeight",cancellable = true)
	private void tweakActiveEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> ci){
		try{
			ci.setReturnValue((RPGComponent.getEyeHeightFromProvider(this)/1.62F)*ci.getReturnValue());
		}catch(NullPointerException err){
			//fail
		}
	}

}
