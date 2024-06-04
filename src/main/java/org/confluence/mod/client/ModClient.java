package org.confluence.mod.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.ColorResolver;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.confluence.mod.Confluence;
import org.confluence.mod.block.ModBlocks;
import org.confluence.mod.client.color.IntegerRGB;
import org.confluence.mod.client.model.entity.BeeProjectileModel;
import org.confluence.mod.client.model.entity.BulletModel;
import org.confluence.mod.client.model.entity.fishing.BaseFishingHookModel;
import org.confluence.mod.client.model.entity.hook.BaseHookModel;
import org.confluence.mod.client.model.entity.hook.SkeletronHandModel;
import org.confluence.mod.client.model.entity.hook.WebSlingerModel;
import org.confluence.mod.client.particle.BulletParticle;
import org.confluence.mod.client.particle.ExtendedBreakingItemParticle;
import org.confluence.mod.client.particle.ModParticles;
import org.confluence.mod.client.renderer.block.ActuatorsBlockRenderer;
import org.confluence.mod.client.renderer.entity.*;
import org.confluence.mod.client.renderer.entity.fishing.BaseFishingHookRenderer;
import org.confluence.mod.client.renderer.entity.hook.*;
import org.confluence.mod.client.renderer.gui.ConfluenceOverlays;
import org.confluence.mod.item.common.Gels;

import static org.confluence.mod.entity.ModEntities.*;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = Confluence.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ModClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CuriosClient.registerRenderers();

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.EBONY_LOG_BLOCKS.DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.EBONY_LOG_BLOCKS.TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PALM_LOG_BLOCKS.DOOR.get(), RenderType.cutout());
        });
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerBelow(VanillaGuiOverlay.ITEM_NAME.id(), "mana_hud", ConfluenceOverlays.HUD_MANA);
        event.registerAboveAll("info_hud", ConfluenceOverlays.INFO_HUD);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        CuriosClient.registerLayers(event::registerLayerDefinition);

        event.registerLayerDefinition(BulletModel.LAYER_LOCATION, BulletModel::createBodyLayer);
        event.registerLayerDefinition(BeeProjectileModel.LAYER_LOCATION, BeeProjectileModel::createBodyLayer);
        event.registerLayerDefinition(BaseHookModel.LAYER_LOCATION, BaseHookModel::createBodyLayer);
        event.registerLayerDefinition(WebSlingerModel.LAYER_LOCATION, WebSlingerModel::createBodyLayer);
        event.registerLayerDefinition(SkeletronHandModel.LAYER_LOCATION, SkeletronHandModel::createBodyLayer);

        event.registerLayerDefinition(BaseFishingHookModel.WOOD, BaseFishingHookModel::createWoodLayer);
        event.registerLayerDefinition(BaseFishingHookModel.REINFORCED, BaseFishingHookModel::createReinforcedLayer);
        event.registerLayerDefinition(BaseFishingHookModel.SOULS, BaseFishingHookModel::createSoulsLayer);
        event.registerLayerDefinition(BaseFishingHookModel.FLESH, BaseFishingHookModel::createFleshLayer);
        event.registerLayerDefinition(BaseFishingHookModel.SCARAB, BaseFishingHookModel::createScarabLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BLUE_SLIME.get(), c -> new CustomSlimeRenderer(c, "blue"));
        event.registerEntityRenderer(GREEN_SLIME.get(), c -> new CustomSlimeRenderer(c, "green"));
        event.registerEntityRenderer(PINK_SLIME.get(), c -> new CustomSlimeRenderer(c, "pink"));
        event.registerEntityRenderer(CORRUPTED_SLIME.get(), c -> new CustomSlimeRenderer(c, "corrupted"));
        event.registerEntityRenderer(DESERT_SLIME.get(), c -> new CustomSlimeRenderer(c, "desert"));
        event.registerEntityRenderer(EVIL_SLIME.get(), c -> new CustomSlimeRenderer(c, "evil"));
        event.registerEntityRenderer(ICE_SLIME.get(), c -> new CustomSlimeRenderer(c, "ice"));
        event.registerEntityRenderer(LAVA_SLIME.get(), c -> new CustomSlimeRenderer(c, "lava"));
        event.registerEntityRenderer(LUMINOUS_SLIME.get(), c -> new CustomSlimeRenderer(c, "luminous"));
        event.registerEntityRenderer(CRIMSON_SLIME.get(), c -> new CustomSlimeRenderer(c, "crimson"));
        event.registerEntityRenderer(PURPLE_SLIME.get(), c -> new CustomSlimeRenderer(c, "purple"));
        event.registerEntityRenderer(RED_SLIME.get(), c -> new CustomSlimeRenderer(c, "red"));
        event.registerEntityRenderer(TROPIC_SLIME.get(), c -> new CustomSlimeRenderer(c, "tropic"));
        event.registerEntityRenderer(YELLOW_SLIME.get(), c -> new CustomSlimeRenderer(c, "yellow"));
        event.registerEntityRenderer(BLACK_SLIME.get(), c -> new CustomSlimeRenderer(c, "black"));

        event.registerEntityRenderer(DEMON_EYE.get(), DemonEyeRenderer::new);

        event.registerEntityRenderer(BASE_BULLET.get(), BulletRenderer::new);
        event.registerEntityRenderer(FALLING_STAR_ITEM_ENTITY.get(), FallingStarRenderer::new);
        event.registerEntityRenderer(BEE_PROJECTILE.get(), BeeProjectileRenderer::new);
        event.registerEntityRenderer(LAVA_FISHING_HOOK.get(), FishingHookRenderer::new);
        event.registerEntityRenderer(BASE_FISHING_HOOK.get(), BaseFishingHookRenderer::new);
        event.registerEntityRenderer(EFFECT_THROWN_POTION.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(STAR_CLOAK.get(), StarCloakEntityRenderer::new);

        event.registerEntityRenderer(BASE_HOOK.get(), BaseHookRenderer::new);
        event.registerEntityRenderer(WEB_SLINGER.get(), WebSlingerRenderer::new);
        event.registerEntityRenderer(SKELETRON_HAND.get(), SkeletronHandRenderer::new);
        event.registerEntityRenderer(SLIME_HOOK.get(), SlimeHookRenderer::new);
        event.registerEntityRenderer(FISH_HOOK.get(), FishHookRenderer::new);
        event.registerEntityRenderer(IVY_WHIP.get(), IvyWhipRenderer::new);
        event.registerEntityRenderer(BAT_HOOK.get(), BatHookRenderer::new);
        event.registerEntityRenderer(CANDY_CANE_HOOK.get(), CandyCaneHookRenderer::new);
        event.registerEntityRenderer(DUAL_HOOK.get(), DualHookRenderer::new);
        event.registerEntityRenderer(HOOK_OF_DISSONANCE.get(), HookOfDissonanceRenderer::new);
        event.registerEntityRenderer(THORN_HOOK.get(), ThornHookRenderer::new);
        event.registerEntityRenderer(MIMIC_HOOK.get(), MimicHookRenderer::new);
        event.registerEntityRenderer(ANTI_GRAVITY_HOOK.get(), AntiGravityHookRenderer::new);
        event.registerEntityRenderer(SPOOKY_HOOK.get(), SpookyHookRenderer::new);
        event.registerEntityRenderer(CHRISTMAS_HOOK.get(), ChristmasHookRenderer::new);
        event.registerEntityRenderer(LUNAR_HOOK.get(), LunarHookRenderer::new);
        /* todo 静止钩 */

        event.registerEntityRenderer(BASE_AMMO.get(), AmmoRenderer::new);

        event.registerBlockEntityRenderer(ModBlocks.ACTUATORS_ENTITY.get(), ActuatorsBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpecial(ModParticles.ITEM_BLUE_GEL.get(), new ExtendedBreakingItemParticle.SlimeBallProvider(Gels.BLUE_GEL.get()));
        event.registerSpecial(ModParticles.ITEM_PINK_GEL.get(), new ExtendedBreakingItemParticle.SlimeBallProvider(Gels.PINK_GEL.get()));
        event.registerSpecial(ModParticles.ITEM_HONEY_GEL.get(), new ExtendedBreakingItemParticle.SlimeBallProvider(Gels.HONEY_GEL.get()));
        event.registerSpecial(ModParticles.ITEM_FROZEN_GEL.get(), new ExtendedBreakingItemParticle.SlimeBallProvider(Gels.FROZEN_GEL.get()));

        event.registerSpriteSet(ModParticles.RUBY_BULLET.get(), BulletParticle.Provider::new);
        event.registerSpriteSet(ModParticles.AMBER_BULLET.get(), BulletParticle.Provider::new);
        event.registerSpriteSet(ModParticles.TOPAZ_BULLET.get(), BulletParticle.Provider::new);
        event.registerSpriteSet(ModParticles.EMERALD_BULLET.get(), BulletParticle.Provider::new);
        event.registerSpriteSet(ModParticles.DIAMOND_BULLET.get(), BulletParticle.Provider::new);
        event.registerSpriteSet(ModParticles.AMETHYST_BULLET.get(), BulletParticle.Provider::new);
        event.registerSpriteSet(ModParticles.SAPPHIRE_BULLET.get(), BulletParticle.Provider::new);
    }

    public static final ColorResolver HALLOW_WATER_RESOLVER = (biome, x, z) -> -1554953;

    @SubscribeEvent
    public static void registerColorResolvers(RegisterColorHandlersEvent.ColorResolvers event) {
        event.register(HALLOW_WATER_RESOLVER);
    }

    public static final BlockColor HALLOW_LEAVES_COLOR = (blockState, getter, pos, tint) -> {
        if (pos == null) return -1;

        int i = Math.abs(pos.getX()) % 12;
        int k = Math.abs(pos.getZ()) % 12;
        IntegerRGB x;
        IntegerRGB z;

        if (i <= 4) x = IntegerRGB.HALLOW_A.mixture(IntegerRGB.HALLOW_B, i * 0.25F);
        else if (i <= 8) x = IntegerRGB.HALLOW_B.mixture(IntegerRGB.HALLOW_C, (i - 4) * 0.25F);
        else x = IntegerRGB.HALLOW_C.mixture(IntegerRGB.HALLOW_A, (i - 8) * 0.25F);

        if (k <= 4) z = IntegerRGB.HALLOW_A.mixture(IntegerRGB.HALLOW_B, k * 0.25F);
        else if (k <= 8) z = IntegerRGB.HALLOW_B.mixture(IntegerRGB.HALLOW_C, (k - 4) * 0.25F);
        else z = IntegerRGB.HALLOW_C.mixture(IntegerRGB.HALLOW_A, (k - 8) * 0.25F);

        return x.mixture(z, 0.5F).get();
    };

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(HALLOW_LEAVES_COLOR, ModBlocks.PEARL_LOG_BLOCKS.LEAVES.get());
    }
}
