package org.confluence.mod.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import top.theillusivec4.curios.Curios;

import static org.confluence.mod.Confluence.MODID;

public final class ModTags {
    public static final TagKey<Block> NEEDS_4_LEVEL = BlockTags.create(new ResourceLocation(MODID, "needs_4_level"));
    public static final TagKey<Block> NEEDS_5_LEVEL = BlockTags.create(new ResourceLocation(MODID, "needs_5_level"));
    public static final TagKey<Block> NEEDS_6_LEVEL = BlockTags.create(new ResourceLocation(MODID, "needs_6_level"));
    public static final TagKey<Block> NEEDS_7_LEVEL = BlockTags.create(new ResourceLocation(MODID, "needs_7_level"));
    public static final TagKey<Block> NEEDS_8_LEVEL = BlockTags.create(new ResourceLocation(MODID, "needs_8_level"));
    public static final TagKey<Block> FLOWER_BOOTS_AVAILABLE = BlockTags.create(new ResourceLocation(MODID, "flower_boots_available"));

    public static final TagKey<Item> CURIO = ItemTags.create(new ResourceLocation(Curios.MODID, "curio"));
    public static final TagKey<Item> PROVIDE_MANA = ItemTags.create(new ResourceLocation(MODID, "provide_mana"));
    public static final TagKey<Item> PROVIDE_LIFE = ItemTags.create(new ResourceLocation(MODID, "provide_life"));
    public static final TagKey<Item> COIN = ItemTags.create(new ResourceLocation(MODID, "coin"));

    public static final TagKey<Fluid> FISHING_ABLE = FluidTags.create(new ResourceLocation(MODID, "fishing_able"));

    public static final TagKey<DamageType> HARMFUL_EFFECT = TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID, "harmful_effect"));
}
