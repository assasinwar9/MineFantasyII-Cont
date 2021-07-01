package minefantasy.mf2.network.packet;

import io.netty.buffer.ByteBuf;
import minefantasy.mf2.block.tileentity.TileEntitySoakingTrough;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class SoakingTroughPacket extends PacketMF {
    public static final String packetName = "MF2_SoakingTroughPacket";
    private int[] coords = new int[3];
    private int fill;
    private String liquid;
    private boolean soakingMode;

    public SoakingTroughPacket(TileEntitySoakingTrough tile) {
        coords = new int[]{tile.xCoord, tile.yCoord, tile.zCoord};
        fill = tile.fill;
        liquid = tile.liquid;
        soakingMode = tile.soakingMode;
    }

    public SoakingTroughPacket() {
    }

    @Override
    public void process(ByteBuf packet, EntityPlayer player) {
        coords = new int[]{packet.readInt(), packet.readInt(), packet.readInt()};
        TileEntity entity = player.worldObj.getTileEntity(coords[0], coords[1], coords[2]);
        int newFill = packet.readInt();

        if (entity != null && entity instanceof TileEntitySoakingTrough) {
            TileEntitySoakingTrough tile = (TileEntitySoakingTrough) entity;
            tile.fill = newFill;
        }
    }

    @Override
    public String getChannel() {
        return packetName;
    }

    @Override
    public void write(ByteBuf packet) {
        for (int a = 0; a < coords.length; a++) {
            packet.writeInt(coords[a]);
        }
        packet.writeInt(fill);
    }
}
