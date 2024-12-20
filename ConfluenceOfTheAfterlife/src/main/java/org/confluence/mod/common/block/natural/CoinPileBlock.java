package org.confluence.mod.common.block.natural;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.confluence.mod.common.init.ModSoundEvents;
import org.confluence.mod.common.init.ModTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CoinPileBlock extends FallingBlock {
    public static final MapCodec<CoinPileBlock> CODEC = simpleCodec(CoinPileBlock::new);
    public static final BooleanProperty ISBASE = BooleanProperty.create("isbase");
    private static final IntegerProperty HEAPS = IntegerProperty.create("heaps", 1, 12);
    private static final VoxelShape ONE_CUBE = box(3.0, 0.0, 3.0, 13.0, 3.0, 13.0);
    private static final VoxelShape TWO_CUBES = box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0);
    private static final VoxelShape THREE_CUBES = box(3.0, 0.0, 3.0, 13.0, 5.0, 13.0);
    private static final VoxelShape FOUR_CUBES = box(3.0, 0.0, 3.0, 13.0, 9.0, 13.0);
    private static final VoxelShape FIVE_CUBES = box(3.0, 0.0, 3.0, 13.0, 11.0, 13.0);
    private static final VoxelShape SIX_CUBES = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);

    public CoinPileBlock(Properties properties) {
        super(properties.sound(ModSoundEvents.Types.COIN));
        registerDefaultState(stateDefinition.any().setValue(HEAPS, 1).setValue(ISBASE, true));
    }

    public CoinPileBlock() {
        this(Properties.of());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        int heaps = state.getValue(HEAPS);
        if (heaps <= 1) {
            return ONE_CUBE;
        } else if (heaps <= 3) {
            return TWO_CUBES;
        } else if (heaps == 4) {
            return THREE_CUBES;
        } else if (heaps == 5) {
            return FOUR_CUBES;
        } else if (heaps <= 8) {
            return FIVE_CUBES;
        } else {
            return SIX_CUBES;
        }
    }

    @Override
    public boolean useShapeForLightOcclusion(@NotNull BlockState state) {
        return true;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext UseContext) {
        return !UseContext.isSecondaryUseActive() && UseContext.getItemInHand().is(asItem()) && state.getValue(HEAPS) < 12 || super.canBeReplaced(state, UseContext);
    }


    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState clickedBlockState = context.getLevel().getBlockState(context.getClickedPos());
        if (clickedBlockState.is(this) && clickedBlockState.getValue(HEAPS) < 12) {
            return clickedBlockState.cycle(HEAPS);
        }
        return defaultBlockState().setValue(ISBASE, !isCoinPileBlock(context.getLevel().getBlockState(context.getClickedPos().below())));
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockState Below = level.getBlockState(pos.below());
        return isCoinPileBlock(Below) || !Below.isAir();
    }

    @Override
    protected @NotNull MapCodec<CoinPileBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction facing, @NotNull BlockState blockstate, @NotNull LevelAccessor level, @NotNull BlockPos CurrentPos, @NotNull BlockPos facingPos) {
        level.scheduleTick(CurrentPos, this, getDelayAfterPlace());
        if (blockstate.isAir()) {
            return state;
        }
        if (facing == Direction.DOWN) {
            return state.setValue(ISBASE, !isCoinPileBlock(blockstate));
        }
        return state;
    }

    private boolean isCoinPileBlock(BlockState blockState) {
        return blockState.is(this) || blockState.is(ModTags.Blocks.COIN_PILE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HEAPS, ISBASE);
    }

    @Override
    public void onLand(Level pLevel, BlockPos pPos, BlockState pState, BlockState pReplaceableState, FallingBlockEntity pFallingBlock) {
        pLevel.setBlockAndUpdate(pPos, pState.setValue(ISBASE, !isCoinPileBlock(pLevel.getBlockState(pPos.below()))));
    }
}
