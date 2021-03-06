package com.example.examplemod.block;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class AbstractMachineBlock extends AbstractFurnaceBlock {
    public AbstractMachineBlock(Properties properties){
        super(properties);
    }

    @Override
    protected void openContainer(World worldIn, BlockPos pos, PlayerEntity player){
        TileEntity tileEntity = worldIn.getBlockEntity(pos);
        if(tileEntity instanceof INamedContainerProvider){
            player.openMenu((INamedContainerProvider) tileEntity);
        }
    }
    @Override
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving){
        if(state.getBlock() != newState.getBlock()){
            TileEntity tileEntity = worldIn.getBlockEntity(pos);
            if(tileEntity instanceof IInventory){
                InventoryHelper.dropContents(worldIn,pos,(IInventory) tileEntity);
                worldIn.updateNeighbourForOutputSignal(pos,this);
            }
            super.onRemove(state,worldIn,pos,newState,isMoving);
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
        TileEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof AbstractMachineBaseTileEntity) {
            return calcRedstoneFromInventory((AbstractMachineBaseTileEntity) tileEntity);
        }
        return super.getAnalogOutputSignal(blockState, worldIn, pos);
    }

    private static int calcRedstoneFromInventory(AbstractMachineBaseTileEntity inv) {
        // Copied from Container.calcRedstoneFromInventory
        int slotsFilled = 0;
        float fillRatio = 0.0F;

        for (int i = 0; i < inv.getContainerSize(); ++i) {
            ItemStack itemstack = inv.getItem(i);
            if (!itemstack.isEmpty()) {
                fillRatio += (float) itemstack.getCount() / Math.min(inv.getMaxStackSize(), itemstack.getMaxStackSize());
                ++slotsFilled;
            }
        }

        fillRatio = fillRatio / (float) inv.getContainerSize();
        return MathHelper.floor(fillRatio * 14.0F) + (slotsFilled > 0 ? 1 : 0);
    }
}
