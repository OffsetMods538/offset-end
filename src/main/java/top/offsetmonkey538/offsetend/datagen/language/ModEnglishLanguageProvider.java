package top.offsetmonkey538.offsetend.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import top.offsetmonkey538.offsetend.registry.ModBlocks;
import top.offsetmonkey538.offsetend.registry.ModStatusEffects;

public class ModEnglishLanguageProvider extends FabricLanguageProvider {
    public ModEnglishLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModBlocks.END_MUSHROOM, "End Mushroom");
        translationBuilder.add(ModStatusEffects.END_AFFINITY, "End Affinity");
    }
}
