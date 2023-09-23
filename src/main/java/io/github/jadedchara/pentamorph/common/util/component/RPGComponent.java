package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import io.github.jadedchara.pentamorph.common.util.misc.HitboxAccess;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class RPGComponent implements RPGEdit, AutoSyncedComponent {

	public String cha = "human";
	public PlayerEntity provider;

	public EntityDimensions dimensions;
	public float crouchHeight;
	public float swimHeight;
	public float swimWidth;
	public float eyeHeight;

	public RPGComponent(PlayerEntity player){
		this.provider=player;
	}

	public void setProvidedCharacter(PlayerEntity provider, String character, EntityDimensions dimensions,
									 float crouchHeight, float swimHeight, float swimWidth, float eyeHeight) {
		this.provider = provider;
		this.cha = character;
		this.dimensions = dimensions;
		this.crouchHeight = crouchHeight;
		this.swimHeight = swimHeight;
		this.swimWidth = swimWidth;
		this.eyeHeight = eyeHeight;
		if(provider instanceof HitboxAccess){
			((HitboxAccess) provider).setEntitySize(dimensions,eyeHeight,crouchHeight,swimHeight,swimWidth);
		}
		RPGComponentInitializer.RPG_COMPONENT.sync(this.provider);
	}

	//@Override
	public static String getProvidedCharacter(PlayerEntity provider) {
		//RPGComponentInitializer.RPG_COMPONENT.maybeGet(provider).get().getCurrentCharacter();
		return RPGComponentInitializer.RPG_COMPONENT.maybeGet(provider).map(RPGComponent::getCurrentCharacter).orElse("human");
	}
	public String getCurrentCharacter(){
		return this.cha;
	}

	public float getCrouchHeight() {
		return this.crouchHeight;
	}
	public float getSwimHeight(){
		return this.swimHeight;
	}
	public float getSwimWidth(){
		return this.swimWidth;
	}
	public float getEyeHeight(){
		return this.eyeHeight;
	}
	public EntityDimensions getDimensions(){
		return this.dimensions;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		this.cha = tag.getString("character");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putString("character",cha);
	}
}
