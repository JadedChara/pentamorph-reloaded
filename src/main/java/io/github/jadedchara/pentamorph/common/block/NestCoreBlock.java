package io.github.jadedchara.pentamorph.common.block;

import com.mojang.serialization.MapCodec;
import io.github.jadedchara.pentamorph.common.blockentity.NestCoreBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NestCoreBlock extends BlockWithEntity {
	public NestCoreBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return null;
	}

	@Nullable
	@Override
	public BlockRenderType getRenderType(BlockState state){
		return BlockRenderType.ANIMATED;

	}


	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new NestCoreBlockEntity(pos,state);

	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return super.getTicker(world, state, type);
	}
}
