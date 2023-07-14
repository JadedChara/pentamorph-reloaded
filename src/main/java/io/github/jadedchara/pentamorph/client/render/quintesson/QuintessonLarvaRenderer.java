package io.github.jadedchara.pentamorph.client.render.quintesson;

import io.github.jadedchara.pentamorph.client.model.QuintessonLarvaModel;
import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonLarva;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class QuintessonLarvaRenderer extends GeoEntityRenderer<QuintessonLarva> {
	public QuintessonLarvaRenderer(EntityRendererFactory.Context context){
		super(context, new QuintessonLarvaModel());
		this.shadowRadius = 0.35f;
	}
}
