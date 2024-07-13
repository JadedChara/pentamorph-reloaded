package io.github.jadedchara.pentamorph.mixin;


import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponentInitializer;
import io.github.jadedchara.pentamorph.common.util.misc.HitboxAccess;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements HitboxAccess, LivingEntityAccessor {
	@Shadow
	public abstract boolean isSwimming();

	@Shadow
	protected abstract void initDataTracker();

	@Shadow
	public abstract EntityDimensions getDimensions(EntityPose pose);

	@Inject(at=@At("HEAD"), method="initDataTracker",cancellable = true)
	private void initData(CallbackInfo ci){

	}
	public float tempheight = 1.8F;
	public float tempwidth = 0.6F;
	public float tempEyeLevel = 1.62F;
	public String rpgChar = "human";
	public EntityPose lastPose;
	public EntityDimensions tweaked_standing_dimensions = EntityDimensions.changing(tempwidth,tempheight);
	public ImmutableMap<Object, Object> tweaked_pose_dimensions;

	protected PlayerEntityMixin(World world) {
		super(EntityType.PLAYER, world);
	}

	@Inject(at=@At("HEAD"), method="tick", cancellable = true)
	private void dataTick(CallbackInfo ci){
		try{
			if(!this.rpgChar.equals(RPGComponent.getProvidedCharacter(this))){
				this.rpgChar = RPGComponent.getProvidedCharacter(this);
				this.tempEyeLevel = RPGComponent.getProvidedEyeHeight(this);
				this.tempheight = RPGComponent.getProvidedHeight(this);
				this.tempwidth = RPGComponent.getProvidedWidth(this);
				this.tweaked_standing_dimensions = EntityDimensions.changing(this.tempwidth,this.tempheight);
				this.tweaked_pose_dimensions = ImmutableMap.builder()
					.put(EntityPose.STANDING, this.tweaked_standing_dimensions)
					.put(EntityPose.SLEEPING, SLEEPING_DIMENSIONS)
					.put(EntityPose.FALL_FLYING, EntityDimensions.changing(this.tempwidth, this.tempwidth))
					.put(EntityPose.SWIMMING, EntityDimensions.changing(this.tempwidth, this.tempwidth))
					.put(EntityPose.SPIN_ATTACK, EntityDimensions.changing(this.tempwidth, this.tempwidth))
					.put(EntityPose.CROUCHING, EntityDimensions.changing(this.tempwidth, (1.5F/1.8F)*this.tempheight))
					.put(EntityPose.DYING, EntityDimensions.fixed(0.2F, 0.2F)).build();
				this.lastPose = this.getPose();
				this.setPose(EntityPose.CROUCHING);
				this.setPose(this.lastPose);
				System.out.println(this.getClass() + " / " + Thread.currentThread().getName());
			}
		}catch(NullPointerException ignore){
		}
		//this.updatePose();
	}

	@Inject(at = @At("HEAD"), method = "getDimensions", cancellable = true)
	private void tweakDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> ci){
		try{
			//System.out.println(this.rpgChar + " / ("+this.getClass()+")");
			//this.callGetEyeHeight(pose,EntityDimensions.changing(tempwidth,tempheight));
			//System.out.println(this.rpgChar + " /Source: ("+this.getClass()+")");
			//System.out.println(this.tweaked_standing_dimensions);

			if(!this.rpgChar.equals("human")){
				ci.setReturnValue((EntityDimensions) this.tweaked_pose_dimensions.getOrDefault(pose,
					this.tweaked_standing_dimensions));
			}

		}catch(NullPointerException err){
			System.out.println("[Failed to Adjust Scaling]");
		}

	}

	@Inject(at = @At("RETURN"), method= "getActiveEyeHeight",cancellable = true)
	private void tweakActiveEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> ci){
		try{
			ci.setReturnValue((RPGComponent.getProvidedEyeHeight(this)/1.62F)*ci.getReturnValue());


		}catch(NullPointerException err){
			System.out.println("[Failed to adjust eye level]");
		}
	}

}
