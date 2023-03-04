package top.offsetmonkey538.offsetend.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.offsetend.block.EndMushroomBlock;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModBlocks {

    public static final EndMushroomBlock END_MUSHROOM        = register("end_mushroom",        new EndMushroomBlock());
    public static final FlowerPotBlock   POTTED_END_MUSHROOM = register("potted_end_mushroom", new FlowerPotBlock(END_MUSHROOM, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));

    private static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    public static void register() {
        // Registers blocks by loading this class.
    }
}
