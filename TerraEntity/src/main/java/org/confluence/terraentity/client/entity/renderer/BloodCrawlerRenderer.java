package org.confluence.terraentity.client.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

import org.confluence.terraentity.client.entity.model.GeoNormalModel;
import org.confluence.terraentity.entity.monster.BloodCrawler;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BloodCrawlerRenderer extends GeoEntityRenderer<BloodCrawler> {
    public BloodCrawlerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeoNormalModel<>("blood_crawler"));
    }

    @Override
    protected float getDeathMaxRotation(BloodCrawler animatable){
        return 0;
    }
}
