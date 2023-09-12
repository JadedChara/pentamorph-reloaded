package io.github.jadedchara.pentamorph.client.model.rpg;

import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.util.GeoReplacedPlayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GeoReplacedPlayerModel extends GeoModel<GeoReplacedPlayer> {
	/*public Identifier MODEL;
	public Identifier TEXTURE;
	public Identifier ANIMATION;*/
	private static final Identifier MODEL = new Identifier(Pentamorph.MOD_ID,"geo/entity/quint/quintlarva.geo.json");
	private static final Identifier ANIMATION = new Identifier(Pentamorph.MOD_ID,"animations/entity/quint/quintlarva.animation.json");
	private static final Identifier TEXTURE = new Identifier(Pentamorph.MOD_ID,"textures/entity/quint/quintlarva.png");
	@Override
	public Identifier getModelResource(GeoReplacedPlayer anim) {return MODEL;};
	@Override
	public Identifier getTextureResource(GeoReplacedPlayer anim) {return TEXTURE;};
	@Override
	public Identifier getAnimationResource(GeoReplacedPlayer anim) {return ANIMATION;};

	//configuration resources
	/*public void setMODEL(String character){
		MODEL = new Identifier(Pentamorph.MOD_ID,"geo/entity/rpg/"+character+".geo.json");
	}
	public void setTEXTURE(String character){
		TEXTURE = new Identifier(Pentamorph.MOD_ID,"textures/entity/rpg"+character+".png");
	}
	public void setANIMATION(String character){
		ANIMATION = new Identifier(Pentamorph.MOD_ID,"animations/entity/rpg/"+character+".animation.json");
	}*/
}
