package top.offsetmonkey538.offsetend.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.offsetend.entity.effect.ModStatusEffects;
import top.offsetmonkey538.offsetend.fluid.ModFluidTags;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract boolean isAlive();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyExpressionValue(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;isSubmergedIn(Lnet/minecraft/registry/tag/TagKey;)Z"
            )
    )
    private boolean offsetend$makeEndWaterDepleteAir(boolean original) {
        return this.isSubmergedIn(ModFluidTags.END_WATER) || original;
    }

    @ModifyExpressionValue(
            method = "tickMovement",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;getFluidHeight(Lnet/minecraft/registry/tag/TagKey;)D"
            )
    )
    private double offsetend$makeItPossibleToSwimUpInEndWater(double original) {
        return this.getFluidHeight(ModFluidTags.END_WATER) > 0 ? this.getFluidHeight(ModFluidTags.END_WATER) : original;
    }

    @ModifyReturnValue(
            method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z",
            at = @At("RETURN")
    )
    @SuppressWarnings("ConstantValue")
    private boolean offsetend$makePlayersWithEndAffinityNotAngerEndermenOrShulkers(boolean original, LivingEntity target) {
        if (!((Object) this instanceof EndermanEntity || (Object) this instanceof ShulkerEntity)) return original;
        if (!(target instanceof PlayerEntity player) || !player.hasStatusEffect(ModStatusEffects.END_AFFINITY)) return original;
        return false;
    }
}
