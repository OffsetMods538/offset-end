package top.offsetmonkey538.offsetend.world.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> END_WATER_LAKE_PLACED_KEY = register("end_water_lake_placed");


    public static void bootstrap(Registerable<PlacedFeature> context) {
        final var configuredFeatureLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(
                context,
                END_WATER_LAKE_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.END_WATER_LAKE_KEY),
                RarityFilterPlacementModifier.of(20),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
    }

    private static RegistryKey<PlacedFeature> register(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id(name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers) {
        context.register(key, new PlacedFeature(configuredFeature, List.of(modifiers)));
    }
}
