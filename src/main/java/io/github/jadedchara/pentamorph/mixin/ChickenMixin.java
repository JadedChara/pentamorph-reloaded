package io.github.jadedchara.pentamorph.mixin;

import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonLarva;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.ChickenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ChickenEntity.class)
public class ChickenMixin {
	//@Inject(at = @At("RETURN"),method = "onDeath", cancellable = true)
	public void onDeath(DamageSource dmgSrc){
		if (dmgSrc.getAttacker() instanceof QuintessonLarva){
			//Replace Larva with Juror
			dmgSrc.getAttacker().kill();

		}
	}
}
