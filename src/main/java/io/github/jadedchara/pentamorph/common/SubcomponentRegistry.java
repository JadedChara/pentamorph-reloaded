package io.github.jadedchara.pentamorph.common;

import com.mojang.datafixers.TypeRewriteRule;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class SubcomponentRegistry {
	public static void init(){

	}
	public static final ComponentKey<RPGComponent> RPG=
			ComponentRegistry.getOrCreate(new Identifier(Pentamorph.MOD_ID,"rpg"),RPGComponent.class);
	/*public static String getProvidedCharacter(PlayerEntity provider){
		String cha = RPG.maybeGet(provider).map(RPGManage::getProvidedCharacter).orElse("Unconfigured");
		//System.out.print("Character set to: [" + cha + "]");
		return cha;
	}
	public static void setProvidedCharacter(PlayerEntity provider, String character){
		RPG.get(provider).setProvidedCharacter(character);
		RPG.sync(provider);
		//System.out.print("Character set to: [" + character + "]");
	}*/
}
