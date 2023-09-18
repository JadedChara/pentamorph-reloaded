package io.github.jadedchara.pentamorph.common.entity.rpg;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import software.bernie.geckolib.animatable.GeoReplacedEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;

public class GeoReplacedPlayer implements GeoReplacedEntity {
	public String character = "quintlarva";
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private static final RawAnimation WALK = RawAnimation.begin().thenPlay("animation.quintlarva.walk");
	private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("animation.quintlarva.idle");
	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, state ->{
			if (state.isMoving()){
				return state.setAndContinue(WALK);
			}else {
				return state.setAndContinue(IDLE);
			}
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Override
	public EntityType<?> getReplacingEntityType() {
		return EntityType.PLAYER;

	}

	public UUID playerid;
	public GameProfile gameProfile;

	public void setPlayerid(){

	}
}
