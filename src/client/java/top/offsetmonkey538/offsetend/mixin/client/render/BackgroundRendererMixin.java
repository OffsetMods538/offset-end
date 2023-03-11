package top.offsetmonkey538.offsetend.mixin.client.render;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.offsetmonkey538.offsetend.fluid.ModFluidTags;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {

    @Shadow private static float red;

    @Shadow private static float green;

    @Shadow private static float blue;

    @Shadow private static long lastWaterFogColorUpdateTime;

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/world/ClientWorld$Properties;getHorizonShadingRatio()F"
            )
    )
    private static void offsetend$addBlackFogToEndWater(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci) {
        FluidState fluid = world.getFluidState(camera.getBlockPos());
        if (!fluid.isIn(ModFluidTags.END_WATER)) return;

        red = green = blue = 0x16 / 255f;

        lastWaterFogColorUpdateTime = -1L;
    }
}
