package org.confluence.terraentity.entity.monster.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;

import org.confluence.terraentity.utils.FloatRGB;
import org.confluence.terraentity.entity.util.DeathAnimOptions;
import org.confluence.terraentity.init.ModEntities;
import org.confluence.terraentity.init.ModParticles;
import org.confluence.terraentity.init.ModTags;
import org.confluence.terraentity.mixin.accessor.SlimeAccessor;
import org.confluence.terraentity.utils.ModUtils;
import org.jetbrains.annotations.NotNull;

public class BaseSlime extends Slime implements DeathAnimOptions {
    private final int size;
    private final FloatRGB color;

    public BaseSlime(EntityType<? extends Slime> slime, Level level, int color, int size) {
        super(slime, level);
        this.size = size;
        // setSize在constructor中调用时size还没更新，再变一遍
        setSize(size, false);
        this.color = FloatRGB.fromInteger(color);
    }

    public static AttributeSupplier.Builder createSlimeAttributes(float attackDamage, int armor, float maxHealth) {
        return Mob.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, attackDamage)
                .add(Attributes.ARMOR, armor)
                .add(Attributes.MAX_HEALTH, maxHealth);
    }

    public static boolean checkSlimeSpawn(EntityType<? extends Mob> type, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        if (!(pLevel instanceof Level level)) {
            return false;
        }
        if (!checkMobSpawnRules(type, pLevel, pSpawnType, pPos, pRandom)) {
            return false;
        } else if (type == ModEntities.BLUE_SLIME.get() || type == ModEntities.GREEN_SLIME.get() || type == ModEntities.PURPLE_SLIME.get()
                || type == ModEntities.ICE_SLIME.get() || type == ModEntities.DESERT_SLIME.get() || type == ModEntities.JUNGLE_SLIME.get()
                || type == ModEntities.PINK_SLIME.get()) {
            int y = pPos.getY();
            return y > 30 && y < 260 && level.isDay() && pLevel.canSeeSky(pPos);
        } else if (type == ModEntities.YELLOW_SLIME.get() || type == ModEntities.RED_SLIME.get()) {
            return pLevel.getBrightness(LightLayer.SKY, pPos) == 0 && pPos.getY() > 30;
        } else if (type == ModEntities.BLACK_SLIME.get()) {
            return pLevel.getBrightness(LightLayer.SKY, pPos) == 0 && pPos.getY() <= 30;
        }
        // 剩下的条件用方块的isValidSpawn方法
        return false;
    }

    @Override
    public void tick() {
        resetFallDistance();
        if (onGround() && !((SlimeAccessor) this).isWasOnGround()) {
            int i = getSize();
            for (int j = 0; j < i * 8; ++j) {
                float f = random.nextFloat() * Mth.TWO_PI;
                float f1 = random.nextFloat() * 0.5F + 0.5F;
                float f2 = Mth.sin(f) * (float) i * 0.5F * f1;
                float f3 = Mth.cos(f) * (float) i * 0.5F * f1;
                level().addParticle(ModParticles.ITEM_GEL.get(), getX() + (double) f2, getY(), getZ() + (double) f3, color.red(), color.green(), color.blue());
            }
        }
        if (this.getType().equals(ModEntities.LAVA_SLIME.get())) {
            if (isInWater()) {
                this.hurt(this.level().damageSources().freeze(), 0.8F);
            }
            this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 500, 4, false, true));
        }
        super.tick();
    }

    @Override
    protected boolean spawnCustomParticles() {
        return true;
    }

    @Override
    public void setSize(int pSize, boolean pResetHealth) {
        int i = Mth.clamp(size, 1, 127);
        entityData.set(ID_SIZE, i);
        reapplyPosition();
        refreshDimensions();
        getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2F + 0.1F * i);

        this.xpReward = i;
    }

    @Override
    public void remove(@NotNull RemovalReason removalReason) {
        brain.clearMemories();
        setRemoved(removalReason);
//        invalidateCaps();
    }

    @Override
    public float[] getBloodColor() {
        return color.mixture(new FloatRGB(0, 0, 0), 0.5f).toArray();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (getType() == ModEntities.TROPIC_SLIME.get() && pSource.is(DamageTypes.DROWN)) {
            return false;
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public boolean isInWater() {
        if (getType() == ModEntities.TROPIC_SLIME.get()) {
            return false;
        }
        return super.isInWater();
    }
    // tip 持有tag的物品转换史莱姆
    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        if (level().isClientSide) return super.mobInteract(pPlayer, pHand);
        ItemStack item = pPlayer.getItemInHand(pHand);
        if (item.is(ModTags.Items.HONEY_BUCKET) || item.is(ModTags.Items.BOTTOMLESS_HONEY_BUCKET)) {
            EntityType<? extends Slime> type = getType();
            if (type == ModEntities.BLUE_SLIME.get() || type == ModEntities.GREEN_SLIME.get() || type == ModEntities.PURPLE_SLIME.get()) {
                HoneySlime honeySlime = new HoneySlime(ModEntities.HONEY_SLIME.get(), level(), 0xf8e234);
                honeySlime.setPos(position());
                honeySlime.setDeltaMovement(getDeltaMovement());
                honeySlime.setXRot(getXRot());
                honeySlime.setYRot(getYRot());
                level().addFreshEntity(honeySlime);
                discard();
                if (!pPlayer.getAbilities().instabuild && item.is(ModTags.Items.HONEY_BUCKET)) {
                    pPlayer.setItemInHand(pHand, new ItemStack(Items.BUCKET));
                }
                level().playSound(null, getX(), getY(), getZ(), SoundEvents.HONEY_DRINK, SoundSource.AMBIENT, 1.0F, 1.0F);
            }
        }
        return super.mobInteract(pPlayer, pHand);
    }


    @Override
    protected void dealDamage(@NotNull LivingEntity pLivingEntity) {
        if (isAlive()) {
            int i = getSize();
            if (distanceToSqr(pLivingEntity) < 0.6 * (double) i * 0.6 * (double) i && hasLineOfSight(pLivingEntity) && pLivingEntity.hurt(damageSources().mobAttack(this), getAttackDamage())) {
                playSound(SoundEvents.SLIME_ATTACK, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                DamageSource damagesource = this.damageSources().mobAttack(this);
                if (this.level() instanceof ServerLevel serverlevel)
                    EnchantmentHelper.doPostAttackEffects(serverlevel, pLivingEntity, damagesource);
                if (getType() == ModEntities.ICE_SLIME.get()) {
                    if (ModUtils.isMaster(level()) || (ModUtils.isAtLeastExpert(level()) && level().random.nextBoolean())) {
                        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), this);
                    }
                } else if (getType() == ModEntities.LAVA_SLIME.get()) {
                    pLivingEntity.setRemainingFireTicks(5);
                }
            }
        }
    }

    @Override
    protected void tickDeath() {
        super.tickDeath();
        if (this.getType().equals(ModEntities.LAVA_SLIME.get())) {
            StateDefinition<Block, BlockState> stateDefinition = Blocks.LAVA.getStateDefinition();
            Property<?> levelProperty = stateDefinition.getProperty("level");
            if (levelProperty instanceof IntegerProperty integerProperty) {
                if (ModUtils.isAtLeastExpert(level())) {
                    if (level().getBlockState(BlockPos.containing(this.position())).isAir() || level().getBlockState(BlockPos.containing(position())).canBeReplaced(Fluids.LAVA)) {
                        //todo 未知且非固定出现的渲染bug
                        level().setBlock(BlockPos.containing(position()), Blocks.LAVA.defaultBlockState().setValue(integerProperty, 14), 2);
                    }
                }
            }
        }
    }

    @Override
    public boolean isInWall() { // 防止骑僵尸时窒息
        Entity vehicle = getControlledVehicle();
        return vehicle == null ? super.isInWall() : vehicle.isInWall();
    }

}
