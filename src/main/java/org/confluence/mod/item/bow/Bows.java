package org.confluence.mod.item.bow;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;
import org.confluence.mod.misc.ModRarity;

import static org.confluence.mod.item.ModItems.ITEMS;

public final class Bows {
    public static final RegistryObject<ShortBowItem> WOODEN_SHORT_BOW = ITEMS.register("wooden_short_bow", () -> new ShortBowItem(4.0F, new Item.Properties().rarity(ModRarity.WHITE).durability(384)));
    public static final RegistryObject<ShortBowItem> COPPER_SHORT_BOW = ITEMS.register("copper_short_bow", () -> new ShortBowItem(4.5F, new Item.Properties().rarity(ModRarity.BLUE).durability(640)));

    @OnlyIn(Dist.CLIENT)
    public static void registerPull() {
        ResourceLocation pull = new ResourceLocation("pull");
        ClampedItemPropertyFunction shortBowPull = (itemStack, clientLevel, living, speed) -> living != null && living.getUseItem() == itemStack ? (float) (itemStack.getUseDuration() - living.getUseItemRemainingTicks()) / ShortBowItem.FULL_POWER_TICKS : 0.0F;
        ResourceLocation pulling = new ResourceLocation("pulling");
        ClampedItemPropertyFunction shortBowPulling = (itemStack, clientLevel, living, speed) -> living != null && living.isUsingItem() && living.getUseItem() == itemStack ? 1.0F : 0.0F;

        ItemProperties.register(WOODEN_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(WOODEN_SHORT_BOW.get(), pulling, shortBowPulling);
        ItemProperties.register(COPPER_SHORT_BOW.get(), pull, shortBowPull);
        ItemProperties.register(COPPER_SHORT_BOW.get(), pulling, shortBowPulling);
    }

    public static void init() {}
}
