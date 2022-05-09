package com.example.examplemod.block.generator.SolarGenerator;

import com.example.examplemod.block.AbstractMachineBlock;
import com.example.examplemod.block.generator.CarbonGenerator.CarbonGeneratorTileEntity;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SolarGeneratorBlock extends AbstractMachineBlock {
    public SolarGeneratorBlock() {
        super(Properties.of(Material.METAL).strength(6, 20).sound(SoundType.METAL));
    }

    @Override
    protected void openContainer(World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof SolarGeneratorTileEntity) {
            player.openMenu((INamedContainerProvider) tileEntity);
        }
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        return new SolarGeneratorTileEntity();
    }
}
