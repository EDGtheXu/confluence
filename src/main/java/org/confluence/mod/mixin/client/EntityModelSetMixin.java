package org.confluence.mod.mixin.client;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import org.confluence.mod.mixinauxiliary.IModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityModelSet.class)
public abstract class EntityModelSetMixin {
    @Inject(method = "bakeLayer", at = @At("RETURN"))
    private void bakeLayer(ModelLayerLocation pModelLayerLocation, CallbackInfoReturnable<ModelPart> cir){
        ModelPart root = cir.getReturnValue();
        root.getAllParts().forEach(child->{
            ((IModelPart) (Object) child).confluence$root(root);
        });
        ((IModelPart) (Object) root).confluence$root(root);
    }
}
