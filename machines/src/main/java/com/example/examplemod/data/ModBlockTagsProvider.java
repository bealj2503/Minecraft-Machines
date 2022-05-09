package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.setup.ModBlocks;
import com.example.examplemod.setup.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, ExampleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Blocks.ORES_SILVER).add(ModBlocks.SILVER_ORE.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_SILVER).add(ModBlocks.SILVER_BLOCK.get());
        tag(ModTags.Blocks.ORES_LEAD).add(ModBlocks.LEAD_ORE.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_LEAD).add(ModBlocks.LEAD_BLOCK.get());
        tag(ModTags.Blocks.ORES_COPPER).add(ModBlocks.COPPER_ORE.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_COPPER).add(ModBlocks.COPPER_BLOCK.get());
        tag(ModTags.Blocks.ORES_TIN).add(ModBlocks.TIN_ORE.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_TIN).add(ModBlocks.TIN_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_BRONZE).add(ModBlocks.BRONZE_BLOCK.get());
        tag(Tags.Blocks.ORES)
                .addTag(ModTags.Blocks.ORES_SILVER)
                .addTag(ModTags.Blocks.ORES_LEAD)
                .addTag(ModTags.Blocks.ORES_COPPER)
                .addTag(ModTags.Blocks.ORES_TIN);
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS_SILVER)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS_LEAD)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS_COPPER)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS_TIN)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS_BRONZE);
        tag(ModTags.Blocks.METAL_PRESS).add(ModBlocks.METAL_PRESS.get());
        tag(ModTags.Blocks.CARBON_GENERATOR).add(ModBlocks.CARBON_GENERATOR.get());
        tag(ModTags.Blocks.SOLAR_GENERATOR).add(ModBlocks.SOLAR_GENERATOR.get());
        tag(ModTags.Blocks.CABLE).add(ModBlocks.CABLE.get());
        tag(ModTags.Blocks.ALLOY_FURNACE).add(ModBlocks.ALLOY_FURNACE.get());
    }
}
