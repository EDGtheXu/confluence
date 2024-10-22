package org.confluence.mod.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.confluence.mod.Confluence;
import org.confluence.mod.client.ClientConfigs;
import org.confluence.mod.client.model.entity.bomb.*;
import org.confluence.mod.client.renderer.entity.bomb.*;
import org.confluence.mod.client.renderer.gui.HealthHudLayer;
import org.confluence.mod.client.renderer.gui.InfoHudOverlay;
import org.confluence.mod.client.renderer.gui.ManaHudLayer;

import static org.confluence.mod.common.init.ModEntities.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Confluence.MODID)
public final class ModClientEvents {
    @SubscribeEvent
    public static void onRegisterClientReload(RegisterClientReloadListenersEvent event) {

    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ClientConfigs.onLoad();
        });
    }

    @SubscribeEvent
    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerBelow(VanillaGuiLayers.SELECTED_ITEM_NAME, Confluence.asResource("mana_hud"), new ManaHudLayer());
        event.registerAboveAll(Confluence.asResource("info_hud"), new InfoHudOverlay());
        event.registerAboveAll(Confluence.asResource("health_hud"), new HealthHudLayer());
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BaseBombEntityModel.LAYER_LOCATION, BaseBombEntityModel::createBodyLayer);
        event.registerLayerDefinition(BouncyBombEntityModel.LAYER_LOCATION, BouncyBombEntityModel::createBodyLayer);
        event.registerLayerDefinition(ScarabBombEntityModel.LAYER_LOCATION, ScarabBombEntityModel::createBodyLayer);
        event.registerLayerDefinition(StickyBombEntityModel.LAYER_LOCATION, StickyBombEntityModel::createBodyLayer);
        event.registerLayerDefinition(BombFishEntityModel.LAYER_LOCATION, BombFishEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BOMB_ENTITY.get(), BaseBombEntityRenderer::new);
        event.registerEntityRenderer(BOUNCY_BOMB_ENTITY.get(), BouncyBombEntityRenderer::new);
        event.registerEntityRenderer(SCARAB_BOMB_ENTITY.get(), ScarabBombEntityRenderer::new);
        event.registerEntityRenderer(STICKY_BOMB_ENTITY.get(), StickyBombEntityRenderer::new);
        event.registerEntityRenderer(BOMB_FISH_ENTITY.get(), BombFishEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {

    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {

    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {

    }

    @SubscribeEvent
    public static void registerEntitySpectatorShaders(RegisterEntitySpectatorShadersEvent event) {

    }
}
