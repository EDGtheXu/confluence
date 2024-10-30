package org.confluence.mod.common.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.confluence.mod.common.init.ModEntities;

public class IceBladeSwordProjectile extends SwordProjectile {
    public IceBladeSwordProjectile(EntityType<IceBladeSwordProjectile> entityType, Level pLevel) {
        super(entityType, pLevel);
    }

    public IceBladeSwordProjectile(LivingEntity living) {
        super(ModEntities.ICE_BLADE_SWORD_PROJECTILE.get(), living.level(), living);

    }

    @Override
    protected int getBaseDamage() {
        return 7;
    }

    @Override
    protected float getBaseKnockBack() {
        return 1.0F;
    }
}
