package top.offsetmonkey538.offsetend.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModFluids {

    public static final EndWaterFluid STILL_END_WATER   = register("end_water",         new EndWaterFluid.Still());
    public static final EndWaterFluid FLOWING_END_WATER = register("flowing_end_water", new EndWaterFluid.Flowing());

    private static <T extends Fluid> T register(String name, T fluid) {
        return Registry.register(Registries.FLUID, id(name), fluid);
    }

    public static void register() {
        // Registers fluids by loading this class.
    }
}
