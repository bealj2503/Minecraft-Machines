package com.example.examplemod.block;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public interface IEnergyHandler {
    EnergyStorageMy getEnergyMy();

    default LazyOptional<IEnergyStorage> getEnergy(@Nullable Direction side){
        return getEnergyMy().getCapability(CapabilityEnergy.ENERGY, side);
    }

    default int getEnergyStored(){
        IEnergyStorage energy = getEnergy(null).orElse(new EnergyStorage(100_000));
        return energy.getEnergyStored();
    }

    default int getMaxEnergyStored(){
        IEnergyStorage energy = getEnergy(null).orElse(new EnergyStorage(100_000));
        return energy.getMaxEnergyStored();
    }

    default void setEnergyStoredDirectly(int value){
        getEnergy(null).ifPresent(e -> {
            if(e instanceof EnergyStorageMy){
                ((EnergyStorageMy) e).setEnergyDirectly(value);
            }
        });
    }

    default void readEnergy(CompoundNBT tags){
        setEnergyStoredDirectly(tags.getInt("Energy"));
    }
    default void writeEnergy(CompoundNBT tags){
        tags.putInt("Energy",getEnergyStored());
    }
}
