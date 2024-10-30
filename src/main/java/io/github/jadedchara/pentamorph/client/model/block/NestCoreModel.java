package io.github.jadedchara.pentamorph.client.model.block;

import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.blockentity.NestCoreBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class NestCoreModel extends DefaultedBlockGeoModel<NestCoreBlockEntity> {

	public NestCoreModel() {
		super(new Identifier(Pentamorph.MOD_ID,"nest_core"));

	}
	@Override
	public RenderLayer getRenderType(NestCoreBlockEntity animatable, Identifier texture) {
		return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
	}
}
