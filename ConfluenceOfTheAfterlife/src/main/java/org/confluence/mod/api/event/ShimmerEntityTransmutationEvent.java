package org.confluence.mod.api.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.confluence.mod.common.data.saved.ConfluenceData;
import org.confluence.mod.common.data.saved.GamePhase;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class ShimmerEntityTransmutationEvent extends Event {
    public static final ArrayList<EntityTransmutation> ENTITY_TRANSMUTATION = new ArrayList<>();
    protected final Entity sourceEntity;
    protected int coolDown;
    protected double speedY;

    public ShimmerEntityTransmutationEvent(Entity source) {
        this.sourceEntity = source;
        this.coolDown = 200;
        this.speedY = 0.6;
    }

    public Entity getSource() {
        return sourceEntity;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getSpeedY() {
        return speedY;
    }

    public static class Pre extends ShimmerEntityTransmutationEvent implements ICancellableEvent {
        private int transformTime = 20;

        public Pre(Entity source) {
            super(source);
        }

        public void setTransformTime(int transformTime) {
            this.transformTime = transformTime;
        }

        public int getTransformTime() {
            return transformTime;
        }
    }

    public static class Post extends ShimmerEntityTransmutationEvent {
        private Entity target;

        public Post(Entity source) {
            super(source);
        }

        public void setTarget(Entity entity) {
            this.target = entity;
        }

        public @Nullable Entity getTarget() {
            if (target == null) {
                int ordinal = ConfluenceData.get((ServerLevel) sourceEntity.level()).getGamePhase().ordinal();
                for (EntityTransmutation transmutation : ENTITY_TRANSMUTATION) {
                    if (ordinal < transmutation.gamePhase.ordinal()) continue;
                    if (transmutation.source.test(sourceEntity)) {
                        this.target = transmutation.target.create(sourceEntity.level());
                        if (target == null) continue;
                        target.setPos(sourceEntity.position());
                        target.setXRot(sourceEntity.getXRot());
                        target.setYRot(sourceEntity.getYRot());
                        target.setYHeadRot(sourceEntity.getYHeadRot());
                        if (target instanceof LivingEntity livingTarget && sourceEntity instanceof LivingEntity livingSource) {
                            float ratio = livingSource.getHealth() / livingSource.getMaxHealth();
                            livingTarget.setHealth(livingTarget.getMaxHealth() * ratio);
                        }
                        return target;
                    }
                }
            }
            return target;
        }
    }

    public static void addEntity(EntityType<?> source, EntityType<?> target) {
        ENTITY_TRANSMUTATION.add(new EntityTransmutation(entity -> entity.getType() == source, target, GamePhase.BEFORE_SKELETRON));
    }

    public static void addEntity(Predicate<Entity> source, EntityType<?> target) {
        ENTITY_TRANSMUTATION.add(new EntityTransmutation(source, target, GamePhase.BEFORE_SKELETRON));
    }

    public static void addEntity(EntityType<?> source, EntityType<?> target, GamePhase gamePhase) {
        ENTITY_TRANSMUTATION.add(new EntityTransmutation(entity -> entity.getType() == source, target, gamePhase));
    }

    public static void addEntity(Predicate<Entity> source, EntityType<?> target, GamePhase gamePhase) {
        ENTITY_TRANSMUTATION.add(new EntityTransmutation(source, target, gamePhase));
    }

    public record EntityTransmutation(Predicate<Entity> source, EntityType<?> target, GamePhase gamePhase) {}
}
