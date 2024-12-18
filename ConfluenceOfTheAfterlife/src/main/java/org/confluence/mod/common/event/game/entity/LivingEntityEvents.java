package org.confluence.mod.common.event.game.entity;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.*;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.CommonConfigs;
import org.confluence.mod.common.effect.beneficial.ArcheryEffect;
import org.confluence.mod.common.effect.beneficial.ThornsEffect;
import org.confluence.mod.common.effect.harmful.ManaSicknessEffect;
import org.confluence.mod.common.init.ModDamageTypes;
import org.confluence.mod.common.item.sword.BaseSwordItem;
import org.confluence.mod.util.ModUtils;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = Confluence.MODID)
public final class LivingEntityEvents {
    @SubscribeEvent
    public static void livingDeath(LivingDeathEvent event) {
        LivingEntity living = event.getEntity();
        if (event.getSource().getEntity() instanceof ServerPlayer) {
            if (CommonConfigs.DROP_MONEY.get() && living instanceof Enemy) {
                Level level = living.level();
                AttributeInstance attack = living.getAttribute(Attributes.ATTACK_DAMAGE);
                AttributeInstance armor = living.getAttribute(Attributes.ARMOR);
                double healthFactor = living.getMaxHealth() * 0.05;
                double attackFactor = attack == null ? 0.0 : attack.getValue() * 0.25;
                double armorFactor = armor == null ? 0.45 : (armor.getValue() + 1.0) * 0.45;
                double difficultyFactor = switch (level.getDifficulty()) {
                    case PEACEFUL -> 0.5;
                    case EASY -> 0.75;
                    case NORMAL -> 1.0;
                    case HARD -> 1.5;
                };
                int amount = (int) Math.min(Math.round((healthFactor + attackFactor + armorFactor) * difficultyFactor), 7290L);
                ModUtils.dropMoney(amount, living.getX(), living.getEyeY() - 0.3, living.getZ(), level);
            }
        }
    }

    @SubscribeEvent
    public static void livingHeal(LivingHealEvent event) {

    }

    @SubscribeEvent
    public static void livingBreathe(LivingBreatheEvent event) {

    }

    @SubscribeEvent
    public static void finalizeSpawn(FinalizeSpawnEvent event) {

    }

    @SubscribeEvent
    public static void livingEquipmentChange(LivingEquipmentChangeEvent event) {

    }

    @SubscribeEvent
    public static void livingEntityUseItem$tick(LivingEntityUseItemEvent.Tick event) {

    }

    @SubscribeEvent
    public static void livingDamage$Pre(LivingDamageEvent.Pre event) {
        LivingEntity living = event.getEntity();
        if (living.level().isClientSide) return;
        DamageSource damageSource = event.getSource();
        if (damageSource.is(DamageTypes.FELL_OUT_OF_WORLD) || damageSource.is(DamageTypes.GENERIC_KILL)) return;

        if (damageSource.is(ModDamageTypes.BOULDER) && living.getType().is(Tags.EntityTypes.BOSSES)) {
            event.setNewDamage(0.0F);
            return;
        }

        float amount = event.getNewDamage();

        ThornsEffect.apply(living, damageSource.getEntity(), amount);
        //MagicCuffs.apply(living, damageSource, amount);

        amount = ArcheryEffect.apply(living, damageSource, amount);
        amount = ManaSicknessEffect.apply(damageSource, amount);
        //amount = BreathingReed.apply(living, damageSource, amount);

        event.setNewDamage(amount);
    }

    @SubscribeEvent
    public static void livingDamage$Post(LivingDamageEvent.Post event) {
        DamageSource damageSource = event.getSource();
        LivingEntity damageEntity = event.getEntity();
        if (damageSource.getEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.getItemInHand(event.getEntity().getUsedItemHand()).getItem() instanceof BaseSwordItem sword) {
                if (sword.modifier != null) {
                    sword.modifier.onHitEffects.forEach(effect -> effect.accept(livingEntity,damageEntity));
                }
            }
        }
    }
}
