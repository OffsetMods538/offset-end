package top.offsetmonkey538.offsetend.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class EndMushroomBlock extends PlantBlock {

    public EndMushroomBlock() {
        super(FabricBlockSettings.of(Material.PLANT, MapColor.BLACK).noCollision().sounds(BlockSoundGroup.GRASS).breakInstantly().nonOpaque());
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.END_STONE);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);
    }
}
