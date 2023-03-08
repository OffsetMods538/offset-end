package top.offsetmonkey538.offsetend.render.entity.model;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModEntityModelLayers {
    public static final EntityModelLayer END_COW = registerMain("end_cow", CowEntityModel::getTexturedModelData);

    private static EntityModelLayer registerMain(String name, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        final EntityModelLayer entityModelLayer = new EntityModelLayer(id(name), "main");
        EntityModelLayerRegistry.registerModelLayer(entityModelLayer, provider);
        return entityModelLayer;
    }

    public static void register() {
        // Registers entity model layers by loading this class.
    }
}
