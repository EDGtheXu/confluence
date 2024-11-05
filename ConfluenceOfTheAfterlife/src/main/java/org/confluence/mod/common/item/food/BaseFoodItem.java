package org.confluence.mod.common.item.food;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public class BaseFoodItem extends Item {
    protected static final Consumer<BaseFoodItem.Builder> NO_BUILDER = builder -> {};
    protected Builder builder;

    public BaseFoodItem(Builder builder) {
        super(builder.properties);
        this.builder = builder;
    }

    public static Builder builder(String name) {
        return new Builder(name, new Properties());
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return builder.duration.apply(stack);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return builder.useAnim.apply(stack);
    }

    @Override
    public @NotNull SoundEvent getDrinkingSound() {
        return builder.drinkingSoundType.apply(null);
    }

    @Override
    public @NotNull SoundEvent getEatingSound() {
        return builder.eatingSoundType.apply(null);
    }


    public static class Builder {
        private final String name;
        private final Properties properties;
        private FoodProperties foodProperties;
        private Item item;
        private Function<ItemStack, Integer> duration = duration -> 0;
        private Function<Void, SoundEvent> drinkingSoundType = sound -> SoundEvents.EMPTY;
        private Function<Void, SoundEvent> eatingSoundType = sound -> SoundEvents.EMPTY;
        private Function<ItemStack, UseAnim> useAnim = useAnim -> UseAnim.NONE;

        Builder(String name, Properties properties) {
            this.name = name;
            this.properties = properties;
        }

        public void isFireResistant() {
            this.properties.fireResistant();
        }

        public Builder food(FoodProperties foodProperties) {
            this.foodProperties = foodProperties;
            if (foodProperties != null) {
                properties.food(foodProperties);
            }
            return this;
        }

        public Builder craftRemainder(Item item) {
            this.item = item;
            if (item != null) {
                properties.craftRemainder(item);
            }
            return this;
        }

        public Builder duration(Function<ItemStack, Integer> duration) {
            this.duration = duration;
            return this;
        }

        public Builder useAnim(Function<ItemStack, UseAnim> useAnim) {
            this.useAnim = useAnim;
            return this;
        }

        public Builder drinkingSound(Function<Void, SoundEvent> drinkingSoundType) {
            this.drinkingSoundType = drinkingSoundType;
            return this;
        }

        public Builder eatingSound(Function<Void, SoundEvent> eatingSoundType) {
            this.eatingSoundType = eatingSoundType;
            return this;
        }

        public Builder initialize() {
            properties.stacksTo(64);
            return this;
        }

        public BaseFoodItem build() {
            return new BaseFoodItem(initialize());
        }
    }
}
