package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class RPGComponent implements RPGEdit, AutoSyncedComponent {

	public String cha = "human";
	public PlayerEntity provider;

	public RPGComponent(PlayerEntity player){
		this.provider=player;
	}

	public void setProvidedCharacter(PlayerEntity provider, String character) {
		this.provider = provider;
		this.cha = character;
		RPGComponentInitializer.RPG_COMPONENT.sync(provider);
	}

	//@Override
	public static String getProvidedCharacter(PlayerEntity provider) {

		return RPGComponentInitializer.RPG_COMPONENT.maybeGet(provider).map(RPGComponent::getCurrentCharacter).orElse("human");
	}
	public String getCurrentCharacter(){
		return this.cha;
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
