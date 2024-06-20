package org.confluence.mod.block.natural.herbs;

import net.minecraft.core.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import org.confluence.mod.block.natural.*;
import org.confluence.mod.item.*;
import org.jetbrains.annotations.*;

public class ShineRoot extends BaseHerbBlock {
	@Override
	public boolean mayPlaceOn(@NotNull BlockState groundState, @NotNull BlockGetter worldIn, @NotNull BlockPos pos){
		return groundState.is(Blocks.DIRT) || groundState.is(Blocks.MUD);
	}

	@Override
	protected @NotNull ItemLike getBaseSeedId(){
		return ModItems.SHINE_ROOT_SEED.get();
	}

	@Override
	public boolean canBloom(ServerLevel world, BlockState state){
		return getAge(state) == MAX_AGE;
	}

	@Override
	public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom){
		int age = getAge(pState);
		if(age == MAX_AGE){
			pLevel.setBlock(pPos, pState.setValue(AGE, MAX_AGE - 1), 2);
		}else if(age == MAX_AGE - 1){
			pLevel.setBlock(pPos, pState.setValue(AGE, MAX_AGE), 2);
		}
		super.randomTick(pState, pLevel, pPos, pRandom);
	}

	@Override
	public boolean isRandomlyTicking(@NotNull BlockState pState){
		return true;
	}
}