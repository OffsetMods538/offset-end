package top.offsetmonkey538.offsetend.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.offsetend.fluid.ModFluidTags;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {

    @Shadow protected boolean isSubmergedInWater;

    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyReturnValue(
            method = "updateWaterSubmersionState",
            at = @At("RETURN")
    )
    private boolean offsetend$TODO(boolean original) {
        if (!this.isSubmergedIn(ModFluidTags.END_WATER)) return original;
        this.isSubmergedInWater = true;
        return true;
    }
}
