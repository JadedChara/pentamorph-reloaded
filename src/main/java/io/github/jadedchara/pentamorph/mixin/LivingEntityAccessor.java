package io.github.jadedchara.pentamorph.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface LivingEntityAccessor {
	//@Invoker
	//float callGetEyeHeight(EntityPose pose);
	//@Invoker
	//float callGetActiveEyeHeight(EntityPose pose);
}
