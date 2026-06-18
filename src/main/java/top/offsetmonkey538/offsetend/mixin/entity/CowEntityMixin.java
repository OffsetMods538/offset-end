package top.offsetmonkey538.offsetend.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.offsetmonkey538.offsetend.entity.EndCowEntity;

@Mixin(CowEntity.class)
public abstract class CowEntityMixin extends AnimalEntity {
    protected CowEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "interactMob",
            at = @At("HEAD"),
            cancellable = true
    )
    @SuppressWarnings("ConstantValue")
    private void offsetend$callSuperMethodWhenInstanceofEndCow(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if ((Object) this instanceof EndCowEntity) cir.setReturnValue(super.interactMob(player, hand));
    }
}
