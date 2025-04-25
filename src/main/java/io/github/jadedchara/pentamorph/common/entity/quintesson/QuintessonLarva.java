package io.github.jadedchara.pentamorph.common.entity.quintesson;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;



public class QuintessonLarva extends PathAwareEntity implements GeoEntity {
	//basic pre-config

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	@Override
	protected void initDataTracker(DataTracker.Builder builder){
		super.initDataTracker(builder);
	}
	@Override
	protected void initGoals(){
		this.goalSelector.add(3, new WanderAroundGoal(this,1.0F));
		this.goalSelector.add(2,new LookAroundGoal(this));
		this.goalSelector.add(1, new TargetGoal<>(this, ChickenEntity.class, true, true));
		this.goalSelector.add(4,new LookAtEntityGoal(this, PlayerEntity.class,1.0F));
		this.goalSelector.add(5, new SwimGoal(this));
		this.goalSelector.add(1, new PounceAtTargetGoal(this, 0.5f));
		this.goalSelector.add(1, new MeleeAttackGoal(this, 5.0F, false));
		//this.goalSelector.add(6, new);
	}

	//modify this one

	private static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.quintlarva.walk");
	private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.quintlarva.idle");
	private static final RawAnimation CHASE = RawAnimation.begin().thenLoop("animation.quintlarva.scramble");
	private static final RawAnimation POUNCE = RawAnimation.begin().thenPlay("animation.quintlarva.pounce");
	private static final RawAnimation SLEEP = RawAnimation.begin().thenLoop("animation.quintlarva.sleep");

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "controller", 1, state -> {
			this.setBodyYaw(this.getHeadYaw());
			if (state.isMoving()) {
				return state.setAndContinue(WALK);
			} else if (this.getTarget() != null) {
				this.lookAtEntity(this.getTarget(), 180, 180);
				return state.setAndContinue(CHASE);
			} else if (this.isAttacking()) {
				return state.setAndContinue(POUNCE);
			}/* else if (this.getWorld().isRaining() || this.getWorld().isThundering()) {
				this.sleep(this.getBlockPos());
				return state.setAndContinue(SLEEP);
			}*/ else{
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
	public QuintessonLarva(EntityType<? extends PathAwareEntity> type, World world){
		super(type,world);

	}


	@Override
	public void onDeath(DamageSource src){
		//System.out.println("Bleeeeeegh.");


	}




}
