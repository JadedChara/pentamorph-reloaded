package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import io.github.jadedchara.pentamorph.common.util.misc.HitboxAccess;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class RPGComponent implements RPGEdit, AutoSyncedComponent {

	public String cha = "human";
	public PlayerEntity provider;

	public EntityDimensions dimensions = EntityDimensions.changing(0.6f,1.8f);
	public float crouchHeight = 1.5f;
	public float swimHeight = 0.6f;
	public float swimWidth = 0.6f;
	public float eyeHeight = 1.62f;

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
		if(provider instanceof ServerPlayerEntity){
			RPGComponentInitializer.RPG_COMPONENT.sync(this.provider);
		}
		provider.calculateDimensions();
		//RPGComponentInitializer.RPG_COMPONENT.sync(this.provider);
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

	/*
	STATIC METHODS
	 */
	public static @Nullable Float getEyeHeightFromProvider(LivingEntity provider) {
		RPGComponent r = RPGComponentInitializer.RPG_COMPONENT.getNullable(provider);
		return r==null ? null: r.getEyeHeight();
	}
	public static @Nullable EntityDimensions getDimensionsFromProvider(LivingEntity provider){
		RPGComponent r = RPGComponentInitializer.RPG_COMPONENT.getNullable(provider);
		return r==null ? null: r.getDimensions();
	}

	///**/

	@Override
	public void readFromNbt(NbtCompound tag) {
		this.cha = tag.getString("character");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putString("character",cha);
	}

	/*public EntityDimensions readDimensions(NbtCompound tag){
		return new EntityDimensions(tag.getString("dim"),);
	}
	public void writeDimensions (NbtCompound tag, Float height,Float width){
		tag.putString("dim",width+"-"+height);
	}*/
}
