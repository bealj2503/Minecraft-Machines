package com.example.examplemod.block;

import com.example.examplemod.api.RedstoneMode;
import com.example.examplemod.util.EnumUtils;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IItemProvider;


public abstract class AbstractMachineBaseTileEntity extends AbstractEnergyTileEntity {
    public static final int FIELDS_COUNT = 5;


    protected RedstoneMode redstoneMode = RedstoneMode.IGNORED;

    protected final IIntArray fields = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                //Minecraft actually sends fields as shorts, so we need to split energy into 2 fields
                case 0:
                    // Energy lower bytes
                    return AbstractMachineBaseTileEntity.this.getEnergyStored() & 0xFFFF;
                case 1:
                    // Energy upper bytes
                    return (AbstractMachineBaseTileEntity.this.getEnergyStored() >> 16) & 0xFFFF;
                case 2:
                    // Max energy lower bytes
                    return AbstractMachineBaseTileEntity.this.getMaxEnergyStored() & 0xFFFF;
                case 3:
                    // Max energy upper bytes
                    return (AbstractMachineBaseTileEntity.this.getMaxEnergyStored() >> 16) & 0xFFFF;
                case 4:
                    return AbstractMachineBaseTileEntity.this.redstoneMode.ordinal();
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 4:
                    AbstractMachineBaseTileEntity.this.redstoneMode = EnumUtils.byOrdinal(value, RedstoneMode.IGNORED);
                    break;
            }
        }

        @Override
        public int getCount() {
            return FIELDS_COUNT;
        }
    };

    protected AbstractMachineBaseTileEntity(TileEntityType<?> typeIn, int inventorySize, int maxEnergy, int maxReceive, int maxExtract) {
        super(typeIn, inventorySize , maxEnergy, maxReceive, maxExtract);

    }

    public RedstoneMode getRedstoneMode() {
        return redstoneMode;
    }

    public void setRedstoneMode(RedstoneMode redstoneMode) {
        this.redstoneMode = redstoneMode;
    }

    @Override
    public IIntArray getFields() {
        return fields;
    }

    @Override
    public void load(BlockState state, CompoundNBT tags) {
        super.load(state, tags);
        this.redstoneMode = EnumUtils.byOrdinal(tags.getByte("RedstoneMode"), RedstoneMode.IGNORED);
    }

    @Override
    public CompoundNBT save(CompoundNBT tags) {
        super.save(tags);
        tags.putByte("RedstoneMode", (byte) this.redstoneMode.ordinal());
        return tags;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        super.onDataPacket(net, packet);
        CompoundNBT tags = packet.getTag();
        this.redstoneMode = EnumUtils.byOrdinal(tags.getByte("RedstoneMode"), RedstoneMode.IGNORED);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tags = super.getUpdateTag();
        tags.putByte("RedstoneMode", (byte) this.redstoneMode.ordinal());
        return tags;
    }
}
