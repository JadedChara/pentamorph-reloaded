package io.github.jadedchara.pentamorph.common.entity.quintesson;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
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
	@Override
	protected void initGoals(){
		this.goalSelector.add(3, new WanderAroundGoal(this,1.0F));
		this.goalSelector.add(2,new LookAroundGoal(this));
		this.goalSelector.add(1, new TargetGoal<>(this, ChickenEntity.class, true, true));
		this.goalSelector.add(4,new LookAtEntityGoal(this, PlayerEntity.class,1.0F));
		this.goalSelector.add(5, new SwimGoal(this));
		this.goalSelector.add(1, new MeleeAttackGoal(this, 5.0F, false));
		//this.goalSelector.add(6, new);
	}

	//modify this one
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.isAttacking()){
			System.out.println("IsAttacking = true!");
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.quintlarva.scramble",
					ILoopType.EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}else if(event.isMoving()){
			System.out.println("IsMoving = true!");
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.quintlarva.walk",
					ILoopType.EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}else if(this.isAlive()){
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.quintlarva.idle",
					ILoopType.EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
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
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 40.0)
				;
	}
	//start config
	public QuintessonLarva(EntityType<? extends PathAwareEntity> type, World world){
		super(type,world);

	}
}
