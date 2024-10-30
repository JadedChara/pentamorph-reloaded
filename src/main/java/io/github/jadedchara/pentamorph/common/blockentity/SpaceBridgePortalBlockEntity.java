package io.github.jadedchara.pentamorph.common.blockentity;

import io.github.jadedchara.pentamorph.common.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.List;


public class SpaceBridgePortalBlockEntity extends BlockEntity {

	public SpaceBridgePortalBlockEntity(BlockPos pos, BlockState state) {
		super(BlockRegistry.SPACE_BRIDGE_PORTAL_BLOCK_ENTITY, pos, state);
	}
	public static void tick(World world, BlockPos pos, BlockState state, SpaceBridgePortalBlockEntity entity){
		/*List<? extends PlayerEntity> playerEntities = world.getPlayers();
		for ( PlayerEntity p : playerEntities){
			if (p.getBlockPos().isWithinDistance(entity.getPos(),16)){
				//
			}
		}*/

	}
}
