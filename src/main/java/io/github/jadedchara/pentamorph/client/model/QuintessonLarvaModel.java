package io.github.jadedchara.pentamorph.client.model;


import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonLarva;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class QuintessonLarvaModel extends AnimatedGeoModel<QuintessonLarva> {
	private static final Identifier MODEL = new Identifier(Pentamorph.MOD_ID,"geo/entity/quint/quintlarva.geo.json");
	private static final Identifier ANIMATION = new Identifier(Pentamorph.MOD_ID,"animations/entity/quint/quintlarva" +
			".animation.json");
	private static final Identifier TEXTURE = new Identifier(Pentamorph.MOD_ID,"textures/entity/quint/quintlarva.png");
	@Override
	public Identifier getModelResource(QuintessonLarva larva){
		return MODEL;
	}
	public Identifier getTextureResource(QuintessonLarva larva){
		return TEXTURE;
	}
	public Identifier getAnimationResource(QuintessonLarva larva){
		return ANIMATION;
	}
}
