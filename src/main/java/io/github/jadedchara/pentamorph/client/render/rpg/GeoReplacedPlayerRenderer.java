package io.github.jadedchara.pentamorph.client.render.rpg;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import io.github.jadedchara.pentamorph.client.model.rpg.GeoReplacedPlayerModel;
import io.github.jadedchara.pentamorph.common.entity.rpg.GeoReplacedPlayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.player.PlayerEntity;

import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;


public class GeoReplacedPlayerRenderer extends GeoReplacedEntityRenderer<PlayerEntity, GeoReplacedPlayer> {
	public GeoReplacedPlayerRenderer(EntityRendererFactory.Context renderManager) {

		super(renderManager, new GeoReplacedPlayerModel(), new GeoReplacedPlayer());
		if(super.getCurrentEntity() != null){
			super.getAnimatable().playerid = super.getCurrentEntity().getUuid();
		};
		if(super.getCurrentEntity() !=null) {
			
			super.getAnimatable().gameProfile = super.getCurrentEntity().getGameProfile();
		};
	}
	//@Override
	//public setModel
}
