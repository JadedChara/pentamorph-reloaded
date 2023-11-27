package io.github.jadedchara.pentamorph.mixin;


import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
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

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements HitboxAccess{
	@Shadow
	public abstract boolean isSwimming();

	@Shadow
	public abstract EntityDimensions getDimensions(EntityPose pose);

	@Shadow
	protected abstract void initDataTracker();

	@Inject(at=@At("HEAD"), method="initDataTracker",cancellable = true)
	private void initData(CallbackInfo ci){
		//
	}
	public float tempheight = 1.8F;
	public float tempwidth = 0.6F;

	protected PlayerEntityMixin(World world) {
		super(EntityType.PLAYER, world);
		super.calculateDimensions();
		super.initDataTracker();
	}


	/*@Inject(at = @At("RETURN"), method = "getDimensions", cancellable = true)
	private void tweakDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> ci){
		if(
				((RPGComponent.getProvidedWidth(this) != tempwidth)&&(RPGComponent.getProvidedHeight(this) !=tempheight))
						||((RPGComponent.getProvidedWidth(this) != tempwidth)||(RPGComponent.getProvidedHeight(this) !=tempheight))
		){
			tempheight = RPGComponent.getProvidedHeight(this);
			tempwidth = RPGComponent.getProvidedWidth(this);
			System.out.println(this.getHeight()+":"+this.getWidth());
		}
		try{
			ci.setReturnValue(new EntityDimensions(
					(tempwidth/0.6F)*ci.getReturnValue().width,
					(tempheight/1.8F)*ci.getReturnValue().height,
					false
			));
		}catch(NullPointerException err){
			System.out.println("[Failed To adjust dimensions]");
		}

	}*/
	@Inject(at = @At("RETURN"), method= "getActiveEyeHeight",cancellable = true)
	private void tweakActiveEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> ci){
		try{
			ci.setReturnValue((RPGComponent.getProvidedEyeHeight(this)/1.62F)*ci.getReturnValue());
		}catch(NullPointerException err){
			System.out.println("[Failed to adjust eye level]");
		}
	}

}
