package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.crafting.recipe.AlloyRecipe;
import com.example.examplemod.setup.ModRecipes;
import com.example.examplemod.util.NameUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AlloyRecipeBuilder {
    private final Map<Ingredient, Integer> ingredients = new LinkedHashMap<>();
    private final ItemStack result;
    private final int processTime;
    private AlloyRecipeBuilder(IItemProvider result, int count, int processTime){
        this.result = new ItemStack(result, count);
        this.processTime = processTime;
    }
    public static AlloyRecipeBuilder builder(IItemProvider result, int count, int processTime) {
        return new AlloyRecipeBuilder(result, count, processTime);
    }


    public AlloyRecipeBuilder ingredient(Ingredient ingredient, int count) {
        ingredients.put(ingredient, count);
        return this;
    }

    public AlloyRecipeBuilder ingredient(IItemProvider item, int count) {
        return ingredient(Ingredient.of(item), count);
    }

    public AlloyRecipeBuilder ingredient(ITag<Item> tag, int count) {
        return ingredient(Ingredient.of(tag), count);
    }



    public void build(Consumer<IFinishedRecipe> consumer) {
        ResourceLocation resultId = NameUtils.fromItem(result);
        ResourceLocation id = new ResourceLocation(
                "minecraft".equals(resultId.getNamespace()) ? ExampleMod.MOD_ID : resultId.getNamespace(),
                "alloy/" + resultId.getPath());
        build(consumer, id);
    }

    public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new Result(id, this));
    }

    public class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final AlloyRecipeBuilder builder;

        public Result(ResourceLocation id, AlloyRecipeBuilder builder) {
            this.id = id;
            this.builder = builder;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("process_time", builder.processTime);

            JsonArray ingredients = new JsonArray();
            builder.ingredients.forEach((ingredient, count) ->
                    ingredients.add(serializeIngredient(ingredient, count)));
            json.add("ingredients", ingredients);

            JsonObject result = new JsonObject();
            result.addProperty("item", NameUtils.fromItem(builder.result).toString());
            if (builder.result.getCount() > 1) {
                result.addProperty("count", builder.result.getCount());
            }
            json.add("result", result);
        }

        private JsonElement serializeIngredient(Ingredient ingredient, int count) {
            JsonObject json = new JsonObject();
            json.add("value", ingredient.toJson());
            json.addProperty("count", count);
            return json;
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public IRecipeSerializer<?> getType() {
            return ModRecipes.ALLOY.get();
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
