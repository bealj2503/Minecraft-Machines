package com.example.examplemod.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public interface IBlockProvider extends IItemProvider {
    /**
     * Get the block this object represents.
     *
     * @return The block, which may be newly constructed
     */
    Block asBlock();

    /**
     * Shortcut for getting the default state of the block.
     *
     * @return Default block state
     */
    default BlockState asBlockState() {
        return asBlock().defaultBlockState();
    }

    @Override
    default Item asItem() {
        return asBlock().asItem();
    }
}

