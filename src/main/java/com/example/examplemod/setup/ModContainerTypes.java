package com.example.examplemod.setup;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.alloyfurnace.AlloyFurnaceContainer;
import com.example.examplemod.block.alloyfurnace.AlloyFurnaceScreen;
import com.example.examplemod.block.alloyfurnace.AlloyFurnaceTileEntity;
import com.example.examplemod.block.generator.CarbonGenerator.CarbonGeneratorContainer;
import com.example.examplemod.block.generator.CarbonGenerator.CarbonGeneratorScreen;
import com.example.examplemod.block.generator.SolarGenerator.SolarGeneratorContainer;
import com.example.examplemod.block.generator.SolarGenerator.SolarGeneratorScreen;
import com.example.examplemod.block.metalpress.MetalPressContainer;
import com.example.examplemod.block.metalpress.MetalPressScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;


public class ModContainerTypes {
    public static ContainerType<MetalPressContainer> METAL_PRESS;
    public static ContainerType<CarbonGeneratorContainer> CARBON_GENERATOR;
    public static ContainerType<SolarGeneratorContainer> SOLAR_GENERATOR;
    public static ContainerType<AlloyFurnaceContainer> ALLOY_FURNACE;
    public static void registerAll(RegistryEvent.Register<ContainerType<?>> event) {
        METAL_PRESS = register("metal_press", MetalPressContainer::new);
        CARBON_GENERATOR = register("carbon_generator", CarbonGeneratorContainer::new);
        SOLAR_GENERATOR = register("solar_generator", SolarGeneratorContainer::new);
        ALLOY_FURNACE = register("alloy_furnace", AlloyFurnaceContainer::new);
    }
    private ModContainerTypes(){}
    static void register(){}
    @OnlyIn(Dist.CLIENT)
    public static void registerScreens(FMLClientSetupEvent event) {
        ScreenManager.register(METAL_PRESS, MetalPressScreen::new);
        ScreenManager.register(CARBON_GENERATOR, CarbonGeneratorScreen::new);
        ScreenManager.register(SOLAR_GENERATOR, SolarGeneratorScreen::new);
        ScreenManager.register(ALLOY_FURNACE, AlloyFurnaceScreen::new);
    }
    private static <C extends Container> ContainerType<C> register(String name, ContainerType.IFactory<C> containerFactory) {
        ContainerType<C> type = new ContainerType<>(containerFactory);
        return register(name, type);
    }

    private static <C extends Container> ContainerType<C> register(String name, ContainerType<C> containerType) {
        containerType.setRegistryName(ExampleMod.getId(name));
        ForgeRegistries.CONTAINERS.register(containerType);
        return containerType;
    }
}
