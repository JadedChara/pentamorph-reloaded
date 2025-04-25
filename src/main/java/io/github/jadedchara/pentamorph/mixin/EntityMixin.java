package io.github.jadedchara.pentamorph.mixin;

import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
	public EntityMixin(EntityType<?> type, World world) {
		super();
	}

	//@Shadow
	//public final float getEyeY(EntityPose pose);


	//@Inject(at=@At("RETURN"),method="getEyeHeight",cancellable = true)
	//void getTweakedEyeHeight(EntityPose pose, CallbackInfoReturnable<Float> cir){

	//}

}
