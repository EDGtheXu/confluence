package org.confluence.mod.common.init.item;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.item.bow.RoutineBowItem;
import org.confluence.mod.common.item.bow.ShortBowItem;
import org.confluence.terra_curio.common.component.ModRarity;

public class BowItems {
    public static final DeferredRegister.Items BOWS = DeferredRegister.createItems(Confluence.MODID);

    public static final DeferredItem<ShortBowItem> WOODEN_SHORT_BOW = BOWS.register("wooden_short_bow", () -> new ShortBowItem(4.0F, 384, ModRarity.WHITE));
    public static final DeferredItem<ShortBowItem> COPPER_SHORT_BOW = BOWS.register("copper_short_bow", () -> new ShortBowItem(4.5F, 640, ModRarity.WHITE));
    public static final DeferredItem<ShortBowItem> TIN_SHORT_BOW = BOWS.register("tin_short_bow", () -> new ShortBowItem(4.5F, 768, ModRarity.WHITE));
    public static final DeferredItem<ShortBowItem> IRON_SHORT_BOW = BOWS.register("iron_short_bow", () -> new ShortBowItem(5.0F, 896, ModRarity.WHITE));
    public static final DeferredItem<ShortBowItem> LEAD_SHORT_BOW = BOWS.register("lead_short_bow", () -> new ShortBowItem(5.0F, 1024, ModRarity.WHITE));
    public static final DeferredItem<ShortBowItem> SILVER_SHORT_BOW = BOWS.register("silver_short_bow", () -> new ShortBowItem(5.5F, 1152, ModRarity.WHITE));
    public static final DeferredItem<ShortBowItem> TUNGSTEN_SHORT_BOW = BOWS.register("tungsten_short_bow", () -> new ShortBowItem(5.5F, 1280, ModRarity.WHITE));
    public static final DeferredItem<ShortBowItem> GOLDEN_SHORT_BOW = BOWS.register("golden_short_bow", () -> new ShortBowItem(6.0F, 1408, ModRarity.WHITE));
    public static final DeferredItem<ShortBowItem> PLATINUM_SHORT_BOW = BOWS.register("platinum_short_bow", () -> new ShortBowItem(6.0F, 1536, ModRarity.WHITE));


    public static final DeferredItem<RoutineBowItem> COPPER_BOW = BOWS.register("copper_bow", () -> new RoutineBowItem(3.0F, 640, ModRarity.WHITE));
    public static final DeferredItem<RoutineBowItem> TIN_BOW = BOWS.register("tin_bow", () -> new RoutineBowItem(3.0F, 768, ModRarity.WHITE));
    public static final DeferredItem<RoutineBowItem> IRON_BOW = BOWS.register("iron_bow", () -> new RoutineBowItem(3.5F, 896, ModRarity.WHITE));
    public static final DeferredItem<RoutineBowItem> LEAD_BOW = BOWS.register("lead_bow", () -> new RoutineBowItem(3.5F, 1024, ModRarity.WHITE));
    public static final DeferredItem<RoutineBowItem> SILVER_BOW = BOWS.register("silver_bow", () -> new RoutineBowItem(4.0F, 1152, ModRarity.WHITE));
    public static final DeferredItem<RoutineBowItem> TUNGSTEN_BOW = BOWS.register("tungsten_bow", () -> new RoutineBowItem(4.0F, 1280, ModRarity.WHITE));
    public static final DeferredItem<RoutineBowItem> GOLDEN_BOW = BOWS.register("golden_bow", () -> new RoutineBowItem(4.5F, 1408, ModRarity.WHITE));
    public static final DeferredItem<RoutineBowItem> PLATINUM_BOW = BOWS.register("platinum_bow", () -> new RoutineBowItem(4.5F, 1536, ModRarity.WHITE));

    @OnlyIn(Dist.CLIENT)
    public static void registerProperties() {
        ResourceLocation pull = ResourceLocation.withDefaultNamespace("pull");
        ClampedItemPropertyFunction shortBowPull = (itemStack, clientLevel, living, speed) -> living != null && living.getUseItem() == itemStack ? (float) (itemStack.getUseDuration(living) - living.getUseItemRemainingTicks()) / ShortBowItem.MAX_DRAW_DURATION : 0.0F;
        ClampedItemPropertyFunction bowPull = (itemStack, clientLevel, living, speed) -> living != null && living.getUseItem() == itemStack ? (float) (itemStack.getUseDuration(living) - living.getUseItemRemainingTicks()) / BowItem.MAX_DRAW_DURATION : 0.0F;
        ResourceLocation pulling = ResourceLocation.withDefaultNamespace("pulling");
        ClampedItemPropertyFunction bowPulling = (itemStack, clientLevel, living, speed) -> living != null && living.isUsingItem() && living.getUseItem() == itemStack ? 1.0F : 0.0F;

        ItemProperties.register(WOODEN_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(WOODEN_SHORT_BOW.get(), pulling, bowPulling);
        ItemProperties.register(COPPER_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(COPPER_SHORT_BOW.get(), pulling, bowPulling);
        ItemProperties.register(TIN_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(TIN_SHORT_BOW.get(), pulling, bowPulling);
        ItemProperties.register(IRON_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(IRON_SHORT_BOW.get(), pulling, bowPulling);
        ItemProperties.register(LEAD_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(LEAD_SHORT_BOW.get(), pulling, bowPulling);
        ItemProperties.register(SILVER_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(SILVER_SHORT_BOW.get(), pulling, bowPulling);
        ItemProperties.register(TUNGSTEN_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(TUNGSTEN_SHORT_BOW.get(), pulling, bowPulling);
        ItemProperties.register(GOLDEN_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(GOLDEN_SHORT_BOW.get(), pulling, bowPulling);
        ItemProperties.register(PLATINUM_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(PLATINUM_SHORT_BOW.get(), pulling, bowPulling);

        ItemProperties.register(COPPER_BOW.get(), pull, bowPull);
        ItemProperties.register(COPPER_BOW.get(), pulling, bowPulling);
        ItemProperties.register(TIN_BOW.get(), pull, bowPull);
        ItemProperties.register(TIN_BOW.get(), pulling, bowPulling);
        ItemProperties.register(IRON_BOW.get(), pull, bowPull);
        ItemProperties.register(IRON_BOW.get(), pulling, bowPulling);
        ItemProperties.register(LEAD_BOW.get(), pull, bowPull);
        ItemProperties.register(LEAD_BOW.get(), pulling, bowPulling);
        ItemProperties.register(SILVER_BOW.get(), pull, bowPull);
        ItemProperties.register(SILVER_BOW.get(), pulling, bowPulling);
        ItemProperties.register(TUNGSTEN_BOW.get(), pull, bowPull);
        ItemProperties.register(TUNGSTEN_BOW.get(), pulling, bowPulling);
        ItemProperties.register(GOLDEN_BOW.get(), pull, bowPull);
        ItemProperties.register(GOLDEN_BOW.get(), pulling, bowPulling);
        ItemProperties.register(PLATINUM_BOW.get(), pull, bowPull);
        ItemProperties.register(PLATINUM_BOW.get(), pulling, bowPulling);
    }
}
