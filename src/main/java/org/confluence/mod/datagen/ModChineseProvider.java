package org.confluence.mod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.confluence.mod.Confluence;
import org.confluence.mod.effect.ModEffects;
import org.confluence.mod.item.ModItems;

import static org.confluence.mod.item.curio.CurioItems.*;

public class ModChineseProvider extends LanguageProvider {
    public ModChineseProvider(PackOutput output) {
        super(output, Confluence.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add("creativetab.confluence.curios", "汇流来世 | 饰品");

        add("curios.tooltip.speed_boots", "穿戴者可飞速奔跑");
        add("curios.tooltip.may_fly", "可飞行");
        add("curios.tooltip.jump_boost", "增加跳跃高度");
        add("curios.tooltip.multi_jump", "可让持有者二连跳");
        add("curios.tooltip.negates_fall_damage", "消除掉落伤害");
        add("curios.tooltip.fall_resistance", "增加抗坠落性");
        add("curios.tooltip.watch", "报时");
        add("curios.tooltip.fire_immune", "对火块免疫");
        add("curios.tooltip.fluid_walk.part", "提供在水和蜂蜜上行走的能力");
        add("curios.tooltip.fluid_walk.all", "提供在水、蜂蜜、岩浆上行走的能力");
        add("curios.tooltip.lava_immune", "对岩浆免疫7秒");
        add("curios.tooltip.lava_hurt_reduce", "减少因触碰熔岩而造成的伤害");
        add("curios.tooltip.fire_attack", "近战攻击造成火焰伤害");
        add("curios.tooltip.auto_attack", "启用近战武器自动挥动");
        add("curios.tooltip.aggro_attach", "多人模式时，敌人更可能以你为目标");
        add("curios.tooltip.armor_pass", "盔甲穿透力提高%s点");
        add("curios.tooltip.projectile_attack", "远程伤害提高%s%%");
        add("curios.tooltip.compass", "显示水平位置");
        add("curios.tooltip.depth_meter", "显示深度");
        add("curios.tooltip.dps_meter", "显示你的每秒伤害");
        add("curios.tooltip.fishermans_pocket_guide", "显示钓鱼信息");
        add("curios.tooltip.life_form_analyzer", "显示您周围稀有生物的名称");
        add("curios.tooltip.metal_detector", "显示你周围最贵重的矿石");
        add("curios.tooltip.radar", "探测你周围的敌人");
        add("curios.tooltip.sextant", "显示月相");
        add("curios.tooltip.stopwatch", "显示玩家的移动速度");
        add("curios.tooltip.tally_counter", "显示怪物击杀数量");
        add("curios.tooltip.weather_radio", "显示天气");
        add("curios.tooltip.scope", "扩大远程武器的视野范围");
        add("curios.tooltip.scope2", "手持远程武器并潜行可拉远视野");
        add("curios.tooltip.wall_climb", "可爬墙，按住shift键可快速下滑");
        add("curios.tooltip.wall_slide", "可沿墙滑下，按住shift键可更快地下滑");
        add("curios.tooltip.tabi", "双击一个方向可猛冲");
        add("curios.tooltip.hurt_evasion", "有几率避开攻击");

        add("info.confluence.time", "时间: [%s:%s]");
        add("info.confluence.radar", "敌人: %s");
        add("info.confluence.compass.east", "东: %s, ");
        add("info.confluence.compass.west", "西: %s, ");
        add("info.confluence.compass.south", "南: %s");
        add("info.confluence.compass.north", "北: %s");
        add("info.confluence.depth_meter.surface", "地表: %s");
        add("info.confluence.depth_meter.underground", "地下: %s");
        add("info.confluence.tally_counter.unknown", "击杀数不可用");
        add("info.confluence.tally_counter", "已杀死 '");
        add("info.confluence.life_form_analyzer.none", "未发现稀有生物");
        add("info.confluence.life_form_analyzer", "发现稀有生物: %s");
        add("info.confluence.metal_detector.none", "未发现稀有方块");
        add("info.confluence.metal_detector", "在附近发现%s!");
        add("info.confluence.stopwatch", "速度: %s m/s");
        add("info.confluence.dps_meter", "DPS: %s");
        add("info.confluence.sextant.0", "月相: 满月");
        add("info.confluence.sextant.1", "月相: 亏凸月");
        add("info.confluence.sextant.2", "月相: 下弦月");
        add("info.confluence.sextant.3", "月相: 残月");
        add("info.confluence.sextant.4", "月相: 新月");
        add("info.confluence.sextant.5", "月相: 峨眉月");
        add("info.confluence.sextant.6", "月相: 上弦月");
        add("info.confluence.sextant.7", "月相: 盈凸月");
        add("info.confluence.weather_radio.clear", "天气: 晴天");
        add("info.confluence.weather_radio.cloudy", "天气: 阴天");
        add("info.confluence.weather_radio.rain", "天气: 下雨");
        add("info.confluence.weather_radio.snow", "天气: 下雪");
        add("info.confluence.weather_radio.thunder", "天气: 雷暴");
        add("info.confluence.fishermans_pocket_guide", "渔力: %s");

        add("key.confluence.metal_detector", "检测矿物");

        add("curios.identifier.accessory", "配饰");
        add("curios.modifiers.accessory", "佩戴配饰时：");

        add("item.confluence.demon_heart.tooltip", "永久增加配饰栏数量");
        add("item.confluence.vitamins.tooltip", "对虚弱免疫");
        add("item.confluence.fast_clock.tooltip", "对缓慢免疫");
        add("item.confluence.blindfold.tooltip", "对失明免疫");
        add("item.confluence.trifold_map.tooltip", "对反胃免疫");
        add("item.confluence.the_plan.tooltip", "对缓慢和反胃免疫");
        add("item.confluence.holy_water.tooltip", "对凋零免疫");
        add("item.confluence.energy_bar.tooltip", "对饥饿免疫");
        add("item.confluence.flashlight.tooltip", "对黑暗免疫");
        add("item.confluence.hand_drill.tooltip", "对挖掘疲劳免疫");
        add("item.confluence.shot_put.tooltip", "对漂浮免疫");
        add("item.confluence.searchlight.tooltip", "对失明和黑暗免疫");
        add("item.confluence.detoxification_capsule.tooltip", "对中毒和凋零免疫");
        add("item.confluence.explorers_equipment.tooltip", "对挖掘疲劳和漂浮免疫");
        add("item.confluence.nutrient_solution.tooltip", "对虚弱和饥饿免疫");
        add("item.confluence.ankh_charm.tooltip", "对大部分减益免疫");
        add("item.confluence.honey_comb.tooltip", "受到伤害后释放蜜蜂并将使用者浸入蜂蜜中");
        add("item.confluence.bezoar.tooltip", "对中毒免疫");
        add("item.confluence.cobalt_shield.tooltip", "对击退免疫");
        add("item.confluence.band_of_regeneration.tooltip", "缓慢再生生命");
        add("item.confluence.magiluminescence.tooltip", "提高移动速度和加速度");
        add("item.confluence.magiluminescence.tooltip3", "“我黑暗生命中的一道短暂曙光。”");
        add("item.confluence.sandstorm_on_a_bottle.tooltip", "可让持有者更好地二连跳");
        add("item.confluence.ice_skates.tooltip", "提供额外冰面行动力");
        add("item.confluence.dunerider_boots.tooltip", "穿戴者可飞速奔跑，在沙地上还能跑得更快");
        add("item.confluence.dunerider_boots.tooltip2", "“无节律行走就不会引来蠕虫”");
        add("item.confluence.lucky_horseshoe.tooltip", "“据说能带来好运、驱除邪灵”");
        add("item.confluence.lightning_boots.tooltip", "可飞行、可飞速奔跑");
        add("item.confluence.horseshoe_balloon.tooltip", "增加跳跃高度、消除掉落伤害");
        add("item.confluence.lava_waders.tooltip2", "对火块免疫、对岩浆免疫7秒");
        add("item.confluence.bundle_of_balloons.tooltip", "可让持有者四段跳");
        add("item.confluence.bundle_of_horseshoe_balloon.tooltip", "可让持有者四段跳");
        add("item.confluence.bundle_of_horseshoe_balloon.tooltip2", "增加跳跃高度、消除掉落伤害");
        add("item.confluence.water_walking_boots.tooltip", "提供在水和蜂蜜上行走的能力");
        add("item.confluence.magma_skull.tooltip", "对火块免疫、近战攻击造成火焰伤害");
        add("item.confluence.frostspark_boots.tooltip", "可飞行、飞速奔跑、并提供额外冰面行动力");
        add("item.confluence.sun_stone.tooltip", "在白天时略微增强属性值");
        add("item.confluence.moon_stone.tooltip", "在夜晚时略微增强属性值");
        add("item.confluence.putrid_scent.tooltip", "多人模式下，敌怪不太可能以你为目标");
        add("item.confluence.putrid_scent.tooltip2", "伤害和暴击率增加5%");
        add("item.confluence.power_glove.tooltip", "增加近战击退、近战速度增加12%");
        add("item.confluence.panic_necklace.tooltip", "受到伤害后增加移动速度");
        add("item.confluence.paladins_shield.tooltip", "当生命值超过25%时，吸收对团队中玩家造成的25%伤害");
        add("item.confluence.frozen_shield.tooltip", "当生命值超过25%时，吸收对团队中玩家造成的25%伤害");
        add("item.confluence.frozen_shield.tooltip2", "当生命值低于50%时，在主人周围放置一个外壳，使伤害降低25%");
        add("item.confluence.frozen_turtle_shell.tooltip", "当生命值低于50%时，在主人周围放置一个外壳，使伤害降低25%");
        add("item.confluence.fire_gauntlet.tooltip", "增加近战击退并使攻击附着火焰伤害");
        add("item.confluence.eye_of_the_golem.tooltip", "暴击率提高10%");
        add("item.confluence.destroyer_emblem.tooltip", "暴击率提高8%");
        add("item.confluence.cross_necklace.tooltip", "增加受到伤害后的无敌时间");
        add("item.confluence.terraspark_boots.tooltip2", "对火块免疫并在7秒内对熔岩免疫");
        add("item.confluence.fledgling_wings.tooltip", "提供飞行的能力");
        add("item.confluence.worm_scarf.tooltip", "所受伤害减少17%");
        add("item.confluence.shield_of_cthulhu.tooltip", "允许冲刺，疾跑以冲刺");
        add("item.confluence.brain_of_confusion.tooltip", "有几率制造幻觉并躲避攻击");
        add("item.confluence.brain_of_confusion.tooltip2", "闪避后暂时增加暴击几率");
        add("item.confluence.brain_of_confusion.tooltip3", "被击中后可能会迷惑附近的敌人");
        add("item.confluence.royal_gel.tooltip", "史莱姆将变成友好生物");
        add("item.confluence.arcane_flower.tooltip3", "敌人不太可能以你为目标");
        add("item.confluence.celestial_stone.tooltip", "小幅提高属性值");
        add("item.confluence.charm_of_myths.tooltip", "提供生命恢复，减少治疗药水的冷却时间");
        add("item.confluence.philosophers_stone.tooltip", "减少治疗药水的冷却时间");
        add("item.confluence.gravity_globe.tooltip", "可让持有者反转重力");
        add("item.confluence.gravity_globe.tooltip2", "按跳跃键可改变重力");
        add("item.confluence.ancient_chisel.tooltip", "采矿速度提高25%");
        add("item.confluence.ancient_chisel.tooltip2", "“古老的问题需要古老的解决方案”");
        add("item.confluence.flower_boots.tooltip", "你走过的草地上会长出花朵");
        add("item.confluence.treasure_magnet.tooltip", "扩大物品拾取范围");
        add("item.confluence.sorcerer_emblem.tooltip", "魔法伤害提高15%");
        add("item.confluence.hand_warmer.tooltip", "对寒冷和冰冻效果免疫");
        add("item.confluence.star_cloak.tooltip", "受到伤害后会使星星坠落");
        add("item.confluence.hive_pack.tooltip", "增加友好蜜蜂的力量");
        add("item.confluence.sweetheart_necklace.tooltip", "受到伤害后释放蜜蜂并将使用者浸入蜂蜜中、并提高移动速度");
        add("item.confluence.magic_quiver.tooltip", "箭的伤害提高10%，箭的速度大大提高");
        add("item.confluence.magic_quiver.tooltip2", "20%几率不消耗箭");
        add("item.confluence.molten_quiver.tooltip", "点燃木箭，火光熊熊");
        add("item.confluence.molten_quiver.tooltip2", "“在恐惧中颤抖吧！”");
        add("item.confluence.sniper_scope.tooltip", "远程伤害和暴击率各提高10%");
        add("item.confluence.recon_scope.tooltip", "“发现敌人”");
        add("item.confluence.portable_cement_mixer.tooltip", "右键点击延迟降低1");
        add("item.confluence.brick_layer.tooltip", "右键点击延迟降低1");
        add("item.confluence.architect_gizmo_pack.tooltip", "右键点击延迟降低2，且降低效果不能与其材料的降低效果叠加");
        add("item.confluence.climbing_claws.tooltip", "结合鞋钉使用时能力还会有所提升");
        add("item.confluence.shoe_spikes.tooltip", "结合攀爬爪使用时能力还会有所提升");
        add("item.confluence.frog_gear.tooltip", "“身为绿皮生物可太难了”");

        add("death.attack.star_cloak", "%1$s 被坠星压扁了");

        add(ModEffects.PALADINS_SHIELD.get(), "圣骑士护盾");
        add(ModEffects.CEREBRAL_MINDTRICK.get(), "控脑术");
        add(ModEffects.CONFUSED.get(), "困惑");
        add(ModEffects.HONEY.get(), "蜂蜜");
        add(ModEffects.GRAVITATION.get(), "重力反转");

        add(ModItems.DEMON_HEART.get(), "恶魔之心");

        // 饰品
        add(AGLET.get(), "金属带扣");
        add(ANKLET_OF_THE_WIND.get(), "疾风脚镯");
        add(CLOUD_IN_A_BOTTLE.get(), "云朵瓶");
        add(SHACKLE.get(), "脚镣");
        add(EXTENDO_GRIP.get(), "加长握爪");
        add(BLIZZARD_IN_A_BOTTLE.get(), "暴雪瓶");
        add(ICE_SKATES.get(), "溜冰鞋");
        add(HERMES_BOOTS.get(), "赫尔墨斯靴");
        add(FLURRY_BOOTS.get(), "疾风雪靴");
        add(SAILFISH_BOOTS.get(), "旗鱼靴");
        add(DUNERIDER_BOOTS.get(), "沙丘行者靴");
        add(ROCKET_BOOTS.get(), "火箭靴");
        add(SPECTRE_BOOTS.get(), "幽灵靴");
        add(LIGHTNING_BOOTS.get(), "闪电靴");
        add(FROSTSPARK_BOOTS.get(), "霜花靴");
        add(SHINY_RED_BALLOON.get(), "闪亮红气球");
        add(CLOUD_IN_A_BALLOON.get(), "云朵气球");
        add(BLIZZARD_IN_A_BALLOON.get(), "暴雪气球");
        add(FROG_LEG.get(), "蛙腿");
        add(AMBHIPIAN_BOOTS.get(), "水陆两用靴");
        add(LUCKY_HORSESHOE.get(), "幸运马掌");
        add(BEZOAR.get(), "牛黄");
        add(BLACK_BELT.get(), "黑腰带");
        add(MAGILUMINESCENCE.get(), "魔光护符");
        add(FLIPPER.get(), "脚蹼");
        add(SANDSTORM_IN_A_BOTTLE.get(), "沙暴瓶");
        add(FART_IN_A_JAR.get(), "罐中臭屁");
        add(SANDSTORM_IN_A_BALLOON.get(), "沙暴气球");
        add(FART_IN_A_BALLOON.get(), "臭屁气球");
        add(FROG_FLIPPER.get(), "青蛙脚蹼");
        add(BLUE_HORSESHOE_BALLOON.get(), "蓝马掌气球");
        add(WHITE_HORSESHOE_BALLOON.get(), "白马掌气球");
        add(YELLOW_HORSESHOE_BALLOON.get(), "黄马掌气球");
        add(GREEN_HORSESHOE_BALLOON.get(), "绿马掌气球");
        add(BLINDFOLD.get(), "蒙眼布");
        add(AVENGER_EMBLEM.get(), "复仇者勋章");
        add(COBALT_SHIELD.get(), "钴护盾");
        add(CROSS_NECKLACE.get(), "十字项链");
        add(DESTROYER_EMBLEM.get(), "毁灭者勋章");
        add(EYE_OF_THE_GOLEM.get(), "石巨人之眼");
        add(PINK_HORSESHOE_BALLOON.get(), "粉马掌气球");
        add(SHARKRON_BALLOON.get(), "鲨鱼龙气球");
        add(BALLOON_PUFFERFISH.get(), "气球河豚鱼");
        add(TSUNAMI_IN_A_BOTTLE.get(), "海啸瓶");
        add(MAGMA_SKULL.get(), "岩浆骷髅头");
        add(LAVA_CHARM.get(), "熔岩护身符");
        add(OBSIDIAN_ROSE.get(), "黑曜石玫瑰");
        add(OBSIDIAN_SHIELD.get(), "黑曜石护盾");
        add(OBSIDIAN_SKULL.get(), "黑曜石骷髅头");
        add(MOLTEN_SKULL_ROSE.get(), "熔火骷髅头玫瑰");
        add(OBSIDIAN_SKULL_ROSE.get(), "黑曜石骷髅头玫瑰");
        add(COPPER_WATCH.get(), "铜表");
        add(TIN_WATCH.get(), "锡表");
        add(SILVER_WATCH.get(), "银表");
        add(TUNGSTEN_WATCH.get(), "钨表");
        add(GOLD_WATCH.get(), "金表");
        add(PLATINUM_WATCH.get(), "铂金表");
        add(PALADINS_SHIELD.get(), "圣骑士护盾");
        add(RADAR.get(), "雷达");
        add(DEPTH_METER.get(), "深度计");
        add(COMPASS.get(), "罗盘");
        add(FERAL_CLAWS.get(), "狂爪手套");
        add(FIRE_GAUNTLET.get(), "烈火手套");
        add(FLESH_KNUCKLES.get(), "血肉指虎");
        add(POWER_GLOVE.get(), "强力手套");
        add(PUTRID_SCENT.get(), "腐香囊");
        add(MOLTEN_CHARM.get(), "熔火护身符");
        add(WATER_WALKING_BOOTS.get(), "水上漂靴");
        add(OBSIDIAN_WATER_WALKING_BOOTS.get(), "黑曜石水上漂靴");
        add(LAVA_WADERS.get(), "熔岩靴");
        add(TERRASPARK_BOOTS.get(), "泰拉闪耀靴");
        add(OBSIDIAN_HORSESHOE.get(), "黑曜石马掌");
        add(BERSERKERS_GLOVE.get(), "狂战士手套");
        add(TITAN_GLOVE.get(), "泰坦手套");
        add(TALLY_COUNTER.get(), "杀怪计数器");
        add(BUNDLE_OF_BALLOONS.get(), "气球束");
        add(BUNDLE_OF_HORSESHOE_BALLOONS.get(), "马掌气球束");
        add(FROZEN_TURTLE_SHELL.get(), "冰冻海龟壳");
        add(FROZEN_SHIELD.get(), "冰冻护盾");
        add(HERO_SHIELD.get(), "英雄护盾");
        add(MOON_STONE.get(), "月亮石");
        add(SUN_STONE.get(), "太阳石");
        add(MECHANICAL_GLOVE.get(), "机械手套");
        add(PANIC_NECKLACE.get(), "恐慌项链");
        add(MAGMA_STONE.get(), "岩浆石");
        add(METAL_DETECTOR.get(), "金属探测器");
        add(STOPWATCH.get(), "秒表");
        add(REK_3000.get(), "R.E.K.3000");
        add(LIFE_FORM_ANALYZER.get(), "生命体分析机");
        add(DPS_METER.get(), "每秒伤害计数器");
        add(FISHERMANS_POCKET_GUIDE.get(), "渔民袖珍宝典");
        add(WEATHER_RADIO.get(), "天气收音机");
        add(SEXTANT.get(), "六分仪");
        add(GPS.get(), " 全球定位系统");
        add(GOBLIN_TECH.get(), "哥布林数据仪");
        add(FISH_FINDER.get(), "探鱼器");
        add(PDA.get(), "个人数字助手");
        add(SHARK_TOOTH_NECKLACE.get(), "鲨牙项链");
        add(CELESTIAL_STONE.get(), "天界石");
        add(FAST_CLOCK.get(), "快走时钟");
        add(SORCERER_EMBLEM.get(), "巫师徽章");
        add(ANCIENT_CHISEL.get(), "远古凿子");
        add(ROYAL_GEL.get(), "皇家凝胶");
        add(SHIELD_OF_CTHULHU.get(), "克苏鲁护盾");
        add(WORM_SCARF.get(), "蠕虫围巾");
        add(BRAIN_OF_CONFUSION.get(), "混乱之脑");
        add(VITAMINS.get(), "维生素");
        add(GRAVITY_GLOBE.get(), "重力球");
        add(WARRIOR_EMBLEM.get(), "战士徽章");
        add(TREASURE_MAGNET.get(), "宝藏磁石");
        add(FLOWER_BOOTS.get(), "花靴");
        add(FAIRY_BOOTS.get(), "仙灵靴");
        add(RANGER_EMBLEM.get(), "游侠徽章");
        add(HAND_WARMER.get(), "暖手宝");
        add(STAR_CLOAK.get(), "星星斗篷");
        add(STAR_VEIL.get(), "星星面纱");
        add(BEE_CLOAK.get(), "蜜蜂斗篷");
        add(HIVE_PACK.get(), "蜂巢背包");
        add(HONEY_COMB.get(), "蜂窝");
        add(STINGER_NECKLACE.get(), "毒刺项链");
        add(SWEETHEART_NECKLACE.get(), "甜心项链");
        add(HONEY_BALLOON.get(), "蜂蜜气球");
        add(AMBER_HORSESHOE_BALLOON.get(), "琥珀马掌气球");
        add(MAGIC_QUIVER.get(), "魔法箭袋");
        add(MOLTEN_QUIVER.get(), "熔火箭袋");
        add(STALKERS_QUIVER.get(), "潜行者箭袋");
        add(BAND_OF_REGENERATION.get(), "再生手环");
        add(ANGLER_EARRING.get(), "渔夫耳环");
        add(HOLY_WATER.get(), "圣水");
        add(DETOXIFICATION_CAPSULE.get(), "解毒囊");
        add(ENERGY_BAR.get(), "能量棒");
        add(NUTRIENT_SOLUTION.get(), "营养液");
        add(FLASHLIGHT.get(), "手电筒");
        add(SEARCHLIGHT.get(), "探照灯");
        add(TRIFOLD_MAP.get(), "三折地图");
        add(THE_PLAN.get(), "计划书");
        add(HAND_DRILL.get(), "手钻");
        add(SHOT_PUT.get(), "铅球");
        add(EXPLORERS_EQUIPMENT.get(), "探险家宝具");
        add(ANKH_CHARM.get(), "十字章护身符");
        add(ANKH_SHIELD.get(), "十字章护盾");
        add(RIFLE_SCOPE.get(), "步枪瞄准镜");
        add(SNIPER_SCOPE.get(), "狙击镜");
        add(RECON_SCOPE.get(), "侦察镜");
        add(TOOLBELT.get(), "工具腰带");
        add(TOOLBOX.get(), "工具箱");
        add(PORTABLE_CEMENT_MIXER.get(), "便携式水泥搅拌机");
        add(BRICK_LAYER.get(), "砌砖刀");
        add(ARCHITECT_GIZMO_PACK.get(), "建筑师发明背包");
        add(CLIMBING_CLAWS.get(), "攀爬爪");
        add(SHOE_SPIKES.get(), "鞋钉");
        add(TIGER_CLIMBING_GEAR.get(), "猛虎攀爬装备");
        add(TABI.get(), "分趾厚底袜");
        add(MASTER_NINJA_GEAR.get(), "忍者大师装备");
        add(FROG_WEBBING.get(), "青蛙蹼");
        add(FROG_GEAR.get(), "青蛙装备");
    }
}
