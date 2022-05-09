package com.example.examplemod.block.alloyfurnace;

import com.example.examplemod.block.AbstractMachineBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AlloyFurnaceBlock extends AbstractMachineBlock {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public AlloyFurnaceBlock() {
        super(Properties.of(Material.METAL).strength(6,20).sound(SoundType.METAL));
    }
    @Override
    protected void openContainer(World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof AlloyFurnaceTileEntity) {
            player.openMenu((INamedContainerProvider) tileEntity);
        }
    }
    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        return new AlloyFurnaceTileEntity();
    }
}
