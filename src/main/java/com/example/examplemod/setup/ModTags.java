package com.example.examplemod.setup;

import com.example.examplemod.ExampleMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static final class Blocks {
        public static final ITag.INamedTag<Block> ORES_SILVER = forge("ores/silver");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_SILVER = forge("storage_blocks/silver");
        public static final ITag.INamedTag<Block> ORES_LEAD = forge("ores/lead");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_LEAD = forge("storage_blocks/lead");
        public static final ITag.INamedTag<Block> ORES_COPPER = forge("ores/copper");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_COPPER = forge("storage_blocks/copper");
        public static final ITag.INamedTag<Block> ORES_TIN = forge("ores/tin");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_TIN = forge("storage_blocks/tin");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_BRONZE = forge("storage_blocks/bronze");
        public static final ITag.INamedTag<Block> METAL_PRESS = mod("machines/metal_press");
        public static final ITag.INamedTag<Block> CARBON_GENERATOR = mod("machines/carbon_generator");
        public static final ITag.INamedTag<Block> SOLAR_GENERATOR = mod("machines/solar_generator");
        public static final ITag.INamedTag<Block> ALLOY_FURNACE = mod("machines/alloy_furnace");
        public static final ITag.INamedTag<Block> CABLE = mod("connectors/cable");
        private static ITag.INamedTag<Block> forge(String path) {
            return BlockTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Block> mod(String path) {
            return BlockTags.bind(new ResourceLocation(ExampleMod.MOD_ID, path).toString());
        }
    }

    public static final class Items {
        public static final Tags.IOptionalNamedTag<Item> PLATES = tag("plates");
        public static final ITag.INamedTag<Item> ORES_SILVER = forge("ores/silver");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_SILVER = forge("storage_blocks/silver");
        public static final ITag.INamedTag<Item> COAL_GENERATOR_FUELS = mod("coal_generator_fuels");
        public static final ITag.INamedTag<Item> INGOTS_SILVER = forge("ingots/silver");
        public static final ITag.INamedTag<Item> NUGGETS_SILVER = forge("nuggets/silver");
        public static final ITag.INamedTag<Item> PLATES_SILVER = mod("plates/silver");
        public static final ITag.INamedTag<Item> ORES_LEAD = forge("ores/lead");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_LEAD = forge("storage_blocks/lead");
        public static final ITag.INamedTag<Item> INGOTS_LEAD = forge("ingots/lead");
        public static final ITag.INamedTag<Item> NUGGETS_LEAD = forge("nuggets/lead");
        public static final ITag.INamedTag<Item> PLATES_LEAD = mod("plates/lead");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_COPPER = forge("storage_blocks/copper");
        public static final ITag.INamedTag<Item> ORES_COPPER = forge("ores/copper");
        public static final ITag.INamedTag<Item> INGOTS_COPPER = forge("ingots/copper");
        public static final ITag.INamedTag<Item> NUGGETS_COPPER = forge("nuggets/copper");
        public static final ITag.INamedTag<Item> PLATES_COPPER = mod("plates/copper");
        public static final ITag.INamedTag<Item> ORES_TIN = forge("ores/tin");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_TIN = forge("storage_blocks/tin");
        public static final ITag.INamedTag<Item> INGOTS_TIN = forge("ingots/tin");
        public static final ITag.INamedTag<Item> NUGGETS_TIN = forge("nuggets/tin");
        public static final ITag.INamedTag<Item> PLATES_TIN = mod("plates/tin");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_BRONZE = forge("storage_blocks/bronze");
        public static final ITag.INamedTag<Item> INGOTS_BRONZE = forge("ingots/bronze");
        public static final ITag.INamedTag<Item> NUGGETS_BRONZE = forge("nuggets/bronze");
        public static final ITag.INamedTag<Item> PLATES_BRONZE = mod("plates/bronze");
        public static final ITag.INamedTag<Item> PLATES_IRON = mod("plates/iron");
        public static final ITag.INamedTag<Item> PLATES_GOLD = mod("plates/gold");
        public static final ITag.INamedTag<Item> WRENCH = mod("tools/wrench");

        private static Tags.IOptionalNamedTag<Item> tag(String name)
        {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.bind(new ResourceLocation(ExampleMod.MOD_ID, path).toString());
        }
    }
}
