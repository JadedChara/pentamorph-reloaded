package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class RPGComponent implements RPGEdit, AutoSyncedComponent {

	public String cha = "human";
	public PlayerEntity provider;
	public float standingHeight = 1.8F;
	public float standingWidth = 0.6F;
	public float eyeHeight = 1.62f;

	public RPGComponent(PlayerEntity player){
		//this.provider=player;
	}

	public void setProvidedCharacter(PlayerEntity pr, String character, float sh, float sw, float eh) {
		this.provider = pr;
		this.cha = character;
		this.standingHeight = sh;
		this.standingWidth = sw;
		this.eyeHeight = eh;
		RPGComponentInitializer.RPG_COMPONENT.sync(pr);
		System.out.println("Syncing changes...\n [set dimensions to: "+this.standingHeight + " " + this.standingWidth +"]\n[set eye level to: "+this.eyeHeight+"]");
	}
	//@Override
	public static String getProvidedCharacter(PlayerEntity pr) {
		return RPGComponentInitializer.RPG_COMPONENT.maybeGet(pr).map(RPGComponent::getCurrentCharacter).orElse(
				"human");
	}
	public static float getProvidedEyeHeight(LivingEntity pr){
		return RPGComponentInitializer.RPG_COMPONENT.maybeGet(pr).map(RPGComponent::getEyeHeight).orElse(1.62F);
	}
	public static float getProvidedHeight(LivingEntity pr){
		return RPGComponentInitializer.RPG_COMPONENT.maybeGet(pr).map(RPGComponent::getStandingHeight).orElse(1.8F);
	}
	public static float getProvidedWidth(LivingEntity pr){
		return RPGComponentInitializer.RPG_COMPONENT.maybeGet(pr).map(RPGComponent::getStandingWidth).orElse(0.6F);
	}
	public String getCurrentCharacter(){
		return this.cha;
	}

	public float getEyeHeight(){
		return this.eyeHeight;
	}

	public float getStandingHeight(){return this.standingHeight;}
	public float getStandingWidth(){return this.standingWidth;}


	@Override
	public void readFromNbt(NbtCompound tag) {
		this.cha = tag.getString("character");
		this.eyeHeight = tag.getFloat("eyelevel");
		this.standingHeight = tag.getFloat("standHeight");
		this.standingWidth = tag.getFloat("standWidth");
	}
	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putString("character",cha);
		tag.putFloat("eyelevel",eyeHeight);
		tag.putFloat("standheight",standingHeight);
		tag.putFloat("standwidth",standingWidth);
	}
}
