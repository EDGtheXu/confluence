package org.confluence.mod.client.renderer.block;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.confluence.mod.Confluence;
import org.confluence.mod.block.common.BaseChestBlock;
import org.jetbrains.annotations.NotNull;

public class BaseChestBlockRenderer extends ChestRenderer<BaseChestBlock.Entity> {
    public static final Material LOCKED_GOLDEN = chest("locked_golden");
    public static final Material LOCKED_GOLDEN_LEFT = chest("locked_golden_left");
    public static final Material LOCKED_GOLDEN_RIGHT = chest("locked_golden_right");
    public static final Material UNLOCKED_GOLDEN = chest("unlocked_golden");
    public static final Material UNLOCKED_GOLDEN_LEFT = chest("unlocked_golden_left");
    public static final Material UNLOCKED_GOLDEN_RIGHT = chest("unlocked_golden_right");

    public BaseChestBlockRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    protected @NotNull Material getMaterial(BaseChestBlock.Entity blockEntity, @NotNull ChestType chestType) {
        return switch (blockEntity.variant) {
            case LOCKED_GOLDEN -> chooseMaterial(chestType, LOCKED_GOLDEN, LOCKED_GOLDEN_LEFT, LOCKED_GOLDEN_RIGHT);
            case UNLOCKED_GOLDEN -> chooseMaterial(chestType, UNLOCKED_GOLDEN, UNLOCKED_GOLDEN_LEFT, UNLOCKED_GOLDEN_RIGHT);
        };
    }

    private static Material chest(String pChestName) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(Confluence.MODID, "entity/chest/" + pChestName));
    }

    private static Material chooseMaterial(ChestType pChestType, Material pDoubleMaterial, Material pLeftMaterial, Material pRightMaterial) {
        return switch (pChestType) {
            case LEFT -> pLeftMaterial;
            case RIGHT -> pRightMaterial;
            case SINGLE -> pDoubleMaterial;
        };
    }
}