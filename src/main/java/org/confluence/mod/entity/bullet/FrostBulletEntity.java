package org.confluence.mod.entity.bullet;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.confluence.mod.client.particle.ModParticles;
import org.confluence.mod.entity.ModEntities;

public class FrostBulletEntity extends BaseBulletEntity {
    public FrostBulletEntity(EntityType<BaseBulletEntity> entityType, Level level) {
        super(entityType, level);
    }

    public FrostBulletEntity(Player player, Level level) {
        this(ModEntities.FROST_BULLET.get(), level);
        setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        setOwner(player);
    }

    @Override
    public SimpleParticleType getParticle() {
        return ModParticles.RUBY_BULLET.get();
    }

    @Override
    protected double getGravity() {
        return 0.5;
    }
}
