package com.example.examplemod.block.generator.CarbonGenerator;

import com.example.examplemod.block.AbstractMachineBaseContainer;
import com.example.examplemod.block.AbstractMachineTileEntity;
import com.example.examplemod.setup.ModContainerTypes;
import com.example.examplemod.util.InventoryUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class CarbonGeneratorContainer extends AbstractMachineBaseContainer<CarbonGeneratorTileEntity> {
    final CarbonGeneratorTileEntity tileEntity;

    public CarbonGeneratorContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new CarbonGeneratorTileEntity(), new IntArray(AbstractMachineTileEntity.FIELDS_COUNT));
    }

    public CarbonGeneratorContainer(int id, PlayerInventory playerInventory, CarbonGeneratorTileEntity tileEntity, IIntArray fieldsIn) {
        super(ModContainerTypes.CARBON_GENERATOR, id, tileEntity, fieldsIn);
        this.tileEntity = tileEntity;
        this.addSlot(new Slot(this.tileEntity, 0, 56, 35));


        InventoryUtils.createPlayerSlots(playerInventory, 8, 84).forEach(this::addSlot);


    }

    public int getBurnTime() {
        return fields.get(5);
    }

    public int getTotalBurnTime() {
        return fields.get(6);
    }

    public boolean isBurning() {
        return getBurnTime() > 0;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            final int inventorySize = 1;
            final int playerInventoryEnd = inventorySize + 27;
            final int playerHotbarEnd = playerInventoryEnd + 9;

            if (index != 0) {
                if (this.isFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
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

    private boolean isFuel(ItemStack stack) {
        return CarbonGeneratorTileEntity.isFuel(stack);
    }
}
