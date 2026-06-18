package top.offsetmonkey538.offsetend.render.entity.feature;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.LivingEntity;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class EndCowEyesFeatureRenderer<T extends LivingEntity> extends EyesFeatureRenderer<T, CowEntityModel<T>> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(id("textures/entity/end_cow/end_cow_eyes.png"));

    public EndCowEyesFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}
