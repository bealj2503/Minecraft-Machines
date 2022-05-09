package com.example.examplemod.api;

import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public interface IWrenchable {
    ActionResultType onWrench(ItemUseContext context);
}
