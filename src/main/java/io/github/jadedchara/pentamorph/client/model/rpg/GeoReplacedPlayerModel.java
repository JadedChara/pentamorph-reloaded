package io.github.jadedchara.pentamorph.client.model.rpg;

import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.entity.rpg.GeoReplacedPlayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

import java.util.concurrent.ExecutionException;

public class GeoReplacedPlayerModel extends GeoModel<GeoReplacedPlayer> {
	/*public Identifier MODEL;
	public Identifier TEXTURE;
	public Identifier ANIMATION;*/

	public Identifier MODEL = new Identifier(
			Pentamorph.MOD_ID,
			"geo/entity/quint/quintlarva.geo.json"
	);
	public Identifier ANIMATION = new Identifier(
			Pentamorph.MOD_ID,
			"animations/entity/quint/quintlarva.animation.json"
	);
	public Identifier TEXTURE = new Identifier(
			Pentamorph.MOD_ID,
			"textures/entity/quint/quintlarva.png"
	);
	@Override
	public Identifier getModelResource(GeoReplacedPlayer anim) {
		//return MODEL;
		if(anim.character == "human"){
			//
		}
		return this.MODEL;
	};
	@Override
	public Identifier getTextureResource(GeoReplacedPlayer anim) {
		if(anim.character == "human"){
			/*MinecraftClient.getInstance().getSkinProvider().loadSkin(
					anim.gameProfile,
					((type, identifier,minecraftProfileTexture) -> {
						this.TEXTURE = identifier;
					}), true);*/

			this.TEXTURE = MinecraftClient.getInstance().getSkinProvider().getSkin(anim.gameProfile).texture();
		}
		return this.TEXTURE;
	};
	@Override
	public Identifier getAnimationResource(GeoReplacedPlayer anim) {
		if(anim.character == "human"){
			//
		}
		return this.ANIMATION;
	};

	//configuration resources
	public void setModel(String character){
		MODEL = new Identifier(Pentamorph.MOD_ID,"geo/entity/rpg/"+character+".geo.json");
	}
	public void setTexture(String character){
		TEXTURE = new Identifier(Pentamorph.MOD_ID,"textures/entity/rpg"+character+".png");
	}
	public void setAnimation(String character){
		ANIMATION = new Identifier(Pentamorph.MOD_ID,"animations/entity/rpg/"+character+".animation.json");
	}
	public GeoReplacedPlayerModel getInst(){
		return this;
	}
}
