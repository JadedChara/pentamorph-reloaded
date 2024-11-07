package io.github.jadedchara.pentamorph.common.block;

import com.mojang.serialization.MapCodec;
import io.github.jadedchara.pentamorph.common.BlockRegistry;
import io.github.jadedchara.pentamorph.common.blockentity.NestCoreBlockEntity;
import io.github.jadedchara.pentamorph.common.util.misc.PSMAccess;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class NestCoreBlock extends BlockWithEntity {
	public NestCoreBlockEntity attachedEntity;
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
		attachedEntity = new NestCoreBlockEntity(pos,state);
		return attachedEntity;

	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		//return super.getTicker(world, state, type);
		return checkType(type, BlockRegistry.NEST_CORE_BLOCK_ENTITY,NestCoreBlockEntity::tick);

	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context){
		return VoxelShapes.cuboid(0.0f,0.0f,0.0f,1.0f,0.5f,1.0f);
	}

	@Override
	public void onBroken(WorldAccess world, BlockPos pos, BlockState state){
		/*try{
			((PSMAccess)this.attachedEntity.getPsm()).erase(this.attachedEntity.getSporeEmitter());
		}catch(Exception e){
			System.out.println(e);

			this.attachedEntity.getPsm().clear();

		}*/
	}

}
