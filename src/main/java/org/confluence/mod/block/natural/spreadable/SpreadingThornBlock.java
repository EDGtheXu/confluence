package org.confluence.mod.block.natural.spreadable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.confluence.mod.block.natural.ThornBlock;
import org.jetbrains.annotations.NotNull;

public class SpreadingThornBlock extends ThornBlock implements ISpreadable {
    private final Type type;

    public SpreadingThornBlock(float amount, Block ground, Type type){
        super(amount, ground);
        this.type = type;
        registerDefaultState(stateDefinition.any().setValue(STILL_ALIVE, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        super.createBlockStateDefinition(builder);
        builder.add(STILL_ALIVE);
    }


    @Override
    public Type getType(){
        return type;
    }

    @Override
    public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom){
        if(!pLevel.isAreaLoaded(pPos, 3)) return;
        spread(pState, pLevel, pPos, pRandom);
        super.randomTick(pState, pLevel, pPos, pRandom);
    }
}
