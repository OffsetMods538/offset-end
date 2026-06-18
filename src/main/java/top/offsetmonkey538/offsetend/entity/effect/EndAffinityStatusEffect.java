package top.offsetmonkey538.offsetend.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class EndAffinityStatusEffect extends StatusEffect {
    public EndAffinityStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 14711290);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }
}
