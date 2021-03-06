package com.example.examplemod.crafting.recipe;

import com.example.examplemod.setup.ModRecipes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;


public class PressingRecipe implements IRecipe<IInventory> {
    private final ResourceLocation recipeId;
    private int processTime;
    private Ingredient ingredient;
    private int ingredientCount;
    private ItemStack result;


    public PressingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
    }

    public int getProcessTime() {
        return processTime;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }
    @Override
    public boolean matches(IInventory inv, World worldIn) {
        ItemStack stack = inv.getItem(0);
        return ingredient.test(stack) && stack.getCount() >= ingredientCount;
    }

    @Override
    public ItemStack assemble(IInventory inv) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.PRESSING.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.Types.PRESSING;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PressingRecipe> {
        @Override
        public PressingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            PressingRecipe recipe = new PressingRecipe(recipeId);
            recipe.processTime = JSONUtils.getAsInt(json, "process_time", 400);
            JsonElement ingredientJson = json.get("ingredient");
            if (ingredientJson.isJsonObject() && ingredientJson.getAsJsonObject().has("value")) {
                JsonObject obj = ingredientJson.getAsJsonObject();
                recipe.ingredient = Ingredient.fromJson(obj.get("value"));
                recipe.ingredientCount = JSONUtils.getAsInt(obj, "count", 1);
            } else {
                recipe.ingredient = Ingredient.fromJson(ingredientJson);
                recipe.ingredientCount = JSONUtils.getAsInt(ingredientJson.getAsJsonObject(), "count", 1);
            }
            recipe.result = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            return recipe;
        }

        @Override
        public PressingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            PressingRecipe recipe = new PressingRecipe(recipeId);
            recipe.processTime = buffer.readVarInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);
            recipe.ingredientCount = buffer.readByte();
            recipe.result = buffer.readItem();
            return recipe;
        }

        @Override
        public void toNetwork(PacketBuffer buffer, PressingRecipe recipe) {
            buffer.writeVarInt(recipe.processTime);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeByte(recipe.ingredientCount);
            buffer.writeItem(recipe.result);
        }
    }
}
