package top.offsetmonkey538.offsetend.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

import static net.minecraft.item.Items.BOWL;
import static net.minecraft.item.Items.ENDER_EYE;
import static top.offsetmonkey538.offsetend.registry.ModItems.END_MUSHROOM;
import static top.offsetmonkey538.offsetend.registry.ModItems.END_MUSHROOM_STEW;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder
                .create(RecipeCategory.FOOD, END_MUSHROOM_STEW)
                .input(END_MUSHROOM, 2)
                .input(BOWL, 1)
                .input(ENDER_EYE, 1)
                .criterion("has_ender_mushroom", conditionsFromItem(END_MUSHROOM_STEW))
                .offerTo(exporter);
    }
}
