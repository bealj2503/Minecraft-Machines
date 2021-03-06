package com.example.examplemod.block.generator.CarbonGenerator;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.generator.AbstractGeneratorTileEntity;
import com.example.examplemod.setup.ModTags;
import com.example.examplemod.setup.ModTileEntityTypes;
import com.example.examplemod.sideProxy;
import com.example.examplemod.util.TextUtil;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;

public class CarbonGeneratorTileEntity extends AbstractGeneratorTileEntity {
    public static final int MAX_ENERGY = 10_000;
    public static final int MAX_SEND = 500;
    public static final int ENERGY_CREATED_PER_TICK = 60;

    public CarbonGeneratorTileEntity() {
        super(ModTileEntityTypes.CARBON_GENERATOR, 1, MAX_ENERGY, 0, MAX_SEND);
    }

    static boolean isFuel(ItemStack stack) {
        return isFuel(stack, true);
    }

    private static boolean isFuel(ItemStack stack, boolean firstAttempt) {
        // Workaround for https://github.com/SilentChaos512/Silents-Mechanisms/issues/126
        try {
            return stack.getItem().is(ModTags.Items.COAL_GENERATOR_FUELS) && AbstractFurnaceTileEntity.isFuel(stack);
        } catch (IllegalStateException ex) {
            ExampleMod.PROXY.tryFetchTagsHack();
        }

        return firstAttempt && isFuel(stack, false);
    }

    private static int getBurnTime(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack);
    }
    @Override
    protected boolean doesSeeSky(){ return false; }
    @Override
    protected float calculateSunIntensity(){
        return 0;
    }
    @Override
    protected boolean hasFuel() {
        return isFuel(getItem(0));
    }

    @Override
    protected void consumeFuel() {
        ItemStack fuel = getItem(0);
        burnTime = getBurnTime(fuel);
        if (burnTime > 0) {
            totalBurnTime = burnTime;

            if (fuel.hasContainerItem()) {
                setItem(0, fuel.getContainerItem());
            } else if (!fuel.isEmpty()) {
                fuel.shrink(1);
                if (fuel.isEmpty()) {
                    setItem(0, fuel.getContainerItem());
                }
            }
        }
    }

    @Override
    protected int getEnergyCreatedPerTick() {
        return ENERGY_CREATED_PER_TICK;
    }

    @Override
    protected BlockState getActiveState() {
        return getBlockState().setValue(AbstractFurnaceBlock.LIT, true);
    }

    @Override
    protected BlockState getInactiveState() {
        return getBlockState().setValue(AbstractFurnaceBlock.LIT, false);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[]{0};
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return isFuel(stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return !isFuel(stack);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return TextUtil.name("Coal Generator");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new CarbonGeneratorContainer(id, playerInventory, this, this.fields);
    }
}
