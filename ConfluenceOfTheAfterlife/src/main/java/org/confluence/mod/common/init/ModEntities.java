package org.confluence.mod.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.entity.fishing.BaseFishingHook;
import org.confluence.mod.common.entity.fishing.BloodyFishingHook;
import org.confluence.mod.common.entity.fishing.CurioFishingHook;
import org.confluence.mod.common.entity.fishing.HotlineFishingHook;
import org.confluence.mod.common.entity.projectile.BaseArrowEntity;
import org.confluence.mod.common.entity.projectile.EffectThrownPotion;
import org.confluence.mod.common.entity.projectile.bombs.*;

import java.util.function.Supplier;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Confluence.MODID);

    public static final Supplier<EntityType<BaseBombEntity>> BOMB_ENTITY = ENTITIES.register("bomb_entity", () -> EntityType.Builder.<BaseBombEntity>of(BaseBombEntity::new, MobCategory.MISC).sized(BaseBombEntity.DIAMETER, BaseBombEntity.DIAMETER).clientTrackingRange(4).updateInterval(10).build("confluence:bomb_entity"));
    public static final Supplier<EntityType<BouncyBombEntity>> BOUNCY_BOMB_ENTITY = ENTITIES.register("bouncy_bomb_entity", () -> EntityType.Builder.<BouncyBombEntity>of(BouncyBombEntity::new, MobCategory.MISC).sized(0.375F, 0.375F).clientTrackingRange(4).updateInterval(10).build("confluence:bouncy_bomb_entity"));
    public static final Supplier<EntityType<ScarabBombEntity>> SCARAB_BOMB_ENTITY = ENTITIES.register("scarab_bomb_entity", () -> EntityType.Builder.<ScarabBombEntity>of(ScarabBombEntity::new, MobCategory.MISC).sized(0.375F, 0.375F).clientTrackingRange(4).updateInterval(10).build("confluence:scarab_bomb_entity"));
    public static final Supplier<EntityType<StickyBombEntity>> STICKY_BOMB_ENTITY = ENTITIES.register("sticky_bomb_entity", () -> EntityType.Builder.<StickyBombEntity>of(StickyBombEntity::new, MobCategory.MISC).sized(0.375F, 0.375F).clientTrackingRange(4).updateInterval(10).build("confluence:sticky_bomb_entity"));
    public static final Supplier<EntityType<BombFishEntity>> BOMB_FISH_ENTITY = ENTITIES.register("bomb_fish_entity", () -> EntityType.Builder.<BombFishEntity>of(BombFishEntity::new, MobCategory.MISC).sized(0.375F, 0.375F).clientTrackingRange(4).updateInterval(10).build("confluence:bomb_fish_entity"));

    public static final Supplier<EntityType<BaseArrowEntity>> ARROW_PROJECTILE = ENTITIES.register("arrow_projectile", () -> EntityType.Builder.<BaseArrowEntity>of(BaseArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build("confluence:arrow_projectile"));
    public static final Supplier<EntityType<EffectThrownPotion>> EFFECT_THROWN_POTION = ENTITIES.register("effect_thrown_potion", () -> EntityType.Builder.<EffectThrownPotion>of(EffectThrownPotion::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("confluence:effect_thrown_potion"));

    public static final Supplier<EntityType<BaseFishingHook>> BASE_FISHING_HOOK = ENTITIES.register("base_fishing_hook", () -> EntityType.Builder.<BaseFishingHook>of(BaseFishingHook::new, MobCategory.MISC).noSave().noSummon().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(5).build("confluence:base_fishing_hook"));
    public static final Supplier<EntityType<HotlineFishingHook>> HOTLINE_FISHING_HOOK = ENTITIES.register("hotline_fishing_hook", () -> EntityType.Builder.<HotlineFishingHook>of(HotlineFishingHook::new, MobCategory.MISC).noSave().noSummon().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(5).build("confluence:hotline_fishing_hook"));
    public static final Supplier<EntityType<CurioFishingHook>> CURIO_FISHING_HOOK = ENTITIES.register("curio_fishing_hook", () -> EntityType.Builder.<CurioFishingHook>of(CurioFishingHook::new, MobCategory.MISC).noSave().noSummon().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(5).build("confluence:curio_fishing_hook"));
    public static final Supplier<EntityType<BloodyFishingHook>> BLOODY_FISHING_HOOK = ENTITIES.register("bloody_fishing_hook", () -> EntityType.Builder.<BloodyFishingHook>of(BloodyFishingHook::new, MobCategory.MISC).noSave().noSummon().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(5).build("confluence:bloody_fishing_hook"));
}