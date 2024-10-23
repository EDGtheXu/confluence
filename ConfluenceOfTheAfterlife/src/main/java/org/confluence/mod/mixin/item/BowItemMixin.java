package org.confluence.mod.mixin.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.confluence.mod.common.item.bow.ShortBowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BowItem.class)
public abstract class BowItemMixin {
    @WrapOperation(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BowItem;getPowerForTime(I)F"))
    private float shortBowPower(int pCharge, Operation<Float> original, @Local(argsOnly = true) ItemStack itemStack) {
        if (itemStack.getItem() instanceof ShortBowItem shortBow) {
            return shortBow.getShortPowerForTime(pCharge);
        }
        return original.call(pCharge);
    }

    @WrapOperation(method = "shootProjectile", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;shootFromRotation(Lnet/minecraft/world/entity/Entity;FFFFF)V"))
    private void shortBowSoot(Projectile instance, Entity shooter, float x, float y, float z, float velocity, float inaccuracy, Operation<Void> original) {
        if ((Object) this instanceof ShortBowItem shortBow) {
            original.call(instance, shooter, x, y, z, velocity * shortBow.getVelocityMultiplier() / 3.0F, inaccuracy);
        } else {
            original.call(instance, shooter, x, y, z, velocity, inaccuracy);
        }
    }
}
