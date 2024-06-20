package org.confluence.mod.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.confluence.mod.util.ModUtils;
import org.jetbrains.annotations.NotNull;

public class MoneyHoleEntity extends Entity {
    private int age;

    public MoneyHoleEntity(EntityType<MoneyHoleEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.age = 0;
    }

    public MoneyHoleEntity(Level level, Vec3 position) {
        super(ModEntities.MONEY_HOLE.get(), level);
        setNoGravity(true);
        setPos(position);
        setDeltaMovement(0.0, 0.5, 0.0);
        this.age = 0;
    }

    @Override
    public void tick() {
        super.tick();
        setDeltaMovement(getDeltaMovement().scale(0.96));
        move(MoverType.SELF, getDeltaMovement());
        if (!level().isClientSide && age % 10 == 0) {
            ModUtils.dropMoney(random.nextInt(9, 82), getX(), getY(), getZ(), level());
        }
        if (this.age++ > 200) discard();
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag pCompound) {}

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag pCompound) {}
}
