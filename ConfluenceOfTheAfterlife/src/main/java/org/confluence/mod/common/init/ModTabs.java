package org.confluence.mod.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.init.block.ModDecorativeBlocks;
import org.confluence.mod.common.init.block.ModOreBlocks;
import org.confluence.mod.common.init.item.*;


public final class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Confluence.MODID);

    //TODO 图标暂时为空
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING_BLOCKS = TABS.register("building_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.AIR))
                    .title(Component.translatable("creativetab.confluence.building_blocks"))
                    .displayItems((parameters, output) -> {
                        ModDecorativeBlocks.BLOCKS.getEntries().forEach(block -> output.accept(block.get()));
                    })
                    .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NATURAL_BLOCKS = TABS.register("natural_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.AIR))
                    .title(Component.translatable("creativetab.confluence.natural_blocks"))
                    .displayItems((parameters, output) -> {
                        ModOreBlocks.BLOCKS.getEntries().forEach(block -> output.accept(block.get()));
                    })
                    .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVES = TABS.register("misc",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.AIR))
                    .title(Component.translatable("creativetab.confluence.misc"))
                    .displayItems((parameters, output) -> {
                        //ModBlocks.BOX.get().getAll(Registries.BLOCK).forEach(entry -> output.accept(entry.get(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY));
                    })
                    .build()
    );
    // 材料
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MATERIALS = TABS.register("materials",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MaterialItems.CRYSTAL_SHARDS_ITEM.get()))
                    .title(Component.translatable("creativetab.confluence.materials"))
                    .displayItems((parameters, output) -> {
                        MaterialItems.MATERIALS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build());
    // 工具
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOOLS = TABS.register("tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(IconItems.TOOLS_ICON.get()))
                    .title(Component.translatable("creativetab.confluence.tools"))
                    .displayItems((parameters, output) -> {
                        AxeItems.AXE.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build());
    // 战士武器
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WARRIORS = TABS.register("warriors",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SwordItems.COPPER_BOARD_SWORD.get()))
                    .title(Component.translatable("creativetab.confluence.warriors"))
                    .displayItems((parameters, output) -> {
                        SwordItems.SWORDS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build());
    // 射手武器
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SHOOTERS = TABS.register("shooters",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BowItems.COPPER_SHORT_BOW.get()))
                    .title(Component.translatable("creativetab.confluence.rangers"))
                    .displayItems((parameters, output) -> {
                        BowItems.BOWS.getEntries().forEach(item -> output.accept(item.get()));
                        ArrowItems.ARROWS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build());
    // 法师武器
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGES = TABS.register("mages",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(IconItems.ITEM_ICON.get()))
                    .title(Component.translatable("creativetab.confluence.mages"))
                    .displayItems((parameters, output) -> {

                    })
                    .build());
    // 召唤武器
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SUMMONERS = TABS.register("summoners",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(IconItems.ITEM_ICON.get()))
                    .title(Component.translatable("creativetab.confluence.summoners"))
                    .displayItems((parameters, output) -> {

                    })
                    .build());
    // 开发者物品
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEVELOPER = TABS.register("developer",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(IconItems.ITEM_ICON.get()))
                    .title(Component.translatable("creativetab.confluence.developer"))
                    .displayItems((parameters, output) -> {

                    })
                    .build());
    // 食物与药水
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FOOD_AND_POTIONS = TABS.register("food_and_potions",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(IconItems.FOOD_ICON.get()))
                    .title(Component.translatable("creativetab.confluence.food_and_potions"))
                    .displayItems((parameters, output) -> {
                            TerraPotions.POTIONS.getEntries().forEach(item -> output.accept(item.get()));
                            FoodItems.FOODS.getEntries().forEach(item -> output.accept(item.get()));
                    })
                    .build());
    // 盔甲
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ARMORS = TABS.register("armors",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(IconItems.ITEM_ICON.get()))
                    .title(Component.translatable("creativetab.confluence.armors"))
                    .displayItems((parameters, output) -> {

                    })
                    .build());
    // 器械
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MECHANICAL = TABS.register("mechanical",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(IconItems.ITEM_ICON.get()))
                    .title(Component.translatable("creativetab.confluence.mechanical"))
                    .displayItems((parameters, output) -> {

                    })
                    .build());
}