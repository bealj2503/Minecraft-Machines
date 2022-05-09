package com.example.examplemod.setup;

import com.example.examplemod.item.WrenchItem;
import com.example.examplemod.util.ItemRegistryObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import org.lwjgl.system.CallbackI;

import java.util.function.Supplier;

public class ModItems {
    public static final RegistryObject<Item> SILVER_INGOT = Registration.ITEMS.register("silver_ingot", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> SILVER_NUGGET = Registration.ITEMS.register("silver_nugget", ()->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> SILVER_PLATE = Registration.ITEMS.register("silver_plate",() ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> LEAD_INGOT = Registration.ITEMS.register("lead_ingot", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> LEAD_NUGGET = Registration.ITEMS.register("lead_nugget", ()->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> LEAD_PLATE = Registration.ITEMS.register("lead_plate",() ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> COPPER_INGOT = Registration.ITEMS.register("copper_ingot", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> COPPER_NUGGET = Registration.ITEMS.register("copper_nugget", ()->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> COPPER_PLATE = Registration.ITEMS.register("copper_plate",() ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> TIN_INGOT = Registration.ITEMS.register("tin_ingot", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> TIN_NUGGET = Registration.ITEMS.register("tin_nugget", ()->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> TIN_PLATE = Registration.ITEMS.register("tin_plate",() ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> BRONZE_INGOT = Registration.ITEMS.register("bronze_ingot", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BRONZE_NUGGET = Registration.ITEMS.register("bronze_nugget", ()->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BRONZE_PLATE = Registration.ITEMS.register("bronze_plate",() ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> IRON_PLATE = Registration.ITEMS.register("iron_plate",() ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> GOLD_PLATE = Registration.ITEMS.register("gold_plate",() ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final ItemRegistryObject<WrenchItem> WRENCH = register("wrench", WrenchItem::new);
    static void register() {}
    private static <T extends Item> ItemRegistryObject<T> register(String name, Supplier<T> item) {
        return new ItemRegistryObject<>(Registration.ITEMS.register(name, item));
    }
}
