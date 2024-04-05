package org.confluence.mod.effect.BeneficialEffect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ThornsEffect extends MobEffect {   //荆棘 给予伤害来源反伤
    DamageSource damageSource;
    float damage;

    public ThornsEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    public void onAdd(LivingEntity entity, float ThornMultiplier) {
        if (entity instanceof Player) {
            onThorn(entity, ThornMultiplier);
        }
    }

    @SubscribeEvent
    public void onEntityDamage(LivingHurtEvent event) {
        damage = event.getAmount();
    }

    private void onThorn(LivingEntity entity, float ThornMultiplier) {
        if (damageSource != null) {
            entity.hurt(damageSource, damage * ThornMultiplier);
        }
    }
}