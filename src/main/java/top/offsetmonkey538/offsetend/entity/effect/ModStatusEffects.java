package top.offsetmonkey538.offsetend.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModStatusEffects {

    public static final StatusEffect END_AFFINITY = register("end_affinity", new EndAffinityStatusEffect());

    private static StatusEffect register(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, id(name), effect);
    }

    public static void register() {
        // Registers status effects by loading this class.
    }
}
