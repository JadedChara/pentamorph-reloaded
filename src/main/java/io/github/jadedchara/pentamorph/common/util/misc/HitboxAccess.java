package io.github.jadedchara.pentamorph.common.util.misc;

import net.minecraft.entity.EntityDimensions;

public interface HitboxAccess{
	//float eyeHeight = 1.62f;
	//float crouchHeight = 1.5f;
	//float swimHeight = 0.6f;
	//float swimWidth = 0.6f;
	//EntityDimensions dimensions = EntityDimensions.changing(0.6f,1.8f);
	default void setEntitySize(EntityDimensions dimensions, float eyeHeight, float crouchHeight, float swimHeight,
					float swimWidth){

	}

	default EntityDimensions getEntitySize(){
		return null;
	};
	default float getEntityEyeLevel(){
		return 0.0f;
	};
	default float getEntityCrouchHeight(){
		return 0.0f;
	};
	default float getEntitySwimHeight(){
		return 0.0f;
	};
	default float getEntitySwimWidth(){
		return 0.0f;
	};
}
