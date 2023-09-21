package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import io.github.jadedchara.pentamorph.Pentamorph;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class RPGManage implements RPGEdit, AutoSyncedComponent {
	public static final ComponentKey<RPGManage> RPG=
			ComponentRegistry.getOrCreate(new Identifier(Pentamorph.MOD_ID,"rpg"),RPGManage.class);
	public String cha = "player";
	public PlayerEntity provider;

	public void setProvidedCharacter(PlayerEntity provider, String character) {
		this.provider = provider;
		this.cha = character;
		RPG.sync(provider);
	}

	//@Override
	public static String getProvidedCharacter(PlayerEntity provider) {

		return RPG.maybeGet(provider).map(RPGManage::getCurrentCharacter).orElse("human");
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
