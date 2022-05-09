package com.example.examplemod.block.alloyfurnace;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.AbstractMachineScreen;
import com.example.examplemod.block.metalpress.MetalPressContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AlloyFurnaceScreen extends AbstractMachineScreen<AlloyFurnaceContainer> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ExampleMod.MOD_ID, "textures/gui/alloy_furnace.png");

    public AlloyFurnaceScreen(AlloyFurnaceContainer containerIn, PlayerInventory playerInventoryIn, ITextComponent titleIn) {
        super(containerIn, playerInventoryIn, titleIn);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return TEXTURE;
    }

    @Override
    protected int getProgressArrowPosX(int guiPosX) {
        return guiPosX + 79;
    }

    @Override
    protected int getProgressArrowPosY(int guiPosY) {
        return guiPosY + 35;
    }
}
