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

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;



public class QuintessonJuror extends PathAwareEntity implements GeoEntity {
	//basic pre-config

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
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

	private static final RawAnimation WALK = RawAnimation.begin().thenPlay("animation.quintjuror.walk");
	private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("animation.quintjuror.idle");

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "controller", 2, state ->{
			if (state.isMoving()){
				return state.setAndContinue(WALK);
			}else {
				return state.setAndContinue(IDLE);
			}
		}));
	}
	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	//modify this one...
	public static DefaultAttributeContainer.Builder createAttributes(){
		return MobEntity.createAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1D)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 40.0)
			;

	}
	//start config
	public QuintessonJuror(EntityType<? extends PathAwareEntity> type, World world){
		super(type,world);
	}

}
