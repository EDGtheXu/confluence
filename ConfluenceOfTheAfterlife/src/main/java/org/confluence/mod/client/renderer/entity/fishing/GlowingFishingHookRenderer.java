package org.confluence.mod.client.renderer.entity.fishing;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.confluence.mod.Confluence;
import org.confluence.mod.client.model.entity.fishing.GlowingFishingHookModel;
import org.confluence.mod.common.entity.fishing.CurioFishingHook;
import org.confluence.mod.util.color.IntegerRGB;
import org.jetbrains.annotations.NotNull;

import static org.confluence.mod.client.renderer.entity.fishing.BaseFishingHookRenderer.renderString;

public class GlowingFishingHookRenderer extends EntityRenderer<CurioFishingHook> {
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[]{
        Confluence.asResource("textures/entity/fishing/common.png"),
        Confluence.asResource("textures/entity/fishing/glowing.png"),
        Confluence.asResource("textures/entity/fishing/lava_moss.png"),
        Confluence.asResource("textures/entity/fishing/helium_moss.png"),
        Confluence.asResource("textures/entity/fishing/neon_moss.png"),
        Confluence.asResource("textures/entity/fishing/argon_moss.png"),
        Confluence.asResource("textures/entity/fishing/krypton_moss.png"),
        Confluence.asResource("textures/entity/fishing/xenon_moss.png")
    };
    private static final RenderType[] GLOWS = new RenderType[]{
        null,
        RenderType.entityCutoutNoCull(Confluence.asResource("textures/entity/fishing/glowing_glow.png")), // todo glowing
        RenderType.entityCutoutNoCull(Confluence.asResource("textures/entity/fishing/lava_moss_glow.png")), // todo glowing
        RenderType.entityCutoutNoCull(Confluence.asResource("textures/entity/fishing/helium_moss_glow.png")), // 无需发光
        RenderType.entityCutoutNoCull(Confluence.asResource("textures/entity/fishing/neon_moss_glow.png")), // todo glowing
        RenderType.entityCutoutNoCull(Confluence.asResource("textures/entity/fishing/argon_moss_glow.png")), // todo glowing
        RenderType.entityCutoutNoCull(Confluence.asResource("textures/entity/fishing/krypton_moss_glow.png")), // todo glowing
        RenderType.entityCutoutNoCull(Confluence.asResource("textures/entity/fishing/xenon_moss_glow.png")) // todo glowing
    };
    private final GlowingFishingHookModel mossModel;
    private final GlowingFishingHookModel commonModel;
    private final GlowingFishingHookModel glowingModel;

    public GlowingFishingHookRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.mossModel = new GlowingFishingHookModel(pContext.bakeLayer(GlowingFishingHookModel.MOSS));
        this.commonModel = new GlowingFishingHookModel(pContext.bakeLayer(GlowingFishingHookModel.COMMON));
        this.glowingModel = new GlowingFishingHookModel(pContext.bakeLayer(GlowingFishingHookModel.GLOWING));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CurioFishingHook pEntity) {
        return TEXTURES[pEntity.getVariant().getId()];
    }

    public GlowingFishingHookModel getModel(CurioFishingHook fishingHook) {
        CurioFishingHook.Variant variant = fishingHook.getVariant();
        if (variant == CurioFishingHook.Variant.COMMON) {
            return commonModel;
        } else if (variant == CurioFishingHook.Variant.GLOWING) {
            return glowingModel;
        } else {
            return mossModel;
        }
    }

    @Override
    public void render(@NotNull CurioFishingHook pEntity, float pEntityYaw, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        int id = pEntity.getVariant().getId();
        ResourceLocation texture = TEXTURES[id];
        GlowingFishingHookModel model = getModel(pEntity);
        model.renderToBuffer(pPoseStack, pBuffer.getBuffer(model.renderType(texture)), pPackedLight, OverlayTexture.NO_OVERLAY);
        RenderType glow = GLOWS[id];
        if (glow != null) {
            // todo glowing
        }
        renderString(entityRenderDispatcher, pEntity, pPartialTick, pPoseStack, pBuffer, IntegerRGB.BLACK);
    }
}
