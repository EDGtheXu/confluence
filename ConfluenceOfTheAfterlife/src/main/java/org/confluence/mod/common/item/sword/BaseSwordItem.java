package org.confluence.mod.common.item.sword;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.ItemAbility;
import org.apache.commons.lang3.function.TriConsumer;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.item.sword.stagedy.EffectStrategy;
import org.confluence.mod.common.item.sword.stagedy.InventoryTickStrategy;
import org.confluence.mod.common.item.sword.stagedy.ProjectileStrategy;
import org.confluence.mod.common.item.sword.stagedy.projectile.AbstractProjContainer;
import org.confluence.terra_curio.common.component.ModRarity;
import org.confluence.terra_curio.common.init.TCDataComponentTypes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class BaseSwordItem extends SwordItem {
    public ModifierBuilder modifier;
    /**
     * MC白色剑
     */
    public BaseSwordItem(Tier tier, int rawDamage, float rawSpeed) {
        this(tier, ModRarity.WHITE, rawDamage, rawSpeed);
    }
    /**
     * MC带颜色的剑
     */
    public BaseSwordItem(Tier tier, ModRarity rarity, int rawDamage, float rawSpeed) {
        super(tier, new Item.Properties()
                .component(TCDataComponentTypes.MOD_RARITY, rarity)
                .component(DataComponents.ATTRIBUTE_MODIFIERS,
                        createAttributes(tier,rawDamage, rawSpeed))
        );
        this.modifier = new ModifierBuilder();
    }
    /**
     * TR带特殊效果的刀
     */
    public BaseSwordItem(Tier tier, ModRarity rarity, int rawDamage, float rawSpeed, ModifierBuilder modifier) {
        super(tier, new Item.Properties()
                .component(TCDataComponentTypes.MOD_RARITY, rarity)
                .component(DataComponents.ATTRIBUTE_MODIFIERS,
                        modifier.attributeModifiersBuilder
                                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, rawDamage + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, rawSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                                .build())
        );
        this.modifier = modifier;
    }

    public static class ModifierBuilder {
        public AbstractProjContainer proj;
        public List<BiConsumer<LivingEntity,LivingEntity>> onHitEffects = new ArrayList<>();
        public QuaConsumer<ItemStack,Level,Entity,Boolean> inventoryTick;
        public ItemAttributeModifiers.Builder attributeModifiersBuilder = ItemAttributeModifiers.builder();
        private int modifyCount = 0;
        public boolean canPerformSweep = true;
        private float sweepRange = 3.0F;

        /**
         * 添加击中效果
         * @see EffectStrategy
         */
        public ModifierBuilder addOnHitEffect(BiConsumer<LivingEntity,LivingEntity> onHit){
            this.onHitEffects.add(onHit);
            return this;
        }

        /**
         * 添加属性修改器
         */
        public ModifierBuilder addAttributeModifier(Holder<Attribute> attribute, float amount, AttributeModifier.Operation operation){
            this.attributeModifiersBuilder.add(attribute,new AttributeModifier(Confluence.asResource("sword.modifier."+modifyCount++),amount,operation),EquipmentSlotGroup.MAINHAND);
            return this;
        }

        /**
         * 设置弹幕
         * @see ProjectileStrategy
         */
        public ModifierBuilder setProj(AbstractProjContainer proj){
            this.proj = proj;
            return this;
        }

        /**
         * 禁用横扫
         */
        public ModifierBuilder setCanNotPerformSweep(){
            this.canPerformSweep = false;
            return this;
        }

        /**
         * 设置横扫范围
         */
        public ModifierBuilder setSweepRange(float sweepRange){
            this.sweepRange = sweepRange;
            return this;
        }

        /**
         * 背包每刻效果
         * @see InventoryTickStrategy
         */
        public ModifierBuilder setInventoryTick(QuaConsumer<ItemStack,Level,Entity,Boolean> inventoryTick){
            this.inventoryTick = inventoryTick;
            return this;
        }


    }

    @Override
    public @NotNull AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        return super.getSweepHitBox(stack, player, target).inflate(modifier.sweepRange);
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility itemAbility) {
        return super.canPerformAction(stack, itemAbility) && modifier.canPerformSweep;
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(modifier.inventoryTick!= null) modifier.inventoryTick.accept(stack,level,entity,isSelected);
    }

    @FunctionalInterface
    public interface QuaConsumer<A,B,C,D> {
        void accept(A a,B b,C c,D d);
    }



    protected ItemAttributeModifiers modifiers;
    protected void addAttributeModifiers(Consumer<ItemAttributeModifiers.Builder> consumer) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        consumer.accept(builder);
        this.modifiers = builder.build();
    }
    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers(@NotNull ItemStack stack) {
        return modifiers == null ? super.getDefaultAttributeModifiers(stack) : modifiers;
    }
    @Override
    public @NotNull MutableComponent getName(@NotNull ItemStack pStack) {
        return Component.translatable(getDescriptionId()).withStyle(style -> style.withColor(pStack.get(TCDataComponentTypes.MOD_RARITY).getColor()));
    }
}
