package com.example.examplemod.Network;

import com.example.examplemod.api.RedstoneMode;
import com.example.examplemod.block.AbstractMachineBaseContainer;
import com.example.examplemod.block.AbstractMachineBaseTileEntity;
import com.example.examplemod.util.EnumUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SetRedstoneModePacket {
    private RedstoneMode mode;

    public SetRedstoneModePacket() {
    }

    public SetRedstoneModePacket(RedstoneMode mode) {
        this.mode = mode;
    }

    public static SetRedstoneModePacket fromBytes(PacketBuffer buffer) {
        SetRedstoneModePacket packet = new SetRedstoneModePacket();
        packet.mode = EnumUtils.byOrdinal(buffer.readByte(), RedstoneMode.IGNORED);
        return packet;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeByte(this.mode.ordinal());
    }

    public static void handle(SetRedstoneModePacket packet, Supplier<NetworkEvent.Context> context) {
        ServerPlayerEntity player = context.get().getSender();
        context.get().enqueueWork(() -> handlePacket(packet, player));
        context.get().setPacketHandled(true);
    }

    private static void handlePacket(SetRedstoneModePacket packet, ServerPlayerEntity player) {
        if (player != null) {
            if (player.containerMenu instanceof AbstractMachineBaseContainer) {
                TileEntity tileEntity = ((AbstractMachineBaseContainer) player.containerMenu).getTileEntity();
                if (tileEntity instanceof AbstractMachineBaseTileEntity) {
                    ((AbstractMachineBaseTileEntity) tileEntity).setRedstoneMode(packet.mode);
                }
            }
        }
    }
}
