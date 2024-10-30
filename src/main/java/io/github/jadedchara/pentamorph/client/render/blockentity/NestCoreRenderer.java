package io.github.jadedchara.pentamorph.client.render.blockentity;

import io.github.jadedchara.pentamorph.client.model.block.NestCoreModel;
import io.github.jadedchara.pentamorph.common.blockentity.NestCoreBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class NestCoreRenderer extends GeoBlockRenderer<NestCoreBlockEntity> {
	public NestCoreRenderer() {
		super(new NestCoreModel());
		super.renderLayers.addLayer(new AutoGlowingGeoLayer<>(this));

	}
}
