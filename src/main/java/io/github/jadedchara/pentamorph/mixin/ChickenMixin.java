package io.github.jadedchara.pentamorph.mixin;

import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonLarva;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ChickenEntity.class)
public class ChickenMixin extends AnimalEntity{
	protected ChickenMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void onDeath(DamageSource dmgSrc){
		if (dmgSrc.getAttacker() instanceof QuintessonLarva){
			//Placeholder for now. It will tick up a value, and then with a 75% chance, replace Larva with Juror
			dmgSrc.getAttacker().kill();

		}
	}

	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return null;
	}
}
