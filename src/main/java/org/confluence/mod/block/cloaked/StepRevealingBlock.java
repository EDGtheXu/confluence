package org.confluence.mod.block.cloaked;

import com.google.gson.JsonObject;
import de.dafuqs.revelationary.RevelationRegistry;
import net.minecraft.commands.arguments.blocks.BlockStateParser;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class StepRevealingBlock extends Block {
    public static final IntegerProperty REVEAL_STEP = IntegerProperty.create("reveal_step", 0, 2);

    public StepRevealingBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(REVEAL_STEP, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(REVEAL_STEP);
    }

    public static void create(int state, int step, Block... blocks) {
        JsonObject stateJson = new JsonObject();
        JsonObject nameJson = new JsonObject();
        for (Block block : blocks) {
            String stateStr = BlockStateParser.serialize(block.defaultBlockState().setValue(REVEAL_STEP, state));
            stateJson.addProperty(stateStr, "minecraft:deepslate");
            nameJson.addProperty(stateStr, "block.minecraft.deepslate");
        }
        JsonObject revJson = new JsonObject();
        revJson.addProperty("advancement", "confluence:reveal/step" + step);
        revJson.add("block_states", stateJson);
        revJson.add("block_name_replacements", nameJson);
        RevelationRegistry.registerFromJson(revJson);
    }
}
