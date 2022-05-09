package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.setup.ModItems;
import com.example.examplemod.setup.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, ExampleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        builder(forgeId("nuggets/coal"));
        builder(forgeId("storage_blocks/charcoal"));
        copy(ModTags.Blocks.ORES_SILVER, ModTags.Items.ORES_SILVER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_SILVER, ModTags.Items.STORAGE_BLOCKS_SILVER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        copy(ModTags.Blocks.ORES_LEAD, ModTags.Items.ORES_LEAD);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_LEAD, ModTags.Items.STORAGE_BLOCKS_LEAD);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        copy(ModTags.Blocks.ORES_COPPER, ModTags.Items.ORES_COPPER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_COPPER, ModTags.Items.STORAGE_BLOCKS_COPPER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        copy(ModTags.Blocks.ORES_TIN, ModTags.Items.ORES_TIN);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_TIN, ModTags.Items.STORAGE_BLOCKS_TIN);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        copy(ModTags.Blocks.STORAGE_BLOCKS_BRONZE, ModTags.Items.STORAGE_BLOCKS_BRONZE);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        tag(ModTags.Items.INGOTS_SILVER).add(ModItems.SILVER_INGOT.get());
        tag(ModTags.Items.NUGGETS_SILVER).add(ModItems.SILVER_NUGGET.get());
        tag(ModTags.Items.PLATES_SILVER).add(ModItems.SILVER_PLATE.get());

        tag(ModTags.Items.INGOTS_LEAD).add(ModItems.LEAD_INGOT.get());
        tag(ModTags.Items.NUGGETS_LEAD).add(ModItems.LEAD_NUGGET.get());
        tag(ModTags.Items.PLATES_LEAD).add(ModItems.LEAD_PLATE.get());

        tag(ModTags.Items.INGOTS_COPPER).add(ModItems.COPPER_INGOT.get());
        tag(ModTags.Items.NUGGETS_COPPER).add(ModItems.COPPER_NUGGET.get());
        tag(ModTags.Items.PLATES_COPPER).add(ModItems.COPPER_PLATE.get());

        tag(ModTags.Items.INGOTS_TIN).add(ModItems.TIN_INGOT.get());
        tag(ModTags.Items.NUGGETS_TIN).add(ModItems.TIN_NUGGET.get());
        tag(ModTags.Items.PLATES_TIN).add(ModItems.TIN_PLATE.get());

        tag(ModTags.Items.INGOTS_BRONZE).add(ModItems.BRONZE_INGOT.get());
        tag(ModTags.Items.NUGGETS_BRONZE).add(ModItems.BRONZE_NUGGET.get());
        tag(ModTags.Items.PLATES_BRONZE).add(ModItems.BRONZE_PLATE.get());

        tag(ModTags.Items.PLATES_IRON).add(ModItems.IRON_PLATE.get());
        tag(ModTags.Items.PLATES_GOLD).add(ModItems.GOLD_PLATE.get());
        tag(ModTags.Items.WRENCH).add(ModItems.WRENCH.get());
        tag(Tags.Items.INGOTS)
                .addTag(ModTags.Items.INGOTS_BRONZE)
                .addTag(ModTags.Items.INGOTS_TIN)
                .addTag(ModTags.Items.INGOTS_COPPER)
                .addTag(ModTags.Items.INGOTS_LEAD)
                .addTag(ModTags.Items.INGOTS_SILVER);
        tag(Tags.Items.NUGGETS)
                .addTag(ModTags.Items.NUGGETS_BRONZE)
                .addTag(ModTags.Items.NUGGETS_TIN)
                .addTag(ModTags.Items.NUGGETS_COPPER)
                .addTag(ModTags.Items.NUGGETS_LEAD)
                .addTag(ModTags.Items.NUGGETS_SILVER);
        tag(ModTags.Items.PLATES)
                .addTag(ModTags.Items.PLATES_GOLD)
                .addTag(ModTags.Items.PLATES_IRON)
                .addTag(ModTags.Items.PLATES_BRONZE)
                .addTag(ModTags.Items.PLATES_TIN)
                .addTag(ModTags.Items.PLATES_COPPER)
                .addTag(ModTags.Items.PLATES_LEAD)
                .addTag(ModTags.Items.PLATES_SILVER);
        tag(ModTags.Items.COAL_GENERATOR_FUELS)
                .addTag(ItemTags.COALS)
                .addTag(itemTag(forgeId("nuggets/coal")))
                .addTag(itemTag(forgeId("storage_blocks/charcoal")))
                .addTag(Tags.Items.STORAGE_BLOCKS_COAL)
                .addTag(ItemTags.LOGS_THAT_BURN)
                .addTag(ItemTags.PLANKS);

    }


    private void builder(ResourceLocation id, IItemProvider... items) {
        tag(itemTag(id)).add(Arrays.stream(items).map(IItemProvider::asItem).toArray(Item[]::new));
    }
    private static ResourceLocation forgeId(String path) {
        return new ResourceLocation("forge", path);
    }
    private static ITag.INamedTag<Item> itemTag(ResourceLocation id) {
        return ItemTags.bind(id.toString());
    }
}
