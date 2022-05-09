package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.setup.ModRecipes;


import com.example.examplemod.util.NameUtils;
import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import java.util.function.Consumer;

public class PressingRecipeBuilder {
    private final Ingredient ingredient;
    private final int ingredientCount;
    private final ItemStack result;
    private final int processTime;

    private PressingRecipeBuilder(Ingredient ingredient, int ingredientCount, IItemProvider result, int resultCount, int processTime) {
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
        this.result = new ItemStack(result, resultCount);
        this.processTime = processTime;
    }

    public static PressingRecipeBuilder builder(Ingredient ingredient, int ingredientCount, IItemProvider result, int resultCount, int processTime) {
        return new PressingRecipeBuilder(ingredient, ingredientCount, result, resultCount, processTime);
    }

    public static PressingRecipeBuilder builder(ITag<Item> ingredient, int ingredientCount, IItemProvider result, int resultCount, int processTime) {
        return builder(Ingredient.of(ingredient), ingredientCount, result, resultCount, processTime);
    }

    public static PressingRecipeBuilder builder(IItemProvider ingredient, int ingredientCount, IItemProvider result, int resultCount, int processTime) {
        return builder(Ingredient.of(ingredient), ingredientCount, result, resultCount, processTime);
    }

    public void build(Consumer<IFinishedRecipe> consumer) {
        ResourceLocation resultId = NameUtils.fromItem(result);
        ResourceLocation id = new ResourceLocation(
                "minecraft".equals(resultId.getNamespace()) ? ExampleMod.MOD_ID : resultId.getNamespace(),
                "pressing/" + resultId.getPath());
        build(consumer, id);
    }

    public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new Result(id, this));
    }

    public class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final PressingRecipeBuilder builder;

        public Result(ResourceLocation id, PressingRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("process_time", builder.processTime);

            JsonObject ingredient = new JsonObject();
            ingredient.add("value", builder.ingredient.toJson());
            ingredient.addProperty("count", builder.ingredientCount);
            json.add("ingredient", ingredient);

            JsonObject result = new JsonObject();
            result.addProperty("item", NameUtils.fromItem(builder.result).toString());
            if (builder.result.getCount() > 1) {
                result.addProperty("count", builder.result.getCount());
            }
            json.add("result", result);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public IRecipeSerializer<?> getType() {
            return ModRecipes.PRESSING.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
