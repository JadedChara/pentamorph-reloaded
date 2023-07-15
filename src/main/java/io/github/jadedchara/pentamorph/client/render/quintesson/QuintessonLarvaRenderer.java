package io.github.jadedchara.pentamorph.client.render.quintesson;

import io.github.jadedchara.pentamorph.client.model.QuintessonLarvaModel;
import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonLarva;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class QuintessonLarvaRenderer extends GeoEntityRenderer<QuintessonLarva> {
	private ItemStack itemStack;
	private VertexConsumerProvider vertexConsumerProvider;
	public QuintessonLarvaRenderer(EntityRendererFactory.Context context){
		super(context, new QuintessonLarvaModel());
		this.shadowRadius = 0.35f;
	}
	@Override
	public void render(QuintessonLarva quintLarva, float entityYaw, float partialTick, MatrixStack poseStack,
					   VertexConsumerProvider bufferSource, int packedLight){
		super.render(quintLarva, entityYaw, partialTick, poseStack, bufferSource, packedLight);
	}
}
