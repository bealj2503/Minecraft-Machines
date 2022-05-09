package com.example.examplemod.data.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.setup.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SixWayBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper){
        super(gen,ExampleMod.MOD_ID,existingFileHelper);
    }
    @Override
    protected void registerStatesAndModels(){
        simpleBlock(ModBlocks.SILVER_BLOCK.get());
        simpleBlock(ModBlocks.SILVER_ORE.get());
        simpleBlock(ModBlocks.LEAD_BLOCK.get());
        simpleBlock(ModBlocks.LEAD_ORE.get());
        simpleBlock(ModBlocks.COPPER_BLOCK.get());
        simpleBlock(ModBlocks.COPPER_ORE.get());
        simpleBlock(ModBlocks.TIN_BLOCK.get());
        simpleBlock(ModBlocks.TIN_ORE.get());
        simpleBlock(ModBlocks.BRONZE_BLOCK.get());

        //orientable(ModBlocks.METAL_PRESS.get(),"block/metal_press_side","block/metal_press_front", "block/metal_press_top","block/metal_press_bottom");
        //orientable(ModBlocks.CARBON_GENERATOR.get(), "block/carbon_generator_side", "block/carbon_generator_front", "block/carbon_generator_top","block/carbon_generator_bottom");
        //orientable(ModBlocks.SOLAR_GENERATOR.get(), "block/solar_generator_side", "block/solar_generator_side", "block/solar_generator_top", "block/solar_generator_bottom");
    }
    public void orientable(Block block, String side, String front, String top, String bottom){
        horizontalBlock(block, models().orientableWithBottom(block.getRegistryName().getPath(), modLoc(side), modLoc(front), modLoc(bottom), modLoc(top)));
    }



}
