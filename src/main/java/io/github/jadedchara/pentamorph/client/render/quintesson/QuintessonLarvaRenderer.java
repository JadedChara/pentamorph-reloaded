package io.github.jadedchara.pentamorph.client.render.quintesson;

import foundry.veil.api.quasar.emitters.module.ParticleModule;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import io.github.jadedchara.pentamorph.client.model.quintesson.QuintessonLarvaModel;
import io.github.jadedchara.pentamorph.common.entity.quintesson.QuintessonLarva;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class QuintessonLarvaRenderer extends GeoEntityRenderer<QuintessonLarva>{
	private ItemStack itemStack;
	private VertexConsumerProvider vertexConsumerProvider;
	public QuintessonLarvaRenderer(EntityRendererFactory.Context context){
		super(context, new QuintessonLarvaModel());
		this.shadowRadius = 0.35f;

/*adjust renderer for particle emission. Maybe have a particle emitter associated, dependent on evolution, a la Scape
 and Run?
*/
	}
	@Override
	public void render(QuintessonLarva quintLarva, float entityYaw, float partialTick, MatrixStack poseStack,
					   VertexConsumerProvider bufferSource, int packedLight){
		super.render(quintLarva, entityYaw, partialTick, poseStack, bufferSource, packedLight);
	}
}
