package top.offsetmonkey538.offsetend;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import top.offsetmonkey538.offsetend.block.ModBlocks;
import top.offsetmonkey538.offsetend.fluid.ModFluids;

public class OffsetEndClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.END_MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_END_MUSHROOM, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_END_WATER, ModFluids.FLOWING_END_WATER);


		FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_END_WATER, ModFluids.FLOWING_END_WATER, new SimpleFluidRenderHandler(
				new Identifier("minecraft:block/water_still"),
				new Identifier("minecraft:block/water_flow"),
				0x161616
		));
	}
}
