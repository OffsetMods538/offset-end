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

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModItems {

    public static final BlockItem END_MUSHROOM = register("end_mushroom", ModBlocks.END_MUSHROOM);

    private static BlockItem register(String name, Block block) {
        return register(name, new BlockItem(block, new FabricItemSettings()));
    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    private static void addItemsToGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.RED_MUSHROOM, END_MUSHROOM);
        });
    }

    public static void register() {
        // Registers items by loading this class.
        addItemsToGroups();
    }
}
