package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;

public class RPGManage implements RPGEdit, AutoSyncedComponent {
	public String cha = "player";
	@Override
	public void setCharacter(String character) {
		this.cha = character;
	}

	@Override
	public String getCharacter() {
		return cha;
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
