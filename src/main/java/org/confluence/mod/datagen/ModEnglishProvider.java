package org.confluence.mod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.common.data.LanguageProvider;
import org.confluence.mod.Confluence;
import org.confluence.mod.block.ModBlocks;
import org.confluence.mod.datagen.limit.CustomName;
import org.confluence.mod.effect.ModEffects;
import org.confluence.mod.entity.ModEntities;
import org.confluence.mod.item.ModItems;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ModEnglishProvider extends LanguageProvider {
    public ModEnglishProvider(PackOutput output) {
        super(output, Confluence.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creativetab.confluence.building_blocks", "Confluence | Buildings");
        add("creativetab.confluence.natural_blocks", "Confluence | Naturals");
        add("creativetab.confluence.materials", "Confluence | Materials");
        add("creativetab.confluence.creatures", "Confluence | Creatures");
        add("creativetab.confluence.tools", "Confluence | Tools");
        add("creativetab.confluence.warriors", "Confluence | Warriors");
        add("creativetab.confluence.shooters", "Confluence | Shooters");
        add("creativetab.confluence.mages", "Confluence | Mages");
        add("creativetab.confluence.summoners", "Confluence | Summoners");
        add("creativetab.confluence.creatives", "Confluence | Creatives");
        add("creativetab.confluence.food_and_potions", "Confluence | Food & Potions");
        add("creativetab.confluence.armors", "Confluence | Armors");
        add("creativetab.confluence.functional", "Confluence | Functional");
        add("creativetab.confluence.curios", "Confluence | Curios");

        add("item.confluence.meteorite_ingot.tooltip", "Warm to the touch");

        add("curios.tooltip.speed_boots", "The wearer can run super fast");
        add("curios.tooltip.may_fly", "Allows flight");
        add("curios.tooltip.jump_boost", "Increases jump height");
        add("curios.tooltip.multi_jump", "Allows the holder to double jump");
        add("curios.tooltip.fall_resistance", "Increases fall resistance");
        add("curios.tooltip.negates_fall_damage", "Negates fall damage");
        add("curios.tooltip.watch", "Tell the time");
        add("curios.tooltip.fire_immune", "Grants immunity to fire blocks");
        add("curios.tooltip.fluid_walk.part", "Provides the ability to walk on water & honey");
        add("curios.tooltip.fluid_walk.all", "Provides the ability t walk on water, honey & lava");
        add("curios.tooltip.lava_immune", "Provides 7 seconds of immunity to lava");
        add("curios.tooltip.lava_hurt_reduce", "Reduces damage from touching lava");
        add("curios.tooltip.fire_attack", "Melee attacks inflict fire damage");

        add("info.confluence.time", "Time: [%s:%s]");
        add("info.confluence.radar", "Enemies: %s");
        add("info.confluence.compass.east", "East: %s, ");
        add("info.confluence.compass.west", "West: %s, ");
        add("info.confluence.compass.south", "South: %s");
        add("info.confluence.compass.north", "North: %s");
        add("info.confluence.depth_meter.surface", "Surface: %s");
        add("info.confluence.depth_meter.underground", "Underground: %s");
        add("info.confluence.tally_counter.unknown", "Kill count unavailable");
        add("info.confluence.tally_counter", "Killed '");
        add("info.confluence.life_form_analyzer.none", "No rare creatures nearby!");
        add("info.confluence.life_form_analyzer", "%s detected nearby!");
        add("info.confluence.metal_detector.none", "No treasure nearby!");
        add("info.confluence.metal_detector", "%s detected nearby!");
        add("info.confluence.stopwatch", "Speed: %s m/s");
        add("info.confluence.dps_meter", "DPS: %s");
        add("info.confluence.sextant.0", "Moon phase: Full Moon");
        add("info.confluence.sextant.1", "Moon phase: Waning Gibbous");
        add("info.confluence.sextant.2", "Moon phase: Third Quarter");
        add("info.confluence.sextant.3", "Moon phase: Waning Crescent");
        add("info.confluence.sextant.4", "Moon phase: New Moon");
        add("info.confluence.sextant.5", "Moon phase: Waxing Crescent");
        add("info.confluence.sextant.6", "Moon phase: First Quarter");
        add("info.confluence.sextant.7", "Moon phase: Waxing Gibbous");

        add("item.confluence.honey_comb.tooltip", "Releases bees and douses the user in honey when damaged(WIP)");
        add("item.confluence.bezoar.tooltip", "Immunity to Poison");
        add("item.confluence.blindfold.tooltip", "Immunity to Darkness");
        add("item.confluence.cobalt_shield.tooltip", "Grants immunity to knockback");
        add("item.confluence.band_of_regeneration.tooltip", "Slowly regenerates life");
        add("item.confluence.band_of_starpower.tooltip", "Increases maximum mana by 20");
        add("item.confluence.mechanical_lens.tooltip", "Grants improved wire vision");
        add("item.confluence.spectre_goggles.tooltip", "Enables Echo Sight, showing hidden blocks");
        add("item.confluence.magiluminescence.tooltip", "Increases movement speed and acceleration");
        add("item.confluence.magiluminescence.tooltip2", "Provides light when worn(WIP)");
        add("item.confluence.magiluminescence.tooltip3", "'A brief light in my dark life.'");
        add("item.confluence.sandstorm_on_a_bottle.tooltip", "Allows the holder to do an improved double jump");
        add("item.confluence.ice_skates.tooltip", "Provides extra mobility on ice");
        add("item.confluence.ice_skates.tooltip2", "Ice will not break when you fall on it");
        add("item.confluence.dunerider_boots.tooltip", "The wearer can run super fast, and even faster on sand");
        add("item.confluence.dunerider_boots.tooltip2", "'Walk without rhythm and you won't attract the worm'");
        add("item.confluence.lucky_horseshoe.tooltip", "'Said to bring good fortune and keep evil spirits at bay'");
        add("item.confluence.lightning_boots.tooltip", "Allows flight, super fast running");
        add("item.confluence.horseshoe_balloon.tooltip", "Increases jump height and negates fall damage");
        add("item.confluence.lava_waders.tooltip2", "Grants immunity to fire blocks and 7 seconds of immunity to lava");
        add("item.confluence.bundle_of_balloons.tooltip", "Allows the holder to quadruple jump");
        add("item.confluence.water_walking_boots.tooltip", "Provides the ability to walk on water & honey");
        add("item.confluence.magma_skull.tooltip", "Immunity to fire blocks, melee attacks deal fire damage");
        add("item.confluence.frostspark_boots.tooltip", "Allows flight, super fast running, and extra mobility on ice");
        add("item.confluence.tally_counter.tooltip", "Displays how many monsters have been killed");
        add("item.confluence.radar.tooltip", "Detects enemies around you");
        add("item.confluence.compass.tooltip", "Displays horizontal position");
        add("item.confluence.depth_meter.tooltip", "Displays depth");
        add("item.confluence.titan_glove.tooltip", "Increases melee knockback");
        add("item.confluence.sun_stone.tooltip", "During daytime, grants minor increase");
        add("item.confluence.moon_stone.tooltip", "During nighttime, grants minor increase");
        add("item.confluence.putrid_scent.tooltip", "Enemies are less likely to target you");
        add("item.confluence.putrid_scent.tooltip2", "5% increased damage and critical strike chance");
        add("item.confluence.power_glove.tooltip", "Increases melee knockback");
        add("item.confluence.power_glove.tooltip2", "Enables auto swing for melee weapons");
        add("item.confluence.feral_claws.tooltip", "Enables auto swing for melee weapons");
        add("item.confluence.panic_necklace.tooltip", "Increases movement speed after taking damage");
        add("item.confluence.paladins_shield.tooltip", "Absorbs 25% of damage done to players on your team when above 25% life");
        add("item.confluence.mechanical_glove.tooltip", "Enables auto swing for melee weapons");
        add("item.confluence.hero_shield.tooltip2", "Enemies are more likely to target you");
        add("item.confluence.frozen_shield.tooltip2", "Puts a shell around the owner when below 50% life that reduces damage by 25%");
        add("item.confluence.frozen_turtle_shell.tooltip", "Puts a shell around the owner when below 50% life that reduces damage by 25%");
        add("item.confluence.flesh_knuckles.tooltip", "Enemies are more likely to target you");
        add("item.confluence.fire_gauntlet.tooltip", "Increases melee knockback and melee attacks inflict fire damage");
        add("item.confluence.fire_gauntlet.tooltip2", "Enables auto swing for melee weapons");
        add("item.confluence.eye_of_the_golem.tooltip", "10% increased critical strike chance");
        add("item.confluence.berserkers_glove.tooltip", "Enables auto swing for melee weapons");
        add("item.confluence.berserkers_glove.tooltip2", "Enemies are more likely to target you");
        add("item.confluence.destroyer_emblem.tooltip", "8% increased critical strike chance");
        add("item.confluence.cross_necklace.tooltip", "Increases length of invincibility after taking damage");
        add("item.confluence.black_belt.tooltip", "Gives a chance to dodge attacks");
        add("item.confluence.terraspark_boots.tooltip2", "Grants immunity to fire blocks and 7 seconds of immunity to lava");
        add("item.confluence.stopwatch.tooltip", "Displays how fast the player is moving");
        add("item.confluence.metal_detector.tooltip", "Displays the most valuable ore around you");
        add("item.confluence.life_form_analyzer.tooltip", "Displays the name of rare creatures around you");
        add("item.confluence.rek_3000.tooltip", "Displays number of monsters, kill count, and rare creatures");
        add("item.confluence.fledgling_wings.tooltip", "Allows flight and slow fall");

        add("death.attack.falling_star", "%1$s was squashed by a falling star");

        ModBlocks.BLOCKS.getEntries().forEach(block -> {
            Block block1 = block.get();
            if (!(block1 instanceof WallSignBlock)) add(block1, toTitleCase(block.getId().getPath()));
        });
        ModItems.ITEMS.getEntries().forEach(item -> {
            Item item1 = item.get();
            if (item1 instanceof BlockItem) return;
            if (item1 instanceof CustomName customName) add(item1, customName.getName());
            else add(item1, toTitleCase(item.getId().getPath()));
        });
        ModEntities.ENTITIES.getEntries().forEach(entity -> add(entity.get(), toTitleCase(entity.getId().getPath())));
        ModEffects.MOB_EFFECTS.getEntries().forEach(effect -> add(effect.get(), toTitleCase(effect.getId().getPath())));
    }

    private static String toTitleCase(String raw) {
        return Arrays.stream(raw.split("_"))
            .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
            .collect(Collectors.joining(" "));
    }
}
