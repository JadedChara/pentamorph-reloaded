package io.github.jadedchara.pentamorph.mixin;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Inject(at=@At("RETURN"),method="getEyeHeight",cancellable = true)
	void getTweakedEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> cir){
		if((LivingEntity)(Object)this instanceof PlayerEntity){
			//cir.setReturnValue(0.0F);
		}
	}

}
