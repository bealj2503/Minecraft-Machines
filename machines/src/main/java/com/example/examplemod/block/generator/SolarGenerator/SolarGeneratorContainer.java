package com.example.examplemod.block.generator.SolarGenerator;

import com.example.examplemod.block.AbstractMachineBaseContainer;
import com.example.examplemod.block.AbstractMachineTileEntity;
import com.example.examplemod.setup.ModContainerTypes;
import com.example.examplemod.util.InventoryUtils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class SolarGeneratorContainer extends AbstractMachineBaseContainer<SolarGeneratorTileEntity> {
    final SolarGeneratorTileEntity tileEntity;

    public SolarGeneratorContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new SolarGeneratorTileEntity(), new IntArray(AbstractMachineTileEntity.FIELDS_COUNT));
    }

    public SolarGeneratorContainer(int id, PlayerInventory playerInventory, SolarGeneratorTileEntity tileEntity, IIntArray fieldsIn) {
        super(ModContainerTypes.SOLAR_GENERATOR, id, tileEntity, fieldsIn);
        this.tileEntity = tileEntity;
        this.addSlot(new Slot(this.tileEntity, 0, 56, 35));


        InventoryUtils.createPlayerSlots(playerInventory, 8, 84).forEach(this::addSlot);


    }
}
