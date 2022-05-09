package com.example.examplemod.block.generator;

import com.example.examplemod.block.AbstractMachineBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Properties;

public abstract class AbstractGeneratorBlock extends AbstractMachineBlock {
    public AbstractGeneratorBlock( Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        // TODO: Show energy production rate and fuel consumption rate?
    }
}
