package top.offsetmonkey538.offsetend.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import top.offsetmonkey538.offsetend.entity.effect.ModStatusEffects;

import static net.minecraft.item.FoodComponents.MUSHROOM_STEW;

public class ModFoodComponents {

    public static final FoodComponent END_MUSHROOM_STEW = new FoodComponent.Builder()
            .hunger(MUSHROOM_STEW.getHunger())
            .saturationModifier(MUSHROOM_STEW.getSaturationModifier())
            .statusEffect(new StatusEffectInstance(ModStatusEffects.END_AFFINITY, 2400, 0), 1)
            .build();
}
