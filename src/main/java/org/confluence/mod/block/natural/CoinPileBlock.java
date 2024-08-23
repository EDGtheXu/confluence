package org.confluence.mod.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import org.confluence.mod.block.ModBlocks;
import org.confluence.mod.datagen.limit.CustomItemModel;
import org.confluence.mod.datagen.limit.CustomModel;
import org.confluence.mod.misc.ModSoundEvents;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CoinPileBlock extends FallingBlock implements CustomModel, CustomItemModel {
    public static final BooleanProperty IS_BASE = BooleanProperty.create("is_base");
    private static final IntegerProperty HEAPS = IntegerProperty.create("heaps", 1, 12);
    private static final VoxelShape ONE_CUBE = Block.box(3.0, 0.0, 3.0, 13.0, 3.0, 13.0);
    private static final VoxelShape TWO_CUBES = Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0);
    private static final VoxelShape THREE_CUBES = Block.box(3.0, 0.0, 3.0, 13.0, 5.0, 13.0);
    private static final VoxelShape FOUR_CUBES = Block.box(3.0, 0.0, 3.0, 13.0, 9.0, 13.0);
    private static final VoxelShape FIVE_CUBES = Block.box(3.0, 0.0, 3.0, 13.0, 11.0, 13.0);
    private static final VoxelShape SIX_CUBES = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);

    public CoinPileBlock() {
        super(Properties.of().sound(ModSoundEvents.Types.COIN));
        registerDefaultState(stateDefinition.any().setValue(HEAPS, 1).setValue(IS_BASE, true));
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
        Level level = context.getLevel();
        BlockState clickedBlockState = level.getBlockState(context.getClickedPos());
        if (clickedBlockState.is(this) && clickedBlockState.getValue(HEAPS) < 12) {
            return clickedBlockState.cycle(HEAPS);
        }

        return clickedBlockState.setValue(IS_BASE, !isCoinPileBlock(level.getBlockState(context.getClickedPos().below())));
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockState Below = level.getBlockState(pos.below());
        return isCoinPileBlock(Below) || !Below.isAir();
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState pState, @NotNull Direction pFacing, @NotNull BlockState pFacingState, @NotNull LevelAccessor pLevel, @NotNull BlockPos pCurrentPos, @NotNull BlockPos pFacingPos) {
        pLevel.scheduleTick(pCurrentPos, this, this.getDelayAfterPlace());
        return pState.setValue(IS_BASE, !isCoinPileBlock(pLevel.getBlockState(pCurrentPos.below())));
    }

    private boolean isCoinPileBlock(BlockState blockState) {
        return blockState.is(this) || blockState.is(ModBlocks.COPPER_COIN_PILE.get()) || blockState.is(ModBlocks.SILVER_COIN_PILE.get()) ||
            blockState.is(ModBlocks.GOLDEN_COIN_PILE.get()) || blockState.is(ModBlocks.PLATINUM_COIN_PILE.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HEAPS, IS_BASE);
    }
}
