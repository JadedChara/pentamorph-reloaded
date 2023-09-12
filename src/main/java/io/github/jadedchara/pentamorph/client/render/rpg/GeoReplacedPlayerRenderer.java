package io.github.jadedchara.pentamorph.client.render.rpg;

import io.github.jadedchara.pentamorph.client.model.rpg.GeoReplacedPlayerModel;
import io.github.jadedchara.pentamorph.common.util.GeoReplacedPlayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;


public class GeoReplacedPlayerRenderer extends GeoReplacedEntityRenderer<PlayerEntity, GeoReplacedPlayer> {

	public GeoReplacedPlayerRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new GeoReplacedPlayerModel(), new GeoReplacedPlayer());
	}
}
