package org.confluence.mod.common.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MeteoriteOre extends Block {
    public MeteoriteOre() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops());
    }

    @Override
    public void stepOn(Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Entity entity) {
        if (level.isClientSide) return;
        entity.igniteForTicks(100);
    }
}
