package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.setup.ModBlocks;
import com.example.examplemod.setup.ModItems;
import com.example.examplemod.setup.ModTags;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

        ingotRecipe(consumer,ModBlocks.SILVER_BLOCK.get(), ModItems.SILVER_INGOT.get());
        ingotRecipe(consumer,ModItems.SILVER_INGOT.get(), ModItems.SILVER_NUGGET.get());
        blockRecipe(consumer,ModBlocks.SILVER_BLOCK.get(), ModItems.SILVER_INGOT.get());
        blockRecipe(consumer,ModItems.SILVER_INGOT.get(),ModItems.SILVER_NUGGET.get(), "silver_ingot_nuggets");
        smeltRecipe(consumer,ModBlocks.SILVER_ORE.get(),ModItems.SILVER_INGOT.get(),"silver_ingot_smelting", "silver_ingot_blasting");
        ingotRecipe(consumer,ModBlocks.LEAD_BLOCK.get(), ModItems.LEAD_INGOT.get());
        ingotRecipe(consumer,ModItems.LEAD_INGOT.get(), ModItems.LEAD_NUGGET.get());
        blockRecipe(consumer,ModBlocks.LEAD_BLOCK.get(), ModItems.LEAD_INGOT.get());
        blockRecipe(consumer,ModItems.LEAD_INGOT.get(),ModItems.LEAD_NUGGET.get(), "lead_ingot_nuggets");
        smeltRecipe(consumer,ModBlocks.LEAD_ORE.get(),ModItems.LEAD_INGOT.get(),"lead_ingot_smelting", "lead_ingot_blasting");
        ingotRecipe(consumer,ModBlocks.COPPER_BLOCK.get(), ModItems.COPPER_INGOT.get());
        ingotRecipe(consumer,ModItems.COPPER_INGOT.get(), ModItems.COPPER_NUGGET.get());
        blockRecipe(consumer,ModBlocks.COPPER_BLOCK.get(), ModItems.COPPER_INGOT.get());
        blockRecipe(consumer,ModItems.COPPER_INGOT.get(),ModItems.COPPER_NUGGET.get(), "copper_ingot_nuggets");
        smeltRecipe(consumer,ModBlocks.COPPER_ORE.get(),ModItems.COPPER_INGOT.get(),"copper_ingot_smelting", "copper_ingot_blasting");
        ingotRecipe(consumer,ModBlocks.TIN_BLOCK.get(), ModItems.TIN_INGOT.get());
        ingotRecipe(consumer,ModItems.TIN_INGOT.get(), ModItems.TIN_NUGGET.get());
        blockRecipe(consumer,ModBlocks.TIN_BLOCK.get(), ModItems.TIN_INGOT.get());
        blockRecipe(consumer,ModItems.TIN_INGOT.get(),ModItems.TIN_NUGGET.get(), "tin_ingot_nuggets");
        smeltRecipe(consumer,ModBlocks.TIN_ORE.get(),ModItems.TIN_INGOT.get(),"tin_ingot_smelting", "tin_ingot_blasting");
        ingotRecipe(consumer,ModBlocks.BRONZE_BLOCK.get(), ModItems.BRONZE_INGOT.get());
        ingotRecipe(consumer,ModItems.BRONZE_INGOT.get(), ModItems.BRONZE_NUGGET.get());
        blockRecipe(consumer,ModBlocks.BRONZE_BLOCK.get(), ModItems.BRONZE_INGOT.get());
        blockRecipe(consumer,ModItems.BRONZE_INGOT.get(),ModItems.BRONZE_NUGGET.get(), "bronze_ingot_nuggets");

        ShapedRecipeBuilder.shaped(ModItems.WRENCH)
                .define('/', ModTags.Items.INGOTS_SILVER)
                .define('#', ModTags.Items.INGOTS_BRONZE)
                .pattern("# #")
                .pattern(" / ")
                .pattern(" / ")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_BRONZE))
                .save(consumer);

        PressingRecipeBuilder.builder(ModTags.Items.INGOTS_SILVER,1, ModItems.SILVER_PLATE.get(),1,200)
                .build(consumer);
        PressingRecipeBuilder.builder(ModTags.Items.INGOTS_LEAD, 1, ModItems.LEAD_PLATE.get(),1,200)
                .build(consumer);
        PressingRecipeBuilder.builder(ModTags.Items.INGOTS_COPPER, 1, ModItems.COPPER_PLATE.get(),1,200)
                .build(consumer);
        PressingRecipeBuilder.builder(ModTags.Items.INGOTS_TIN, 1, ModItems.TIN_PLATE.get(),1,200)
                .build(consumer);
        PressingRecipeBuilder.builder(ModTags.Items.INGOTS_BRONZE, 1, ModItems.BRONZE_PLATE.get(),1,200)
                .build(consumer);
        PressingRecipeBuilder.builder(Tags.Items.INGOTS_GOLD, 1, ModItems.GOLD_PLATE.get(),1,200)
                .build(consumer);
        PressingRecipeBuilder.builder(Tags.Items.INGOTS_IRON, 1, ModItems.IRON_PLATE.get(),1,200)
                .build(consumer);

        AlloyRecipeBuilder.builder(ModItems.BRONZE_INGOT.get(),4,300)
                .ingredient(ModTags.Items.INGOTS_COPPER, 3)
                .ingredient(ModTags.Items.INGOTS_TIN,1)
                .build(consumer);

    }
    private void ingotRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider modBlock, IItemProvider modItem){
        ShapelessRecipeBuilder.shapeless(modItem, 9)
                .requires(modBlock)
                .unlockedBy("has_item", has(modItem))
                .save(consumer);
    }
    private void ingotRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider modBlock, IItemProvider modItem, String resourceLocation){
        ShapelessRecipeBuilder.shapeless(modItem, 9)
                .requires(modBlock)
                .unlockedBy("has_item", has(modItem))
                .save(consumer, new ResourceLocation(resourceLocation));
    }
    private void blockRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider modBlock, IItemProvider modItem){
        ShapedRecipeBuilder.shaped(modBlock)
                .define('#', modItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(modItem))
                .save(consumer);
    }
    private void blockRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider modBlock, IItemProvider modItem, String resourceLocation){
        ShapedRecipeBuilder.shaped(modBlock)
                .define('#', modItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(modItem))
                .save(consumer, new ResourceLocation(resourceLocation));
    }
    private void smeltRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider modBlock, IItemProvider modItem, String smeltId, String blastId){
        CookingRecipeBuilder.smelting(Ingredient.of(modBlock), modItem, 0.7f, 200)
                .unlockedBy("has_item", has(modBlock))
                .save(consumer, modId(smeltId));
        CookingRecipeBuilder.blasting(Ingredient.of(modBlock), modItem, 0.7f, 100)
                .unlockedBy("has_item", has(modBlock))
                .save(consumer, modId(blastId));
    }
    private static ResourceLocation modId(String path) {
        return new ResourceLocation(ExampleMod.MOD_ID, path);
    }
}
