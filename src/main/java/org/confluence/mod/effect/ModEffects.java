package org.confluence.mod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.confluence.mod.Confluence;
import org.confluence.mod.effect.beneficial.*;
import org.confluence.mod.effect.harmful.*;
import org.confluence.mod.effect.neutral.CerebralMindtrickEffect;
import org.confluence.mod.effect.neutral.LoveEffect;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Confluence.MODID);

    public static final RegistryObject<ManaSicknessEffect> MANA_SICKNESS = EFFECTS.register("mana_sickness", ManaSicknessEffect::new);
    public static final RegistryObject<ExquisitelyStuffedEffect> EXQUISITELY_STUFFED = EFFECTS.register("exquisitely_stuffed", ExquisitelyStuffedEffect::new);
    public static final RegistryObject<IronSkinEffect> IRON_SKIN = EFFECTS.register("iron_skin", IronSkinEffect::new);
    public static final RegistryObject<EnduranceEffect> ENDURANCE = EFFECTS.register("endurance", EnduranceEffect::new);
    public static final RegistryObject<InfernoEffect> INFERNO = EFFECTS.register("inferno", InfernoEffect::new);
    public static final RegistryObject<LifeForceEffect> LIFE_FORCE = EFFECTS.register("life_force", LifeForceEffect::new);
    public static final RegistryObject<RageEffect> RAGE = EFFECTS.register("rage", RageEffect::new);
    public static final RegistryObject<WrathEffect> WRATH = EFFECTS.register("wrath", WrathEffect::new);
    public static final RegistryObject<ThornsEffect> THORNS = EFFECTS.register("thorns", ThornsEffect::new);
    public static final RegistryObject<HoneyEffect> HONEY = EFFECTS.register("honey", HoneyEffect::new);
    public static final RegistryObject<ManaRegenerationEffect> MANA_REGENERATION = EFFECTS.register("mana_regeneration", ManaRegenerationEffect::new);
    public static final RegistryObject<PaladinsShieldEffect> PALADINS_SHIELD = EFFECTS.register("paladins_shield", PaladinsShieldEffect::new);
    public static final RegistryObject<TitanEffect> TITAN = EFFECTS.register("titan", TitanEffect::new);
    public static final RegistryObject<GravitationEffect> GRAVITATION = EFFECTS.register("gravitation", GravitationEffect::new);
    public static final RegistryObject<BuilderEffect> BUILDER = EFFECTS.register("builder", BuilderEffect::new);
    public static final RegistryObject<FishingEffect> FISHING = EFFECTS.register("fishing", FishingEffect::new);
    public static final RegistryObject<BleedingEffect> BLEEDING = EFFECTS.register("bleeding", BleedingEffect::new);
    public static final RegistryObject<AcidVenomEffect> ACID_VENOM = EFFECTS.register("acid_venom", AcidVenomEffect::new);
    public static final RegistryObject<FrostburnEffect> FROST_BURN = EFFECTS.register("frost_burn", FrostburnEffect::new);
    public static final RegistryObject<CursedInfernoEffect> CURSED_INFERNO = EFFECTS.register("cursed_inferno", CursedInfernoEffect::new);
    public static final RegistryObject<SilencedEffect> SILENCED = EFFECTS.register("silenced", SilencedEffect::new);
    public static final RegistryObject<CursedEffect> CURSED = EFFECTS.register("cursed", CursedEffect::new);
    public static final RegistryObject<ConfusedEffect> CONFUSED = EFFECTS.register("confused", ConfusedEffect::new);
    public static final RegistryObject<WitheredArmorEffect> WITHERED_ARMOR = EFFECTS.register("withered_armor", WitheredArmorEffect::new);
    public static final RegistryObject<IchorEffect> ICHOR = EFFECTS.register("ichor", IchorEffect::new);
    public static final RegistryObject<PotionSicknessEffect> POTION_SICKNESS = EFFECTS.register("potion_sickness", PotionSicknessEffect::new);
    public static final RegistryObject<CerebralMindtrickEffect> CEREBRAL_MINDTRICK = EFFECTS.register("cerebral_mindtrick", CerebralMindtrickEffect::new);
    public static final RegistryObject<BrokenArmorEffect> BROKEN_ARMOR = EFFECTS.register("broken_armor", BrokenArmorEffect::new);
    public static final RegistryObject<StonedEffect> STONED = EFFECTS.register("stoned", StonedEffect::new);
    public static final RegistryObject<MagicPowerEffect> MAGIC_POWER = EFFECTS.register("magic_power", MagicPowerEffect::new);
    public static final RegistryObject<ObsidianSkinEffect> OBSIDIAN_SKIN = EFFECTS.register("obsidian_skin", ObsidianSkinEffect::new);
    public static final RegistryObject<LuckEffect> LUCK_EFFECT = EFFECTS.register("luck", LuckEffect::new);
    public static final RegistryObject<WaterWalkingEffect> WATER_WALKING = EFFECTS.register("water_walking", WaterWalkingEffect::new);
    public static final RegistryObject<LoveEffect> LOVE = EFFECTS.register("love", LoveEffect::new);
    public static final RegistryObject<HeartReachEffect> HEART_REACH = EFFECTS.register("heart_reach", HeartReachEffect::new);
    public static final RegistryObject<ArcheryEffect> ARCHERY = EFFECTS.register("archery", ArcheryEffect::new);
    public static final RegistryObject<FlipperEffect> FLIPPER = EFFECTS.register("flipper", FlipperEffect::new);
    public static final RegistryObject<ShineEffect> SHINE = EFFECTS.register("shine", ShineEffect::new);

    public static void heal(LivingEntity living, float amount) {
        if (living.level().getGameTime() % 20 == 0) {
            if (living.hasEffect(HONEY.get())) amount += 1;
            living.heal(amount);
        }
    }
}
