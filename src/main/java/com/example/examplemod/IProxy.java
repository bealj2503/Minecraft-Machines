package com.example.examplemod;

import net.minecraft.server.MinecraftServer;

public interface IProxy {
    /**
     * Used to attempt to work around instances where mods break the tag registry
     */
    void tryFetchTagsHack();

    MinecraftServer getServer();
}
