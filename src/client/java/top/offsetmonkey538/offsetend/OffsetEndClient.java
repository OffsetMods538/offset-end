package top.offsetmonkey538.offsetend;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import top.offsetmonkey538.offsetend.registry.ModBlocks;

public class OffsetEndClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.END_MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_END_MUSHROOM, RenderLayer.getCutout());
	}
}
