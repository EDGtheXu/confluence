package org.confluence.mod.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static net.minecraft.world.item.ItemStack.ATTRIBUTE_MODIFIER_FORMAT;

public final class ModUtils {
    public static float nextFloat(RandomSource randomSource, float origin, float bound) {
        if (origin >= bound) {
            throw new IllegalArgumentException("bound - origin is non positive");
        } else {
            return origin + randomSource.nextFloat() * (bound - origin);
        }
    }

    public static void createItemEntity(Item item, int count, double x, double y, double z, Level level) {
        if (count <= 0) return;
        ItemEntity itemEntity = new ItemEntity(level, x, y, z, new ItemStack(item, count));
        itemEntity.setPickUpDelay(40);
        level.addFreshEntity(itemEntity);
    }

    public static Component getModifierTooltip(double amount, String type) {
        boolean b = amount > 0.0;
        amount *= 100.0;
        return Component.translatable(
            "prefix.confluence.tooltip." + (b ? "plus" : "take"),
            ATTRIBUTE_MODIFIER_FORMAT.format(b ? amount : -amount),
            Component.translatable("prefix.confluence.tooltip." + type)
        ).withStyle(b ? ChatFormatting.BLUE : ChatFormatting.RED);
    }

    @SuppressWarnings("unchecked")
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> getTicker(BlockEntityType<A> a, BlockEntityType<E> b, BlockEntityTicker<? super E> ticker) {
        return a == b ? (BlockEntityTicker<A>) ticker : null;
    }
}
