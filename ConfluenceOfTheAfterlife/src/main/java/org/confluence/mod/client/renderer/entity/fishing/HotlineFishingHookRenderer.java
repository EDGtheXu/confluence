package org.confluence.mod.client.renderer.entity.fishing;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.confluence.mod.Confluence;
import org.confluence.mod.client.model.entity.fishing.HotlineFishingHookModel;
import org.confluence.mod.common.entity.fishing.HotlineFishingHook;
import org.confluence.mod.util.color.IntegerRGB;
import org.jetbrains.annotations.NotNull;

import static org.confluence.mod.client.renderer.entity.fishing.BaseFishingHookRenderer.renderString;

public class HotlineFishingHookRenderer extends EntityRenderer<HotlineFishingHook> {
    private static final ResourceLocation TEXTURE = Confluence.asResource("textures/entity/fishing/hotline.png");
    private final HotlineFishingHookModel model;

    public HotlineFishingHookRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new HotlineFishingHookModel(pContext.bakeLayer(HotlineFishingHookModel.LAYER_LOCATION));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull HotlineFishingHook pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(@NotNull HotlineFishingHook pEntity, float pEntityYaw, float pPartialTick, @NotNull PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        model.renderToBuffer(pPoseStack, pBuffer.getBuffer(model.renderType(TEXTURE)), pPackedLight, OverlayTexture.NO_OVERLAY);
        // todo glowing
        renderString(entityRenderDispatcher, pEntity, pPartialTick, pPoseStack, pBuffer, IntegerRGB.ORANGE);
    }
}
