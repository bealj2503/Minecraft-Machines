package com.example.examplemod.block.alloyfurnace;

import com.example.examplemod.block.AbstractMachineContainer;
import com.example.examplemod.block.AbstractMachineTileEntity;
import com.example.examplemod.block.metalpress.MetalPressTileEntity;
import com.example.examplemod.setup.ModContainerTypes;
import com.example.examplemod.util.InventoryUtils;
import com.example.examplemod.util.SlotOutputOnly;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class AlloyFurnaceContainer extends AbstractMachineContainer<AlloyFurnaceTileEntity> {
    public AlloyFurnaceContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new AlloyFurnaceTileEntity(), new IntArray(AbstractMachineTileEntity.FIELDS_COUNT));
    }

    public AlloyFurnaceContainer(int id, PlayerInventory playerInventory, AlloyFurnaceTileEntity tileEntity, IIntArray fieldsIn) {
        super(ModContainerTypes.ALLOY_FURNACE, id, tileEntity, fieldsIn);

        this.addSlot(new Slot(this.tileEntity, 0, 16, 35));
        this.addSlot(new Slot(this.tileEntity, 1, 36, 35));
        this.addSlot(new Slot(this.tileEntity, 2, 56, 35));
        this.addSlot(new SlotOutputOnly(this.tileEntity, 3, 117, 35));

        InventoryUtils.createPlayerSlots(playerInventory, 8, 84).forEach(this::addSlot);

    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            System.out.println("is this the problem?");
            final int inventorySize = 4;
            final int playerInventoryEnd = inventorySize + 27;
            final int playerHotbarEnd = playerInventoryEnd + 9;

            if (index == 3) {
                if (!this.moveItemStackTo(itemstack1, inventorySize, playerHotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index >=inventorySize) {
                if (this.isAlloyIngredient(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, inventorySize-1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerInventoryEnd) {
                    if (!this.moveItemStackTo(itemstack1, playerInventoryEnd, playerHotbarEnd, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerHotbarEnd && !this.moveItemStackTo(itemstack1, inventorySize, playerInventoryEnd, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, inventorySize, playerHotbarEnd, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    private boolean isAlloyIngredient(ItemStack stack) {
        // TODO
        return true;
    }
}
