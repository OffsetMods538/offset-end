package top.offsetmonkey538.offsetend;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.offsetend.registry.ModBlocks;

public class OffsetEnd implements ModInitializer {
	public static final String MOD_ID = "offset-end";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.register();
	}

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
}
