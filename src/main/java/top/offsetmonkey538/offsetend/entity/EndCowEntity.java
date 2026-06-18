package top.offsetmonkey538.offsetend.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import top.offsetmonkey538.offsetend.item.ModItems;

public class EndCowEntity extends CowEntity implements Shearable {

    private static final int TELEPORT_RANGE = 16;
    private static final int TELEPORT_TRIES = 20;

    public EndCowEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BOWL) && !this.isBaby()) {
            player.playSound(SoundEvents.ENTITY_MOOSHROOM_MILK, 1.0f, 1.0f);
            ItemStack stewItem = ItemUsage.exchangeStack(itemStack, player, ModItems.END_MUSHROOM_STEW.getDefaultStack());
            player.setStackInHand(hand, stewItem);
            this.teleportRandomly();
            return ActionResult.success(this.world.isClient);
        }
        if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            this.sheared(SoundCategory.PLAYERS);
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.world.isClient) {
                itemStack.damage(1, player, player1 -> player.sendToolBreakStatus(hand));
            }
            return ActionResult.success(this.world.isClient);
        }
        return super.interactMob(player, hand);
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

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        if (!(world instanceof ServerWorld serverWorld)) return;
        CowEntity cow = EntityType.COW.create(serverWorld);
        if (cow == null) return;


        serverWorld.playSoundFromEntity(null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1, 1);
        serverWorld.spawnParticles(ParticleTypes.EXPLOSION, getX(), getY(), getZ(), 1, 0, 0, 0, 0);


        this.discard();


        cow.refreshPositionAndAngles(getX(), getY(), getZ(), getYaw(), getPitch());
        cow.setHealth(this.getHealth());
        cow.bodyYaw = this.bodyYaw;
        if (this.hasCustomName()) {
            cow.setCustomName(this.getCustomName());
            cow.setCustomNameVisible(this.isCustomNameVisible());
        }
        if (this.isPersistent()) cow.setPersistent();
        cow.setInvulnerable(this.isInvulnerable());

        serverWorld.spawnEntity(cow);


        for (int i = 0; i < 5; i++) serverWorld.spawnEntity(new ItemEntity(serverWorld, getX(), getBodyY(1), getZ(), ModItems.END_MUSHROOM.getDefaultStack()));
    }

    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isBaby();
    }
}
