package io.github.jadedchara.pentamorph.client.model.quintesson;


import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonJuror;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;



public class QuintessonJurorModel extends GeoModel<QuintessonJuror> {
	private static final Identifier MODEL = new Identifier(
		Pentamorph.MOD_ID,
		"geo/entity/quint/quintjuror.geo.json"
	);
	private static final Identifier ANIMATION = new Identifier(
		Pentamorph.MOD_ID,
		"animations/entity/quint/quintjuror.animation.json"
	);
	private static final Identifier TEXTURE = new Identifier(
		Pentamorph.MOD_ID,
		"textures/entity/quint/quintjuror.png"
	);
	@Override
	public Identifier getModelResource(QuintessonJuror juror){
		return MODEL;
	}
	public Identifier getTextureResource(QuintessonJuror juror){
		return TEXTURE;
	}
	public Identifier getAnimationResource(QuintessonJuror juror){
		return ANIMATION;
	}
}
