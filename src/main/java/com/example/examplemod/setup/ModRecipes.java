package com.example.examplemod.setup;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.crafting.recipe.AlloyRecipe;
import com.example.examplemod.crafting.recipe.PressingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public final class ModRecipes {
    public static final class Types {
        public static final IRecipeType<PressingRecipe> PRESSING = IRecipeType.register(
                ExampleMod.MOD_ID + ":pressing");
        public static final IRecipeType<AlloyRecipe> ALLOY = IRecipeType.register(
                ExampleMod.MOD_ID + ":alloy");

        private Types() {}
    }
    public static final RegistryObject<IRecipeSerializer<?>> PRESSING = Registration.RECIPE_SERIALIZERS.register(
            "pressing", PressingRecipe.Serializer::new);
    public static final RegistryObject<IRecipeSerializer<?>> ALLOY = Registration.RECIPE_SERIALIZERS.register(
            "alloy", AlloyRecipe.Serializer::new);

    private ModRecipes() {}

    static void register() {}

}