package top.offsetmonkey538.offsetend.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;
import top.offsetmonkey538.offsetend.entity.EndCowEntity;
import top.offsetmonkey538.offsetend.render.entity.feature.EndCowEyesFeatureRenderer;
import top.offsetmonkey538.offsetend.render.entity.feature.EndCowMushroomFeatureRenderer;
import top.offsetmonkey538.offsetend.render.entity.model.ModEntityModelLayers;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class EndCowEntityRenderer extends MobEntityRenderer<EndCowEntity, CowEntityModel<EndCowEntity>> {

    public EndCowEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(ModEntityModelLayers.END_COW)), 0.5f);
        addFeature(new EndCowEyesFeatureRenderer<>(this));
        addFeature(new EndCowMushroomFeatureRenderer<>(this, context.getBlockRenderManager()));
    }

    @Override
    public Identifier getTexture(EndCowEntity entity) {
        return id("textures/entity/end_cow/end_cow.png");
    }
}
