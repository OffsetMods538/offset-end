package top.offsetmonkey538.offsetend.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import top.offsetmonkey538.offsetend.registry.ModBlocks;
import top.offsetmonkey538.offsetend.registry.ModItems;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.END_MUSHROOM, drops(ModItems.END_MUSHROOM));
        addPottedPlantDrops(ModBlocks.POTTED_END_MUSHROOM);
    }
}
