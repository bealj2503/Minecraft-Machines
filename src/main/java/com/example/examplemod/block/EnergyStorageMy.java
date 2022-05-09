package com.example.examplemod.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.EnumMap;

public class EnergyStorageMy extends EnergyStorageBase {
    private final EnumMap<Direction, LazyOptional<Connection>> connections = new EnumMap<>(Direction.class);
    private final TileEntity tileEntity;

    public EnergyStorageMy(int capacity, int maxReceive, int maxExtract, TileEntity tileEntity){
        super(capacity,maxReceive, maxExtract);
        this.tileEntity = tileEntity;
        Arrays.stream(Direction.values()).forEach(d-> connections.put(d, LazyOptional.of(Connection::new)));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
        if(side == null) return super.getCapability(cap,null);
        return CapabilityEnergy.ENERGY.orEmpty(cap, connections.get(side).cast());
    }
    @Override
    public void invalidate(){
        super.invalidate();
        connections.values().forEach(LazyOptional::invalidate);
    }

    public void createEnergy(int amount){
        this.energy = Math.min(this.energy + amount, getMaxEnergyStored());
    }

    public void consumeEnergy(int amount){
        this.energy = Math.max(this.energy - amount, 0);
    }

    public void setEnergyDirectly(int amount){
        this.energy = amount;
    }
    public class Connection implements IEnergyStorage{
        private long lastReceiveTick;

        @Override
        public int receiveEnergy(int maxReceive,boolean simulate){
            World world = EnergyStorageMy.this.tileEntity.getLevel();
            if(world == null){
                return 0;
            }
            int received = EnergyStorageMy.this.receiveEnergy(maxReceive, simulate);
            if(received > 0 && !simulate){
                this.lastReceiveTick = world.getGameTime();
            }
            return received;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate){
            World world = EnergyStorageMy.this.tileEntity.getLevel();
            if(world == null){
                return 0;
            }
            long time = world.getGameTime();
            if(time != this.lastReceiveTick){
                return EnergyStorageMy.this.extractEnergy(maxExtract, simulate);
            }
            return 0;
        }

        @Override
        public int getEnergyStored(){
            return EnergyStorageMy.this.getEnergyStored();
        }

        @Override
        public int getMaxEnergyStored(){
            return  EnergyStorageMy.this.getMaxEnergyStored();
        }

        @Override
        public boolean canExtract(){
            return EnergyStorageMy.this.canExtract();
        }

        @Override
        public boolean canReceive(){
            return EnergyStorageMy.this.canReceive();
        }
    }
}
