package io.github.jadedchara.pentamorph.common.blockentity;

import io.github.jadedchara.pentamorph.common.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class SpaceBridgePortalBlockEntity extends BlockEntity {

	public SpaceBridgePortalBlockEntity(BlockPos pos, BlockState state) {
		super(BlockRegistry.SPACE_BRIDGE_PORTAL_BLOCK_ENTITY, pos, state);
	}
}
