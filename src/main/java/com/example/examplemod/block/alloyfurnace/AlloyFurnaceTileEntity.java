package com.example.examplemod.block.alloyfurnace;

import com.example.examplemod.block.AbstractMachineTileEntity;
import com.example.examplemod.crafting.recipe.AlloyRecipe;

import com.example.examplemod.setup.ModRecipes;
import com.example.examplemod.setup.ModTileEntityTypes;
import com.example.examplemod.util.TextUtil;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class AlloyFurnaceTileEntity extends AbstractMachineTileEntity<AlloyRecipe> {
    public static final int MAX_ENERGY = 50_000;
    public static final int MAX_RECEIVE = 500;
    public static final int ENERGY_USED_PER_TICK = 20;

    // Inventory constants
    static final int INPUT_SLOT_COUNT = 3;
    private static final int[] SLOTS_INPUT = IntStream.range(0,INPUT_SLOT_COUNT).toArray();
    private static final int[] SLOTS_OUTPUT = {INPUT_SLOT_COUNT};
    private static final int[] SLOTS_ALL = IntStream.range(0, INPUT_SLOT_COUNT + 1).toArray();

    public AlloyFurnaceTileEntity() {
        super(ModTileEntityTypes.ALLOY_FURNACE, SLOTS_ALL.length);
    }

    @Override
    protected int getEnergyUsedPerTick() {
        return ENERGY_USED_PER_TICK;
    }

    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
    @Override
    protected int[] getOutputSlots() {
        return SLOTS_OUTPUT;
    }

    @Nullable
    @Override
    protected AlloyRecipe getRecipe() {
        if (level == null) return null;
        AlloyRecipe recipe =  level.getRecipeManager().getRecipeFor(ModRecipes.Types.ALLOY, this, level).orElse(null);
        return recipe;
    }

    @Override
    protected int getProcessTime(AlloyRecipe recipe) {
        return recipe.getProcessTime();
    }

    @Override
    protected Collection<ItemStack> getProcessResults(AlloyRecipe recipe) {
        return Collections.singleton(recipe.assemble(this));
    }

    @Override
    protected void consumeIngredients(AlloyRecipe recipe) {
        recipe.consumeIngredients(this);
    }

    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
    @Override
    public int[] getSlotsForFace(Direction side) {
        return SLOTS_ALL;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return index < INPUT_SLOT_COUNT;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == INPUT_SLOT_COUNT;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return TextUtil.name("Alloy Furnace");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new AlloyFurnaceContainer(id, playerInventory, this, this.fields);
    }

    List<String> getDebugText() {
        return ImmutableList.of(
                "progress = " + this.fields.get(0),
                "processTime = " + this.fields.get(1),
                "energy = " + this.fields.get(2) + " FE / " + getMaxEnergyStored() + " FE",
                "ENERGY_USED_PER_TICK = " + ENERGY_USED_PER_TICK,
                "MAX_RECEIVE = " + MAX_RECEIVE
        );
    }
}

