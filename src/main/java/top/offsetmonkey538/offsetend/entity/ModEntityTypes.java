package top.offsetmonkey538.offsetend.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModEntityTypes {

    public static final EntityType<EndCowEntity> END_COW = register(
            "end_cow",
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EndCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, id(name), entityType);
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(END_COW, EndCowEntity.createCowAttributes());
    }

    public static void register() {
        // Registers entity types by loading this class.
        registerAttributes();
    }
}
