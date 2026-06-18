package top.offsetmonkey538.offsetend.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public final class ModFluidTags {
    public static final TagKey<Fluid> END_WATER = TagKey.of(RegistryKeys.FLUID, id("end_water"));
}
