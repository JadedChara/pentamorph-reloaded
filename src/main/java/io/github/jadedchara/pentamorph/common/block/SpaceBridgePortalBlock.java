package io.github.jadedchara.pentamorph.common.block;

import com.mojang.serialization.MapCodec;
import io.github.jadedchara.pentamorph.common.blockentity.SpaceBridgePortalBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.state.property.Properties;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

public class SpaceBridgePortalBlock extends BlockWithEntity {
	public static final BooleanProperty POWERED = Properties.POWERED;
	public static final DirectionProperty FACING = Properties.FACING;
	public SpaceBridgePortalBlock(Settings settings) {
		super(settings);
		try {
			this.setDefaultState(
				this
					.getStateManager()
					.getDefaultState()
					.with(FACING, Direction.NORTH)
					.with(POWERED, false));
		}catch(Exception E){

		}
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return null;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new SpaceBridgePortalBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return super.getTicker(world, state, type);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> GameEventListener getGameEventListener(ServerWorld world, T blockEntity) {
		return super.getGameEventListener(world, blockEntity);
	}
}
