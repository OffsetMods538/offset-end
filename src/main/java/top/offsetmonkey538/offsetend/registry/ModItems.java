package top.offsetmonkey538.offsetend.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.offsetend.item.EndMushroomStewItem;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModItems {

    public static final BlockItem           END_MUSHROOM      = register("end_mushroom",      ModBlocks.END_MUSHROOM);
    public static final EndMushroomStewItem END_MUSHROOM_STEW = register("end_mushroom_stew", new EndMushroomStewItem());

    private static BlockItem register(String name, Block block) {
        return register(name, new BlockItem(block, new FabricItemSettings()));
    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void addItemsToGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.RED_MUSHROOM, END_MUSHROOM);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addBefore(Items.MUSHROOM_STEW, ModItems.END_MUSHROOM_STEW);
        });
    }

    public static void register() {
        // Registers items by loading this class.
        addItemsToGroups();
    }
}
