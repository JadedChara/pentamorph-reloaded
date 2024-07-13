package io.github.jadedchara.pentamorph.common.block;

import io.github.jadedchara.pentamorph.common.blockentity.SpaceBridgePortalBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SpaceBridgePortalBlock extends Block implements BlockEntityProvider {
	public SpaceBridgePortalBlock(Settings settings) {
		super(settings);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new SpaceBridgePortalBlockEntity(pos, state);
	}
}
