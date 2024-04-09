package org.confluence.mod.effect.HarmfulEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.confluence.mod.effect.ModEffects;

public class ConfusedEffect extends MobEffect { //困惑 反向移动
    public ConfusedEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B008B);
    }

    public static void onAdd(LivingEntity entity) {
        if (entity instanceof Player) {
            entity.setDeltaMovement(entity.getDeltaMovement().x() * -1, entity.getDeltaMovement().y() * -1, entity.getDeltaMovement().z() * -1);
        }
    }

    public static void onRemove(LivingEntity entity) {
        if (entity instanceof Player && entity.hasEffect(new MobEffectInstance(ModEffects.CONFUSED.get()).getEffect())) {
            entity.removeEffect(new MobEffectInstance(ModEffects.CONFUSED.get()).getEffect());
        }
    }
}
