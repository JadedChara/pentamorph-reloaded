package io.github.jadedchara.pentamorph.common;

import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponent;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

public class SubcomponentRegistry {
	public static void init(){

	}
	public static final ComponentKey<RPGComponent> RPG=
			ComponentRegistry.getOrCreate(Identifier.of(Pentamorph.MOD_ID,"rpg"),RPGComponent.class);
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
