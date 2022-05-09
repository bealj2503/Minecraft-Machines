package com.example.examplemod;

import net.minecraft.server.MinecraftServer;

public class sideProxy implements IProxy{
    private MinecraftServer server = null;
    sideProxy(){

    }
    @Override
    public void tryFetchTagsHack() {}
    @Override
    public MinecraftServer getServer() {
        return server;
    }

}
