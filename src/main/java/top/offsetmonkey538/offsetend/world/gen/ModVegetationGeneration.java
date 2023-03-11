package top.offsetmonkey538.offsetend.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import top.offsetmonkey538.offsetend.world.feature.ModPlacedFeatures;

public class ModVegetationGeneration {

    public static void generate() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.END_MIDLANDS, BiomeKeys.END_HIGHLANDS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.END_MUSHROOM_PLACED_KEY
        );
    }
}
