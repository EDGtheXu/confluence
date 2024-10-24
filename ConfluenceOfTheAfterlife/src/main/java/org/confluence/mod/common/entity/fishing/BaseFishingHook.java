package org.confluence.mod.common.entity.fishing;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.confluence.mod.common.init.ModEntities;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public class BaseFishingHook extends AbstractFishingHook implements VariantHolder<BaseFishingHook.Variant> {
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(BaseFishingHook.class, EntityDataSerializers.INT);

    public BaseFishingHook(EntityType<BaseFishingHook> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BaseFishingHook(Player player, Level pLevel, int pLuck, int pLureSpeed, Variant variant) {
        super(ModEntities.BASE_FISHING_HOOK.get(), pLevel, pLuck, pLureSpeed);
        setVariant(variant);
        setup(player);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VARIANT_ID, 0);
    }

    @Override
    public void setVariant(Variant pVariant) {
        entityData.set(DATA_VARIANT_ID, pVariant.id);
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.byId(entityData.get(DATA_VARIANT_ID));
    }

    public enum Variant implements StringRepresentable {
        WOOD(0, "wood"),
        REINFORCED(1, "reinforced"),
        FISHER_OF_SOULS(2, "fisher_of_souls"),
        FLESHCATCHER(3, "fleshcatcher"),
        SCARAB(4, "scarab"),
        FIBERGLASS(5, "fiberglass"),
        MECHANICS(6, "mechanics"),
        SITTING_DUCKS(7, "sitting_ducks"),
        GOLDEN(8, "golden");

        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        final int id;
        private final String name;

        Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public static Variant byId(int pId) {
            return BY_ID.apply(pId);
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }
}
