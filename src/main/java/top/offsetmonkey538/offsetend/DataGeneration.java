package top.offsetmonkey538.offsetend;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import top.offsetmonkey538.offsetend.registry.ModBlocks;
import top.offsetmonkey538.offsetend.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class DataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModEnglishLanguageProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
    }

    private static class ModModelProvider extends FabricModelProvider {
        public ModModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.END_MUSHROOM, ModBlocks.POTTED_END_MUSHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        }
    }

    private static class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
        public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(ModBlocks.POTTED_END_MUSHROOM);
        }
    }

    private static class ModEnglishLanguageProvider extends FabricLanguageProvider {

        protected ModEnglishLanguageProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add(ModBlocks.END_MUSHROOM, "End Mushroom");
        }

    }

    private static class ModBlockLootTableProvider extends FabricBlockLootTableProvider {

        protected ModBlockLootTableProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
            addDrop(ModBlocks.END_MUSHROOM, drops(ModItems.END_MUSHROOM));
            addPottedPlantDrops(ModBlocks.POTTED_END_MUSHROOM);
        }
    }
}
