package com.example.examplemod.block.generator.SolarGenerator;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.AbstractMachineBaseContainer;
import com.example.examplemod.block.AbstractMachineBaseScreen;
import com.example.examplemod.block.generator.CarbonGenerator.CarbonGeneratorContainer;
import com.example.examplemod.util.TextUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SolarGeneratorScreen extends AbstractMachineBaseScreen<SolarGeneratorContainer> {
    public static final ResourceLocation TEXTURE = ExampleMod.getId("textures/gui/coal_generator.png");

    public SolarGeneratorScreen(SolarGeneratorContainer container, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(container, playerInventory, titleIn);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return TEXTURE;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderTooltip(MatrixStack matrixStack, int x, int y) {
        if (isHovering(153, 17, 13, 51, x, y)) {
            ITextComponent text = TextUtil.energyWithMax(menu.getEnergyStored(), menu.tileEntity.getMaxEnergyStored());
            renderTooltip(matrixStack, text, x, y);
        }
        super.renderTooltip(matrixStack, x, y);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        super.renderBg(matrixStack, partialTicks, x, y);

        if (minecraft == null) return;
        int xPos = (this.width - this.imageWidth) / 2;
        int yPos = (this.height - this.imageHeight) / 2;

        // Fuel remaining

        // Energy meter
        int energyBarHeight = menu.getEnergyBarHeight();
        if (energyBarHeight > 0) {
            blit(matrixStack, xPos + 154, yPos + 68 - energyBarHeight, 176, 31, 12, energyBarHeight);
        }
    }
}
