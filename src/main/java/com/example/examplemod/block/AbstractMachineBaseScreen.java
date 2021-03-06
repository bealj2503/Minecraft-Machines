package com.example.examplemod.block;

import com.example.examplemod.Network.Network;
import com.example.examplemod.Network.SetRedstoneModePacket;
import com.example.examplemod.api.RedstoneMode;
import com.example.examplemod.client.RedstoneModeButton;
import com.example.examplemod.util.TextUtil;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;

public abstract class AbstractMachineBaseScreen<C extends AbstractMachineBaseContainer<?>> extends ContainerScreen<C> {
    public AbstractMachineBaseScreen(C screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    public abstract ResourceLocation getGuiTexture();

    @Override
    protected void init() {
        super.init();
        this.addButton(new RedstoneModeButton(menu, this.leftPos - 16, this.topPos, 16, 16, button -> {
            RedstoneMode mode = ((RedstoneModeButton) button).getMode();
            Network.channel.sendToServer(new SetRedstoneModePacket(mode));
        }));
    }

    @Override
    protected void renderTooltip(MatrixStack matrixStack, int x, int y) {
        if (isHovering(153, 17, 13, 51, x, y)) {
            IFormattableTextComponent text = TextUtil.energyWithMax(menu.getEnergyStored(), menu.getMaxEnergyStored());
            renderTooltip(matrixStack, text, x, y);
        }

        super.renderTooltip(matrixStack, x, y);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (minecraft == null) return;
        GlStateManager._color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(getGuiTexture());
        int xPos = (this.width - this.imageWidth) / 2;
        int yPos = (this.height - this.imageHeight) / 2;
        blit(matrixStack, xPos, yPos, 0, 0, this.imageWidth, this.imageHeight);


    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        this.font.draw(matrixStack, this.title.getString(), 8.0F, 6.0F, 4210752);
//        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);

        for (Widget widget : this.buttons) {
            if (widget.isHovered() && widget instanceof RedstoneModeButton) {
                RedstoneMode mode = ((RedstoneModeButton) widget).getMode();
                renderTooltip(matrixStack, TextUtil.name(mode.name()), x - leftPos, y - topPos);
            }
        }
    }
}
