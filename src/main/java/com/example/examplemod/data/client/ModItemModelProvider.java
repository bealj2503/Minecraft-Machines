package com.example.examplemod.data.client;

import com.example.examplemod.ExampleMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, ExampleMod.MOD_ID,existingFileHelper);
    }

    @Override
    protected void registerModels(){
        withExistingParent("silver_block", modLoc("block/silver_block"));
        withExistingParent("silver_ore", modLoc("block/silver_ore"));
        withExistingParent("lead_block", modLoc("block/lead_block"));
        withExistingParent("lead_ore", modLoc("block/lead_ore"));
        withExistingParent("copper_block", modLoc("block/copper_block"));
        withExistingParent("copper_ore", modLoc("block/copper_ore"));
        withExistingParent("tin_block", modLoc("block/tin_block"));
        withExistingParent("tin_ore", modLoc("block/tin_ore"));
        withExistingParent("bronze_block", modLoc("block/bronze_block"));

        //withExistingParent("metal_press",modLoc("block/metal_press"));
        //withExistingParent("carbon_generator", modLoc("block/carbon_generator"));
        //withExistingParent("solar_generator", modLoc("block/solar_generator"));
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "silver_ingot");
        builder(itemGenerated, "silver_nugget");
        builder(itemGenerated, "silver_plate");
        builder(itemGenerated, "lead_ingot");
        builder(itemGenerated, "lead_nugget");
        builder(itemGenerated, "lead_plate");
        builder(itemGenerated, "copper_ingot");
        builder(itemGenerated, "copper_nugget");
        builder(itemGenerated, "copper_plate");
        builder(itemGenerated, "tin_ingot");
        builder(itemGenerated, "tin_nugget");
        builder(itemGenerated, "tin_plate");
        builder(itemGenerated, "bronze_ingot");
        builder(itemGenerated, "bronze_nugget");
        builder(itemGenerated, "bronze_plate");
        builder(itemGenerated, "gold_plate");
        builder(itemGenerated, "iron_plate");
        builder(itemGenerated, "wrench");
}

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0","item/"+name);
    }
}
