package com.example.examplemod.block.generator.SolarGenerator;

import com.example.examplemod.block.EnergyStorageMy;
import com.example.examplemod.block.generator.AbstractGeneratorTileEntity;
import com.example.examplemod.block.generator.CarbonGenerator.CarbonGeneratorContainer;
import com.example.examplemod.setup.ModTileEntityTypes;
import com.example.examplemod.util.TextUtil;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.LightType;

import javax.annotation.Nullable;

public class SolarGeneratorTileEntity extends AbstractGeneratorTileEntity {
    public static final int MAX_ENERGY = 100_000;
    public static final int MAX_SEND = 500;
    public static final int ENERGY_CREATED_PER_TICK = 60;

    public float sunIntensity;
    public SolarGeneratorTileEntity(){ super(ModTileEntityTypes.SOLAR_GENERATOR, 1, MAX_ENERGY, 0, MAX_SEND);}

    @Override
    protected boolean hasFuel() {
        return false;
    }

    @Override
    protected void consumeFuel() {
    }

    @Override
    protected int getEnergyCreatedPerTick(){
        float eff = calculateSunIntensity();
        {
            float raining = level.getRainLevel(1F);
            raining = raining > 0.2F ? (raining - 0.2F)/ 0.8F : 0F;
            raining = (float) Math.sin(raining * Math.PI / 2F);
            raining = 1F - raining * (1F - 0.6F);

            float thundering = level.getThunderLevel(1F);
            thundering = thundering > 0.75F ? (thundering - 0.75F) / 0.25F : 0F;
            thundering = (float) Math.sin(thundering * Math.PI / 2F);
            thundering = 1F - thundering * (1F - 0.4F);

            eff *= raining * thundering;
        }
        if(level.isClientSide) sunIntensity = eff;
        int ret = (int)(ENERGY_CREATED_PER_TICK * eff);
        return ret;
    }

    @Override
    protected BlockState getActiveState() {
        return getBlockState().setValue(AbstractFurnaceBlock.LIT, true);
    }

    @Override
    protected BlockState getInactiveState() {
        return getBlockState().setValue(AbstractFurnaceBlock.LIT, false);
    }
    @Override
    protected boolean doesSeeSky() {
        boolean seeSky;
        if (this.level.dimensionType().hasSkyLight()){
            seeSky = this.level != null && this.level.getBrightness(LightType.SKY, this.getBlockPos().above()) > 0 && this.getBlockPos() != null ? this.level.canSeeSky(this.getBlockPos().above()) : false;
        }
        else{
            seeSky = false;
        }
        return seeSky;
    }
    @Override
    protected float calculateSunIntensity(){

        float angleRadians = level.getSunAngle(1F);
        if(angleRadians > Math.PI){
            angleRadians = (float) (2 * Math.PI - angleRadians  );
        }
        int lowLightCount = 0;
        float multiplicator = 1.5F - (lowLightCount * .122F );
        float displacement = 1.2F + (lowLightCount * .08F);
        return MathHelper.clamp(multiplicator * MathHelper.cos(angleRadians/displacement), 0 ,1);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return TextUtil.name("Solar Generator");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new SolarGeneratorContainer(id, playerInventory, this, this.fields);
    }

    @Override
    public int[] getSlotsForFace(Direction p_180463_1_) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, @Nullable Direction p_180462_3_) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
        return false;
    }
}
