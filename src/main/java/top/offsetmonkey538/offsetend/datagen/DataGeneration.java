package top.offsetmonkey538.offsetend.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import top.offsetmonkey538.offsetend.datagen.language.ModEnglishLanguageProvider;
import top.offsetmonkey538.offsetend.datagen.tag.ModBlockTagProvider;
import top.offsetmonkey538.offsetend.world.feature.ModConfiguredFeatures;
import top.offsetmonkey538.offsetend.world.feature.ModPlacedFeatures;

public class DataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModWorldgenProvider::new);

        // Tags
        pack.addProvider(ModBlockTagProvider::new);

        // Language
        pack.addProvider(ModEnglishLanguageProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE,     ModPlacedFeatures::bootstrap);
    }
}
