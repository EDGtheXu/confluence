package org.confluence.mod.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidType;
import org.confluence.mod.capability.ability.AbilityProvider;
import org.confluence.mod.effect.beneficial.ObsidianSkinEffect;
import org.confluence.mod.effect.neutral.ShimmerEffect;
import org.confluence.mod.fluid.ModFluids;
import org.confluence.mod.fluid.ShimmerEntityTransmutationEvent;
import org.confluence.mod.item.curio.CurioItems;
import org.confluence.mod.item.curio.combat.IFireImmune;
import org.confluence.mod.item.curio.combat.IHurtEvasion;
import org.confluence.mod.item.curio.expert.RoyalGel;
import org.confluence.mod.item.curio.expert.ShieldOfCthulhu;
import org.confluence.mod.item.curio.movement.IFallResistance;
import org.confluence.mod.util.CuriosUtils;
import org.confluence.mod.util.IEntity;
import org.confluence.mod.util.IFishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntity {
    @Shadow
    public abstract DamageSources damageSources();

    @Shadow
    protected abstract BlockPos getOnPos(float p_216987_);

    @Shadow(remap = false)
    public abstract FluidType getEyeInFluidType();

    @Shadow
    public abstract void discard();

    @Shadow
    public abstract void setNoGravity(boolean pNoGravity);

    @Unique
    private int c$cthulhuSprintingTime = 0;
    @Unique
    private boolean c$isShouldRot = false;
    @Unique
    private boolean c$isInShimmer = false;
    @Unique
    private boolean c$wasInShimmer = false;
    @Unique
    private int c$coolDown = 0;
    @Unique
    private int c$transforming = 0;
    @Unique
    private boolean c$originalNoGravity = false;

    @Override
    public void c$setCoolDown(int ticks) {
        this.c$coolDown = ticks;
    }

    @Override
    public void c$setOriginalNoGravity(boolean bool) {
        this.c$originalNoGravity = bool;
    }

    @Override
    public int c$getCthulhuSprintingTime() {
        return c$cthulhuSprintingTime;
    }

    @Override
    public void c$setCthulhuSprintingTime(int amount) {
        this.c$cthulhuSprintingTime = amount;
    }

    @Override
    public void c$setShouldRot(boolean bool) {
        this.c$isShouldRot = bool;
    }

    @Override
    public boolean c$isShouldRot() {
        return c$isShouldRot;
    }

    @Redirect(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isInLava()Z"))
    private boolean resetLavaImmune(Entity instance) {
        AtomicBoolean inLava = new AtomicBoolean(instance.isInLava());
        if (c$getSelf() instanceof Player living) {
            BlockPos onPos = living.getOnPos();
            if (living.level().getFluidState(onPos).is(FluidTags.LAVA) && !living.level().getFluidState(onPos.above()).is(FluidTags.LAVA)) return false;
            living.getCapability(AbilityProvider.CAPABILITY).ifPresent(playerAbility -> {
                if (inLava.get()) {
                    if (playerAbility.decreaseLavaImmuneTicks()) {
                        inLava.set(false);
                    }
                } else {
                    playerAbility.increaseLavaImmuneTicks();
                }
            });
        }
        return inLava.get();
    }

    @Inject(method = "isInvulnerableTo", at = @At("RETURN"), cancellable = true)
    private void immune(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (damageSource.is(DamageTypes.FELL_OUT_OF_WORLD)) return;
        if (c$getSelf() instanceof LivingEntity living) {
            if (IHurtEvasion.isInvul(living) ||
                IFallResistance.isInvul(living, damageSource) ||
                IFireImmune.isInvul(living, damageSource) ||
                ObsidianSkinEffect.isInvul(living, damageSource) ||
                ShimmerEffect.isInvul(living, damageSource) ||
                RoyalGel.isInvul(living, damageSource) ||
                ShieldOfCthulhu.isInvul(living)
            ) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "setSprinting", at = @At("TAIL"))
    private void sprinting(boolean bool, CallbackInfo ci) {
        if (bool && c$getSelf() instanceof Player living) {
            if (c$cthulhuSprintingTime == 0 && CuriosUtils.hasCurio(living, CurioItems.SHIELD_OF_CTHULHU.get())) {
                ShieldOfCthulhu.apply(living);
                this.c$cthulhuSprintingTime = 32;
            }
        }
    }

    @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.BEFORE))
    private void tickProfiler(CallbackInfo ci) {
        if (c$cthulhuSprintingTime > 0) this.c$cthulhuSprintingTime--;
        if (c$coolDown < 0) this.c$coolDown = 0;
        this.c$isInShimmer = getEyeInFluidType() == ModFluids.SHIMMER.fluidType().get();
        if (c$isInShimmer) {
            Entity self = c$getSelf();
            if (!self.level().isClientSide && c$coolDown == 0) {
                ShimmerEntityTransmutationEvent.Pre pre = new ShimmerEntityTransmutationEvent.Pre(self);
                if (MinecraftForge.EVENT_BUS.post(pre)) {
                    c$setup(self, pre.getCoolDown());
                } else if (c$transforming < pre.getTransformTime()) {
                    this.c$transforming++;
                    self.addDeltaMovement(new Vec3(0.0, -5.0E-4F, 0.0));
                } else {
                    ShimmerEntityTransmutationEvent.Post post = new ShimmerEntityTransmutationEvent.Post(self);
                    MinecraftForge.EVENT_BUS.post(post);
                    Entity target = post.getTarget();
                    if (target != null) {
                        discard();
                        c$setup(target, post.getCoolDown());
                        self.level().addFreshEntity(target);
                        return;
                    }
                }
            }

            if (!c$wasInShimmer && self instanceof Projectile projectile) {
                Vec3 motion = projectile.getDeltaMovement();
                projectile.setDeltaMovement(motion.x, -motion.y, motion.z);
                this.c$wasInShimmer = true;
            }
        } else {
            this.c$transforming = 0;
            if (c$coolDown > 0) this.c$coolDown--;
            if (!c$originalNoGravity && c$coolDown == 0) setNoGravity(false);
            this.c$wasInShimmer = false;
        }
    }

    @Inject(method = "playerTouch", at = @At("TAIL"))
    private void collidingCheck(Player player, CallbackInfo ci) {
        IEntity iEntity = (IEntity) player;
        if (iEntity.c$isOnCthulhuSprinting()) {
            Entity self = c$getSelf();
            Vec3 vector = player.getDeltaMovement();
            self.addDeltaMovement(new Vec3(vector.x * 1.6, 0.6, vector.z * 1.6));
            self.hurt(damageSources().playerAttack(player), 7.8F);
            player.setDeltaMovement(vector.scale(-0.9));
            iEntity.c$setCthulhuSprintingTime(20);
        }
    }

    @Inject(method = "fireImmune", at = @At("RETURN"), cancellable = true)
    private void fireProof(CallbackInfoReturnable<Boolean> cir) {
        if (c$getSelf() instanceof IFishingHook fishingHook) {
            cir.setReturnValue(fishingHook.c$isLavaHook());
        }
    }

    @Unique
    private Entity c$getSelf() {
        return (Entity) (Object) this;
    }

    @Unique
    private static void c$setup(Entity entity, int coolDown) {
        IEntity iEntity = (IEntity) entity;
        iEntity.c$setOriginalNoGravity(entity.isNoGravity());
        iEntity.c$setCoolDown(coolDown);
        entity.setNoGravity(true);
        Vec3 motion = entity.getDeltaMovement();
        entity.setDeltaMovement(motion.x, 0.1, motion.z);
    }
}
