package top.offsetmonkey538.offsetend.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import top.offsetmonkey538.offsetend.datagen.language.ModEnglishLanguageProvider;
import top.offsetmonkey538.offsetend.datagen.tag.ModBlockTagProvider;

public class DataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        // Tags
        pack.addProvider(ModBlockTagProvider::new);

        // Language
        pack.addProvider(ModEnglishLanguageProvider::new);
    }
}
