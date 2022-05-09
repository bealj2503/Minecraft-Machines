package com.example.examplemod;

import com.example.examplemod.Network.Network;
import com.example.examplemod.setup.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.TagRegistryManager;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("examplemod")
public class ExampleMod
{
    public static final String MOD_ID = "examplemod";
    public static final String MOD_NAME = "Pogger mod";
    public static IProxy PROXY;
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    public static final ItemGroup ITEM_GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.METAL_PRESS);
        }
    };
    public ExampleMod() {

        Network.init();

        Registration.register();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addGenericListener(ContainerType.class, ModContainerTypes::registerAll);
        modEventBus.addGenericListener(TileEntityType.class, ModTileEntityTypes::registerAll);
        PROXY = DistExecutor.safeRunForDist(() -> Client::new, () -> Server::new);

    }


    public static class Client extends sideProxy {
        Client(){
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        }
        private void clientSetup(FMLClientSetupEvent event) {

            RenderTypeLookup.setRenderLayer(ModBlocks.CABLE.get(),RenderType.cutout());
            System.out.println("fucking cable cunts");
            //RenderTypeLookup.setRenderLayer(ModBlocks.CABLE.get(),RenderType.cutoutMipped());

            //RenderTypeLookup.setRenderLayer(ModBlocks.CABLE.get(),RenderType.translucent());
            ModContainerTypes.registerScreens(event);

        }
        @Override
        public void tryFetchTagsHack() {
            TagRegistryManager.resetAllToEmpty();
        }
    }
    static class Server extends sideProxy {
        Server() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
        }

        private void serverSetup(FMLDedicatedServerSetupEvent event) {
        }
    }
    public static ResourceLocation getId(String path){
        return new ResourceLocation(MOD_ID, path);
    }
}
