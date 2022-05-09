package com.example.examplemod.block;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyStorageBase extends EnergyStorage implements ICapabilityProvider {
    private final LazyOptional<EnergyStorageBase> lazy;
    public EnergyStorageBase(int capacity, int maxReceive, int maxExtract){
        super(capacity, maxReceive, maxExtract, 0);
        this.lazy = LazyOptional.of(() -> this);
    }

    @Nonnull
    @Override
    public <T>LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
        return CapabilityEnergy.ENERGY.orEmpty(cap, lazy.cast());
    }

    public void invalidate(){this.lazy.invalidate();}
}
