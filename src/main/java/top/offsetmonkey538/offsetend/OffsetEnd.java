package top.offsetmonkey538.offsetend;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.offsetend.block.ModBlocks;
import top.offsetmonkey538.offsetend.entity.effect.ModStatusEffects;
import top.offsetmonkey538.offsetend.fluid.ModFluids;
import top.offsetmonkey538.offsetend.item.ModItems;
import top.offsetmonkey538.offsetend.world.gen.ModWorldGeneration;

public class OffsetEnd implements ModInitializer {
	public static final String MOD_ID = "offset-end";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.register();
		ModItems.register();
		ModStatusEffects.register();
		ModFluids.register();

		ModWorldGeneration.generate();

		LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (!source.isBuiltin() || !LootTables.END_CITY_TREASURE_CHEST.equals(id)) return;
			LootPool.Builder poolBuilder = LootPool.builder()
					.with(ItemEntry.builder(ModItems.END_MUSHROOM_STEW))
					.rolls(ConstantLootNumberProvider.create(1f));

			tableBuilder.pool(poolBuilder);
		}));
	}

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
}
