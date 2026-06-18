package top.offsetmonkey538.offsetend.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.offsetmonkey538.offsetend.fluid.ModFluidTags;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract boolean isSubmergedIn(TagKey<Fluid> fluidTag);

    @Shadow public abstract boolean updateMovementInFluid(TagKey<Fluid> tag, double speed);

    @Shadow public abstract void extinguish();

    @Shadow protected boolean touchingWater;

    @Shadow public float fallDistance;

    @Shadow public World world;

    @Inject(
            method = "updateWaterState",
            at = @At("TAIL")
    )
    private void offsetend$setTouchingWaterWhenInEndWater(CallbackInfoReturnable<Boolean> cir) {
        if (!this.updateMovementInFluid(ModFluidTags.END_WATER, 0.014)) return;


        this.touchingWater = true;
        this.fallDistance = 0;

        this.extinguish();
    }

    @ModifyExpressionValue(
            method = "updateSubmergedInWaterState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;isSubmergedIn(Lnet/minecraft/registry/tag/TagKey;)Z"
            )
    )
    private boolean offsetend$setSubmergedInWaterWhenInEndWater(boolean original) {
        return this.isSubmergedIn(ModFluidTags.END_WATER) || original;
    }

    @WrapOperation(
            method = "updateSwimming",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"
            )
    )
    private boolean offsetend$makeEndWaterSwimmable(FluidState instance, TagKey<Fluid> tag, Operation<Boolean> original) {
        return instance.isIn(ModFluidTags.END_WATER) || original.call(instance, tag);
    }
}
