package top.offsetmonkey538.offsetend.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Optional;

import static top.offsetmonkey538.offsetend.block.ModBlocks.END_WATER;
import static top.offsetmonkey538.offsetend.fluid.ModFluids.FLOWING_END_WATER;
import static top.offsetmonkey538.offsetend.fluid.ModFluids.STILL_END_WATER;
import static top.offsetmonkey538.offsetend.item.ModItems.END_WATER_BUCKET;

public abstract class EndWaterFluid extends FlowableFluid {

    @Override
    protected ParticleEffect getParticle() {
        return ParticleTypes.PORTAL;
    }

    @Override
    protected void randomDisplayTick(World world, BlockPos pos, FluidState state, Random random) {
        if (!state.isStill()) return;
        if (random.nextInt(10) == 0) world.addParticle(getParticle(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + random.nextDouble(), (double)pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        else if (random.nextInt(256) == 0) world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.ENTITY_ENDERMITE_HURT, SoundCategory.BLOCKS, random.nextFloat() * 0.25f + 0.50f, random.nextFloat() + 0.5f, false);
    }

    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.ofNullable(SoundEvents.ITEM_BUCKET_FILL);
    }

    @Override
    public Fluid getStill() {
        return STILL_END_WATER;
    }

    @Override
    public Fluid getFlowing() {
        return FLOWING_END_WATER;
    }

    @Override
    public Item getBucketItem() {
        return END_WATER_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return END_WATER.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    protected boolean isInfinite(World world) {
        return true;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    @Override
    protected float getBlastResistance() {
        return 100;
    }

    public static class Flowing extends EndWaterFluid {

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }
    }

    public static class Still extends EndWaterFluid {

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }
    }
}
