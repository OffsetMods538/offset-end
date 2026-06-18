package top.offsetmonkey538.offsetend.mixin.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.offsetmonkey538.offsetend.fluid.ModFluidTags;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

@Mixin(InGameOverlayRenderer.class)
public abstract class InGameOverlayRendererMixin {

    @Unique
    private static final Identifier offsetend$END_WATER_OVERLAY_TEXTURE = id("textures/misc/end_water_overlay.png");

     @Inject(
             method = "renderOverlays",
             at = @At("TAIL")
     )
    private static void offsetend$renderEndWaterOverlayWhenInEndWater(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
         ClientPlayerEntity player = client.player;
         if (player == null || player.isSpectator() || !player.isSubmergedIn(ModFluidTags.END_WATER)) return;



         final float brightness = LightmapTextureManager.getBrightness(player.world.getDimension(), player.world.getLightLevel(new BlockPos(player.getEyePos())));

         RenderSystem.setShader(GameRenderer::getPositionTexProgram);
         RenderSystem.enableTexture();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShaderColor(brightness, brightness, brightness, 0.45f);
         RenderSystem.setShaderTexture(0, offsetend$END_WATER_OVERLAY_TEXTURE);


         Matrix4f positionMatrix = matrices.peek().getPositionMatrix();
         BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
         float yawTextureOffset = -player.getYaw() / 64.0f;
         float pitchTextureOffset = player.getPitch() / 64.0f;

         bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
         bufferBuilder.vertex(positionMatrix, -1.0f, -1.0f, -0.5f).texture(4.0f + yawTextureOffset, 4.0f + pitchTextureOffset).next();
         bufferBuilder.vertex(positionMatrix, 1.0f, -1.0f, -0.5f).texture(0.0f + yawTextureOffset, 4.0f + pitchTextureOffset).next();
         bufferBuilder.vertex(positionMatrix, 1.0f, 1.0f, -0.5f).texture(0.0f + yawTextureOffset, 0.0f + pitchTextureOffset).next();
         bufferBuilder.vertex(positionMatrix, -1.0f, 1.0f, -0.5f).texture(4.0f + yawTextureOffset, 0.0f + pitchTextureOffset).next();

         BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());


         RenderSystem.disableBlend();
     }
}
