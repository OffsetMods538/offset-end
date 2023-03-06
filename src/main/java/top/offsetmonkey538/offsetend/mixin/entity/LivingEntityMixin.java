package top.offsetmonkey538.offsetend.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.offsetend.entity.effect.ModStatusEffects;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

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
