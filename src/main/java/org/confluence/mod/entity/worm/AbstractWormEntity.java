package org.confluence.mod.entity.worm;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.confluence.mod.util.ModUtils;
import software.bernie.geckolib.animatable.GeoEntity;

import java.util.ArrayList;

public abstract class AbstractWormEntity extends Monster implements GeoEntity {
    protected final ArrayList<BaseWormPart<? extends AbstractWormEntity>> wormParts;
    private final int length;
    private final float maxHealth;
    private boolean pendingSegmentsSpawn = false;

    /** 子类应该完全覆盖此方法以自定义蠕虫体节的构造器 */
    protected BaseWormPart<? extends AbstractWormEntity> partConstructor(int index) {
        ModUtils.testMessage(level(), "partConstructor没有Override，李哉赣神魔");
        return new BaseWormPart<>(this, index, maxHealth);
    }
    /** 生成一个新的体节并记录在wormParts中 */
    private BaseWormPart<? extends AbstractWormEntity> spawnWormPart() {
        ModUtils.testMessage(level(), "SPN:"+wormParts.size());
//        Tadpole tadpole = EntityType.TADPOLE.create(pLevel);
        BaseWormPart<? extends AbstractWormEntity> part = partConstructor( wormParts.size() );
        part.moveTo( position() );
        level().addFreshEntity(part);

        this.wormParts.add(part);
        ModUtils.testMessage(level(), part.toString());
        return part;
    }
    public AbstractWormEntity(EntityType<? extends AbstractWormEntity> pEntityType, Level pLevel, int length, float maxHealth) {
        super(pEntityType, pLevel);

        this.maxHealth = maxHealth;
        this.wormParts = new ArrayList<>(length);
        this.length = length;
        // 不要马上生成体节，constructor被调用时还在(0,0,0)
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.get("WormParts") instanceof ListTag listTag) {
            // WormPart发现自己不再是wormParts[i]后会悄悄地似了
            wormParts.clear();
            listTag.forEach(tag -> {
                if (tag instanceof CompoundTag compoundTag) {
                    spawnWormPart().deserializeNBT(compoundTag);
                }
            });
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        ListTag listTag = new ListTag();
        for (BaseWormPart<?> part : wormParts) {
            listTag.add(part.serializeNBT());
        }
        pCompound.put("WormParts", listTag);
    }

    /** 死亡触发的额外机制 */
    protected void deathCallback() {}
    // 同一种蠕虫的移动特性应当是同样的
    protected WormMovementUtils.WormSegmentMovementOptions getWormFollowOption() {
        ModUtils.testMessage(level(), "getWormFollowOption没有Override，李哉赣神魔");
        return null;
    }
    // AI: 循环遍历所有体节并进行AI判定
    @Override
    public void tick() {
        ModUtils.testMessage("AISTART");
        super.tick();

        // 生成体节
        if (pendingSegmentsSpawn) {
            pendingSegmentsSpawn = false;

            for (int i = 0; i < length; i ++) {
                spawnWormPart();
            }
            // 初始化各体节的头/身体/尾节信息
            for (BaseWormPart<? extends AbstractWormEntity> part : wormParts) {
                part.updateSegmentType();
            }
        }

        boolean shouldDie = true;
        ModUtils.testMessage("AISTEP");
        for (BaseWormPart<? extends AbstractWormEntity> part : wormParts) {
            ModUtils.testMessage(level(), part.toString());
            if (part.isAlive()) {
                shouldDie = false;
                break;
            }
        }
        // TODO: 此方法移除实体是否合理？EnderDragon内部的逻辑类似，但有可能需要调整
        if (shouldDie) {
            deathCallback();
            this.remove(Entity.RemovalReason.KILLED);
            this.gameEvent(GameEvent.ENTITY_DIE);
            return;
        }

        // 各体节AI
        for (BaseWormPart<? extends AbstractWormEntity> part : wormParts) {
            if (part.isAlive()) {
                part.tickSegment();
                if (part.segmentType == BaseWormPart.SegmentType.HEAD) {
                    WormMovementUtils.handleSegmentsFollow(wormParts, getWormFollowOption(), part.segmentIndex);
                }
            }
        }
    }
}
