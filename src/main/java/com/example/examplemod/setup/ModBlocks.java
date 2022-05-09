package com.example.examplemod.setup;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.alloyfurnace.AlloyFurnaceBlock;
import com.example.examplemod.block.cable.CableBlock;
import com.example.examplemod.block.generator.CarbonGenerator.CarbonGeneratorBlock;
import com.example.examplemod.block.generator.SolarGenerator.SolarGeneratorBlock;
import com.example.examplemod.block.metalpress.MetalPressBlock;
import com.example.examplemod.util.BlockRegistryObject;
import com.mojang.datafixers.TypeRewriteRule;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<Block> SILVER_ORE = registerReg("silver_ore", () ->
            new Block(AbstractBlock.Properties.of(Material.STONE)
                    .strength(3, 10)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILVER_BLOCK = registerReg("silver_block", () ->
            new Block(AbstractBlock.Properties.of(Material.METAL)
                    .strength(3, 10)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> LEAD_ORE = registerReg("lead_ore", () ->
            new Block(AbstractBlock.Properties.of(Material.STONE)
                    .strength(3, 10)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));
    public static final RegistryObject<Block> LEAD_BLOCK = registerReg("lead_block", () ->
            new Block(AbstractBlock.Properties.of(Material.METAL)
                    .strength(3, 10)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> BRONZE_BLOCK = registerReg("bronze_block", () ->
            new Block(AbstractBlock.Properties.of(Material.METAL)
                    .strength(3, 10)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> COPPER_ORE = registerReg("copper_ore", ()->
            new Block(AbstractBlock.Properties.of(Material.STONE)
            .strength(3,10)
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> COPPER_BLOCK = registerReg("copper_block", () ->
            new Block(AbstractBlock.Properties.of(Material.METAL)
                    .strength(3, 10)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> TIN_ORE = registerReg("tin_ore", ()->
            new Block(AbstractBlock.Properties.of(Material.STONE)
                    .strength(3,10)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));
    public static final RegistryObject<Block> TIN_BLOCK = registerReg("tin_block", () ->
            new Block(AbstractBlock.Properties.of(Material.METAL)
                    .strength(3, 10)
                    .sound(SoundType.METAL)));

    public static final BlockRegistryObject<MetalPressBlock> METAL_PRESS = register("metal_press", MetalPressBlock::new);
    public static final BlockRegistryObject<CarbonGeneratorBlock> CARBON_GENERATOR = register("carbon_generator", CarbonGeneratorBlock::new);
    public static final BlockRegistryObject<SolarGeneratorBlock> SOLAR_GENERATOR = register("solar_generator", SolarGeneratorBlock::new);
    public static final BlockRegistryObject<AlloyFurnaceBlock> ALLOY_FURNACE = register("alloy_furnace", AlloyFurnaceBlock::new);
    public static final BlockRegistryObject<CableBlock> CABLE = register("cable", ()->
            new CableBlock(Block.Properties.of(Material.ICE).strength(1,5).noOcclusion().sound(SoundType.GLASS)));

    static void register() {}
    private static <T extends Block> BlockRegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return new BlockRegistryObject<>(Registration.BLOCKS.register(name, block));
    }

    private static <T extends Block> BlockRegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, ModBlocks::defaultItem);
    }

    private static <T extends Block> BlockRegistryObject<T> register(String name, Supplier<T> block, Function<BlockRegistryObject<T>, Supplier<? extends BlockItem>> item) {
        BlockRegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, item.apply(ret));
        return ret;
    }
    private static <T extends Block> RegistryObject<T> registerNoItemReg(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerReg(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItemReg(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
        return ret;
    }
    private static <T extends Block> Supplier<BlockItem> defaultItem(BlockRegistryObject<T> block) {
        return () -> new BlockItem(block.get(), new Item.Properties().tab(ExampleMod.ITEM_GROUP));
    }
}
