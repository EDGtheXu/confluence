package org.confluence.terraentity.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.confluence.terraentity.init.ModSounds;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class BloodCrawler extends Spider implements GeoEntity {

    private static final int ATTACK_DAMAGE = 15;
    private static final int MAX_HEALTH = 31;
    private static final int DEFENSE = 2;

    public BloodCrawler(EntityType<? extends Spider> type, Level level) {
        super(type, level);
        this.setHealth(MAX_HEALTH);
    }

    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.removeAllGoals(a->!(a instanceof HurtByTargetGoal));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class,false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class,false));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Spider.createMobAttributes()
            .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE)  // 攻击力
            .add(Attributes.MAX_HEALTH, MAX_HEALTH)        // 生命值
            .add(Attributes.ARMOR, DEFENSE)                 // 防御值
            .add(Attributes.MOVEMENT_SPEED, 0.38)          // 移动速度
            .add(Attributes.FOLLOW_RANGE, 32)             // 跟随距离
            .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.01)  // 召唤物品的几率
            .add(Attributes.KNOCKBACK_RESISTANCE, 0.8);     // 击退抗性
    }
    public static boolean checkBloodCrawlerSpawn(EntityType<? extends BloodCrawler> type, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        if (!(pLevel instanceof Level level)) {
            return false; // 如果 pLevel 不是 Level 的实例，返回 false
        }

        if (!checkMobSpawnRules(type, pLevel, pSpawnType, pPos, pRandom)) {
            return false; // 如果不满足基本生成规则，返回 false
        }

        int y = pPos.getY();
        if (y >= 260) {
            return false; // 不能生成在 y = 260 或更高的位置
        }

        return true;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BLOOD_CRAWLER_DEATH.get();
    }
    @Override
    protected SoundEvent getAmbientSound() {
        Random rand = new Random();
        SoundEvent sound1 = ModSounds.BLOOD_CRAWLER_FREE.get();
        SoundEvent sound2 = ModSounds.BLOOD_CRAWLER_FREE_2.get();

        // 随机选择音效
        return rand.nextBoolean() ? sound1 : sound2;
    }
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSounds.BLOOD_CRAWLER_HURT.get();
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkController(this));
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));
    }

}
