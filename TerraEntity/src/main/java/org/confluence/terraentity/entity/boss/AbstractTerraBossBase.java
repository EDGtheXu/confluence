package org.confluence.terraentity.entity.boss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.confluence.terraentity.entity.ai.BossSkill;
import org.confluence.terraentity.entity.ai.CircleBossSkills;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;

import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static org.confluence.terraentity.utils.ModUtils.switchByDifficulty;


@SuppressWarnings("all")
public abstract class AbstractTerraBossBase extends Monster implements GeoEntity {

    public int difficultyIdx;

    public float ironGlomResistance = 0.4f;
    public float explosionResistance = 0.5f;
    public int attackInternal = 20;
    private int _attackInternal = 20;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected ServerBossEvent bossEvent = (ServerBossEvent) new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(true);

    public AbstractTerraBossBase(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        setNoGravity(true);
        this.difficultyIdx = switchByDifficulty(level(), 0, 1, 2);
    }

    public abstract void addSkills();

    public void onAddedToLevel(){
        super.onAddedToLevel();
        this.addSkills();
    }

    // 攻击目标
    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = entity -> entity instanceof Player;

    protected void registerGoals() {
        //this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 100F));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
    }

    // 技能动画
    public CircleBossSkills skills = new CircleBossSkills(this);

    private final Map<String, RawAnimation> skillMap = new HashMap<>();
    private int lastAnimIndex = -1;
    private static final EntityDataAccessor<Integer> DATA_SKILL_INDEX = SynchedEntityData.defineId(AbstractTerraBossBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_SKILL_TICK = SynchedEntityData.defineId(AbstractTerraBossBase.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Vector3f> DATA_ROTATE = SynchedEntityData.defineId(AbstractTerraBossBase.class, EntityDataSerializers.VECTOR3);

    // 动画数据同步
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SKILL_INDEX, 0);
        builder.define(DATA_SKILL_TICK, 0);
        builder.define(DATA_ROTATE, new Vector3f());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            AbstractTerraBossBase entity = (AbstractTerraBossBase) state.getData(DataTickets.ENTITY);
            if (!entity.isAlive()) return PlayState.STOP;
            if (skills.count() == 0) return PlayState.STOP;
            String skillString = entity.skills.getCurSkill();
            if (skillString == null) return PlayState.STOP;
            RawAnimation skill = skillMap.get(skillString);
            if (skill != null) {
                state.setAnimation(skill);
                if (lastAnimIndex != skills.index) {
                    lastAnimIndex = skills.index;
                    state.resetCurrentAnimation();

                    return PlayState.STOP;
                }
                return PlayState.CONTINUE;
            }
            return PlayState.STOP;
        }));
    }


    // 技能逻辑

    public void addSkill(BossSkill bossSkill, RawAnimation anim) {
        this.skills.pushSkill(bossSkill);
        //if(anim==null)return;
        skillMap.put(bossSkill.skill, anim);
    }

    public void addSkillNoAnim(BossSkill bossSkill) {
        this.skills.pushSkill(bossSkill);
        //if(anim==null)return;
        //skillMap.put(bossSkill.skill,anim);
    }

    @Override
    public void tick() {
        // 动画同步

        if (level().isClientSide) {
            skills.index = this.entityData.get(DATA_SKILL_INDEX);
            skills.tick = this.entityData.get(DATA_SKILL_TICK);

        } else {
            skills.tick();
            this.entityData.set(DATA_SKILL_INDEX, skills.index);
            this.entityData.set(DATA_SKILL_TICK, skills.tick);
        }
        collisionHurt();
        super.tick();
        attackInternal--;
        this.setDeltaMovement(getDeltaMovement().scale(0.95));//空气阻力
    }


    public void lookAtPos(Vec3 target, float pMaxYRotIncrease, float pMaxXRotIncrease) {
        double d0 = target.x - this.getX();
        double d2 = target.z - this.getZ();
        double d1 = target.y - this.getEyeY();

        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(Mth.atan2(d2, d0) * 57.2957763671875) - 90.0F;
        float f1 = (float)(-(Mth.atan2(d1, d3) * 57.2957763671875));
        this.setXRot(this.rotlerp(this.getXRot(), f1, pMaxXRotIncrease));
        this.setYRot(this.rotlerp(this.getYRot(), f, pMaxYRotIncrease));
    }

    private float rotlerp(float pAngle, float pTargetAngle, float pMaxIncrease) {
        float f = Mth.wrapDegrees(pTargetAngle - pAngle);
        if (f > pMaxIncrease) {
            f = pMaxIncrease;
        }

        if (f < -pMaxIncrease) {
            f = -pMaxIncrease;
        }

        return pAngle + f;
    }

    // 开启碰撞伤害
    public boolean canCollisionHurt() {
        return true;
    }

    public void collisionHurt() {
        if (canCollisionHurt() && !level().isClientSide) {
            // 包围盒检测造成伤害
            var entities = level().getEntities(this, this.getBoundingBox());
            if (!entities.isEmpty()) {
                for (var e : entities) {
                    if (canAttack(e)){
                        attackInternal = _attackInternal;
                        //测试末影龙
                        e.hurt(this.damageSources().generic(),(float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
                        //e.hurt(this.damageSources().explosion(new Explosion(level(), this, 0.0f, 0,0,3, List.of(this.blockPosition()))), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
                    }
                }
            }
       }
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if(pSource.getEntity() instanceof IronGolem){
            pAmount *= ironGlomResistance;
        }
        if(pSource.is(DamageTypes.EXPLOSION)){
            pAmount *= explosionResistance;
        }

        return super.hurt(pSource,pAmount);
    }

    public boolean canAttack(Entity entity) {
        return attackInternal < 0 &&
                (entity instanceof Player || getTarget() != null && getTarget().is(entity) && entity != this &&!(entity instanceof AbstractTerraBossBase));
    }

    // boss条
    public boolean shouldShowBossBar() {
        return true;
    }

    @Override // boss条显示
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        if (shouldShowBossBar())
            this.bossEvent.addPlayer(player);
    }

    @Override // boss条消失
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        if (shouldShowBossBar())
            this.bossEvent.removePlayer(player);
    }

    @Override // boss条更新
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (shouldShowBossBar())
            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override // 从客户端移除时
    public void onRemovedFromLevel() {
        super.onRemovedFromLevel();
    }

    @Override // 取消墙体窒息伤害
    public boolean isInWall() {
        return false;
    }

    @Override // 是否免疫摔伤
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource damageSource) {
        return false;
    }

    @Override // 是否在实体上渲染着火效果
    public boolean displayFireAnimation() {
        return false;
    }

    @Override // 受伤音效
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    // 旋转同步

    public Vector3f rotCli = new Vector3f();

    public Vector3f getRot() {
        return rotCli;
    }

    public void syncRot() {
        if (level().isClientSide) {
            rotCli = entityData.get(DATA_ROTATE);
        } else {
            if (xRotO != 0) entityData.set(DATA_ROTATE, new Vector3f(xRotO, yBodyRot, 0));
        }
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (hasCustomName()) {
            bossEvent.setName(getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        bossEvent.setName(getDisplayName());
    }
}