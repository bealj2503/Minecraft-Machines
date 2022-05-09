package com.example.examplemod.crafting.recipe;

import com.example.examplemod.setup.ModRecipes;
import com.example.examplemod.util.InventoryUtils;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ibm.icu.impl.locale.XCldrStub;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.LinkedHashMap;
import java.util.Map;

public class AlloyRecipe implements IRecipe<IInventory> {
    private final ResourceLocation recipeId;
    private int processTime;
    private final Map<Ingredient, Integer> ingredients = new LinkedHashMap<>();

    private ItemStack result;


    public AlloyRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
    }

    public int getProcessTime() {
        return processTime;
    }
    public void consumeIngredients(IInventory inv){
        ingredients.forEach(((ingredient,count) -> InventoryUtils.consumeItems(inv,ingredient,count)));
    }
    public Map<Ingredient, Integer> getIngredientMap(){
        return ImmutableMap.copyOf(ingredients);
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        for(Ingredient ingredient : ingredients.keySet()){
            int required = ingredients.get(ingredient);
            int found = InventoryUtils.getTotalCount(inv,ingredient);
            if(found < required)
                return false;

            for(int i = 0; i < 3; i++){
                ItemStack stack = inv.getItem(i);
                if(!stack.isEmpty()){
                    boolean foundMatch = false;
                    for(Ingredient ingredient2 : ingredients.keySet()){
                        if(ingredient2.test(stack)){
                            foundMatch = true;
                            break;
                        }
                    }
                    if(!foundMatch){
                        return false;
                    }
                }
            }
        }
        return true;
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
        return ModRecipes.ALLOY.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.Types.ALLOY;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AlloyRecipe> {
        @Override
        public AlloyRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            AlloyRecipe recipe = new AlloyRecipe(recipeId);
            recipe.processTime = JSONUtils.getAsInt(json, "process_time", 400);
            JSONUtils.getAsJsonArray(json,"ingredients").forEach(element -> {
                JsonObject obj = element.getAsJsonObject();
                Ingredient ingredient = Ingredient.fromJson(obj.get("value"));
                int count = JSONUtils.getAsInt(obj, "count", 1);
                recipe.ingredients.put(ingredient,count);
            });
            recipe.result = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            return recipe;

        }

        @Override
        public AlloyRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            AlloyRecipe recipe = new AlloyRecipe(recipeId);
            recipe.processTime = buffer.readVarInt();

            int ingredientCount = buffer.readByte();
            for(int i = 0; i< ingredientCount; i++){
                Ingredient ingredient = Ingredient.fromNetwork(buffer);
                int count = buffer.readByte();
                recipe.ingredients.put(ingredient,count);
            }
            recipe.result = buffer.readItem();

            return recipe;
        }

        @Override
        public void toNetwork(PacketBuffer buffer, AlloyRecipe recipe) {
            buffer.writeVarInt(recipe.processTime);

            buffer.writeByte(recipe.ingredients.size());
            recipe.ingredients.forEach((ingredient, count) ->{
                ingredient.toNetwork(buffer);
                buffer.writeByte(count);
            });
            buffer.writeItem(recipe.result);
        }
    }
}

