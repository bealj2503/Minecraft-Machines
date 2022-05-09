package com.example.examplemod.setup;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.alloyfurnace.AlloyFurnaceTileEntity;
import com.example.examplemod.block.cable.CableTileEntity;
import com.example.examplemod.block.generator.CarbonGenerator.CarbonGeneratorTileEntity;
import com.example.examplemod.block.generator.SolarGenerator.SolarGeneratorTileEntity;
import com.example.examplemod.block.metalpress.MetalPressTileEntity;
import com.example.examplemod.util.IBlockProvider;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.example.examplemod.setup.ModBlocks.CARBON_GENERATOR;

public class ModTileEntityTypes {
    public static TileEntityType<MetalPressTileEntity> METAL_PRESS;
    public static TileEntityType<CarbonGeneratorTileEntity> CARBON_GENERATOR;
    public static TileEntityType<SolarGeneratorTileEntity> SOLAR_GENERATOR;
    public static TileEntityType<CableTileEntity> CABLE;
    public static TileEntityType<AlloyFurnaceTileEntity> ALLOY_FURNACE;
    public static void registerAll(RegistryEvent.Register<TileEntityType<?>> event) {
        METAL_PRESS = register("metal_press", MetalPressTileEntity::new, ModBlocks.METAL_PRESS);
        CARBON_GENERATOR = register("carbon_generator", CarbonGeneratorTileEntity::new, ModBlocks.CARBON_GENERATOR);
        SOLAR_GENERATOR = register("solar_generator", SolarGeneratorTileEntity::new, ModBlocks.SOLAR_GENERATOR);
        CABLE = register("cable", CableTileEntity::new, ModBlocks.CABLE);
        ALLOY_FURNACE = register("alloy_furnace", AlloyFurnaceTileEntity::new, ModBlocks.ALLOY_FURNACE);
    }
    static void register(){}

    private static <T extends TileEntity> TileEntityType<T> register(String name, Supplier<T> tileFactory, IBlockProvider block) {
        return register(name, tileFactory, block.asBlock());
    }

    private static <T extends TileEntity> TileEntityType<T> register(String name, Supplier<T> tileFactory, Block... blocks) {
        TileEntityType<T> type = TileEntityType.Builder.of(tileFactory, blocks).build(null);
        return register(name, type);
    }

    private static <T extends TileEntity> TileEntityType<T> register(String name, TileEntityType<T> type) {
        if (type.getRegistryName() == null) {
            type.setRegistryName(ExampleMod.getId(name));
        }
        ForgeRegistries.TILE_ENTITIES.register(type);
        return type;
    }


}
