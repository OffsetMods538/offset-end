package top.offsetmonkey538.offsetend.render.entity.feature;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.math.RotationAxis;
import top.offsetmonkey538.offsetend.block.ModBlocks;

public class EndCowMushroomFeatureRenderer<T extends LivingEntity> extends FeatureRenderer<T, CowEntityModel<T>> {
    private final BlockRenderManager blockRenderManager;

    public EndCowMushroomFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> context, BlockRenderManager blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.isBaby()) return;
        MinecraftClient minecraftClient = MinecraftClient.getInstance();

        boolean hasOutline = minecraftClient.hasOutline(entity) && entity.isInvisible();
        if (entity.isInvisible() && !hasOutline) return;

        BlockState mushroom = ModBlocks.END_MUSHROOM.getDefaultState();

        int overlay = LivingEntityRenderer.getOverlay(entity, 0.0f);
        BakedModel bakedModel = this.blockRenderManager.getModel(mushroom);
        matrices.push();
        matrices.translate(0.2f, -0.35f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0f));
        matrices.scale(-1.0f, -1.0f, 1.0f);
        matrices.translate(-0.5f, -0.5f, -0.5f);
        this.renderMushroom(matrices, vertexConsumers, light, hasOutline, mushroom, overlay, bakedModel);
        matrices.pop();
        matrices.push();
        matrices.translate(0.2f, -0.35f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(42.0f));
        matrices.translate(0.1f, 0.0f, -0.6f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0f));
        matrices.scale(-1.0f, -1.0f, 1.0f);
        matrices.translate(-0.5f, -0.5f, -0.5f);
        this.renderMushroom(matrices, vertexConsumers, light, hasOutline, mushroom, overlay, bakedModel);
        matrices.pop();
        matrices.push();
        this.getContextModel().getHead().rotate(matrices);
        matrices.translate(0.0f, -0.7f, -0.2f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-78.0f));
        matrices.scale(-1.0f, -1.0f, 1.0f);
        matrices.translate(-0.5f, -0.5f, -0.5f);
        this.renderMushroom(matrices, vertexConsumers, light, hasOutline, mushroom, overlay, bakedModel);
        matrices.pop();
    }

    private void renderMushroom(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean renderAsModel, BlockState mushroomState, int overlay, BakedModel mushroomModel) {
        if (renderAsModel) {
            this.blockRenderManager.getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE)), mushroomState, mushroomModel, 0.0f, 0.0f, 0.0f, light, overlay);
        } else {
            this.blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay);
        }
    }
}
