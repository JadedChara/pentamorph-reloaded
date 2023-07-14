package io.github.jadedchara.pentamorph.common.entity.quintesson;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class QuintessonLarva extends PathAwareEntity implements IAnimatable {
	//basic pre-config
	private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
	@Override
	protected void initDataTracker(){
		super.initDataTracker();
	}
	//modify this one
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		return PlayState.STOP;
	}
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}
	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	//modify this one...
	public static DefaultAttributeContainer.Builder createAttributes(){
		return MobEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1f)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.1)
				;
	}
	//start config
	public QuintessonLarva(EntityType<? extends PathAwareEntity> type, World world){
		super(type,world);;
	}
}
