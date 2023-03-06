package top.offsetmonkey538.offsetend.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import top.offsetmonkey538.offsetend.block.ModBlocks;
import top.offsetmonkey538.offsetend.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.END_MUSHROOM, ModBlocks.POTTED_END_MUSHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.END_MUSHROOM_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.END_WATER_BUCKET,  Models.GENERATED);
    }
}