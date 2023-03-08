package top.offsetmonkey538.offsetend.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import top.offsetmonkey538.offsetend.item.ModItems;

public class EndCowEntity extends CowEntity {

    private static final int TELEPORT_RANGE = 16;
    private static final int TELEPORT_TRIES = 20;

    public EndCowEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(Items.BOWL) || this.isBaby()) return super.interactMob(player, hand);
        player.playSound(SoundEvents.ENTITY_MOOSHROOM_MILK, 1.0f, 1.0f);
        ItemStack stewItem = ItemUsage.exchangeStack(itemStack, player, ModItems.END_MUSHROOM_STEW.getDefaultStack());
        player.setStackInHand(hand, stewItem);
        this.teleportRandomly();
        return ActionResult.success(this.world.isClient);
    }

    private void teleportRandomly() {
        if (this.world.isClient() || !this.isAlive()) return;
        for (int i = 0; i < TELEPORT_TRIES; i++) {
            double x = this.getX() + (this.random.nextDouble() - 0.5) * TELEPORT_RANGE;
            double y = this.getY() + this.random.nextInt(TELEPORT_RANGE) - (float) TELEPORT_RANGE / 2;
            double z = this.getZ() + (this.random.nextDouble() - 0.5) * TELEPORT_RANGE;

            if (!this.teleport(x, y, z, true)) continue;

            world.emitGameEvent(GameEvent.TELEPORT, getPos(), GameEvent.Emitter.of(this));
            if (!this.isSilent()) playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            return;
        }
    }

    @Override
    public CowEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntityTypes.END_COW.create(serverWorld);
    }
}
