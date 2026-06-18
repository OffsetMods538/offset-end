package top.offsetmonkey538.offsetend.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.StewItem;

public class EndMushroomStewItem extends StewItem {
    public EndMushroomStewItem() {
        super(new FabricItemSettings().maxCount(1).food(ModFoodComponents.END_MUSHROOM_STEW));
    }
}
